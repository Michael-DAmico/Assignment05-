/**
 * @author Christian Burke and Michael D'Amico
 * @version 29 October 2024
 */
package songpack;

import java.io.IOException;
import java.util.ArrayList;

public class Program5 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Program5 <file path> <genre>");
            return;
        }

        String filePath = args[0];
        String genre = args[1];

        BinarySearchTree songTree = new BinarySearchTree();

        // Load songs into the BinarySearchTree
        long startTime = System.currentTimeMillis();
        try {
			BinarySearchTree songs = MyDataReader.readFileToBST(filePath, genre);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        

        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + " milliseconds to read the " + genre + " songs into a binary search tree");
    }
}
