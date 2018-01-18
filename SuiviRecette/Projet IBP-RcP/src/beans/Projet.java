package beans;

import java.util.*;

/**
 * 
 * Nom de classe : Projet
 * 
 * Description : Classe correspondant au stockage des données attribuées à la table projet de la BDD
 *
 * Version : 1.0
 * 
 * Date : 23/10/2017
 * 
 * Copyright : Anthony Camus
 */

public class Projet {

	private int idProjet;
	private String label;
	private List<Campagne> campagnes;
	
	public Projet(int idProjet, String label) 
	{
		this.idProjet = idProjet;
		this.label = label;
		this.campagnes = new ArrayList<Campagne>();
	}
	
	public int getIdProjet() 
	{
		return idProjet;
	}
	public void setIdProjet(int idProjet) 
	{
		this.idProjet = idProjet;
	}
	public String getLabel() 
	{
		return label;
	}
	public void setLabel(String label) 
	{
		this.label = label;
	}

	public List<Campagne> getCampagnes() 
	{
		return campagnes;
	}

	public void setCampagnes(List<Campagne> campagnes)
	{
		this.campagnes = campagnes;
	}
	
	
	
}
