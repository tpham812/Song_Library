/**
 * @author Truong Pham
 * 
 * This class is the handles events for the JButtons in the GUI
 */
package MusicLibrary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonListener implements ActionListener {

	/** Reference to song library GUI */
	SongLibrary_GUI gui;	
	/** Holds the list model */
	DeFaultListModel model;
	/** To check if add or edit button has previously been clicked */
	boolean add, edit;
	
	/**
	 * Constructor.
	 * @param gui Reference to the song library GUI.
	 */
	public ButtonListener(SongLibrary_GUI gui) {
		this.gui = gui;
		model = new DeFaultListModel();
		add = false;
		edit = false;
	}
	
	/**
	 * This method handles events for when a JButton is clicked in the GUI.
	 */
	public void actionPerformed(ActionEvent e) {
			
			/** Handles the event when the add button has been clicked */
			if(e.getSource() == gui.button[0]) {
				add = true;
				gui.textArea[0].setText("");
				for(int order = 0; order < 3; order ++)
					gui.button[order].setEnabled(false);
				for(int order = 0; order < 4; order++) {
					gui.tf[order].setEnabled(true);
					gui.tf[order].setEditable(true);
				}
				for(int order = 3; order < 5; order++) 
					gui.button[order].setEnabled(true);
			}
			
			/** Handles the event when the delete button has been clicked */
			else if(e.getSource() == gui.button[1]) {
				gui.textArea[0].setText("");
				if(gui.songTree.root == null)
					gui.textArea[0].setText("There are no songs to delete. You must have at least one song in the library to delete.");
				else {
					ArrayList<String> newSongs = new ArrayList<String>();
					String[] tempArray = new String[1];
					String[] songDetail = new String[4];
					int index = gui.list.getSelectedIndex();
					songDetail = gui.songTree.findSong(gui.model.get(gui.list.getSelectedIndex()), gui.list.getSelectedIndex());
					gui.list.clearSelection();
					gui.songTree.deleteFromTree(songDetail[0], songDetail[1]);
					gui.songTree.order = 0;
					gui.songTree.getSongs(gui.songTree.root, newSongs);
					model.newList(gui.model, newSongs.toArray(tempArray));
					if(gui.songTree.root!= null && index != 0)
						gui.list.setSelectedIndex(index-1);
					else if(gui.songTree.root!= null)
						gui.list.setSelectedIndex(0);
				}
			}
			
			/** Handles the event when the edit button has been clicked */
			else if(e.getSource() == gui.button[2]) {
				gui.textArea[0].setText("");
				if(gui.songTree.root == null)
					gui.textArea[0].setText("There are no songs to edit. You must have at least one song in the library to edit.");
				else{
					edit = true;
					for(int order = 0; order < 3; order++)
						gui.button[order].setEnabled(false);
					for(int order = 0; order < 4; order++) {
						gui.tf[order].setEnabled(true);
						gui.tf[order].setEditable(true);
					}
					for(int order = 3; order < 5; order++) 
						gui.button[order].setEnabled(true);
					String[] songDetail = new String[4];
					songDetail = gui.songTree.findSong(gui.model.get(gui.list.getSelectedIndex()), gui.list.getSelectedIndex());
					for(int order = 0; order < 4; order ++) 
						gui.tf[order].setText(songDetail[order]);
				}
			}
			
			/** Handles the event when the apply button has been clicked */
			else if(e.getSource() == gui.button[3]) {
				/** Handles the event when edit button has previously been clicked */
				if(edit) {
					gui.textArea[0].setText("");
					String[] songDetail = new String[4];
					songDetail = gui.songTree.findSong(gui.model.get(gui.list.getSelectedIndex()), gui.list.getSelectedIndex());
					gui.songTree.deleteFromTree(songDetail[0], songDetail[1]);
					if(!gui.songTree.addToTree(gui.tf[0].getText().trim(), gui.tf[1].getText().trim(), gui.tf[2].getText().trim(), gui.tf[3].getText().trim()))
						gui.textArea[0].setText("Song already exist in the library. Please enter a new song.");
					else {
						ArrayList<String> newSongs = new ArrayList<String>();
						String[] tempArray = new String[1];
						gui.songTree.order = 0;
						gui.songTree.getSongs(gui.songTree.root, newSongs);
						model.newList(gui.model, newSongs.toArray(tempArray)); 
						int order = gui.songTree.findSongOrder(gui.tf[0].getText().trim(), gui.tf[1].getText().trim());
						gui.list.setEnabled(true);
						gui.list.setSelectedIndex(order);
						System.out.println();
					}
					for(int order = 0; order < gui.tf.length; order ++) 
						gui.tf[order].setText("");
					edit = false;
				}
				/** Handles the event when add button has previously been clicked */
				else if(add) {
					gui.textArea[0].setText(null);
					if(gui.tf[0].getText().trim().isEmpty() && gui.tf[1].getText().trim().isEmpty())
						gui.textArea[0].append("You cannot enter in nothing to the library. You must enter in a song.\nPlease enter in \"Song title\" and \"Artist\" before pressing \"Apply\"");
					else if(gui.tf[0].getText().trim().isEmpty() || gui.tf[1].getText().trim().isEmpty())
						gui.textArea[0].append("You must enter in both \"Song Title\" and \"Artist\"");
					else {
						ArrayList<String> newSongs = new ArrayList<String>();
						String[] tempArray = new String[1];
						if(!gui.songTree.addToTree(gui.tf[0].getText().trim(), gui.tf[1].getText().trim(), gui.tf[2].getText().trim(), gui.tf[3].getText().trim()))
							gui.textArea[0].setText("Song already exist in the library. Please enter a new song.");
						else {
							gui.songTree.order = 0;
							gui.songTree.getSongs(gui.songTree.root, newSongs);
							model.newList(gui.model, newSongs.toArray(tempArray)); 
							int order = gui.songTree.findSongOrder(gui.tf[0].getText().trim(), gui.tf[1].getText().trim());
							gui.list.setEnabled(true);
							gui.list.setSelectedIndex(order);
						}
						
					}
					/** Reset the text fields */
					for(int order = 0; order < gui.tf.length; order ++) 
						gui.tf[order].setText("");
					add = false;
				}
				/** Reset the text fields */
				for(int order = 0; order < 4; order++) {
					gui.tf[order].setEnabled(false);
					gui.tf[order].setEditable(false);
				}  
				/** Reset the buttons */
				for(int order = 3; order < 5; order++) 
					gui.button[order].setEnabled(false);
				for(int order = 0; order < 3; order ++)
					gui.button[order].setEnabled(true);
			}
			
			/** Handles the event when the cancel button has been clicked. */
			else if(e.getSource() == gui.button[4]) {
				for(int order = 0; order < 4; order++) {
					gui.tf[order].setEnabled(false);
					gui.tf[order].setEditable(false);
				}
				for(int order = 3; order < 5; order++) 
					gui.button[order].setEnabled(false);
				for(int order = 0; order < 3; order ++)
					gui.button[order].setEnabled(true);
				for(int order = 0; order < 4; order++) 
					gui.tf[order].setText("");
			}
		}
}	