/**
 * @author Christian Burke and Michael D'Amico
 * @version 14 November 2024
 */
package songpack;

public class AVLTree extends BinarySearchTree{
	private int leftRotation;
	private int rightRotation;
	private int leftRightRotation;
	private int rightLeftRotation;
	
	/**
     * Constructor to initialize an empty AVL Tree with rotation counters set to zero.
     */
	public AVLTree() {
		super();
	
		root = null;
		leftRotation = 0;
		rightRotation = 0;
		leftRightRotation = 0;
		rightLeftRotation = 0;
	}
	
	/**
     * @return the number of left rotations performed.
     */
	public int getLeftRotation() {
		return leftRotation;
	}
	
	/**
     * @return the number of right rotations performed.
     */
	public int getRightRotation() {
		return rightRotation;
	}
	
	/**
     * @return the number of left-right rotations performed.
     */
	public int getLeftRightRotation() {
		return leftRightRotation;
	}
	
	/**
     * @return the number of right-left rotations performed.
     */
	public int getRightLeftRotation() {
		return rightLeftRotation;
	}
	
	/**
     * Updates the height of a given node based on the heights of its children.
     * 
     * @param n the node for which the height is updated
     */
	private void updateHeight(Node n) {
		n.height = 1 + Math.max (height(n.left), height(n.right));
	}
	
	
	/**
     * Performs a left rotation on the given node.
     * Increments the left rotation counter and adjusts the tree structure.
     * 
     * @param current the root of the subtree to rotate
     * @return the new root of the subtree after rotation
     */
	private Node leftRotation(Node current) {
		Node replace = current.right;
		Node replaceLeft = replace.left;
		
		replace.left = current;
		current.right = replaceLeft;
		
		updateHeight(current);
		updateHeight(replace);
		return replace;
		
	}
	
	
	/**
     * Performs a right rotation on the given node.
     * Increments the right rotation counter and adjusts the tree structure.
     * 
     * @param current the root of the subtree to rotate
     * @return the new root of the subtree after rotation
     */
	private Node rightRotation(Node current) {
		Node replace = current.left;
		Node replaceRight = replace.right;
		
		replace.right = current;
		current.left = replaceRight;
		
		updateHeight(current);
		updateHeight(replace);
		return replace;
		
	}
	
		
		
	/**
     * Inserts a Song into the AVL tree and re-balances the tree as necessary.
     * 
     * @param data the Song to insert
     */	
	@Override
	public void insert(Song item) {
        root = insertAVL(root, item);
    }
	
	
	/**
     * Recursive helper method to insert a Song into the subtree rooted at the given node.
     * Balances the subtree after insertion.
     * 
     * @param node the root of the subtree where the Song should be inserted
     * @param data the Song to insert
     * @return the new root of the subtree after insertion and balancing
     */
	protected Node insertAVL(Node node, Song item) {
        if (node == null) {
            return new Node(item);
        }
        if (item.getViews() < node.data.getViews()) {
            node.left = insertAVL(node.left, item);
        } else {
            node.right = insertAVL(node.right, item);
        }
     // Update height after insertion
        node.height = 1 + Math.max(getHeight((Node) node.left), getHeight((Node) node.right));
        
        return balance(node);
    }
	
	 /**
     * Balances the subtree rooted at the given node.
     * Depending on the balance factor, it applies rotations to maintain AVL properties.
     * 
     * @param node the root of the subtree to balance
     * @return the new root of the subtree after balancing
     */
	private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);
        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = leftRotation(node.left);
                leftRightRotation++;
            }
            rightRotation++;
            return rightRotation(node);
        } else if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rightRotation(node.right);
                rightLeftRotation++;
            }
            leftRotation++;
            return leftRotation(node);
        }
        return node;
    }
	 
	private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
	
	public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
	 
}
