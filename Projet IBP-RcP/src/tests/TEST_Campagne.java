package tests;

import org.junit.Test;

import beans.Campagne;
import beans.Projet;

//import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class TEST_Campagne extends TestCase {
	
	
	Projet p = new Projet(1, "Projet de test");
	Campagne c = new Campagne(1, "Campagne de test", p);
	
	@Test
	public void testGetIdCampagne() {
		int expected = 1;
		assertEquals(expected, c.getIdCampagne());
	}

	@Test
	public void testSetIdCampagne() {
		int expected = 2;
		c.setIdCampagne(expected);	
		assertEquals(expected, c.getIdCampagne());
	}

	@Test
	public void testGetLabel() {
		String expected = "Campagne de test";
		assertEquals(expected, c.getLabel());
	}

	@Test
	public void testSetLabel() {
		String expected = "Campagne de test avec des TU";
		c.setLabel(expected);
		assertEquals(expected, c.getLabel());
	}

	@Test
	public void testGetProjet() {
		Projet expected = p;
		assertEquals(expected,c.getProjet());
	}

	@Test
	public void testSetProjet() {
		Projet expected = new Projet(2, "Projet de test de classes");
		c.setProjet(expected);
		assertEquals(expected,c.getProjet());
	}

}
