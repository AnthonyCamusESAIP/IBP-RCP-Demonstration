package beans;

import java.util.*;
/**
 * 
 * Nom de classe : Campagne
 * 
 * Description : Classe correspondant au stockage des données attribuées à la table campagne de la BDD
 *
 * Version : 1.0
 * 
 * Date : 23/10/2017
 * 
 * Copyright : Anthony Camus
 */

public class Campagne {

	private int idCampagne;
	private String label;
	private Projet projet;
	private List<Test> tests;
	
	
	
	public Campagne(int idCampagne, String label, Projet projet) 
	{
		this.idCampagne = idCampagne;
		this.label = label;
		this.projet = projet;
		this.tests = new ArrayList<Test>();
	}

	public int getIdCampagne() 
	{
		return idCampagne;
	}
	
	public void setIdCampagne(int idCampagne) 
	{
		this.idCampagne = idCampagne;
	}
	
	public String getLabel() 
	{
		return label;
	}
	
	public void setLabel(String label) 
	{
		this.label = label;
	}

	public Projet getProjet() 
	{
		return projet;
	}

	public void setProjet(Projet projet) 
	{
		this.projet = projet;
	}

	public List<Test> getTests() 
	{
		return tests;
	}

	public void setTests(List<Test> tests) 
	{
		this.tests = tests;
	}
	
}
