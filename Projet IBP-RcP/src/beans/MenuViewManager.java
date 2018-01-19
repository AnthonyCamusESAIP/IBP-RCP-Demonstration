package beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class MenuViewManager {
    private int projectId;  
    private Map<String,String> projects = new HashMap<String, String>();
    private MysqlConnector mysqlConnect = new MysqlConnector("jdbc:mysql://localhost:3306/","ibp-rcp", "root", "");
    private ArrayList<ArrayList<String>> databaseProjects = new ArrayList<ArrayList<String>>();
    private String projectName;
    
    @PostConstruct
    public void init() {
    	initProject();
    	projects = new HashMap<String, String>();
    	for (ArrayList<String> arrayList : databaseProjects) {
			projects.put(arrayList.get(1), arrayList.get(0));
		}
    }
    
    private void initProject() {
    	List<String> tables = new ArrayList<String>();
    	tables.add("projet");
    	List<String> attributs = new ArrayList<String>();
    	attributs.add("projet.idProjet");
    	attributs.add("projet.nomProjet");
    	databaseProjects = mysqlConnect.MysqlSelect(tables, attributs, "");
    	System.out.println(databaseProjects.toString());
    }

	public Map<String, String> getProjects() {
		return projects;
	}

	public void setProjects(Map<String, String> projects) {
		this.projects = projects;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
