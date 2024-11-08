/**
* @author Christian Burke and Michael D'Amico
* @version 7 November 2024
*/
package songpack;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The {@code program6} class is the entry point for running the search engine.
 * It reads a TSV file containing song data, builds an index, and performs searches 
 * for specified queries, measuring the time taken for each operation.
 */
public class Program6 {
    /**
     * The main method reads a TSV file path from the command-line arguments, 
     * initializes the search engine, and performs search queries.
     *
     * @param args command-line arguments where {@code args[0]} is expected to be 
     *             the path to the TSV file containing song data
     * @throws IOException if there is an error reading the TSV file
     */
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("Please provide the TSV file path.");
            return;
        }
        
        String filePath = args[0];
        ArrayList<Song> songs = MyDataReader.readFileToArrayList(filePath);  // Assuming MyDataReader handles reading

        long startTime = System.currentTimeMillis();
        MySearchEngine searchEngine = new MySearchEngine(songs);
        long endTime = System.currentTimeMillis();
        
        System.out.println((endTime - startTime) + " milliseconds to build the index");
        
        String[] queries = {
            "We are the Champions",
            "I will always love you",
            "walking on sunshine",
            "dancing in the rain",
            "put your hands in the jupitersky"
        };
        
        for (String query : queries) {
            long searchStart = System.currentTimeMillis();
            searchEngine.search(query, songs);
            long searchEnd = System.currentTimeMillis();
            System.out.println((searchEnd - searchStart) + " milliseconds to search for \"" + query + "\"\n");
        }
    }
}
