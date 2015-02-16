/**
 * @author Truong Pham
 * 
 * This class is the main class for song library.
 */
package MusicLibrary;

import javax.swing.JFrame;

import java.io.*;

import java.awt.Dimension;

public class SongLibrary {

	public static void main(String[] args) throws IOException{
		
		/** Create the GUI */
		SongLibrary_GUI sl = new SongLibrary_GUI();
		/** Create the frame */
		JFrame frame = new JFrame("Music Library");
		/** Add components and set preferences to frame */
		frame.add(sl.panel);
		frame.setSize(950,675);
		frame.setMinimumSize(new Dimension(850, 660));
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FrameListener fl = new FrameListener(sl);
		frame.addWindowListener(fl);
	}
}
