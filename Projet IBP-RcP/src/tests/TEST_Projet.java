package tests;


import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import beans.*;
import junit.framework.TestCase;

public class TEST_Projet extends TestCase{

	Projet unProjet = new Projet(0, "projetTest");
	List<Campagne> lstCamp = new ArrayList<Campagne>();
	
	
	@Test
	public void testGetIdProjet() {
		int expected = 0;
		assertEquals(expected, unProjet.getIdProjet());
	}

	@Test
	public void testSetIdProjet() {
		int expected = 1;
		unProjet.setIdProjet(1);
		assertEquals(expected, unProjet.getIdProjet());
	}

	@Test
	public void testGetLabel() {
		String expected = "projetTest";
		assertEquals(expected, unProjet.getLabel());
	}

	@Test
	public void testSetLabel() {
		String expected = "projetTestChangementNom";
		unProjet.setLabel("projetTestChangementNom");
		assertEquals(expected, unProjet.getLabel());
		
	}
	
	@Test
	public void testGetCampagnes() {
	
		
		assertEquals(new ArrayList<Campagne>(), unProjet.getCampagnes());
	}

	@Test
	public void testSetCampagnes() {
		lstCamp.add(new Campagne(0,"Campagne 1",unProjet));
		unProjet.setCampagnes(lstCamp);
		assertEquals(lstCamp, unProjet.getCampagnes());
	}

}
