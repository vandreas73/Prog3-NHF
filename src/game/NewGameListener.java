package game;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Az új játék indítsának kérését figyelő listener.
 */
public class NewGameListener implements ActionListener {
	AkariFrame frame;
	
	/**
	 * Konstruktor
	 * @param f		amelyik ablakhoz tartozik
	 */
	public NewGameListener(AkariFrame f) {
		frame = f;
	}

	/**
	 * A gomb megnyomása esetén megkérdezi milyen méretű és bonyolultságú pályán
	 * szeretnénk játszani.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFrame uj = new JFrame();
		JLabel label = new JLabel("Milyen pályát kérsz?");
		String[] sizes = {"5x5", "7x7", "10x10"};
		JComboBox<String> size = new JComboBox<String>(sizes);
		String[] difficulities = {"Könnyű", "Közepes", "Nehéz"};
		JComboBox<String> difficulity = new JComboBox<String>(difficulities);
		JButton go = new JButton("Mehet");
		JPanel p1 = new JPanel();
		p1.add(label);
		JPanel p2 = new JPanel();
		uj.setLayout(new BorderLayout());
		uj.add(p1, BorderLayout.NORTH);
		p2.add(size);
		p2.add(difficulity);
		p2.add(go);
		go.addActionListener(new NewGameGoListener(size, difficulity, frame, uj));
		uj.add(p2, BorderLayout.CENTER);
		uj.pack();
		uj.setVisible(true);
		uj.setLocationRelativeTo(null);
		
	}
	
	
	/**
	 * Az új pályát kérő ablakban a Mehet feliratú gombot figyeli.
	 */
	public class NewGameGoListener implements ActionListener {
		JComboBox<String> size;
		JComboBox<String> difficulity;
		AkariFrame frame;
		JFrame menu;
		
		/**
		 * Konstruktor
		 * @param s				méret JComboBoxa
		 * @param diff			bonyolultság JComboBoxa
		 * @param f				a korábbi AkariFrame
		 * @param menuFrame		a menü ablaka
		 */
		public NewGameGoListener(JComboBox<String> s, JComboBox<String> diff, AkariFrame f, JFrame menuFrame) {
			size = s;
			difficulity = diff;
			frame = f;
			menu = menuFrame;
		}

		/**
		 * Ha megnyomták, hogy mehet, létrehozza az új pályát, eltünteti a régi ablakokat.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			AkariFrame f = new AkariFrame();
			f.createBoard("palyak/" + (String)size.getSelectedItem()+"."+(difficulity.getSelectedIndex()+1)+".dat", false);
			frame.setVisible(false);
			menu.setVisible(false);
			f.setVisible(true);
		}
		
	}
	
}
