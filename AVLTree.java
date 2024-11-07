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
	
	
	
	public AVLTree() {
		root = null;
		leftRotation = 0;
		rightRotation = 0;
		leftRightRotation = 0;
		rightLeftRotation = 0;
	}
	
	public int getLeftRotation() {
		return leftRotation;
	}
	
	public int getRightRotation() {
		return rightRotation;
	}
	
	public int getLeftRightRotation() {
		return leftRightRotation;
	}
	
	
	public int getRightLeftRotation() {
		return rightLeftRotation;
	}
	
	private void updateHeight(Node n) {
		n.height = 1 + Math.max (height(n.left), height(n.right));
	}
	private Node leftRotation(Node current) {
		Node replace = current.right;
		Node replaceLeft = replace.left;
		
		replace.left = current;
		current.right = replaceLeft;
		
		updateHeight(current);
		updateHeight(replace);
		return replace;
		
	}
	
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
		
	@Override 
	public void insert(Song data) {
		return null;// fix later
	}
	
	private Node insert(Node node, Song data) {
		
	}
*/
}
