package game;

/**
 * Olyan mezőt reprezentáló osztály, amelyre el lehet helyezni lámpát.
 *
 */
public class FeherMezo extends Mezo {
	private static final long serialVersionUID = 1L;
	/**
	 * Van-e lámpa a mezőn
	 */
	private boolean lamp = false;
	/**
	 * Melyik táblához tartozik a mező
	 */
	Board board;
	
	/**
	 * Konstruktor
	 * @param b	amelyik táblához tartozik a mező
	 */
	public FeherMezo(Board b) {
		board = b;
	}
	
	/**
	 * Ha van lámpa, leveszi; ha nincs, rárak.
	 */
	@Override
	public void switchLamp() {
		lamp = !lamp;
	}
	
	
	/**
	 * Visszaadja a világítási típusát.
	 * @return	0: üres, 1: lámpa
	 */
	@Override
	public int lighting() {
		if (lamp) 
			return 1;
		return 0;
	}

}
