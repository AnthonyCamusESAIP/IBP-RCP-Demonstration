package tests;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import beans.*;
import junit.framework.TestCase;

public class TEST_Testeur extends TestCase{

	Testeur unTesteur = new Testeur(0, "jeromeFavier");
	@Test
	public void testGetIdTesteur() {
		int expected = 0;
		assertEquals(expected, unTesteur.getIdTesteur());
	}

	@Test
	public void testSetIdTesteur() {
		int expected = 1;
		unTesteur.setIdTesteur(1);
		assertEquals(expected, unTesteur.getIdTesteur());
	}

	@Test
	public void testGetNomTesteur() {
		String expected = "jeromeFavier";
		assertEquals(expected, unTesteur.getNomTesteur());
	}

	@Test
	public void testSetNomTesteur() {
		String expected = "anthonyCamus";
		unTesteur.setNomTesteur("anthonyCamus");
		assertEquals(expected, unTesteur.getNomTesteur());
	}
	
	@Test
	public void testGetTests() {
	
		assertEquals(new ArrayList<Test>(), unTesteur.getTests());
	}

	@Test
	public void testSetTests() {
		List<beans.Test> lstTest = new ArrayList<beans.Test>();
		lstTest.add(new beans.Test(0, "date", "heure", "status", "nom", null, unTesteur));
		unTesteur.setTests(lstTest);
		assertEquals(lstTest, unTesteur.getTests());
	}


}
