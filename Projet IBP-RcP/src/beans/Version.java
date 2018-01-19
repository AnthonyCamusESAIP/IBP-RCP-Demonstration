package beans;
import java.util.*;
/**
 * 
 * Nom de classe : Version
 * 
 * Description : Classe correspondant au stockage des données attribuées à la table version de la BDD
 *
 * Version : 1.0
 * 
 * Date : 14/10/2017
 * 
 * Copyright : Anthony Camus
 */

public class Version {
	private int idVersion;
	private String nomVersion;
	private List<Projet> projets;
	
	public Version(int idVersion, String nomVersion) {
		this.idVersion = idVersion;
		this.nomVersion = nomVersion;
		this.projets = new ArrayList<Projet>();
	}
	
	public int getIdVersion() {
		return idVersion;
	}
	public void setIdVersion(int idVersion) {
		this.idVersion = idVersion;
	}
	public String getNomVersion() {
		return nomVersion;
	}
	public void setNomVersion(String nomVersion) {
		this.nomVersion = nomVersion;
	}
	public List<Projet> getProjets() {
		return projets;
	}
	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}
	
}
