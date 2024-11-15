/**
 * @author Christian Burke and Michael D'Amico
 * @version 14 November 2024
 */
package songpack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyDataReader {
    

	/**
     * Process a line from the TSV file and returns the corresponding song object
     * @param inputLine TSV line
     * @return Song object
     */
    static Song lineToReport(String inputLine)
    {
        String[] items = inputLine.split("\t");
        String title= items[0];
        String tag= items[1];
        String artist= items[2];
        int year= Integer.parseInt(items[3]);
        int views= Integer.parseInt(items[4]);
        String lyrics= items[5];
        
        Song s = new Song(title, tag, artist, year, views, lyrics);
        return s;
    }
    
    /**
     * This method takes in the tsv file path and returns the binary search tree of songs with the given tag
     * @param tsvFilePath tsv file path
     * @param tag one of the six tags: rap, rb, pop, rock, misc, and country
     * @return binary search tree of songs
     * @throws IOException
     */
    public static BinarySearchTree readFileToBST(String tsvFilePath, String tag) throws IOException
    {
        BinarySearchTree songsBST= new BinarySearchTree();
        int counter = 0;
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(tsvFilePath))) {
			String line = TSVReader.readLine();
            while ((line = TSVReader.readLine()) != null) {   
                Song song = MyDataReader.lineToReport(line);
                if(song.getTag().equalsIgnoreCase(tag))// added ignoreCase 
                  songsBST.insert(song);
              counter+=1;
              // using this to view progress
              if(counter%50000==0)
                  System.out.println(counter + " records added");
            }
		}
            return songsBST;
    }
    
    /**
     * This method takes in the TSV file path and returns an ArrayList of all Song objects in the file
     * @param tsvFilePath tsv file path
     * @return ArrayList of Song objects
     * @throws IOException
     */
    public static ArrayList<Song> readFileToArrayList(String tsvFilePath) throws IOException {
        ArrayList<Song> songsList = new ArrayList<>();
        int counter = 0;
        try (BufferedReader TSVReader = new BufferedReader(new FileReader(tsvFilePath))) {
            String line = TSVReader.readLine(); // Skip header if there is one
            while ((line = TSVReader.readLine()) != null) {
                Song song = MyDataReader.lineToReport(line);
                songsList.add(song);
                counter++;
                // Print progress for every 50,000 records
                if (counter % 50000 == 0)
                    System.out.println(counter + " records added");
            }
        }
        return songsList;
    }
    
    /**
     * Reads songs from a TSV file using the lineToReport method.
     * @param filePath Path to the TSV file.
     * @return ArrayList of Song objects.
     */
    public static ArrayList<Song> readSongsFromTSV(String filePath) {
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

}	
