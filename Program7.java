/**
 * @author Christian Burke and Michael D'Amico
 * @version 14 November 2024
 */
package songpack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Program7 {
    
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java program6 <csv_file_path> <genre/tag>");
            return;
        }
        
        String filePath = args[0];
        String genre = args[1];
        
        ArrayList<Song> songs = readSongsFromTSV(filePath);
        
        
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
        performSearches(avlTree, genre);

        //spacer
        System.out.println();
        
        // Print BST statistics
        System.out.println("Binary Search Tree Statistics for genre: " + genre);
        System.out.println(bstBuildTime+" milliseconds to build the BST for "+genre+" songs");
        System.out.println("The height of the tree is: " + bstTree.getHeight(bstTree.root)); // Assuming you have a getHeight method in BST
        

        // Perform searches and measure time
        //performSearches(avlTree, genre);
        performSearches(bstTree, genre);
    }

    /**
     * Reads songs from a TSV file using the myDataReaders.lineToReport method.
     * @param filePath Path to the TSV file.
     * @return ArrayList of Song objects.
     */
    private static ArrayList<Song> readSongsFromTSV(String filePath) {
        ArrayList<Song> songs = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            
            // Skip header if present
            br.readLine();
            
            while ((line = br.readLine()) != null) {
                // Use lineToReport to parse the line and create a Song object
                Song song = MyDataReader.lineToReport(line);
                songs.add(song);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return songs;
    }

    private static void performSearches(BinarySearchTree tree, String genre) {
        int[] views = new int[] {-2, 12345, 2, 5000, 1000000}; // Example search queries
        for (int view : views) {
            long startTime = System.nanoTime();
            tree.search(view); // Assuming search method is defined in BinarySearchTree
            long endTime = System.nanoTime();
            long searchTime = (endTime - startTime) / 1_000; // Convert to microseconds
            System.out.println("Search time for view " + view + ": " + searchTime + " microseconds");
        }
    }

    private static void performSearches(AVLTree tree, String genre) {
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
