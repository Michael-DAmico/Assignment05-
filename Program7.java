/**
 * @author Christian Burke and Michael D'Amico
 * @version 14 November 2024
 */
package songpack;


import java.util.ArrayList;

public class Program7 {
    
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java program6 <csv_file_path> <genre/tag>");
            return;
        }
        
        String filePath = args[0];
        String genre = args[1];
        
        ArrayList<Song> songs = MyDataReader.readSongsFromTSV(filePath);
        
        
        // Build AVL Tree and BST for the given genre
        long avlStartTime = System.currentTimeMillis();
        AVLTree avlTree = new AVLTree();
        for (Song song : songs) {
            if (song.getTag().equalsIgnoreCase(genre)) {
                avlTree.insert(song);
            }
        }
        long avlEndTime = System.currentTimeMillis();
        
        long bstStartTime = System.currentTimeMillis();
        BinarySearchTree bstTree = new BinarySearchTree();
        for (Song song : songs) {
            if (song.getTag().equalsIgnoreCase(genre)) {
                bstTree.insert(song);
            }
        }
        long bstEndTime = System.currentTimeMillis();
        
        // Calculate build times
        long avlBuildTime = (avlEndTime - avlStartTime) ; // in milliseconds
        long bstBuildTime = (bstEndTime - bstStartTime) ; // in milliseconds
        
        // Print AVL Tree statistics
        System.out.println("AVL Tree Statistics for genre: " + genre);
        System.out.println(avlTree.getLeftRotation()+" Left Rotations");
        System.out.println(avlTree.getRightRotation()+" Right Rotations ");
        System.out.println(avlTree.getLeftRightRotation()+" Left-Right Rotations ");
        System.out.println(avlTree.getRightLeftRotation()+" Right-Left Rotations");
        System.out.println("The height of AVL Tree is: " + avlTree.getHeight(avlTree.root));
        System.out.println(avlBuildTime+ " milliseconds to build the AVL Tree for "+genre+" songs");
        
        // Perform searches and measure time
        performAVLSearches(avlTree, genre);

        //spacer
        System.out.println();
        
        // Print BST statistics
        System.out.println("Binary Search Tree Statistics for genre: " + genre);
        System.out.println(bstBuildTime+" milliseconds to build the BST for "+genre+" songs");
        System.out.println("The height of the tree is: " + bstTree.getHeight(bstTree.root)); // Assuming you have a getHeight method in BST
        

        // Perform searches and measure time
        performBSTSearches(bstTree, genre);
    }

    
    /**
     * Performs search operations on a Binary Search Tree (BST) for specific view count values.
     * Times each search operation and prints the result in microseconds.
     *
     * @param tree  the BinarySearchTree instance to perform searches on
     * @param genre the genre of songs being searched, used for logging or tracking purposes
     */
    private static void performBSTSearches(BinarySearchTree tree, String genre) {
        int[] views = new int[] {-2, 12345, 2, 5000, 1000000}; // Example search queries
        for (int view : views) {
            long startTime = System.nanoTime();
            tree.search(view); // Assuming search method is defined in BinarySearchTree
            long endTime = System.nanoTime();
            long searchTime = (endTime - startTime) / 1_000; // Convert to microseconds
            System.out.println("Search time for view " + view + ": " + searchTime + " microseconds");
        }
    }
    
    
    
    /**
     * Performs search operations on an AVL Tree for specific view count values.
     * Times each search operation and prints the result in microseconds.
     *
     * @param tree  the AVLTree instance to perform searches on
     * @param genre the genre of songs being searched, used for logging or tracking purposes
     */
    private static void performAVLSearches(AVLTree tree, String genre) {
        int[] views = new int[] {-2, 12345, 2, 5000, 1000000}; // Example search queries
        for (int view : views) {
            long startTime = System.nanoTime();
            tree.search(view); // Assuming search method is defined in AVLTree
            long endTime = System.nanoTime();
            long searchTime = (endTime - startTime) / 1_000; // Convert to microseconds
            System.out.println("Search time for view " + view + ": " + searchTime + " microseconds");
        }
    }
}
