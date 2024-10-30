/**
 * @author Christian Burke and Michael D'Amico
 * @version 29 October 2024
 */
package songpack;


import java.io.IOException;
import java.util.ArrayList;


public class Program5 {
	 public static void main(String[] args) throws IOException {
	        if (args.length < 2) {
	            System.out.println("Usage: java Program5 <file path> <genre>");
	            return;
	            
	           
	        }

	        String filePath = args[0];
	        String genre = args[1];

	   
	 // Load songs into the BinarySearchTree
	    long startTime = System.currentTimeMillis();
	    BinarySearchTree songs = MyDataReader.readFileToBST(filePath, genre);
	    
	    long endTime = System.currentTimeMillis();
	    System.out.println((endTime - startTime) + " milliseconds to read the " + genre + " songs into a binary search tree");

	    // Display confirmation of the tree structure
	    if (songs != null) {
	        System.out.println("Is the BinarySearchTree valid (BST)?: " + songs.isBST());
	    	}
	    
	    
	    
	    
	    //Task 2 
	    // Print the top-10 titles of songs with the highest number of views
	    long startTop10Time = System.currentTimeMillis();
	    ArrayList<Song> top10Songs = songs.getTop10Songs();
	    long endTop10Time = System.currentTimeMillis();

	    System.out.println("Top-10 titles of songs with the highest number of views:");
	    for (Song song : top10Songs) {
	        System.out.println(song.getTitle());
	    }
	   
	    System.out.println((endTop10Time - startTop10Time) + " milliseconds to find top-10 popular songs");
	    
	    
	    
	    
	    
	    //Task 3 
	    
	    
	    // Step 1: Clone the tree
	    long cloneStartTime = System.currentTimeMillis();
	    BinarySearchTree clonedTree = songs.clone();
	    long cloneEndTime = System.currentTimeMillis();
	    System.out.println((cloneEndTime - cloneStartTime) + " milliseconds to clone the tree");
	    

	    // Step 2: Filter the cloned tree by view range
	    long filterStartTime = System.currentTimeMillis();
	    clonedTree.filterByView(1000, 10000);
	    long filterEndTime = System.currentTimeMillis();
	    System.out.println((filterEndTime - filterStartTime) + " milliseconds to filter the tree by view range");

	    // Step 3: Validate that the filtered tree is still a BST
	    boolean isValidAfterFilter = clonedTree.isBST();
	    System.out.println("Validation result of cloning and filtering: " + isValidAfterFilter);

	    // Step 4: Find the most popular artist(s) in the filtered view range
	    long popularArtistStartTime = System.currentTimeMillis();
	    ArrayList<String> popularArtists = clonedTree.popularArtists();
	    long popularArtistEndTime = System.currentTimeMillis();

	    System.out.println("The artist(s) with the highest views in the range 1000-10000: " + popularArtists);
	    System.out.println((popularArtistEndTime - popularArtistStartTime) + " milliseconds to find popular artists in the range");
	   
	    }
	}
