package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;

/**
 * A pálya elmentésének kérését figyelő listener.
 */
public class SaveListener implements ActionListener{
	JButton button;
	BoardData data;
	
	/**
	 * @param b		melyik gombot figyeli
	 * @param d		melyik modell tartozik hozzá
	 */
	public SaveListener(JButton b, BoardData d) {
		button = b;
		data = d;
	}

	/**
	 * Ha megnyomták a gombot, elmenti a pályát.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		final JFileChooser fc = new JFileChooser(System.getProperty("user.dir")+"/mentesek");
		
        int returnVal = fc.showSaveDialog(button);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            data.getBoard().serialize(file.getPath());
        }
	}

}
