//Truong Pham
package MusicLibrary;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.event.ListSelectionListener;

public class Song_Interface {

	DefaultListModel<String> model;							JTextArea textArea[];
	JButton button[]; 										JList<DefaultListModel<String>> list; 
	JPanel panel, panel1, panel2, panel3, panel4; 			JScrollPane scroll;
	JTextField tf[];			 							JLabel label[];
	ActionListener buttonListener;							ListSelectionListener listListener;
	Song_Action action;
	
	public Song_Interface() throws IOException {
		
		action = new Song_Action();
		buttonListener = new ButtonListener(this);
		listListener = new ListListener(this);
		model = new DefaultListModel<String>();
		if(!getExistingLibrary()) 
			list = new JList(model);
		else {
			ArrayList<String> songs = new ArrayList<String>();
			action.count = 0;
			action.getSongs(action.root, songs);
			String[] songArray = new String[1];
			songArray = songs.toArray(songArray);
			DeFaultListModelAction modelAction = new DeFaultListModelAction();
			modelAction.newList(model, songArray);
			list = new JList(model);
		}
		list.addListSelectionListener(listListener);
		scroll = new JScrollPane(list);
		scroll.setMaximumSize(new Dimension (800, 300));
		scroll.setMinimumSize(new Dimension(800, 300));
		
		button = new JButton[5];
		button[0] = new JButton("Add");
		button[1] = new JButton("Delete");
		button[2] = new JButton("Edit");
		panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		for(int count = 0; count < 3; count++) {
			panel1.add(button[count]);
			button[count].addActionListener(buttonListener);
			panel1.add(Box.createRigidArea(new Dimension(15,0)));
		}
		
		label = new JLabel[4];
		label[0] = new JLabel("Song Title");
		label[1] = new JLabel("Artist");
		label[2] = new JLabel("Album");
		label[3] = new JLabel("Year");
		panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
		panel2.add(Box.createRigidArea(new Dimension(70,0)));
		panel2.add(label[0]);
		panel2.add(Box.createRigidArea(new Dimension(85,0)));
		panel2.add(label[1]);
		panel2.add(Box.createRigidArea(new Dimension(95,0)));
		panel2.add(label[2]);
		panel2.add(Box.createRigidArea(new Dimension(95,0)));
		panel2.add(label[3]);
		panel2.add(Box.createRigidArea(new Dimension(95,0)));
		
		tf = new JTextField[4];
		for(int count = 0; count < 4; count++) {
			tf[count] = new JTextField();
			tf[count].setEditable(false);
			tf[count].setEnabled(false);
			tf[count].setMaximumSize(new Dimension(125,20));
		}
		panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		for(int count = 0; count < 4; count++) {
			panel3.add(tf[count]);
			panel3.add(Box.createRigidArea(new Dimension(5,0)));
		}
	
		button[3] = new JButton("Apply");
		button[3].setEnabled(false);
		button[4] = new JButton("Cancel");
		button[4].setEnabled(false);
		panel4 = new JPanel();
		panel4.setLayout(new BoxLayout(panel4, BoxLayout.X_AXIS));
		panel4.add(Box.createRigidArea(new Dimension(12,0)));
		for(int count = 3; count < 5; count++) {
			panel4.add(button[count]);
			button[count].addActionListener(buttonListener);
			panel4.add(Box.createRigidArea(new Dimension(10,0)));
		}
		textArea = new JTextArea[2];
		for(int count = 0; count < 2; count++) {
			textArea[count] = new JTextArea(5,65);
			textArea[count].setEditable(false);
		}
		textArea[0].setMaximumSize(new Dimension(600, 65));
		textArea[1].setMinimumSize(new Dimension(600, 65));
		textArea[1].setMaximumSize(new Dimension(600,65));
		textArea[1].setMinimumSize(new Dimension(600,65));
		
		if(action.root != null) 
			list.setSelectedIndex(0);
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(scroll);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(panel1);
		panel.add(Box.createRigidArea(new Dimension(0,40)));
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(Box.createRigidArea(new Dimension(0,45)));
		panel.add(textArea[0]);
		panel.add(textArea[1]);
	}
	public boolean getExistingLibrary() throws IOException {
		
		String[] song = new String[4];
		StringTokenizer tk; 
		int count;
		
		File myFile = new File("Library.text");
		if(!myFile.exists())
			return false;
		else {
			Scanner sc = new Scanner(myFile);
			for(count = 0; count < 4; count ++)
				song[count] = null;
			while(sc.hasNext()) {
				count = 0;
				tk = new StringTokenizer(sc.nextLine(),"|");
				while(tk.hasMoreTokens()) {
					song[count] = tk.nextToken().trim();
					count++;
				}
				action.addToTree(song[0].trim(), song[1].trim(), song[2].trim(), song[3].trim());
			}
			sc.close();
		}
		return true;
	}
}