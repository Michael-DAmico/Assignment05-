# Assignment05-07
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
