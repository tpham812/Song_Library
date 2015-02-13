//Truong Pham
package MusicLibrary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonListener implements ActionListener {

	Song_Interface inter;	DeFaultListModelAction model;
	boolean add, edit;
	
	public ButtonListener(Song_Interface inter) {
		this.inter = inter;
		model = new DeFaultListModelAction();
		add = false;
		edit = false;
	}
	
	public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == inter.button[0]) {
				add = true;
				inter.textArea[0].setText("");
				for(int count = 0; count < 3; count ++)
					inter.button[count].setEnabled(false);
				for(int count = 0; count < 4; count++) {
					inter.tf[count].setEnabled(true);
					inter.tf[count].setEditable(true);
				}
				for(int count = 3; count < 5; count++) 
					inter.button[count].setEnabled(true);
			}
			
			else if(e.getSource() == inter.button[1]) {
				inter.action.printSongs(inter.action.root);
				inter.textArea[0].setText("");
				if(inter.action.root == null)
					inter.textArea[0].setText("There are no songs to delete. You must have at least one song in the library to delete.");
				else {
					ArrayList<String> newSongs = new ArrayList<String>();
					String[] tempArray = new String[1];
					String[] songDetail = new String[4];
					int index = inter.list.getSelectedIndex();
					songDetail = inter.action.findSong(inter.model.get(inter.list.getSelectedIndex()), inter.list.getSelectedIndex(), songDetail);
					inter.list.clearSelection();
					System.out.println(songDetail[0] + " " + songDetail[1]);
					inter.action.deleteFromTree(songDetail[0], songDetail[1]);
					inter.action.count = 0;
					inter.action.getSongs(inter.action.root, newSongs);
					model.newList(inter.model, newSongs.toArray(tempArray));
					if(inter.action.root!= null && index != 0)
						inter.list.setSelectedIndex(index-1);
					else if(inter.action.root!= null)
						inter.list.setSelectedIndex(0);
				}
			}
			
			else if(e.getSource() == inter.button[2]) {
				inter.textArea[0].setText("");
				if(inter.action.root == null)
					inter.textArea[0].setText("There are no songs to edit. You must have at least one song in the library to edit.");
				else{
					edit = true;
					for(int count = 0; count < 3; count++)
						inter.button[count].setEnabled(false);
					for(int count = 0; count < 4; count++) {
						inter.tf[count].setEnabled(true);
						inter.tf[count].setEditable(true);
					}
					for(int count = 3; count < 5; count++) 
						inter.button[count].setEnabled(true);
					String[] songDetail = new String[4];
					songDetail = inter.action.findSong(inter.model.get(inter.list.getSelectedIndex()), inter.list.getSelectedIndex(), songDetail);
					for(int count = 0; count < 4; count ++) 
						inter.tf[count].setText(songDetail[count]);
				}
			}
			
			else if(e.getSource() == inter.button[3]) {

				if(edit) {
					inter.textArea[0].setText("");
					String[] songDetail = new String[4];
					songDetail = inter.action.findSong(inter.model.get(inter.list.getSelectedIndex()), inter.list.getSelectedIndex(), songDetail);
					inter.action.deleteFromTree(songDetail[0], songDetail[1]);
					if(!inter.action.addToTree(inter.tf[0].getText().trim(), inter.tf[1].getText().trim(), inter.tf[2].getText().trim(), inter.tf[3].getText().trim()))
						inter.textArea[0].setText("Song already exist in the library. Please enter a new song.");
					else {
						ArrayList<String> newSongs = new ArrayList<String>();
						String[] tempArray = new String[1];
						inter.action.count = 0;
						inter.action.getSongs(inter.action.root, newSongs);
						model.newList(inter.model, newSongs.toArray(tempArray)); 
						int order = inter.action.findSongOrder(inter.tf[0].getText().trim(), inter.tf[1].getText().trim());
						inter.list.setEnabled(true);
						inter.list.setSelectedIndex(order);
						System.out.println();
					}
					for(int count = 0; count < inter.tf.length; count ++) 
						inter.tf[count].setText("");
					edit = false;
				}
				else if(add) {
					inter.textArea[0].setText(null);
					if(inter.tf[0].getText().trim().isEmpty() && inter.tf[1].getText().trim().isEmpty())
						inter.textArea[0].append("You cannot enter in nothing to the library. You must enter in a song.\nPlease enter in \"Song title\" and \"Artist\" before pressing \"Apply\"");
					else if(inter.tf[0].getText().trim().isEmpty() || inter.tf[1].getText().trim().isEmpty())
						inter.textArea[0].append("You must enter in both \"Song Title\" and \"Artist\"");
					else {
						ArrayList<String> newSongs = new ArrayList<String>();
						String[] tempArray = new String[1];
						if(!inter.action.addToTree(inter.tf[0].getText().trim(), inter.tf[1].getText().trim(), inter.tf[2].getText().trim(), inter.tf[3].getText().trim()))
							inter.textArea[0].setText("Song already exist in the library. Please enter a new song.");
						else {
							inter.action.count = 0;
							inter.action.getSongs(inter.action.root, newSongs);
							model.newList(inter.model, newSongs.toArray(tempArray)); 
							int order = inter.action.findSongOrder(inter.tf[0].getText().trim(), inter.tf[1].getText().trim());
							inter.list.setEnabled(true);
							inter.list.setSelectedIndex(order);
						}
						
					}
					for(int count = 0; count < inter.tf.length; count ++) 
						inter.tf[count].setText("");
					add = false;
				}
				for(int count = 0; count < 4; count++) {
					inter.tf[count].setEnabled(false);
					inter.tf[count].setEditable(false);
				}  
				for(int count = 3; count < 5; count++) 
					inter.button[count].setEnabled(false);
				for(int count = 0; count < 3; count ++)
					inter.button[count].setEnabled(true);
			}
			
			else if(e.getSource() == inter.button[4]) {
				for(int count = 0; count < 4; count++) {
					inter.tf[count].setEnabled(false);
					inter.tf[count].setEditable(false);
				}
				for(int count = 3; count < 5; count++) 
					inter.button[count].setEnabled(false);
				for(int count = 0; count < 3; count ++)
					inter.button[count].setEnabled(true);
				for(int count = 0; count < 4; count++) 
					inter.tf[count].setText("");
			}
		}
}	