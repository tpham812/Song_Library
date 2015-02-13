//Truong Pham
package MusicLibrary;

import javax.swing.JFrame;

import java.io.*;

import java.awt.Dimension;

public class SongLibrary {

	public static void main(String[] args) throws IOException{
		
		Song_Interface si = new Song_Interface();
		JFrame frame = new JFrame("Music Library");
		frame.add(si.panel);
		frame.setSize(950,675);
		frame.setMinimumSize(new Dimension(850, 660));
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FrameListener fl = new FrameListener(si);
		frame.addWindowListener(fl);
	}
}
