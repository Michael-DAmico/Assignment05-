/**
 * @author Christian Burke and Michael D'Amico
 * @version 29 October 2024
 */
package songpack;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Program5 {
	 public static void main(String[] args) throws IOException {
	        if (args.length < 2) {
	            System.out.println("Usage: java Program5 <file path> <genre>");
	            return;
	            
	           
	        }

	        String filePath = args[0];
	        String genre = args[1];

	   
	    //Task 1
	    long startTime = System.currentTimeMillis();
	    // Load songs into the BinarySearchTree
	    BinarySearchTree songs = MyDataReader.readFileToBST(filePath, genre);
	    long endTime = System.currentTimeMillis();
	    System.out.println((endTime - startTime) + " milliseconds to read the " + genre + " songs into a binary search tree");

       	    // Display confirmation of the tree structure
	    if (songs != null) {
		System.out.println("Is the BinarySearchTree valid (BST)?: " + songs.isBST());
	 	}
	    
	    
	    
	    
	    //Task 2  
	    long startTop10Time = System.currentTimeMillis();
	    // Display the top 10 songs by views
	    TopListOfSongs(songs, 10);
	    long endTop10Time = System.currentTimeMillis();
	    System.out.println((endTop10Time - startTop10Time) + " milliseconds to find top-10 popular songs");

	    
	    
	    
	    //Task 3 
	    
	    // Subtask 1: Clone the tree
	    long cloneStartTime = System.currentTimeMillis();
	    BinarySearchTree clonedTree = songs.clone();
	    long cloneEndTime = System.currentTimeMillis();
	    System.out.println((cloneEndTime - cloneStartTime) + " milliseconds to clone the tree");
	    

	    // Subtask 2: Filter the cloned tree by view range
	    long filterStartTime = System.currentTimeMillis();
	    clonedTree.filterByView(1000, 10000);
	    long filterEndTime = System.currentTimeMillis();
	    System.out.println((filterEndTime - filterStartTime) + " milliseconds to filter the tree by view range");

	    // Subtask 3: Validate that the filtered tree is still a BST
	    boolean isValidAfterFilter = clonedTree.isBST();
	    System.out.println("Validation result of cloning and filtering: " + isValidAfterFilter);

	    // Subtask 4: Find the most popular artist(s) in the filtered view range
	    long popularArtistStartTime = System.currentTimeMillis();
	    ArrayList<String> popularArtists = clonedTree.popularArtists();
	    long popularArtistEndTime = System.currentTimeMillis();

	    System.out.println("The artist(s) with the highest views in the range 1000-10000: " + popularArtists);
	    System.out.println((popularArtistEndTime - popularArtistStartTime) + " milliseconds to find popular artists in the range");
	   
	    }
	 
	 
	 /**
	  * Displays a list of the top songs with the highest views from a given
	  * Binary Search Tree of songs.
	  *
	  * <p>This method retrieves a sorted list of songs from the provided 
	  * Binary Search Tree, reverses the list to arrange it in descending 
	  * order based on the number of views, and prints the specified number 
	  * of top songs.</p>
	  *
	  * @param songs      the BinarySearchTree containing the songs to be analyzed
	  * @param listLength the number of top songs to display
	  */
	 private static void TopListOfSongs(BinarySearchTree songs, int listLength) {
	    	// Get the sorted list of songs
	        ArrayList<Song> sortedSongs = songs.toSortedArrayList();
	        // Reverse the list to get descending order by views
	        Collections.reverse(sortedSongs);
	        // Print the top 10 songs with the highest views
	        System.out.println("Top " + listLength + " songs with the highest views:");
	        for (int i = 0; i < listLength && i < sortedSongs.size(); i++) {
	            Song song = sortedSongs.get(i);
	            System.out.println(song.getTitle() + " - Views: " + song.getViews());
	        }
	    }

}
