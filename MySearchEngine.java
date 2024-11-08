/**
 * @author Christian Burke and Michael D'Amico
 * @version 7 November 2024
 */
package songpack;

import java.util.*;

/**
 * The {@code MySearchEngine} class implements a search engine that calculates 
 * and ranks songs based on their relevance to a query using BM25 algorithm.
 * It gets term frequency (TF), inverse document frequency (IDF), and average 
 * song length for scoring relevance.
 */
public class MySearchEngine {
    // Attributes
    private List<Song> songs;
    private TreeMap<Song, TreeMap<String, Double>> tf;
    private TreeMap<String, Double> idf;
    private TreeMap<Song, Double> avgLength;

    /**
     * Constructs a {@code MySearchEngine} instance with the specified list of songs.
     * Initializes term frequency (TF), inverse document frequency (IDF), and 
     * average length calculations for each song.
     *
     * @param songs the list of {@code Song} objects to be indexed by the search engine
     */
    public MySearchEngine(List<Song> songs) {
        this.songs = songs;
        this.tf = new TreeMap<>();
        this.idf = new TreeMap<>();
        this.avgLength = new TreeMap<>();
        calculateTF();
        calculateIDF();
        calculateAvgLength();
    }

    /**
     * Calculates the term frequency (TF) for each song. The frequency of each word 
     * is calculated relative to the total number of words in the song lyrics.
     */
    public void calculateTF() {
        for (Song song : songs) {
            String[] words = song.getLyrics().split(" ");
            TreeMap<String, Double> wordFreq = new TreeMap<>();
            for (String word : words) {
                word = word.toLowerCase();  // Make it case-insensitive
                wordFreq.put(word, wordFreq.getOrDefault(word, 0.0) + 1);
            }
            int totalWords = words.length;
            for (Map.Entry<String, Double> entry : wordFreq.entrySet()) {
                wordFreq.put(entry.getKey(), entry.getValue() / totalWords);
            }
            tf.put(song, wordFreq);
        }
    }

    /**
     * Calculates the inverse document frequency (IDF) for each unique term in the lyrics.
     * The IDF is calculated based on the number of songs that contain each term.
     */
    public void calculateIDF() {
        int totalSongs = songs.size();
        for (Song song : songs) {
            Set<String> uniqueWords = new TreeSet<>(Arrays.asList(song.getLyrics().split(" ")));
            for (String word : uniqueWords) {
                idf.put(word, idf.getOrDefault(word, 0.0) + 1);
            }
        }
        for (Map.Entry<String, Double> entry : idf.entrySet()) {
            double nX = entry.getValue();
            double idfValue = Math.log((totalSongs - nX + 0.5) / (nX + 0.5) + 1);
            idf.put(entry.getKey(), idfValue);
        }
    }

    /**
     * Calculates the average length of songs based on the total number of words 
     * in each song's lyrics.
     */
    public void calculateAvgLength() {
        double totalLength = 0;
        for (Song song : songs) {
            totalLength += song.getLyrics().split(" ").length;
        }
        double avgLengthValue = totalLength / songs.size();
        for (Song song : songs) {
            double songLength = song.getLyrics().split(" ").length;
            avgLength.put(song, songLength / avgLengthValue);
        }
    }

    /**
     * Calculates the relevance score of a song for a given query using the BM25 algorithm.
     * This score indicates how relevant the song is to the search terms in the query.
     *
     * @param song  the {@code Song} object for which relevance is calculated
     * @param query an array of query terms
     * @return the calculated relevance score
     */
    public double calculateRelevance(Song song, String[] query) {
        double relevance = 0.0;
        double songLength = song.getLyrics().split(" ").length;
        double avgSongLength = avgLength.get(song);

        for (String term : query) {
            double localtf = tf.get(song).getOrDefault(term, 0.0);
            double idfValue = idf.getOrDefault(term, 0.0);
            relevance += idfValue * localtf * (songLength / (songLength + 1.2 * (1 - 0.75 + 0.75 * songLength / avgSongLength)));
        }
        return relevance;
    }

    /**
     * Sorts the relevance scores and returns the top-k results.
     *
     * @param treeMap the map of songs and their relevance scores
     * @param topK    the number of top results to return
     * @return a list of map entries representing the top-k results sorted by relevance
     */
    private List<Map.Entry<Song, Double>> sortedByValue(TreeMap<Song, Double> treeMap, int topK) {
        List<Map.Entry<Song, Double>> list = new ArrayList<>(treeMap.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        return list.subList(0, topK);
    }

    /**
     * Prints the search results, displaying the title, artist, and relevance score 
     * for each of the top-ranked songs.
     *
     * @param query   the original search query
     * @param results the list of top results with relevance scores
     */
    private void printSearchResults(String query, List<Map.Entry<Song, Double>> results) {
        System.out.println("Results for " + query);
        int rank = 1;
        for (Map.Entry<Song, Double> entry : results) {
            System.out.println(rank + ": " + entry.getKey().getTitle() + " by " + entry.getKey().getArtist() + "\t" + entry.getValue());
            rank++;
        }
    }

    /**
     * Performs a search on the song collection based on the specified query and displays 
     * the top-5 results.
     *
     * @param query the search query
     */
    public void search(String query) {
        String[] terms = query.toLowerCase().split(" ");
        TreeMap<Song, Double> relevanceScores = new TreeMap<>();

        for (Song song : songs) {
            double relevance = calculateRelevance(song, terms);
            relevanceScores.put(song, relevance);
        }

        List<Map.Entry<Song, Double>> topResults = sortedByValue(relevanceScores, 5);
        printSearchResults(query, topResults);
    }
}
