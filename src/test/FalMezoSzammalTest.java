package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import game.FalMezoSzammal;

/**
 * A számmal rendelkező fal mezők osztályt teszteli.
 *
 */
public class FalMezoSzammalTest {
	
	/**
	 * Teszteli, hogy a konstruktorban megadott számot adja-e vissza.
	 */
	@Test
	public void szamTest() {
		FalMezoSzammal f1 = new FalMezoSzammal(2);
		assertEquals("konstruktor szám", 2, (int)f1.getValue());
		FalMezoSzammal f2 = new FalMezoSzammal(5);
		assertEquals("konstruktor szám", 5, (int)f2.getValue());
		FalMezoSzammal f3 = new FalMezoSzammal(2);
		assertNotSame("uj egyed", f1, f3);
		assertEquals("konstruktor szám", 2, (int)f3.getValue());
	}
	
	
	/**
	 * Teszteli, hogy a switchLamp valóban nem csinál-e semmit.
	 */
	@Test
	public void switchlmap() {
		FalMezoSzammal mezo = new FalMezoSzammal(1);
		mezo.switchLamp();
		assertEquals("switchLamp hatására nem történik változás", 0, mezo.lighting());
	}

}
