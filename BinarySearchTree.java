/**
 * @author Christian Burke and Michael D'Amico
 * @version 29 October 2024
 */
package songpack;

import java.util.ArrayList;

public class BinarySearchTree {
	static class Node{
		Song data;
		Node left;
		Node right;
		public Node(Song data) {
			this.data = data;
			left = right = null;
		}
	}
	Node root;// attribute
	
	public BinarySearchTree() { // constructor
		root = null;
	}
	
	/*
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
	*/
	
	
	
	public ArrayList<Song> toSortedArrayList(){
		ArrayList<Song> result = new ArrayList<>();

	    inOrderTraversal(root, result);
	    
		return result;
	}

	private void inOrderTraversal(Node root, ArrayList<Song> result) { //helpermethod
		// TODO Auto-generated method stub
		if (root == null)
			return;
	
		inOrderTraversal(root.left, result);
		result.add(root.data);
	    inOrderTraversal(root.right, result);
	    
	}

	
	
	
	
	public void insert(Song item) {
		root = insertRecursive(root, item);
		}
		// Helper method
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

	
	
		
		
	public ArrayList<Song> search(int views){
		ArrayList<Song> result = new ArrayList<>();
	    searchRecursive(root, views, result);
	    return result;
	}
	// helper
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
	
	
	
	
	public boolean isBST() {
		return isBSTRecursive(root, null, null);
	}
	private boolean isBSTRecursive(Node node, Song min, Song max) {
	    if (node == null) {
	        return true;
	    }

	    // If node's data is less than or equal to the min allowed or more than the max, it violates BST properties
	    if ((min != null && node.data.compareTo(min) <= 0) || (max != null && node.data.compareTo(max) > 0)) {
	        return false;
	   
	    }

	    // Recursively check the left and right subtrees with updated min and max constraints
	    return isBSTRecursive(node.left, min, node.data) && isBSTRecursive(node.right, node.data, max);
	}
	
	
	
	
	public BinarySearchTree clone() {
		BinarySearchTree clonedTree = new BinarySearchTree();
		clonedTree.root = cloneNode(this.root);
		return clonedTree;
	}
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
	
	
	
	
	public ArrayList<String> popularArtists(){
		ArrayList<String> topArtists = new ArrayList<>();
	    int maxViews = -1;
	    findPopularArtists(root, topArtists, maxViews);
	    return topArtists;
	}

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
	
	
	
	
	public Node filterByView(int minView, int maxView) {
		 root = filterByViewHelper(root, minView, maxView);
		 return root;
	}
	//Helper
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

	
	
	
	
	public ArrayList<Song> getTop10Songs() {
	    ArrayList<Song> topSongs = new ArrayList<>();
	    getTop10SongsHelper(root, topSongs);
	    return topSongs;
	}

	private void getTop10SongsHelper(Node node, ArrayList<Song> topSongs) {
	    if (node == null || topSongs.size() >= 10) return;

	    // Traverse right subtree first for highest views
	    getTop10SongsHelper(node.right, topSongs);

	    // Add the current song if we haven't reached 10 yet
	    if (topSongs.size() < 10) {
	        topSongs.add(node.data);
	    }

	    // Traverse left subtree
	    getTop10SongsHelper(node.left, topSongs);
	}
	
}
 

}
