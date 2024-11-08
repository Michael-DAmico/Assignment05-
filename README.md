# Assignment05
Songs

To run file ensure the arguments are set to
"C:\file\location\of\song_lyrics.tsv"
"rock"

Song Binary Search Tree Program
Overview
This program processes song data, allowing users to read, sort, and analyze song records. 

Contributions:

Christian

  	Main
  	Javadoc,
	  .PDF
  
  
Michael
	
 	BinarySearchTree
 	Javadoc,
	.Pdf


  
  Creations Made:
  
  
 	Added MyDataReader class 
 	Added BinaarySearchTree class
 	Added Program5 class
	Added Song class
 	
  

Classes
1. Song
The Song class represents a song object with attributes such as title, tag, artist, year, views, and lyrics. It implements the Comparable<Song> interface to enable comparison based on the number of views.

Key Methods:
getTitle(), getTag(), getArtist(), getYear(), getViews(), getLyrics()
setTitle(), setTag(), setArtist(), setYear(), setViews(), setLyrics()
compareTo(Song otherSong): Compares songs based on views.

2. MyDataReader
The MyDataReader class handles reading the TSV file and converting each line into a Song object. It also creates a binary search tree of songs filtered by a specific tag.

Key Methods:
lineToReport(String inputLine): Converts a TSV line into a Song object.
readFileToBST(String tsvFilePath, String tag): Reads the TSV file and returns a BST of songs matching the given tag.

3. Program5
The Program5 class contains the main method to execute the program. It handles user input, loads songs into the BST, displays the top songs, clones the tree, filters by view range, and finds popular artists.

Key Tasks:
Load songs into the BST from a TSV file.
Display the top N songs by views.
Clone the BST and filter the cloned tree by view count.
Find and display the most popular artist(s) in a specified view range.



Sample Output
For rock

50000 records added
100000 records added
150000 records added
200000 records added
250000 records added
300000 records added
350000 records added
400000 records added
450000 records added
3100 milliseconds to read the rock songs into a binary search tree
Is the BinarySearchTree valid (BST)?: true
Top 10 songs with the highest views:
Bohemian Rhapsody - Views: 9247817
Hallelujah - Views: 3810370
Do I Wanna Know? - Views: 2965702
Mr. Brightside - Views: 2892121
Dont Stop Believin - Views: 2868577
Hotel California - Views: 2326328
Smells Like Teen Spirit - Views: 2297062
I Miss You - Views: 2155764
Blinded by the Light - Views: 2142723
Wish You Were Here - Views: 1884349
12 milliseconds to find top-10 popular songs
6 milliseconds to clone the tree
1 milliseconds to filter the tree by view range
Validation result of cloning and filtering: true
The artist(s) with the highest views in the range 1000-10000: [Papa Roach]
3 milliseconds to find popular artists in the range



# Assignment06
Songs


Overview
MySearchEngine is a program that allows you to search for song lyrics using a method called BM25, which scores songs based on how well they match your search. It ranks songs by relevance, showing you the best matches at the top.

Contributions:

Christian

  	Program6
   	calculateRelevance
    	search,
     	printSearchResults,
      	sortedByValue,
  	Javadoc
   	
  
  
Michael
	
 	calcculateTF,
  	calculateIDF,
   	calculateAvgLength,
 	Javadoc,
	.Pdf
 	ReadMe


  
  Changes Made:
  
  
 	Added to MyDataReader class readFileToArrayList method
 	Added MySearchEngine class 
 	Added Program6 class

How It Works
The program goes through two main steps: Indexing and Searching.

1. Indexing
During indexing, the program prepares all the song data for quick and accurate searches. Here’s what it calculates:

Term Frequency (TF): Counts how often each word (or term) appears in each song. This helps identify songs that might be a good match for a search based on repeated words.

Inverse Document Frequency (IDF): Measures how unique each word is across all songs. For example, common words like "love" will have a lower score because they appear in many songs, while unique words get higher scores.

Average Song Length: The average number of words in each song. This helps the program fairly compare songs of different lengths.

2. Searching
When you search for a song lyric (for example, “we are the champions”), the program calculates a relevance score for each song based on how well the lyrics match your search terms.

How Relevance is Calculated
For each song, the program:

Looks at each search term in the song (like "we," "are," "champions").
Scores each term based on:
How often it appears in the song (TF)
How unique it is across all songs (IDF)
Combines these scores to get an overall relevance score for the song.
The top 5 songs with the highest scores are shown as the best matches for your search.
  
