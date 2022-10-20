package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import game.Board;
import game.FeherMezo;

/**
 * A fehér mezők osztályt teszteli.
 */
public class FeherMezoTest {
	FeherMezo mezo;
	
	/**
	 * A tesztek előtt létrehoz egy FeherMezo osztályt.
	 */
	@Before
	public void init() {
		mezo = new FeherMezo(new Board());
	}
	
	/**
	 * Teszteli, hogy sikeres volt-e a létrehozás, és hogy üres-e.
	 */
	@Test
	public void constr(){
		assertEquals("Újonnan nincs lámpa", 0, mezo.lighting());
	}
	
	/**
	 * Teszteli, hogy a switchLamp működik-e.
	 */
	@Test
	public void switchlmap() {
		mezo.switchLamp();
		assertEquals("Lámpát váltva van lámpa", 1, mezo.lighting());
		mezo.switchLamp();
		assertEquals("Ismét lámpát váltva nincs lámpa", 0, mezo.lighting());
	}
	
	
	

}
