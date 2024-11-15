package songpack;

public class AVLTree extends BinarySearchTree {
    private int leftRotations = 0;
    private int rightRotations = 0;
    private int leftRightRotations = 0;
    private int rightLeftRotations = 0;

    @Override
    public void insert(Song item) {
        root = insertAVL(root, item);
    }

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

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2	 = x.right;
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = 1 + Math.max(getHeight((Node) y.left), getHeight((Node) y.right));
        x.height = 1 + Math.max(getHeight((Node) x.left), getHeight((Node) x.right));

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = 1 + Math.max(getHeight((Node) x.left), getHeight((Node) x.right));
        y.height = 1 + Math.max(getHeight((Node) y.left), getHeight((Node) y.right));

        return y;
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

    public int getLeftRotations() { return leftRotations; }
    public int getRightRotations() { return rightRotations; }
    public int getLeftRightRotations() { return leftRightRotations; }
    public int getRightLeftRotations() { return rightLeftRotations; }
}
