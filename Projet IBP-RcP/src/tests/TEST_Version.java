package tests;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import beans.Version;
import junit.framework.TestCase;

public class TEST_Version extends TestCase {

	Version uneVersion = new Version(0, "version");
	
	@Test
	public void testGetIdVersion() {

		int expected = 0;
		assertEquals(expected,uneVersion.getIdVersion());
	}

	@Test
	public void testSetIdVersion() {
		
		int expected = 1;
		uneVersion.setIdVersion(1);
		assertEquals(expected,uneVersion.getIdVersion());
	}

	@Test
	public void testGetNomVersion() {

		String expected = "version";
		assertEquals(expected,uneVersion.getNomVersion());
	}

	@Test
	public void testSetNomVersion() {

		String expected = "version2";
		uneVersion.setNomVersion("version2");
		assertEquals(expected,uneVersion.getNomVersion());
	}

	@Test
	public void testGetProjets() {
		
		assertEquals(new ArrayList<beans.Projet>(),uneVersion.getProjets());
	}

	@Test
	public void testSetProjets() {
		
		List<beans.Projet> lstProjet = new ArrayList<beans.Projet>();
		lstProjet.add(new beans.Projet(0, "projet"));
		uneVersion.setProjets(lstProjet);
		assertEquals(lstProjet,uneVersion.getProjets());
	}

}
