package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

@ManagedBean
public class FileUpload {
	
	private DataManager dataManager;
	private File file;

	public void handleFileUpload(FileUploadEvent event) {
        if(event.getFile() != null) {
        	System.out.println();
            file = new File(System.getProperty("java.io.tmpdir")+"lstTest.xls");
			try {
				file.createNewFile();
				event.getFile().write(file.getPath());
				dataManager = new DataManager(new FileInputStream(file.getAbsolutePath()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if (dataManager != null) {
            	dataManager.saveData();
			}
        }
    }
}