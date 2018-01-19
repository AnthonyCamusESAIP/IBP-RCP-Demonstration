package beans;

import javax.annotation.PostConstruct;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIOutput;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

@SuppressWarnings("serial")
@ManagedBean(name = "chartViewManager")
@SessionScoped
public class ChartViewManager implements Serializable{
 
	private MysqlConnector mysqlConnect = new MysqlConnector("jdbc:mysql://localhost:3306/","ibp-rcp", "root", "");
	protected final static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	private ArrayList<ArrayList<String>> databaseProjects = new ArrayList<ArrayList<String>>();
	private DataManager dataManager;
	
	private double avancementMeteo = 0;
	private String avancementImage = ""; 
	
	private Map<String,String> projects = new HashMap<String, String>();
	private int projectId;
	private String projectName;
	private String date;
	private String datePurge;
	private File file;
	
    private PieChartModel pieModel;
    private LineChartModel lineModel;
	private BarChartModel barModel;
	
	private List<String> testPassedThisWeek;
	private List<String> testNAThisWeek;
	private List<String> testFailedThisWeek;
	private List<String> testNotCompletedThisWeek;
	
	private List<String> testPassedWeek1;
	private List<String> testNAWeek1;
	private List<String> testFailedWeek1;
	private List<String> testNotCompletedWeek1;
	
	private List<String> testPassedWeek2;
	private List<String> testNAWeek2;
	private List<String> testFailedWeek2;
	private List<String> testNotCompletedWeek2;
	
	private List<String> testPassedWeek3;
	private List<String> testNAWeek3;
	private List<String> testFailedWeek3;
	private List<String> testNotCompletedWeek3;
	
	private List<String> testPassedWeek4;
	private List<String> testNAWeek4;
	private List<String> testFailedWeek4;
	private List<String> testNotCompletedWeek4;
	
	private List<String> testPassedWeek5;
	private List<String> testNAWeek5;
	private List<String> testFailedWeek5;
	private List<String> testNotCompletedWeek5;
	
	private int nbTestThisWeek;
	private int nbTestWeek1;
	private int nbTestWeek2;
	private int nbTestWeek3;
	private int nbTestWeek4;
	private int nbTestWeek5;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDatePurge() {
		return datePurge;
	}
    public void setDatePurge(String datePurge) {
		this.datePurge = datePurge;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
    public double getAvancementMeteo() {
		return avancementMeteo;
	}
	public void setAvancementMeteo(double avancementMeteo) {
		this.avancementMeteo = avancementMeteo;
	}
	public String getAvancementImage() {
		return avancementImage;
	}
	public void setAvancementImage(String avancementImage) {
		this.avancementImage = avancementImage;
	}
	public LineChartModel getLineModel() {
		return lineModel;
	}
    public BarChartModel getBarModel() {
		return barModel;
	}
	public PieChartModel getPieModel() {
        return pieModel;
    }

	@PostConstruct
    public void init() {
		
		date = mysqlConnect.getLastDataDate(projectId);
		loadProjectList();
		updateProjectName();
    }
	public void initData() {

    	testPassedThisWeek = new ArrayList<String>();
    	testNAThisWeek = new ArrayList<String>();
    	testFailedThisWeek = new ArrayList<String>();
    	testNotCompletedThisWeek = new ArrayList<String>();
    	
    	testPassedWeek1 = new ArrayList<String>();
    	testNAWeek1 = new ArrayList<String>();
    	testFailedWeek1 = new ArrayList<String>();
    	testNotCompletedWeek1 = new ArrayList<String>();
    	
    	testPassedWeek2 = new ArrayList<String>();
    	testNAWeek2 = new ArrayList<String>();
    	testFailedWeek2 = new ArrayList<String>();
    	testNotCompletedWeek2 = new ArrayList<String>();
    	
    	testPassedWeek3 = new ArrayList<String>();
    	testNAWeek3 = new ArrayList<String>();
    	testFailedWeek3 = new ArrayList<String>();
    	testNotCompletedWeek3 = new ArrayList<String>();
    	
    	testPassedWeek3 = new ArrayList<String>();
    	testNAWeek3 = new ArrayList<String>();
    	testFailedWeek3 = new ArrayList<String>();
    	testNotCompletedWeek3 = new ArrayList<String>();
    	
    	testPassedWeek4 = new ArrayList<String>();
    	testNAWeek4 = new ArrayList<String>();
    	testFailedWeek4 = new ArrayList<String>();
    	testNotCompletedWeek4 = new ArrayList<String>();
    	
    	testPassedWeek5 = new ArrayList<String>();
    	testNAWeek5 = new ArrayList<String>();
    	testFailedWeek5 = new ArrayList<String>();
    	testNotCompletedWeek5 = new ArrayList<String>();
    	
    	List<String> tables = new ArrayList<String>();
    	tables.add("projet");
    	tables.add("campagne");
    	tables.add("test");
    	List<String> attributs = new ArrayList<String>();
    	attributs.add("test.statut");
    	Calendar cal = Calendar.getInstance();
    	try {
			cal.setTime(df.parse(date));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	String monday = df.format(cal.getTime());
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.projectName+"' AND (Date BETWEEN '"+monday+"' AND '"+date+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAThisWeek.add(string.get(0));
				break;
			case "Passed":
				testPassedThisWeek.add(string.get(0));
				break;
			case "Failed":
				testFailedThisWeek.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedThisWeek.add(string.get(0));
				break;
			}
		}
    	nbTestThisWeek = testNAThisWeek.size()+testFailedThisWeek.size()+testPassedThisWeek.size()+testNotCompletedThisWeek.size();
    	
    	cal.add(Calendar.DAY_OF_MONTH, -3);
    	String friday = df.format(cal.getTime());
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	monday = df.format(cal.getTime());
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.projectName+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAWeek1.add(string.get(0));
				break;
			case "Passed":
				testPassedWeek1.add(string.get(0));
				break;
			case "Failed":
				testFailedWeek1.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedWeek1.add(string.get(0));
				break;
			}
		}
    	nbTestWeek1 = testNAWeek1.size()+testPassedWeek1.size()+testFailedWeek1.size()+testNotCompletedWeek1.size();
    	
    	cal.add(Calendar.DAY_OF_MONTH, -3);
    	friday = df.format(cal.getTime());
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	monday = df.format(cal.getTime());
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.projectName+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAWeek2.add(string.get(0));
				break;
			case "Passed":
				testPassedWeek2.add(string.get(0));
				break;
			case "Failed":
				testFailedWeek2.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedWeek2.add(string.get(0));
				break;
			}
		}
    	nbTestWeek2 = testNAWeek2.size()+testPassedWeek2.size()+testFailedWeek2.size()+testNotCompletedWeek2.size();
    	
    	cal.add(Calendar.DAY_OF_MONTH, -3);
    	friday = df.format(cal.getTime());
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	monday = df.format(cal.getTime());
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.projectName+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAWeek3.add(string.get(0));
				break;
			case "Passed":
				testPassedWeek3.add(string.get(0));
				break;
			case "Failed":
				testFailedWeek3.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedWeek3.add(string.get(0));
				break;
			}
		}
    	nbTestWeek3 = testNAWeek3.size()+testPassedWeek3.size()+testFailedWeek3.size()+testNotCompletedWeek3.size();
    	
    	cal.add(Calendar.DAY_OF_MONTH, -3);
    	friday = df.format(cal.getTime());
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	monday = df.format(cal.getTime());
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.projectName+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAWeek4.add(string.get(0));
				break;
			case "Passed":
				testPassedWeek4.add(string.get(0));
				break;
			case "Failed":
				testFailedWeek4.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedWeek4.add(string.get(0));
				break;
			}
		}
    	nbTestWeek4 = testNAWeek4.size()+testPassedWeek4.size()+testFailedWeek4.size()+testNotCompletedWeek4.size();
    	
    	cal.add(Calendar.DAY_OF_MONTH, -3);
    	friday = df.format(cal.getTime());
    	cal.add(Calendar.DAY_OF_MONTH, -4);
    	monday = df.format(cal.getTime());
    	for (ArrayList<String> string : mysqlConnect.MysqlSelect(tables, attributs, "nomProjet ='"+this.projectName+"' AND (Date BETWEEN '"+monday+"' AND '"+friday+"')")) {
    		switch (string.get(0)) {
			case "N/A":
				testNAWeek5.add(string.get(0));
				break;
			case "Passed":
				testPassedWeek5.add(string.get(0));
				break;
			case "Failed":
				testFailedWeek5.add(string.get(0));
				break;
			case "Not Completed":
				testNotCompletedWeek5.add(string.get(0));
				break;
			}
		}
    	nbTestWeek5 = testNAWeek5.size()+testPassedWeek5.size()+testFailedWeek5.size()+testNotCompletedWeek5.size();
    	
    }
	private void initProject() {
    	List<String> tables = new ArrayList<String>();
    	tables.add("projet");
    	List<String> attributs = new ArrayList<String>();
    	attributs.add("projet.idProjet");
    	attributs.add("projet.nomProjet");
    	databaseProjects = mysqlConnect.MysqlSelect(tables, attributs, "");
    }
    public void loadProjectList() {
    	initProject();
    	projects = new HashMap<String, String>();
		for (ArrayList<String> arrayList : databaseProjects) {
			projects.put(arrayList.get(1), arrayList.get(0));
		}
    }
    public void updateProjectName() {
    	for (ArrayList<String> arrayList : databaseProjects) {
    		if (Integer.parseInt(arrayList.get(0)) == projectId) {
				this.projectName = arrayList.get(1);
			}
		}
    }
	
    private String formatDate(String dateSelected) {
    	String result;
    	String day = dateSelected.substring(8, 10);
    	String year = dateSelected.substring(24);
    	String month = dateSelected.substring(4,7);
    	switch (month) {
		case "Jan":
			month = "01";
			break;
		case "Feb":
			month = "02";
			break;
		case "Mar":
			month = "03";
			break;
		case "Apr":
			month = "04";
			break;
		case "May":
			month = "05";
			break;
		case "Jun":
			month = "06";
			break;
		case "Jul":
			month = "07";
			break;
		case "Aug":
			month = "08";
			break;
		case "Sep":
			month = "09";
			break;
		case "Oct":
			month = "10";
			break;
		case "Nov":
			month = "11";
			break;
		case "Dec":
			month = "12";
			break;
		}
    	result = year+"-"+month+"-"+day;
    	return result;
    }

    private void createModels() {
        createPieModel();
        createLineModel();
        createBarModel();
    }
    private void createPieModel() {
        pieModel = new PieChartModel();
        if(testNAThisWeek.size() > 0) {
            pieModel.set("NA", testNAThisWeek.size());
        }
        if(testPassedThisWeek.size() > 0) {
            pieModel.set("Passed", testPassedThisWeek.size());
        }
        if(testFailedThisWeek.size() > 0) {
            pieModel.set("Failed", testFailedThisWeek.size());
        }
        if(testNotCompletedThisWeek.size() > 0) {
            pieModel.set("Not Completed", testNotCompletedThisWeek.size());
        }         
        pieModel.setTitle("Résultats des tests");
        pieModel.setLegendPosition("w");
    }
    private void createLineModel() {
    	
    	lineModel = new LineChartModel();
    	
    	ChartSeries testNA = new ChartSeries();
    	testNA.setLabel("Test N/A");
    	ChartSeries testPassed = new ChartSeries();
        testPassed.setLabel("Test passed");
        ChartSeries testFailed = new ChartSeries();
        testFailed.setLabel("Test failed");
        ChartSeries testNotCompleted = new ChartSeries();
        testNotCompleted.setLabel("Test not completed");
 
        Calendar cal = Calendar.getInstance();
    	try {
			cal.setTime(df.parse(date));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	cal.add(Calendar.DAY_OF_MONTH, -7);
    	testNA.set(df.format(cal.getTime()), testNAWeek5.size());
    	testPassed.set(df.format(cal.getTime()), testPassedWeek5.size());
    	testFailed.set(df.format(cal.getTime()), testFailedWeek5.size());
    	testNotCompleted.set(df.format(cal.getTime()), testNotCompletedWeek5.size());
    	
    	cal.add(Calendar.DAY_OF_MONTH, -7);
    	testNA.set(df.format(cal.getTime()), testNAWeek4.size());
    	testPassed.set(df.format(cal.getTime()), testPassedWeek4.size());
    	testFailed.set(df.format(cal.getTime()), testFailedWeek4.size());
    	testNotCompleted.set(df.format(cal.getTime()), testNotCompletedWeek4.size());
    	
    	cal.add(Calendar.DAY_OF_MONTH, -7);
    	testNA.set(df.format(cal.getTime()), testNAWeek3.size());
    	testPassed.set(df.format(cal.getTime()), testPassedWeek3.size());
    	testFailed.set(df.format(cal.getTime()), testFailedWeek3.size());
    	testNotCompleted.set(df.format(cal.getTime()), testNotCompletedWeek3.size());
    	
    	cal.add(Calendar.DAY_OF_MONTH, -7);
    	testNA.set(df.format(cal.getTime()), testNAWeek2.size());
    	testPassed.set(df.format(cal.getTime()), testPassedWeek2.size());
    	testFailed.set(df.format(cal.getTime()), testFailedWeek2.size());
    	testNotCompleted.set(df.format(cal.getTime()), testNotCompletedWeek2.size());
    	
    	cal.add(Calendar.DAY_OF_MONTH, -7);
    	testNA.set(df.format(cal.getTime()), testNAWeek1.size());
    	testPassed.set(df.format(cal.getTime()), testPassedWeek1.size());
    	testFailed.set(df.format(cal.getTime()), testFailedWeek1.size());
    	testNotCompleted.set(df.format(cal.getTime()), testNotCompletedWeek1.size());
    	
    	testNA.set(date, testNAThisWeek.size());
    	testPassed.set(date, testPassedThisWeek.size());
    	testFailed.set(date, testFailedThisWeek.size());
    	testNotCompleted.set(date, testNotCompletedThisWeek.size());

        lineModel.addSeries(testNA);
        lineModel.addSeries(testPassed);
        lineModel.addSeries(testFailed);
        lineModel.addSeries(testNotCompleted);
        lineModel.setTitle("Comparaison des test sur le mois passé");
        lineModel.setLegendPosition("n");
        lineModel.setShowPointLabels(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Date"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        
        int[] tab = {nbTestThisWeek, nbTestWeek1, nbTestWeek2, nbTestWeek3, nbTestWeek4, nbTestWeek5};
        int max = Arrays.stream(tab).max().getAsInt();
        yAxis.setLabel("Nombre de tests");
        yAxis.setMin(0);
        yAxis.setMax(max+1);
    }
    private void createBarModel() {
    	
    	barModel = new BarChartModel();
    	
        Calendar cal = Calendar.getInstance();
    	try {
			cal.setTime(df.parse(date));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	ChartSeries thisWeek = new ChartSeries();
        thisWeek.setLabel("Cette semaine");
        thisWeek.set(date, nbTestThisWeek);
        
        cal.add(Calendar.DAY_OF_MONTH, -7);
    	ChartSeries passedWeek = new ChartSeries();
    	passedWeek.setLabel("Semaine dernière");
    	passedWeek.set(df.format(cal.getTime()), nbTestWeek1);
 
        barModel.addSeries(thisWeek);
        barModel.addSeries(passedWeek);
        
        barModel.setTitle("Comparatif nombre de test total par rapport à la semaine dernière");
        barModel.setLegendPosition("n");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Semaine");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Tests");
        yAxis.setMin(0);
        int[] tab = {nbTestThisWeek, nbTestWeek1};
        int max = Arrays.stream(tab).max().getAsInt();
        yAxis.setMax(max+5);
    }

    public void valueChangeSelectProject(ValueChangeEvent e) {
    	projectId = Integer.parseInt(e.getNewValue().toString());
    	updateProjectName();
    	avancementMeteo();
    	date = mysqlConnect.getLastDataDate(projectId);
    	
    	initData();
        createModels();
    }
    public void valueChangeSelectProject(final AjaxBehaviorEvent event) throws IOException {
    	this.projectId = Integer.parseInt(((UIOutput)event.getSource()).getValue().toString());
    	updateProjectName();
    	avancementMeteo();
    	this.date = mysqlConnect.getLastDataDate(projectId);
    	FacesContext.getCurrentInstance().getExternalContext().redirect("main.xhtml");
        FacesContext.getCurrentInstance().responseComplete();
    	initData();
        createModels();
        
    }  
    public void valueChangeDate(SelectEvent event) {
    	Calendar cal = Calendar.getInstance();
    	try {
			cal.setTime(df.parse(formatDate(event.getObject().toString())));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	cal.add(Calendar.DAY_OF_MONTH, -30);
    	String beginPeriod = df.format(cal.getTime());
    	if (mysqlConnect.verifyNumberDataPeriod(projectId, beginPeriod, date) > 0) {
        	date = formatDate(event.getObject().toString());
        	initData();
            createModels();
		}
    	else {
    		FacesContext context = FacesContext.getCurrentInstance();   
            context.addMessage(null, new FacesMessage("Error",  "Pas de données pour la date sélectionnée.") );
    	}
    }
    public void valueChangeDatePurge(SelectEvent e) {
    	datePurge = formatDate(e.getObject().toString());
    	mysqlConnect.purgeDatabase(datePurge);
    	loadProjectList();
    }
    
    public void avancementMeteo(){
    	List<String> nomTables = new ArrayList<String>();
    	nomTables.add("campagne");
    	nomTables.add("test"); 
    	List<String> listeVariable = new ArrayList<String>();
    	listeVariable.add("count(1)");
    	String condition = "campagne.idProjet = "+ projectId+" AND (statut = 'Passed')";
    	ArrayList<ArrayList<String>> testsPassed = mysqlConnect.MysqlSelect(nomTables, listeVariable, condition);
    	
    	double testPassed = Integer.parseInt(testsPassed.get(0).get(0));
    	
    	nomTables = new ArrayList<String>();
    	nomTables.add("campagne");
    	nomTables.add("test");
    	listeVariable = new ArrayList<String>();
    	listeVariable.add("count(1)");
    	condition = "campagne.idProjet = "+ projectId + " AND (statut = 'Failed')";
    	ArrayList<ArrayList<String>> testsFailed = mysqlConnect.MysqlSelect(nomTables, listeVariable, condition);
    	
    	double testFailed = Integer.parseInt(testsFailed.get(0).get(0));
    	
    	nomTables = new ArrayList<String>();
    	nomTables.add("campagne");
    	nomTables.add("test");
    	listeVariable = new ArrayList<String>(); 
    	listeVariable.add("count(1)");
    	condition = "campagne.idProjet = "+ projectId;
    	ArrayList<ArrayList<String>> nbrTests = mysqlConnect.MysqlSelect(nomTables, listeVariable, condition);
    	
    	double nbrTest = Integer.parseInt(nbrTests.get(0).get(0));
    	  
    	avancementMeteo = Math.round(((testPassed+(testFailed*(0.5)))/nbrTest)*100); 
    	if(avancementMeteo >= 85){
    		avancementImage = "sun.png";
    	} else if (avancementMeteo >= 70){
    		avancementImage = "sun-cloud.png";
    	} else {
    		avancementImage = "cloud-storm.png";
    	}
    	
    }
    
	public void handleFileUpload(FileUploadEvent event) {
        if(event.getFile() != null) {
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
        loadProjectList();
    }
    
}
