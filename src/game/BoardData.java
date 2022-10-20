package game;

import javax.swing.table.AbstractTableModel;

/**
 * Az AkariFrame-ben megjelenő adatok kezeléséért felelős osztály (modell).
 */
public class BoardData extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	/**
	 * A modellben megjelenő adatokat tároló pálya
	 */
	Board board = new Board();
	/**
	 * A modellhez tartozó ablak
	 */
	AkariFrame frame;
	
	/**
	 * Konstruktor
	 * @param f		az AkariFrame, amihez a BoardData tartozzon
	 */
	public BoardData(AkariFrame f) {
		frame = f;
	}

	@Override
	public int getRowCount() {
		return board.rowCount();
	}

	@Override
	public int getColumnCount() {
		return board.columnCount();
	}
	

	/**
	 * Visszaadja az adott cellában megjelenítendő értéket.
	 * Ez lehet egy lámpa vagy a mező tartalma (szám vagy üres string).
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Mezo m = board.getMezoAt(rowIndex, columnIndex);
		if (m.lighting() >= 1) {
			return "💡";
		}
		return m.getValue();
	}
	
	/**
	 * Visszaadja a megadott mezőt.
	 * @param row	sor indexe
	 * @param col	oszlop indexe
	 * @return		kért mező
	 */
	public Mezo getMezoAt(int row, int col) {
		return board.getMezoAt(row, col);
	}
	
	
	/**
	 * Váltja a lámpa állását (levesz/rárak).
	 * @param rowIndex		sor indexe
	 * @param columnIndex	oszlop indexe
	 */
	public void switchLamp(int rowIndex, int columnIndex) {
		if (board.switchLamp(rowIndex, columnIndex)) {
			frame.ready();
		};
		super.fireTableDataChanged();
	}
	
	 /**
	 * A pályát (táblázatot) kattintásokkal módosítjuk, szöveg bevitellel nem.
	 */
	@Override
	 public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	
	/**
	 * A megjelenítendő adatokat stringként ábrázoljuk.
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}
  	
  	
  	/**
  	 * Visszaállítja kezdőállapotára a pályát, azaz eltávolít minden lámpát róla.
  	 */
  	public void restart() {
  		board.restart();
  		super.fireTableDataChanged();
  	}
  	
  	/**
  	 * @return	a hozzá tartozó pályát
  	 */
  	public Board getBoard() {
  		return board;
  	}
}
