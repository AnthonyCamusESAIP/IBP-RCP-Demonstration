package tests;



import java.sql.Date;

import org.junit.Test;

import beans.*;
import junit.framework.TestCase;

public class TEST_Test extends TestCase{

	Projet p = new Projet(1, "Projet de test");
	Campagne c = new Campagne(1, "Campagne de test", p);
	Testeur t = new Testeur(1, "Anthony");
	java.util.Date utilDate = new java.util.Date();
	Date sqlDate = new java.sql.Date(utilDate.getTime());
	beans.Test te = new beans.Test(1,"2017-12-02","15:49:00","Passed","Test n°1", c,t);
	
	@Test
	public void testGetIdTest() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetIdTest() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHeure() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHeure() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetStatut() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetStatut() {
		fail("Not yet implemented");
	}

	public void testGetCampagne() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCampagne() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTesteur() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetTesteur() {
		fail("Not yet implemented");
	}

}
