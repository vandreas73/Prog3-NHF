package game;

/**
 * Falat reprezentáló mező osztály.
 *
 */
public class FalMezo extends Mezo {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Visszaadja, hogy a világítás típusát tekintve egy fal.
	 * @return -1
	 */
	public int lighting() {
		return -1;		//falat jelent
	}
	
	

}
