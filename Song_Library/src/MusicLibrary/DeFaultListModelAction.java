//Truong Pham
package MusicLibrary;

import javax.swing.DefaultListModel;

public class DeFaultListModelAction {

	public void newList(DefaultListModel<String> model, String[] songs) {
		
		model.clear();
		for(int count = 0; count < songs.length; count++) {
			model.addElement(songs[count]);
		}
	}
}