package game;

/**
 * Olyan falat reprezentáló mező, amelynek van egy száma is.
 */
public class FalMezoSzammal extends FalMezo {
	private static final long serialVersionUID = 1L;
	/**
	 * Ez azt jelenti, hogy mennyi lámpának kell mellette lennie.
	 */
	private int n;
	
	/**
	 * Konstruktor, amely egy számot kap.
	 * @param i	mennyi lámpa kell mellette legyen
	 */
	public FalMezoSzammal(int i) {
		n = i;
	}
	
	/**
	 * @return	viszaadja mennyi lámpának kell mellette lennie
	 */
	@Override
	public Object getValue() {
		return n;
	}
	
	
	/**
	 * @return helyes-e a szomszédos lámpaszám
	 */
	 @Override
	public boolean neighbourLampsCheck(Board board, int row, int col) {
		return (n == board.countNeighbourLamps(row, col));
	}
}
