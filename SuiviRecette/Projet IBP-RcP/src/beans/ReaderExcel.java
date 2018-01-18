package beans;
/**
 * 
 * Nom de classe : ReaderExcel
 * 
 * Description : Cette classe permet de lire et et placer les valeur dans un tableau
 *
 * Version : 1.0
 * 
 * Date : 23/10/2017
 * 
 * Copyright : Maryan Cimia
 */

import java.io.FileInputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReaderExcel {

	// Note (Maryan) : implementation des variables
	private FileInputStream file;
	private Workbook workbook1;	
	private Sheet sheet ;
	private Iterator<org.apache.poi.ss.usermodel.Row> iterator;	
	
	
	// Note (Maryan) :init des parametre chemin
	// Note (Alban) : Constructeur ? Besoin TU
	public void initReader(FileInputStream _file){
		
	    try {
			this.file = _file;
			workbook1 = WorkbookFactory.create(file);
			sheet = workbook1.getSheetAt(0);
	    	iterator = sheet.iterator();
		} catch (Exception e) {
			e.printStackTrace();
		} 			
		
	}
	// Note (Maryan) :fermeture
	public void close(){
        try {
        	
        	this.file.close();
			this.workbook1.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	// Note (Maryan) :lecture plus tableau
	public ArrayList<ArrayList<String>> ReadExcel(){		
		String date = "";
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");		//Note (Maryan) : cette ligne permet de convertir la date et choisire son format
		ArrayList<ArrayList<String>> tabDonneeExcel = new ArrayList<ArrayList<String>>();		//Note (Alban) : Création d'un tableau de données
		ArrayList<String> tabLigneDonnee = new ArrayList<String>();		//Note (Alban) : Création d'une ligne de données
		
		try
        {
    		
    		Iterator<Row> iterator = sheet.iterator();
    		
	    		while (iterator.hasNext()) {
	    			
	    			tabLigneDonnee = new ArrayList<String>();
	    			Row nextRow = iterator.next();
	    			Iterator<Cell> cellIterator = nextRow.cellIterator();
	    			
	    			while ((cellIterator.hasNext())) {
	    				
	    				Cell cell = cellIterator.next();
	    				switch (cell.getCellTypeEnum()) {
	    				
							case STRING:
		    					tabLigneDonnee.add(cell.getStringCellValue());
		    					break;
		    				case NUMERIC:
		    					date = formatter.format(cell.getDateCellValue()); // Note (Maryan) : Convertion des Dates en String	    					
		    					tabLigneDonnee.add(date);
		    					break;
		    				default:
								break;
	    				}
	    			}
	    			
	    			tabDonneeExcel.add(tabLigneDonnee);
	    			
	    		}
    		
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		return tabDonneeExcel;
	}
}
