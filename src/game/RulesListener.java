package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A szabályok kérését figyelő listener.
 */
public class RulesListener implements ActionListener {
	JFrame f;
	
	/**
	 * @param frame		a hozzá tarotzó ablak
	 */
	public RulesListener(JFrame frame) {
		f = frame;
	}

	/**
	 * Ha kérik a játékszabályt, beolvassa és megjeleníti a szabályokat.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		File fajl = new File("jatekszabaly.txt");
		Scanner sc;
		String szabaly = "";
		try {
			sc = new Scanner(fajl, "UTF-8");
			while(sc.hasNextLine()) {
				String s = sc.nextLine();
				szabaly += s + '\n';
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		Object[] options = {"OK"};
		JOptionPane.showOptionDialog(f,
		szabaly,
		"Játékszabály",
		JOptionPane.OK_OPTION,
		JOptionPane.QUESTION_MESSAGE,
		null,     //do not use a custom Icon
		options,  //the titles of buttons
		options[0]); //default button title
	}
		
}
