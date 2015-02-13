//Truong Pham
package MusicLibrary;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListListener implements ListSelectionListener {

	Song_Interface inter;
	
	public ListListener(Song_Interface inter) {
		this.inter = inter;
	}
	
	public void valueChanged(ListSelectionEvent e) {
		
		if(!inter.list.isSelectionEmpty()) {
			if(!inter.list.getValueIsAdjusting()) {
				
				for(int count = 0; count < 3; count++)
					inter.button[count].setEnabled(true);
				for(int count = 3; count < 5; count++) 
					inter.button[count].setEnabled(false);
				for(int count = 0; count < 4; count++) {
					inter.tf[count].setText("");
					inter.tf[count].setEnabled(false);
				}
				inter.textArea[1].setText(null);
				String[] newSong = new String[4];
				newSong = inter.action.findSong(inter.model.get(inter.list.getSelectedIndex()), inter.list.getSelectedIndex(), newSong);
				inter.textArea[1].append("Song Name: \t" + newSong[0] +"\nArtist: \t" + newSong[1] + "\nAlbum: \t" + newSong[2] +"\nYear: \t" + newSong[3]);
			}
		}
		else {
			if(inter.action.root == null)
				inter.list.setEnabled(false);
			inter.textArea[1].setText(null);
		}
	}
}