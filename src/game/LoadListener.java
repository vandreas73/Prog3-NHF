package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;


/**
 * A pálya visszatöltésének kérését figyelő listener. 
 */
public class LoadListener implements ActionListener{
	JButton button;
	AkariFrame frame;
	BoardData data;
	
	/**
	 * Konstruktor
	 * @param b		melyik gomb nyomását figyelje
	 * @param f		melyik frame-hez tartozik
	 * @param d		melyik modellhez tartozik
	 */
	public LoadListener(JButton b, AkariFrame f, BoardData d) {
		button = b;
		frame = f;
		data = d;
	}

	/**
	 * Ha megnyomják a gombot, kérdezze meg melyik fájlt szeretné visszatölteni.
	 * Majd indítsa el az új ablak felépítését, a régit pedig tüntesse el.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		final JFileChooser fc = new JFileChooser(System.getProperty("user.dir")+"/mentesek");
	        int returnVal = fc.showOpenDialog(button);
	        fc.setCurrentDirectory(new File(System.getProperty("user.home")));
	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fc.getSelectedFile();
	            AkariFrame f = new AkariFrame();
				f.createBoard(selectedFile.getAbsolutePath(), true);
				frame.setVisible(false);
				f.setVisible(true);
	        }
	}

}
