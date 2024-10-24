package songpack;

import java.util.ArrayList;

public class BinarySearchTree {
	static class Node{
		Song data;
		Node left;
		Node right;
		public Node(Song data) {
			left = right = null;
		}
	}
	Node root;// attribute
	
	public BinarySearchTree() { // constructor
		root = null;
	}
	
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
	
	public ArrayList<Song> toSortedArrayList(){
		ArrayList<Song> result = new ArrayList<>();

	    inOrderTraversal(root, result);
	    
		return result;
	}

	private void inOrderTraversal(Node root, ArrayList<Song> result) { //helpermethod
		// TODO Auto-generated method stub
		if (root != null)
			return;
	
		inOrderTraversal(root.left, result);
		result.add(root.data);
	    inOrderTraversal(root.right, result);
	    
	}
	
	
	
	

}
