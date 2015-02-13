//Truong Pham
package MusicLibrary;

import java.util.ArrayList;


public class Song_Action {

	Song_Tree root;
	int count;
	
	public Song_Action() {
		
		root = null;
		count = 0;
	}
	public boolean addToTree(String songName, String artistName, String album, String year) {
		
		Song_Tree curr = root, parent = null, temp = null;
		if(root == null) {temp = new Song_Tree(parent, songName, artistName, album, year); root = temp; return true;}
		while(curr != null) {
			if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) == 0 && curr.artist.toLowerCase().compareTo(artistName.toLowerCase()) == 0)
				return false;
			else if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) > 0) {
				if(curr.leftChild == null) {
					temp = new Song_Tree(curr, songName, artistName, album, year); 
					curr.leftChild = temp;
					return true;	
				}
				curr = curr.leftChild;
			}
			else {
				if(curr.rightChild == null) {
					temp = new Song_Tree(curr, songName, artistName, album, year);
					curr.rightChild = temp; 
					return true;
				}
				curr = curr.rightChild;
			}
		}
		return true;
	}
	
	public void deleteFromTree(String songName, String artistName) {
		
		Song_Tree curr = root, parent = null, temp = null;
		
		if (root == null) {return;}
		
		if(root.title.toLowerCase().equals(songName.toLowerCase()) && root.artist.toLowerCase().equals(artistName.toLowerCase())) {
			if(root.leftChild == null && root.rightChild == null) {root = null; return;}
			else if(root.leftChild == null && root.rightChild != null) {root = root.rightChild; root.parent = null; return;}
			else if(root.leftChild != null && root.rightChild == null) { root = root.leftChild; root.parent = null; return;}
			else {
				parent = root;
				curr = root.leftChild;
				while(curr.rightChild!= null) {parent= curr; curr = curr.rightChild;}
				if(parent == root) {root.artist = curr.artist; root.title = root.title; root.leftChild = curr.leftChild; return;}
				else {parent.rightChild = curr.leftChild; root.artist = curr.artist; root.title = curr.title; return;}
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
	
	public boolean checkIsEmpty() {
		
		if(root == null) return false;
		else return true;
	}
	
	public String[] findSong(String songName, int order, String[] songDetail) {
		
		Song_Tree curr = root;
		while(curr != null) {
			if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) == 0 && curr.order == order) {
				songDetail[0] = curr.title;
				songDetail[1] = curr.artist;
				songDetail[2] = curr.album;
				songDetail[3] = curr.year;
				break;
			}
			else if(curr.title.toLowerCase().compareTo(songName.toLowerCase()) > 0) 
				curr = curr.leftChild;
			else
				curr = curr.rightChild;
		}	
		return songDetail;
	}
	public int findSongOrder(String songName, String artistName) {
		
		Song_Tree curr = root;
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
	public void getSongs(Song_Tree root, ArrayList<String> newSongs) {
		
		if(root != null) {
			getSongs(root.leftChild, newSongs);
			newSongs.add(root.title);
			root.order = count;
			count++;
			getSongs(root.rightChild, newSongs);
		}
	}
	public void printSongs(Song_Tree root) {
		
		if(root != null) {
			printSongs(root.leftChild);
			System.out.println(root.title + " " + root.artist + " " + root.album + " " + root.year);
			printSongs(root.rightChild);
		}
	}
}