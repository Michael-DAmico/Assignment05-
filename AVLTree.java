package songpack;

/**
 * The AVLTree class extends the BinarySearchTree and implements
 * a self-balancing binary search tree using the AVL rotation mechanism.
 * It tracks the count of rotations performed during insertions to
 * maintain balance, including left, right, left-right, and right-left rotations.
 */
public class AVLTree extends BinarySearchTree {
    private int leftRotations = 0;
    private int rightRotations = 0;
    private int leftRightRotations = 0;
    private int rightLeftRotations = 0;
    
    /**
     * Inserts a Song item into the AVL Tree and balances the tree if necessary.
     *
     * @param item the Song object to be inserted.
     */
    @Override
    public void insert(Song item) {
        root = insertAVL(root, item);
    }

    /**
     * Recursively inserts a Song item into the AVL Tree and ensures that
     * the subtree is balanced after the insertion.
     *
     * @param node the current node in the AVL tree.
     * @param item the Song object to be inserted.
     * @return the balanced node after insertion.
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
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        return balance(node);
    }

    /**
     * Balances the AVL Tree by checking the balance factor of the node
     * and performing the appropriate rotations if necessary.
     *
     * @param node the node to be balanced.
     * @return the balanced node after rotations.
     */
    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
                leftRightRotations++;
            }
            rightRotations++;
            return rotateRight(node);
        } else if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
                rightLeftRotations++;
            }
            leftRotations++;
            return rotateLeft(node);
        }
        return node;
    }

    /**
     * Performs a right rotation on the given node.
     *
     * @param y the node on which to perform the right rotation.
     * @return the new root of the rotated subtree.
     */
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    /**
     * Performs a left rotation on the given node.
     *
     * @param x the node on which to perform the left rotation.
     * @return the new root of the rotated subtree.
     */
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));

        return y;
    }

    /**
     * Calculates the balance factor of a given node.
     *
     * @param node the node for which to calculate the balance factor.
     * @return the balance factor of the node.
     */
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * Gets the height of the specified node.
     *
     * @param node the node for which to get the height.
     * @return the height of the node, or 0 if the node is null.
     */
    public int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * Gets the number of left rotations performed in the AVL Tree.
     *
     * @return the count of left rotations.
     */
    public int getLeftRotations() { return leftRotations; }

    /**
     * Gets the number of right rotations performed in the AVL Tree.
     *
     * @return the count of right rotations.
     */
    public int getRightRotations() { return rightRotations; }

    /**
     * Gets the number of left-right rotations performed in the AVL Tree.
     *
     * @return the count of left-right rotations.
     */
    public int getLeftRightRotations() { return leftRightRotations; }

    /**
     * Gets the number of right-left rotations performed in the AVL Tree.
     *
     * @return the count of right-left rotations.
     */
    public int getRightLeftRotations() { return rightLeftRotations; }
}
