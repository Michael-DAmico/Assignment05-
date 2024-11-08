/**
 * @author Christian Burke and Michael D'Amico
 * @version 5 November 2024
 */
package songpack;

public class Program6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
        
        for (String searchQuery : queries) {
            long searchStart = System.currentTimeMillis();
            searchEngine.search(searchQuery);
            long searchEnd = System.currentTimeMillis();
            System.out.println((searchEnd - searchStart) + " milliseconds to search for \"" + searchQuery + "\"\n");
        }

		
		
	}

	}

}
