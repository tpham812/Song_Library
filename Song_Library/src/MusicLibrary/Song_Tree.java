//Truong Pham
package MusicLibrary;

public class Song_Tree {

	Song_Tree parent, leftChild, rightChild;
	String title, artist, album, year;
	int order;
	
	public Song_Tree (Song_Tree node, String songName, String artistName, String album, String year) {
		
		parent = node; leftChild = null; rightChild = null; 
		title = songName; artist = artistName; this.album = album;
		this.year = year;
	}	
}