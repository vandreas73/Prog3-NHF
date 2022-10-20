package game;

import java.io.Serializable;


/**
 * A Mezo osztály a különböző mezők absztrakt ősosztálya.
 */
public abstract class Mezo implements Serializable {
	private static final long serialVersionUID = 1L;

	
	/**
	 * Itt nem csinál semmit.
	 */
	public void switchLamp() {
		;
	}
	
	/**
	 * Visszaadja a tárolt értéket.
	 * @return itt egy üres String
	 */
	public Object getValue() {
		return "";
	}
	
	/**
	 * Absztrakt metódus.
	 * @return Visszaadja milyen típusú a "világítása" (-1: fal, 0: üres, 1: lámpa)
	 */
	public abstract int lighting();
	
	
	/**
	 * Megvizsgálja, hogy a szomszédos lámpák száma megfelelő-e.
	 * @param board	az a pálya, amin a mező van
	 * @param row	a mező sorának száma
	 * @param col	a mező oszlopának száma
	 * @return		Itt mindig igaz.
	 */
	public boolean neighbourLampsCheck(Board board, int row, int col) {
		return true;
	}
	
}
