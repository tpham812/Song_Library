/**
 * @author Truong Pham
 * 
 * This class is a node that holds a song and its information and link 
 * to its 2 successor and predecessor in the tree.
 */
package MusicLibrary;

public class Song {

	Song parent, leftChild, rightChild;
	String title, artist, album, year;
	int order;
	
	public Song (Song node, String songName, String artistName, String album, String year) {
		
		parent = node; 
		leftChild = null; 
		rightChild = null; 
		title = songName; 
		artist = artistName; 
		this.album = album;
		this.year = year;
	}	
}