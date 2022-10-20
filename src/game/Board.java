package game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A pályát jelképező osztály. Ez bonyolítja le a játék soráni műveletek jelentős részét.
 *
 */
public class Board implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * A pálya mezőit tároló kétdimenziós lista
	 */
	ArrayList<ArrayList<Mezo>> board = new ArrayList<ArrayList<Mezo>>();
	/**
	 * Kész van-e a feladat
	 */
	boolean ready = false;

	
	/**
	 * @return hány sora van (az első sornak, így az egész pályának)
	 */
	public int rowCount() {
		return board.get(0).size();
	}
	
	/**
	 * @return hány oszlopa van a pályának
	 */
	public int columnCount() {
		return board.size();
	}
	
	/**
	 * Visszaadja a pálya megadott paraméterű mezőjét (0-tól indexelve)
	 * @param row	sor indexe
	 * @param col	oszlop indexe
	 * @return		a kért mező
	 */
	public Mezo getMezoAt(int row, int col) {
		return board.get(row).get(col);
	}
	
	
	/**
	 * Váltja a lámpa állását a kért mezőn.
	 * Megvizsgálja, hogy ezzel a lépéssel kész lett-e a feladvány, azaz teljesül-e az összes szabály.
	 * @param rowIndex		sor indexe
	 * @param columnIndex	oszlop indexe
	 * @return				kész van-e a feladat
	 */
	public boolean switchLamp(int rowIndex, int columnIndex) {
		board.get(rowIndex).get(columnIndex).switchLamp();
		if (isReady()) {
			ready = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Hozzáad egy sort a pályához.
	 * @param l	a sor, amit hozzá szeretnénk adni a pályához
	 */
	@SuppressWarnings("unchecked")
	private void addRow(ArrayList<Mezo> l) {
		board.add((ArrayList<Mezo>)(l.clone()));
    }
	
	
	/**
	 * Megvizsgálja, hogy meg van-e világítva a megadott cella.
	 * @param row	sor indexe
	 * @param col	oszlop indexe
	 * @return		meg van-e világítva
	 */
	public boolean isLighted(int row, int col) {
  		if (isLightedHorizontal(row, col, -1)) return true;	//-1 és nem 0, mert a 0 is index
  		if (isLightedHorizontal(row, col, columnCount())) return true;
  		if (isLightedVertical(row, -1, col)) return true;
  		if (isLightedVertical(row, rowCount(), col)) return true;
  		return false;
  	}
  	
	
  	/**
  	 * Megvizsgálja, hogy vízszintes irányban, a megadott oszlopok között világít-e a rá lámpa.
  	 * @param row		sor indexe
  	 * @param startCol	oszlop kezdőindexe
  	 * @param endCol	oszlop végindexe
  	 * @return			meg van-e világítva az adott intervallumból
  	 */
  	private boolean isLightedHorizontal(int row, int startCol, int endCol) {
  		int signum = endCol > startCol ? 1 : -1;
  		for (int c = startCol+signum; c != endCol; c += signum) {
  			int l = getMezoAt(row, c).lighting();
  			if (l >= 1) {	//világít
  				return true;
  			} else if (l == -1) {	//fal
  				return false;
  			}
  		}
  		return false;
  	}
  	
  	
  	/**
  	 * Megvizsgálja, hogy vízszintes irányban, a megadott oszlopok között világít-e a rá lámpa.
  	 * @param startRow	sor kezdőindexe
  	 * @param endRow	sor végindexe
  	 * @param col		oszlop indexe
  	 * @return			meg van-e világítva az adott intervallumból
  	 */
  	private boolean isLightedVertical(int startRow, int endRow, int col) {
  		int signum = endRow > startRow ? 1 : -1;
  		for (int r = startRow+signum; r != endRow; r += signum) {
  			int l = getMezoAt(r, col).lighting();
  			if (l >= 1) {	//világít
  				return true;
  			} else if (l == -1) {	//fal
  				return false;
  			}
  		}
  		return false;
  	}
	
  	/**
  	 * @return kész van-e a pálya
  	 */
  	public boolean getReady() {
  		return ready;
  	}
  	
  	
  	/**
  	 * Megvizsgálja, hogy a pálya jelenlegi állapota megfelel-e minden szabálynak.
  	 * @return kész van-e a feladat
  	 */
  	public boolean isReady() {
  		boolean b;
  		for (int r = 0; r < rowCount(); ++r) {
  			for (int c = 0; c < columnCount(); ++c) {
  				if (!getMezoAt(r, c).neighbourLampsCheck(this, r, c)) {
  					return false;
  				}
  					b = isLighted(r, c);				
  					if (getMezoAt(r, c).lighting() >= 1 && b) {
  						return false;
  					}
  					if (getMezoAt(r, c).lighting() == 0 && !b) {
  						return false;
  					}
  			}
  		}
  		return true;
  	}
  	
  	
  	/**
  	 * Megvizsgálja, hogy hány lámpa van a megadott mező szomszédságában (vízszintesen vagy függőlegesen).
  	 * @param r		sor indexe
  	 * @param c		oszlop indexe
  	 * @return		szomszédos lámpák száma
  	 */
  	public int countNeighbourLamps(int r, int c) {
  		int lamps = 0;
		if (c > 0 && getMezoAt(r, c-1).lighting() >= 1) lamps++;
		if (c < columnCount()-1 && getMezoAt(r, c+1).lighting() >= 1) lamps++;
		if (r > 0 && getMezoAt(r-1, c).lighting() >= 1) lamps++;
		if (r < rowCount()-1 && getMezoAt(r+1, c).lighting() >= 1) lamps++;
			return lamps;
  	}
  	
  	
  	/**
  	 * Fájlba írja a pályát.
  	 * @param filename	a mentendő fájl neve
  	 */
  	public void serialize(String filename) {
	  	try {
	        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename));
	        oos.writeObject(board);
	        oos.close();
	    } catch(Exception ex) {
	        ex.printStackTrace();
	    }
  	}
  	
  	
  	/**
  	 * Beolvassa a megadott fájlból a pályát.
  	 * @param filename		az olvasandó fájl neve
  	 * @param mentett		mentett fájlt kell visszaolvasnunk vagy új játékot kell beolvasni
  	 */
  	@SuppressWarnings("unchecked")
	public void deserialize(String filename, boolean mentett) {
  		try {
  			if(mentett) {
	  			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
	  			board = (ArrayList<ArrayList<Mezo>>)ois.readObject();
	  			ois.close();
  			} else {
	  			Scanner sc = new Scanner(new FileInputStream(filename));
	  			ArrayList<Mezo> l = new ArrayList<Mezo>();
	  			String[] inList;
	  			while (sc.hasNextLine()) {
	  				inList = sc.nextLine().split("");
	  				for (String s : inList) {
	  					switch(s) {
	  					case "-": l.add(new FeherMezo(this)); break;
	  					case "f": l.add(new FalMezo()); break;
	  					default: l.add(new FalMezoSzammal(Integer.parseInt(s)));
	  					}
	  				}
	  				addRow(l);
	  				l.clear();
	  			}
  			}
  		} catch(Exception ex) {
  			ex.printStackTrace();
  		}
  	}
  	
  	
  	/**
  	 * Visszaállítja kezdőállapotára a pályát, azaz eltávolít minden lámpát róla.
  	 */
  	public void restart() {
  		for (ArrayList<Mezo> sor : board) {
  			for (Mezo m : sor) {
  				if (m.lighting() >= 1) {
  					m.switchLamp();
  				}
  			}
  		}
  		ready = false;
  	}

}
