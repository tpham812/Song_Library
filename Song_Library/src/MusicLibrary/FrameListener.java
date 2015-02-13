//Truong Pham
package MusicLibrary;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FrameListener implements WindowListener {

	PrintWriter print;
	Song_Interface inter;
	
	public FrameListener(Song_Interface inter) {
		
		this.inter = inter;
	}

	public void appendToFile(Song_Tree root) {
		
		if(root != null) {
			appendToFile(root.leftChild);
			print.println(root.title + "| " + root.artist + "| " + root.album + "| " + root.year);
			appendToFile(root.rightChild);
		}
	}
	
	public void windowClosing(WindowEvent e) {
		
		File myFile = new File("Library.text");
		if(inter.action.root != null) {
			try {
				print = new PrintWriter("Library.text");
			}
			catch (FileNotFoundException e1) {
 				e1.printStackTrace();
			}
			appendToFile(inter.action.root);
			print.close();
		}	
		else if(inter.action.root == null && myFile.exists())
			myFile.delete();
	}
	
	public void windowActivated(WindowEvent e) {}
	public void windowClosed(WindowEvent e) {}
	public void windowDeactivated(WindowEvent e) {}
	public void windowDeiconified(WindowEvent e) {}
	public void windowIconified(WindowEvent e) {}
	public void windowOpened(WindowEvent e) {}
}