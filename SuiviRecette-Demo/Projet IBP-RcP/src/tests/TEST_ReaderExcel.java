package tests;

import org.junit.Test;
import beans.*;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class TEST_ReaderExcel extends TestCase {

	public void testReadExcel() {
		
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>() ;
		
		ArrayList<String> ligne = new ArrayList<String>();
		
		ligne.add("Date_exec_test");
		ligne.add("Heure_exec_test");
		ligne.add("Statut_du_test");
		ligne.add("Projet");
		ligne.add("Campagne");  
		ligne.add("Nom_du_test");
		ligne.add("Nom_du_testeur");
		expected.add(ligne);
		
		ligne = new ArrayList<String>();
		ligne.add("2017-10-16");
		ligne.add("09:06:53");
		ligne.add("N/A");
		ligne.add("GSP13044 - PPG - PARME - Sujets Prioritaires");
		ligne.add("BPBFC - PSR PF et GF");
		ligne.add("027-B2-PF-SBB-PackSEPA-SEP0014E");
		ligne.add("bnwenda");
		expected.add(ligne);
		
		ligne = new ArrayList<String>();
		ligne.add("2017-10-16");
		ligne.add("10:01:53");
		ligne.add("Passed");
		ligne.add("GSP13044 - PPG - PARME - Sujets Prioritaires");
		ligne.add("BPBFC - PSR PF et GF");
		ligne.add("030-B2-PF-SBB-PackSEPA-SEP0061E");
		ligne.add("bnwenda");
		expected.add(ligne);
		
		ligne = new ArrayList<String>();
		ligne.add("2017-10-16");
		ligne.add("15:14:00");
		ligne.add("Passed");
		ligne.add("GSP13044 - PPG - PARME - Sujets Prioritaires");
		ligne.add("BPBFC - PSR PF et GF");
		ligne.add("035-B2-PF-SBB-PackSEPA-IBP0010E");
		ligne.add("bnwenda");
		expected.add(ligne);
		
		ligne = new ArrayList<String>();
		ligne.add("2017-10-16");
		ligne.add("16:55:46");
		ligne.add("Passed");
		ligne.add("GSP13044 - PPG - PARME - Sujets Prioritaires");
		ligne.add("BPBFC - PSR PF et GF");
		ligne.add("036-B2-PF-SBB-PackSEPA-IBP0014E");
		ligne.add("bnwenda"); 
		expected.add(ligne);

		ligne = new ArrayList<String>();
		ligne.add("2017-10-16");
		ligne.add("16:55:52");
		ligne.add("Failed");
		ligne.add("GSP13044 - PPG - PARME - Sujets Prioritaires");
		ligne.add("BPBFC - PSR PF et GF");
		ligne.add("037-B2-PF-SBB-PackSEPA-IBP0020E");
		ligne.add("bnwenda");
		expected.add(ligne);
		
		
		ReaderExcel excel = new ReaderExcel();
		try {
			excel.initReader(new FileInputStream("src\\beans\\TURacc.xlsx"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	
		assertEquals(expected,excel.ReadExcel());
		
		
		
		
	}

}
