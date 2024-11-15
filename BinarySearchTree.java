/**
 * @author Christian Burke and Michael D'Amico
 * @version 14 November 2024
 */
package songpack;

import java.util.ArrayList;


/**
 * BinarySearchTree class for storing and managing Song objects in a binary search tree structure.
 * Provides methods for insertion, searching, sorting, and filtering of songs.
 */
public class BinarySearchTree {
	
	/**
     * Node class representing a node in the binary search tree.
     */
	static class Node{
		Song data;
		Node left;
		Node right;
		int height;// new
		
		 /**
         * Constructor for Node.
         * @param data The Song data to be stored in the node.
         */
		public Node(Song data) {
			this.data = data;
			left = right = null;
			height = 1;// new
		}
	}
	protected Node root;// attribute
	
	/**
     * Constructor for BinarySearchTree.
     */
	public BinarySearchTree() { // constructor
		root = null;
	}
	
	
	
	// Getting the height of a given node
	protected int height(Node N) {
		if (N == null)
			return 0;
		return N.height;
	
	}
	
	
	
	
	/**
     * Converts the binary search tree to a sorted ArrayList using in-order traversal.
     * @return An ArrayList of Songs in sorted order.
     * Time Complexity: O(n), where n is the number of nodes in the tree.
     */
	public ArrayList<Song> toSortedArrayList(){
		ArrayList<Song> result = new ArrayList<>();

	    inOrderTraversal(root, result);
		return result;
	}
	/**
     * Helper method for in-order traversal.
     * @param root The current node.
     * @param result The ArrayList to store the in-order traversal result.
     */
	private void inOrderTraversal(Node root, ArrayList<Song> result) {
		// TODO Auto-generated method stub
		if (root == null)
			return;
	
		inOrderTraversal(root.left, result);
		result.add(root.data);
	    inOrderTraversal(root.right, result);
	    
	}

	
	/**
     * Inserts a new Song into the binary search tree.
     * @param item The Song to be inserted.
     * Time Complexity: O(log n) on average for a balanced tree, O(n) in the worst case (skewed tree).
     */
	public void insert(Song item) {
		root = insertRecursive(root, item);
		}
	/**
     * Recursive helper method for inserting a song into the tree.
     * @param root The current node.
     * @param item The Song to be inserted.
     * @return The updated node after insertion.
     */
	private Node insertRecursive(Node root, Song item) { 
		if (root == null) {
		   root = new Node(item);
		   return root;
		}

		if (item.getViews() < root.data.getViews()) {
		   root.left = insertRecursive(root.left, item);
		} else {
		   // Insert to the right if views are equal or greater
		   root.right = insertRecursive(root.right, item);
		    }

		    return root;
		}

	
	
		
	/**
     * Searches for songs with views greater than or equal to the specified number.
     * @param views The minimum number of views.
     * @return An ArrayList of Songs matching the search criteria.
     * Time Complexity: O(n), as all nodes may need to be visited.
     */	
	public ArrayList<Song> search(int views){
		ArrayList<Song> result = new ArrayList<>();
	    searchRecursive(root, views, result);
	    return result;
	}
	/**
     * Helper method for recursive search by views.
     * @param root The current node.
     * @param views The minimum views.
     * @param result The ArrayList to store matching songs.
     */
	private void searchRecursive(Node root, int views, ArrayList<Song> result) {
	    if (root == null) {
	        return;
	    }

	    // Check the left subtree
	    searchRecursive(root.left, views, result);

	    // If the current song has views >= specified views, add to result
	    if (root.data.getViews() >= views) {
	        result.add(root.data);
	    }

	    // Check the right subtree
	    searchRecursive(root.right, views, result);
	}
	
	
	
	/**
     * Validates if the binary search tree is a valid BST.
     * @return True if valid, false otherwise.
     * Time Complexity: O(n), as each node must be checked.
     */
	public boolean isBST() {
		return isValidBST(root);
	}
	 /**
     * Helper method to validate the BST.
     * @param root The current node.
     * @return True if subtree rooted at the node is a valid BST, false otherwise.
     */
	private boolean isValidBST(Node root) {
		if (root == null)
			return true;
		if (root.left  == null && root.right == null)
			return true;
		if(root.left!=null && root.data.compareTo(root.left.data)<=0)
			return false;
		if(root.right!=null && root.data.compareTo(root.right.data)> 0 )// dont think its zero
			return false;
		
		boolean bl = isValidBST(root.left);
		boolean br = isValidBST(root.right);
		return br && bl;
		
	}
	
	
	
	 /**
     * Creates a deep copy (clone) of the binary search tree.
     * @return A cloned BinarySearchTree.
     * Time Complexity: O(n), as each node must be cloned.
     */
	public BinarySearchTree clone() {
		BinarySearchTree clonedTree = new BinarySearchTree();
		clonedTree.root = cloneNode(this.root);
		return clonedTree;
	}
	/**
     * Helper method for cloning the tree nodes.
     * @param node The node to clone.
     * @return The cloned node.
     */
	private Node cloneNode(Node node) {
		if (node == null) {
			return null;
		}
		 Node newNode = new Node(node.data); // Create a new node with the same data
		 newNode.left = cloneNode(node.left); // Recursively clone left subtree
		 newNode.right = cloneNode(node.right); // Recursively clone right subtree
		 return newNode;
		 //notice we visit the left and right children before processing the current newNode
	}
	
	
	
	/**
     * Finds the most popular artists based on views.
     * @return An ArrayList of popular artist names.
     * Time Complexity: O(n), as all nodes are visited.
     */
	public ArrayList<String> popularArtists(){
		ArrayList<String> topArtists = new ArrayList<>();
	    int maxViews = -1;
	    findPopularArtists(root, topArtists, maxViews);
	    return topArtists;
	}
	/**
     * Helper method for finding popular artists with maximum views.
     * @param node The current node.
     * @param topArtists The list to store artist names.
     * @param maxViews The current max views.
     * @return The updated max views.
     */
	private int findPopularArtists(Node node, ArrayList<String> topArtists, int maxViews) {
	    if (node == null) return maxViews;

	    // Traverse the left subtree and update maxViews
	    maxViews = findPopularArtists(node.left, topArtists, maxViews);

	    // Process the current node
	    int views = node.data.getViews();
	    if (views > maxViews) {
	        topArtists.clear();  // New maximum, clear old list
	        maxViews = views;    // Update maximum
	        topArtists.add(node.data.getArtist());
	    } else if (views == maxViews) {
	        topArtists.add(node.data.getArtist()); // Add artist with matching max views
	    }

	    // Traverse the right subtree
	    return findPopularArtists(node.right, topArtists, maxViews);
	}
	
	
	
	/**
     * Filters the binary search tree to only include nodes with views within a specified range.
     * @param minView The minimum views.
     * @param maxView The maximum views.
     * @return The root node of the filtered tree.
     * Time Complexity: O(n), as each node must be evaluated.
     */
	public Node filterByView(int minView, int maxView) {
		 root = filterByViewHelper(root, minView, maxView);
		 return root;
	}
	/**
     * Helper method for filtering nodes by views.
     * @param node The current node.
     * @param minView The minimum views.
     * @param maxView The maximum views.
     * @return The node after filtering.
     */
	private Node filterByViewHelper(Node node, int minView,int maxView) {
		if (node == null) {
			return null;
		}
	// Log each node's view count and action taken
	// System.out.println("Checking node with views: " + node.data.getViews());
	
	// Remove nodes less than minView
	if (node.data.getViews() < minView) {
		//System.out.println("Removing node with views < " + minView);
		return filterByViewHelper(node.right, minView, maxView);
	}
	// remove nodes greater than maxView
	if (node.data.getViews() > maxView) {
		//System.out.println("Removing node with views > " + maxView);
		return filterByViewHelper(node.left, minView, maxView);
	}
	// Node is within range, so apply filter to children
    node.left = filterByViewHelper(node.left, minView, maxView);
    node.right = filterByViewHelper(node.right, minView, maxView);
    return node;
	}


	/*
	 * Computes the height of a given binary tree node.
	 * 
	 * The height of a node is defined as the number of edges on the longest path
	 * from the node to a leaf. The height of a null node is considered to be 0.
	 * 
	 * @param node The node whose height is to be computed. If the node is null,
	 *             the height is considered to be 0.
	 * @return The height of the given node in the binary tree.
	 */
	public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

	
	
}
