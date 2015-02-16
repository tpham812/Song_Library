/**
 * @author Truong Pham
 * 
 * This class is the event listener for the frame.
 */
package MusicLibrary;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FrameListener implements WindowListener {

	PrintWriter print;
	SongLibrary_GUI gui;
	
	/**
	 * Constructor of the class
	 * @param gui 
	 */
	public FrameListener(SongLibrary_GUI gui) {
		
		this.gui = gui;
	}

	/**
	 * This method writes all of the songs and its information into the file "Library.text". 
	 * @param root Root of the binary tree holding each songs.
	 */
	public void appendToFile(Song root) {
		
		if(root != null) {
			appendToFile(root.leftChild);
			print.println(root.title + "| " + root.artist + "| " + root.album + "| " + root.year);
			appendToFile(root.rightChild);
		}
	}
	
	/**
	 * Event handler for when the frame is closing.
	 */
	public void windowClosing(WindowEvent e) {
		
		File myFile = new File("Library.text");
		if(gui.songTree.root != null) {
			try {
				print = new PrintWriter("Library.text");
			}
			catch (FileNotFoundException e1) {
 				e1.printStackTrace();
			}
			appendToFile(gui.songTree.root);
			print.close();
		}	
		else if(gui.songTree.root == null && myFile.exists())
			myFile.delete();
	}
	
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
}