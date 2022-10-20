package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * Az újrakezdést figyelő listener.
 *
 */
public class RestartListener implements ActionListener{
	BoardData data;
	/**
	 * A feladat megoldásakor kiírandó dicséret panele.
	 */
	JPanel ugyes;
	
	/**
	 * @param d		a hozzá tartozó modell
	 * @param p		a feladat megoldásakor kiírandó dicséret panele.
	 */
	public RestartListener(BoardData d, JPanel p) {
		data = d;
		ugyes = p;
	}

	/**
	 * Ha megnyomják az újrakezdés gombot, visszaállítja a pályát a kezdeti formára.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ugyes.setVisible(false);
		data.restart();
		
	}

}
