package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

/**
 * A táblában való kattintást figyeli.
 */
public class TableListener implements MouseListener {
	JTable t;
	BoardData data;
	
	/**
	 * @param jt	melyik táblázat tartozik hozzá
	 * @param d		melyik modell tartozik hozzá
	 */
	public TableListener(JTable jt, BoardData d) {
		t = jt;
		data = d;
	}

	/**
	 * Ha klikkelnek egy cellára, meghívja az adott mező switchLamp metódusát.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = t.rowAtPoint(e.getPoint());
		int col = t.columnAtPoint(e.getPoint());
		if (row >= 0 && col >= 0 && !data.getBoard().getReady())
			data.switchLamp(t.rowAtPoint(e.getPoint()), t.columnAtPoint(e.getPoint()));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
