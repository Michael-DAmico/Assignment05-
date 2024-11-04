/**
 * @author Christian Burke and Michael D'Amico
 * @version 5 November 2024
 */
package songpack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.TreeSet;

public class MySearchEngine {
	
	TreeMap<Song, TreeMap<String, Double>> tf;
	TreeMap<String, Double> idf;
	TreeMap<Song, Double> avgLength;
	
	public MySearchEngine(ArrayList<Song> songs) {
		calculateTF(songs);
		calculateIDF(songs);
		calculateAvgLength(songs);
	}
	
	private void calculateTF(ArrayList<Song> songs) {
		//double N = songs.size();
		for (Song s : songs) {
			String[] words = s.getLyrics().split(" ");
            TreeMap<String, Double> wordFreq = new TreeMap<>();
            double totalTerms = words.length;	
       
            
            // Count occurrences of each word in the song
            for (String word : words) {
                wordFreq.put(word, wordFreq.getOrDefault(word, 0.0) + 1);
		}
            // Calculate term frequency as count/totalTerms
            for (String word : wordFreq.keySet()) {
                wordFreq.put(word, wordFreq.get(word) / totalTerms);
            }
           
          
            tf.put(s, wordFreq);  // Store in the tf TreeMap
		}
	}

	
	private void calculateIDF(ArrayList<Song> songs) { // Calculating the occurrence not the frequency in a particular song
		double N = songs.size();
		
		
		for(Song s: songs) {
			String[] terms= s.getLyrics().split(" "); 
			// get all uniqueWords
			TreeSet<String> uniqueWords = new TreeSet<>(Arrays.asList(terms));
			for (String word : uniqueWords) {
				idf.put(word,  idf.getOrDefault(word, 0.0) + 1);
			}
		}
		// idf has only nx values so far: by this code 
		for(String s: idf.keySet()) {
			double nx = idf.get(s);
			double idfValue = (N - nx+0.5) / nx+0.5;
			idfValue = Math.log(idfValue +1);
			idf.put(s, idfValue);
			
		}
			
		
		
	}
	
	
	private void calculateAvgLength(ArrayList<Song> songs) {
		double N = songs.size();
		int numTotalWords = 0;
		
		for(Song s: songs) {
			String[] terms = s.getLyrics().split(" "); 
			String totalWords = s.getLyrics();
			double numTotalWords1 = totalWords.length();
			
			
			
		}
			for(Song a : songs) {
				double sum = (songs.size()/numTotalWords);
				avgLength.put(a, sum);
				
			}
	
		
	}
}
