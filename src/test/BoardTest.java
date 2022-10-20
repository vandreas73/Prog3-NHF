package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import game.Board;

/**
 * A Board osztályt tesztelő osztály.
 */
public class BoardTest {
	Board b;
	
	/**
	 * Beolvasunk egy fájlt minden teszt előtt
	 */
	@Before
	public void init() {
		b = new Board();
		b.deserialize("palyak/5x5.1.dat", false);
	}
	
	
	/**
	 * A tesztek előtt átváltok lámpákat úgy, hogy csak 1 lámpa hiányozzon (az (1,4) mezőről)
	 */
	@Before
	public void lampak() {
		b.switchLamp(2, 0);
		b.switchLamp(3, 1);
		b.switchLamp(4, 0);
		b.switchLamp(1, 2);
		b.switchLamp(0, 3);
		//Még az (1,4) mezőre kell lámpa
	}
	
	/**
	 * Tesztelem, hogy a beolvasás során a sor és oszlopszám stimmel-e.
	 */
	@Test
	public void deserializeTest() {
		assertEquals("sorok száma", 5, b.rowCount());
		assertEquals("oszlopok száma", 5, b.columnCount());
	}

	
	
	/**
	 * Tesztelem, hogy switchLamp() függvény működik-e.
	 * Fehér mezőn rárak/eltávolít lámpát, fal mezőn nincs hatása.
	 */
	@Test
	public void switchLampTest() {
		assertEquals("világítani kéne", 1, b.getMezoAt(2, 0).lighting());
		assertEquals("világítani kéne", 1, b.getMezoAt(0, 3).lighting());
		assertEquals("nem világít", 0, b.getMezoAt(2, 1).lighting());
		b.switchLamp(2, 1);
		assertEquals("nem világít", 1, b.getMezoAt(2, 1).lighting());
		b.switchLamp(2, 1);
		assertEquals("nem világít", 0, b.getMezoAt(2, 1).lighting());
		
		assertEquals("falon switchLamp", -1, b.getMezoAt(0, 1).lighting());
		b.switchLamp(0, 1);
		assertEquals("falon switchLamp", -1, b.getMezoAt(0, 1).lighting());
	}

	
	/**
	 * Tesztelem, hogy a megvilágítottság működik-e.
	 */
	@Test
	public void isLightedTest() {
		assertTrue("meg van világítva", b.isLighted(0, 0));
		assertTrue("meg van világítva", b.isLighted(4, 4));
		assertFalse("nincs megvilágítva", b.isLighted(3, 4));
	}
	
	
	/**
	 * Tesztelem, hogy a szomszédos lámpák számolása helyesen működik-e.
	 */
	@Test
	public void countNeighbourLampsTest() {
		assertEquals(3, b.countNeighbourLamps(3, 0));
		assertEquals(2, b.countNeighbourLamps(1, 3));
		b.switchLamp(1, 4);
		assertEquals(3, b.countNeighbourLamps(1, 3));
	}
	
	/**
	 * Tesztelem, hogy a pálya elkészültét észreveszi-e magától a játék,
	 * illetve hogy újrakezdéskor visszaáll-e nem kész állapotba.
	 */
	@Test
	public void isReadyTest() {
		assertFalse(b.isReady());
		b.switchLamp(1, 4);
		assertTrue(b.isReady());
		b.restart();
		assertFalse(b.isReady());
	}
	
	/**
	 * Tesztelem, hogy restartkor a lámpák törlődnek-e.
	 */
	@Test
	public void restartTest() {
		assertEquals(1, b.getMezoAt(2, 0).lighting());
		b.restart();
		assertEquals(0, b.getMezoAt(2, 0).lighting());
		assertFalse(b.isLighted(0, 0));
	}
	
	
	/**
	 * Tesztelem, hogy mentés és visszaolvasás után a pálya helyes-e.
	 */
	@Test
	public void saveTest() {
		b.serialize("mentesek/save.dat");
		b.deserialize("mentesek/save.dat", true);
		assertEquals("falak mentése-visszaolvasása", -1, b.getMezoAt(3, 0).lighting());
		assertEquals("üres mezők mentése-visszaolvasása", 0, b.getMezoAt(0, 0).lighting());
		assertEquals("lámpákat helyesen mentette és beolvasta", 1, b.getMezoAt(2, 0).lighting());
	}
}
