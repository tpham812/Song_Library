/**
 * @author Truong Pham
 * 
 * This class creates takes in elements and stores in into the list model.
 */
package MusicLibrary;

import javax.swing.DefaultListModel;

public class DeFaultListModel {

	/**
	 * This method takes in song names and stores in into the list model
	 * @param model 	List model	
	 * @param songs		List of songs to put into the list model
	 */
	public void newList(DefaultListModel<String> model, String[] songs) {
		
		model.clear();
		for(int count = 0; count < songs.length; count++) {
			model.addElement(songs[count]);
		}
	}
}