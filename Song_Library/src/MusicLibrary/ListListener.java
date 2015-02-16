/**
 * @author Truong Pham
 * 
 * This class handles events for changes in the JList.
 */
package MusicLibrary;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListListener implements ListSelectionListener {

	SongLibrary_GUI gui;
	
	public ListListener(SongLibrary_GUI gui) {
		this.gui = gui;
	}
	
	/**
	 * This method displays song information in the text area whenever a selection has been selected.
	 */
	public void valueChanged(ListSelectionEvent e) {
		
		if(!gui.list.isSelectionEmpty()) {
			if(!gui.list.getValueIsAdjusting()) {	
				for(int count = 0; count < 3; count++)
					gui.button[count].setEnabled(true);
				for(int count = 3; count < 5; count++) 
					gui.button[count].setEnabled(false);
				for(int count = 0; count < 4; count++) {
					gui.tf[count].setText("");
					gui.tf[count].setEnabled(false);
				}
				gui.textArea[1].setText(null);
				String[] newSong = new String[4];
				newSong = gui.songTree.findSong(gui.model.get(gui.list.getSelectedIndex()), gui.list.getSelectedIndex());
				gui.textArea[1].append("Song Name: \t" + newSong[0] +"\nArtist: \t" + newSong[1] + "\nAlbum: \t" + newSong[2] +"\nYear: \t" + newSong[3]);
			}
		}
		else {
			if(gui.songTree.root == null)
				gui.list.setEnabled(false);
			gui.textArea[1].setText(null);
		}
	}
}