/**
 * @author Truong Pham
 * 
 * This class is a tree data structure that holds the root of the song tree and maintains the tree
 * using various operations.
 */
package MusicLibrary;

import java.util.ArrayList;

public class SongTree {

	/** Holds the beginning node in the song tree */
	Song root;
	
	/** Holds the order of songs that were store in library */
	int order;
	
	public SongTree() {
		
		root = null;
		order = 0;
	}
	
	/**
	 * This method adds a song node to the tree .
	 * @param songName 		Song name
	 * @param artistName 	Artist name
	 * @param album			Album name
	 * @param year			Year
	 * @return				True if add is successful, false if song name and artist name already exits in tree.
	 */
	public boolean addToTree(String songName, String artistName, String album, String year) {
		
		Song curr = root, parent = null, temp = null;
		if(root == null) {temp = new Song(parent, songName, artistName, album, year); root = temp; return true;}
		while(curr != null) {
			if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) == 0 && curr.artist.toLowerCase().compareTo(artistName.toLowerCase()) == 0)
				return false;
			else if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) > 0) {
				if(curr.leftChild == null) {
					temp = new Song(curr, songName, artistName, album, year); 
					curr.leftChild = temp;
					return true;	
				}
				curr = curr.leftChild;
			}
			else {
				if(curr.rightChild == null) {
					temp = new Song(curr, songName, artistName, album, year);
					curr.rightChild = temp; 
					return true;
				}
				curr = curr.rightChild;
			}
		}
		return true;
	}
	
	/**
	 * This method deletes a song node from the tree given the song name and artist name.
	 * @param songName		Song name
	 * @param artistName	Artist name
	 */
	public void deleteFromTree(String songName, String artistName) {
		
		Song curr = root, parent = null, temp = null;
		
		if (root == null) {return;}
		
		if(root.title.toLowerCase().equals(songName.toLowerCase()) && root.artist.toLowerCase().equals(artistName.toLowerCase())) {
			if(root.leftChild == null && root.rightChild == null) {root = null; return;}
			else if(root.leftChild == null && root.rightChild != null) {root = root.rightChild; root.parent = null; return;}
			else if(root.leftChild != null && root.rightChild == null) { root = root.leftChild; root.parent = null; return;}
			else {
				parent = root;
				curr = root.leftChild;
				while(curr.rightChild!= null) {parent= curr; curr = curr.rightChild;}
				if(parent == root) {root.artist = curr.artist; root.title = curr.title; root.album = curr.album; root.year = curr.year; root.leftChild = curr.leftChild; return;}
				else {parent.rightChild = curr.leftChild; root.artist = curr.artist; root.title = curr.title; root.album = curr.album; root.year = curr.year; return;}
			}
		}
		
		while(curr != null) {
			if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) == 0 && curr.artist.toLowerCase().compareTo(artistName.toLowerCase()) == 0) {
				if(curr.leftChild == null && curr.rightChild == null) {
					if(curr.parent.rightChild == curr) {curr.parent.rightChild = null; return;}
					else {curr.parent.leftChild = null; return;}
				}
				else if(curr.leftChild != null && curr.rightChild == null) {
					if(curr.parent.rightChild == curr) {curr.parent.rightChild = curr.leftChild; return;}
					else {curr.parent.leftChild = curr.leftChild; return;}
				}
				else if(curr.leftChild == null && curr.rightChild != null) {
					if(curr.parent.rightChild == curr) {curr.parent.rightChild = curr.rightChild; return;}
					else {curr.parent.leftChild = curr.rightChild; return;}
				}
				else {
					parent = curr;
					temp = curr.leftChild;
					while(temp.rightChild!= null) {parent= temp; temp = temp.rightChild;}
					if(parent == curr) {curr.artist = temp.artist; curr.title = temp.title; curr.leftChild = temp.leftChild; return;}
					else {curr.artist = temp.artist; curr.title = temp.title; parent.rightChild = temp.leftChild; return;}
				}	
			}
			else if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) > 0) {parent = curr; curr = curr.leftChild;}
			else {parent = curr; curr = curr.rightChild;}
		}
	}
	
	/**
	 * This method checks to see if the tree is empty.
	 * @return True if tree is empty, otherwise false.
	 */
	public boolean checkIsEmpty() {
		
		if(root == null) 
			return false;
		else 
			return true;
	}
	
	/**
	 * Find song given the song name.
	 * @param songName 		Song name to search for 
	 * @param order			Song order 
	 * @return				String array containing song information if song is found, otherwise null
	 */
	public String[] findSong(String songName, int order) {
		
		Song curr = root;
		String songDetail[] = new String[4];
		while(curr != null) {
			if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) == 0 && curr.order == order) {
				songDetail[0] = curr.title;
				songDetail[1] = curr.artist;
				songDetail[2] = curr.album;
				songDetail[3] = curr.year;
				return songDetail;
			}
			else if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) > 0) 
				curr = curr.leftChild;
			else
				curr = curr.rightChild;
		}	
		return null;
	}
	
	public int findSongOrder(String songName, String artistName) {
		
		Song curr = root;
		while(curr != null) {
			if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) == 0 && curr.artist.toLowerCase().compareTo(artistName.toLowerCase()) == 0) {
				return curr.order;
			}
			else if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) > 0) 
				curr = curr.leftChild;
			else
				curr = curr.rightChild;
		}
		return 0;
	}
	
	/**
	 * This method gets all song names and store in the given ArrayList.
	 * @param root 		Root of the song tree.
	 * @param newSongs	ArrayList to store song names in.
	 */
	public void getSongs(Song root, ArrayList<String> newSongs) {
		
		if(root != null) {
			getSongs(root.leftChild, newSongs);
			newSongs.add(root.title);
			root.order = order;
			order++;
			getSongs(root.rightChild, newSongs);
		}
	}
	
	/**
	 * This method recursively prints each song and its information in the tree.
	 * @param root		Root of song tree.
	 */
	public void printSongs(Song root) {
		
		if(root != null) {
			printSongs(root.leftChild);
			System.out.println(root.title + " " + root.artist + " " + root.album + " " + root.year);
			printSongs(root.rightChild);
		}
	}
}