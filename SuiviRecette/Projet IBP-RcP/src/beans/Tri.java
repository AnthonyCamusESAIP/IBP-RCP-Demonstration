package beans;

import java.util.ArrayList;

public class Tri {
	private ArrayList<String> heureTest = new ArrayList<String>();
	private ArrayList<String> statutTest = new ArrayList<String>();
	private ArrayList<String> projet = new ArrayList<String>();
	private ArrayList<String> campagne = new ArrayList<String>();
	private ArrayList<String> nomTest = new ArrayList<String>();
	private ArrayList<String> nomTesteur = new ArrayList<String>();
	private ArrayList<String> dateTest = new ArrayList<String>();
	private ArrayList<ArrayList<String>> ligne = new ArrayList<ArrayList<String>>();
	
	
	public ArrayList<ArrayList<String>> getLigne() {
		return ligne;
	}

	public void setLigne(ArrayList<ArrayList<String>> ligne) {
		this.ligne = ligne;
	}

	public ArrayList<String> getDateTest() {
		return dateTest;
	}

	public void setDateTest(ArrayList<String> dateTest) {
		this.dateTest = dateTest;
	}

	public ArrayList<String> getHeureTest() {
		return heureTest;
	}

	public void setHeureTest(ArrayList<String> heureTest) {
		this.heureTest = heureTest;
	}

	public ArrayList<String> getStatutTest() {
		return statutTest;
	}

	public void setStatutTest(ArrayList<String> statutTest) {
		this.statutTest = statutTest;
	}

	public ArrayList<String> getProjet() {
		return projet;
	}

	public void setProjet(ArrayList<String> projet) {
		this.projet = projet;
	}
	
	public ArrayList<String> getCampagne() {
		return campagne;
	}

	public void setCampagne(ArrayList<String> campagne) {
		this.campagne = campagne;
	}

	public ArrayList<String> getNomTest() {
		return nomTest;
	}

	public void setNomTest(ArrayList<String> nomTest) {
		this.nomTest = nomTest;
	}

	public ArrayList<String> getNomTesteur() {
		return nomTesteur;
	}

	public void setNomTesteur(ArrayList<String> nomTesteur) {
		this.nomTesteur = nomTesteur;
	}

	
	public void list(ArrayList<ArrayList<String>> e){
		int i=1;
		int f=0;
		if(ligne.isEmpty()){
		}else{
			System.out.println("ligne non-vide");
		}
		
		
		for(i=0;i<e.size();i++){
			for(f=0;f<e.get(i).size();f++){				
				switch(f){
				case 0:
					dateTest.add(e.get(i).get(f));
					break;
				case 1:
					heureTest.add(e.get(i).get(f));
					break;
				case 2:
					statutTest.add(e.get(i).get(f));
					break;
				case 3:
					projet.add(e.get(i).get(f));
					break;
				case 4:
					campagne.add(e.get(i).get(f));
					break;
				case 5:
					nomTest.add(e.get(i).get(f));
					break;
				case 6:
					nomTesteur.add(e.get(i).get(f));
					break;
				default:
					System.out.println("ne passe dans rien");					
					break;					
				}
			}
			f= 0;
		}
	}
	
	public void okProjet(){
		//int ok =0;
		ArrayList<String> listProjet =new ArrayList<String>();
		ArrayList<Integer> p = new ArrayList<Integer>();
		System.out.println(p);
		listProjet.add(projet.get(1));
		//Note (Maryan) : 
		for(int i=0;i<getProjet().size();i++){
			if(listProjet.get(0)==projet.get(i)){
				p.add(i);
				System.out.println(" boucle for"+p);
			}
		}
		System.out.println(p+" fin de fonction");
	}
	
	
}
