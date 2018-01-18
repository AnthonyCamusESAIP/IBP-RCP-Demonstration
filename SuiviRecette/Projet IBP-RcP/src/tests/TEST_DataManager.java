package tests;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import beans.*;

public class TEST_DataManager {

	@Test
	public void testGetExistingProjects() {
		fail("Not yet implemented");
	}

	@Test
	public void testDataManager() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitExisting() {
		
	}

	@Test
	public void testInitExistingTesteurs() {

		try {
			
			DataManager dm = new DataManager(new FileInputStream("Test"));
			List<Testeur> lstTesteur = new ArrayList<Testeur>();
			lstTesteur.add(new Testeur(1, "bwenda"));
			lstTesteur.add(new Testeur(2, "su24659"));
			lstTesteur.add(new Testeur(3, "at21022"));
			lstTesteur.add(new Testeur(4, "atbar11"));
			lstTesteur.add(new Testeur(5, "ritonpa"));
			
			 dm.initExistingTesteurs();
			 
			assertEquals(lstTesteur,dm.getExistingTesteur());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void testInitExistingVersions() {

		try {
			
			DataManager dm = new DataManager(new FileInputStream("Test"));
			List<Version> lstVersion = new ArrayList<Version>();
			lstVersion.add(new Version(1, "Version0"));
			
			 dm.initExistingVersions();
			 
			assertEquals(lstVersion,dm.getExistingVersion());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testInitExistingProjects() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitExistingCampagnes() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitExistingTest() {
		
		
		try {
			DataManager dm = new DataManager(new FileInputStream("Test"));
			Testeur testeur = new Testeur(2, "su24659");
			Projet proj = new Projet(1, "GSP13044 - PPG - PARME - Sujets Prioritaires");
			Campagne camp = new Campagne(7, "BPS Génération PSR2 Transmission",proj);
			beans.Test test = new beans.Test(87, "2017-10-04", "15:50:03","Passed", "EXF025-BPS N01 - Ctrl sémantiques Transmission- Plus de 4000 remises", camp, testeur);
			beans.Test test2 = new beans.Test(121, "2017-04-28", "14:54:30", "Failed", "EXF025-BPS N100 - Ctrl sémantiques Transmission- Plus de 999999 opérations", camp, testeur);
			dm.initExistingTest(camp);
			
			List<beans.Test> expected = new ArrayList<beans.Test>();
			expected.add(test);
			expected.add(test2);
			
			
			
			assertEquals(expected, camp.getTests());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSaveData() {
		fail("Not yet implemented");
	}

	@Test
	public void testClearData() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveImportedProjects() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveImportedCampagnes() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveImportedTesteurs() {
		fail("Not yet implemented");
	}

	@Test
	public void testSaveImportedTest() {
		fail("Not yet implemented");
	}

}
