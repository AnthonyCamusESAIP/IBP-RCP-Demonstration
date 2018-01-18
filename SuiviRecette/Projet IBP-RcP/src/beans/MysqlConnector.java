/*
 *	Nom de la Classe : MysqlConnector 
 * 
 * 	Description : Connexion à la base de donnée Mysl 
 * 
 *  Version : 1.0
 *  
 *  Date : 23/10/2017
 *  
 *  Copyright : Alban ECOBICHON
 */

package beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlConnector {
	
	private Connection connect;	//Note (Alban): Instance de la connexion
	//Note (Alban): Methode de connexion à la bdd
	
	public MysqlConnector(String url, String dbName, String login, String mdp) {
		connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(url+dbName, login, mdp);
			// TODO : Faire le deploiement de la bdd si elle existe pas
		} catch (Exception ex) {
			if (ex.getMessage().equals("Unknown database 'ibp-rcp'")) {
				try {
					createDatabase("E:\\Documents\\GitHub\\05-IBP-RcP\\SuiviRecette\\Projet IBP-RcP\\src\\beans\\ibp-rcp.sql", DriverManager.getConnection(url, login, mdp));
					Class.forName("com.mysql.jdbc.Driver");
					connect = DriverManager.getConnection(url+dbName, login, mdp);
				} catch (Exception e) {
					System.out.println("Connexion Error :");
					System.out.println(ex.getMessage());
				}
				
			}
			else {
				System.out.println("Connexion Error :");
				System.out.println(ex.getMessage());
			}
		}
	}

	//Note (Alban): Methode de fermeture de la connexion à la bdd
	public void MysqlClose(){

		try {
			
			connect.close();
		} catch (Exception e) {

			System.out.println("Connexion Close Error : ");
			System.out.println(e.getMessage());
		}
	}

	//Note (Alban): Insert de données dans la base
	public int MysqlInsert(Projet projet){
		String sqlQuery = "INSERT INTO projet (nomProjet, idVersion) VALUES ('"+ projet.getLabel().replaceAll("['`\"]", " ")+"',1);";
		
		int result = 0;
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			result  = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlInsert Projet Error : ");
			System.out.println(e.getMessage());
		}
		return result;
	}
	public int MysqlInsert(Campagne campagne){
		
		String sqlQuery = "INSERT INTO campagne (nomCampagne,idProjet) VALUES ('"+ campagne.getLabel().replaceAll("['`\"]", " ")+"',"+campagne.getProjet().getIdProjet()+");";
		
		int result = 0;
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			result  = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlInsert Campagne Error : ");
			System.out.println(e.getMessage()); 
			System.out.println(sqlQuery);
		}
		return result;
	}
	public int MysqlInsert(Testeur testeur){

		String sqlQuery = "INSERT INTO testeur (nomTesteur) VALUES ('"+ testeur.getNomTesteur().replaceAll("['`\"]", " ")+"');";
		
		int result = 0;
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			result  = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlInsert Testeur Error : ");
			System.out.println(e.getMessage());
		}
		return result;
	}
	public int MysqlInsert(Test test){

		String sqlQuery = "INSERT INTO test (date,heure,statut, nomTest, idCampagne,idTesteur) "
				+ "VALUES ('"+ test.getDate().replaceAll("['`\"]", " ") +"','"+ test.getHeure().replaceAll("['`\"]", " ") +"','"+ test.getStatut().replaceAll("['`\"]", " ") +"',"
				+ "'"+ test.getNomTest().replaceAll("['`\"]", " ") +"',"+ test.getCampagne().getIdCampagne() +", "
				+ test.getTesteur().getIdTesteur() +");";
		
		int result = 0;
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			result  = pstmt.executeUpdate();

			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlInsert Test Error : ");
			System.out.println(sqlQuery);
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	//Note (Alban): Selection de données dans la base
	public ArrayList<ArrayList<String>> MysqlSelect(List<String> nomTables, List<String> listeVariable, String condition){
		
		//Note (Alban) : Tableau de données séectionner
		ArrayList<ArrayList<String>> donnees = new ArrayList<ArrayList<String>>();
		//Note (Alban) : Requete Sql de Select
		String sqlQuery = MysqlSelectRequete(nomTables, listeVariable, condition);
		
		//Note (Alban) : Execution de la requete
		try {
			
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			
			/***
			 * implementation d'un arraylist en boucle à demander
			 */
			
			while (rs.next()) {
				ArrayList<String> ligne = new ArrayList<String>();
				//Note (Alban) : Pour chaque listeVariable on ajoute à un Tableau la valeur de la cellule
				for(String variable : listeVariable) {

					ligne.add(rs.getString(variable));
	 
				}
				//Note (Alban) : On ajoute le tableau aux données
				donnees.add(ligne);
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {

			System.out.println("MysqlSelect Error : ");
			System.out.println(e.getMessage());
		}
		
		return donnees;
	}
	public String MysqlSelectRequete(List<String> nomTables, List<String> listeVariable, String condition){
				
		//Note (Alban) : Creation de la requete
		String sqlQuery = "SELECT ";
		for(String variable : listeVariable) {
			sqlQuery += variable;

			if(!(variable.equals(listeVariable.get(listeVariable.size()-1)))) {
			 
			 	sqlQuery += ", ";
			 }
			 
		}
		sqlQuery +=  " FROM ";
		for(String table : nomTables) {
			sqlQuery += table;

			if(!(table.equals(nomTables.get(nomTables.size()-1)))) {
			 
			 	sqlQuery += " NATURAL JOIN ";
			 }
			 
		}
		if (condition != ""){
			
			sqlQuery += " WHERE "+ condition;
		}
	 	sqlQuery += " ;";
		return sqlQuery;
	}
	public int getValueAutoIncrement(String tableName) {
    	int currentValue = -1;
    	String sqlQuery = "Select AUTO_INCREMENT From INFORMATION_SCHEMA.TABLES Where TABLE_SCHEMA = 'ibp-rcp' And TABLE_NAME = '"+tableName+"';";
    	try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
	    		currentValue = rs.getInt(1);
	    	}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("MysqlSelect Error : ");
			System.out.println(e.getMessage());
		}
    	
    	return currentValue;
    }
	public String getLastDataDate(int idProject) {
		String result = "";
		String sqlQuery = "SELECT date FROM test NATURAL JOIN campagne NATURAL JOIN projet Where projet.idProjet = "+idProject+" ORDER BY test.date DESC LIMIT 1";
    	try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
	    		result = rs.getString(1);
	    	}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("MysqlSelect Error : ");
			System.out.println(e.getMessage());
		}
		return result;
	}
	private void createDatabase(String pathToSqlFile, Connection connection) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(pathToSqlFile));
			String str;
			PreparedStatement pstmt = null;
			String sqlQuery = "";
			while ((str = in.readLine()) != null) {
				sqlQuery += str;
				if (sqlQuery.contains(";")) {
					pstmt = connection.prepareStatement(sqlQuery);
					pstmt.execute();
					sqlQuery = "";
				}
			}
			pstmt.close();
			in.close();
		} catch (Exception e) {
			System.err.println("Failed to Execute" + pathToSqlFile +". The error is "+ e.getMessage());
		} 
	}
	public void purgeDatabase(String date) {
		String sqlQuery = "Delete From test Where date < '"+date+"'";
		try {
			PreparedStatement pstmt = connect.prepareStatement(sqlQuery);
			pstmt.execute(sqlQuery);
			sqlQuery = "Select idCampagne from campagne where (SELECT COUNT(idTest) from test WHERE test.idCampagne = campagne.idCampagne) = 0";
			ResultSet rs = pstmt.executeQuery(sqlQuery);
			while (rs.next()) {
	    		sqlQuery = "Delete from campagne where idCampagne  = "+rs.getInt(1);
	    		pstmt = connect.prepareStatement(sqlQuery);
				pstmt.execute(sqlQuery);
	    	}
			sqlQuery = "Select idProjet from projet where (SELECT COUNT(idCampagne) from campagne WHERE campagne.idProjet = projet.idProjet) = 0";
			rs = pstmt.executeQuery(sqlQuery);
			while (rs.next()) {
	    		sqlQuery = "Delete from projet where idProjet  = "+rs.getInt(1);
	    		pstmt = connect.prepareStatement(sqlQuery);
				pstmt.execute(sqlQuery);
	    	}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("MysqlDelete Error : ");
			System.out.println(e.getMessage());
		}
		
	}
	public int verifyNumberDataPeriod(int idProjet, String dateBegin, String dateEnd) {
		int result = 0;
		String sqlQuery = "SELECT COUNT(test.idTest) FROM test NATURAL JOIN campagne WHERE campagne.idProjet = "+idProjet+" AND (test.date BETWEEN '"+dateBegin+"' AND '"+dateEnd+"')";
		PreparedStatement pstmt;
		try {
			pstmt = connect.prepareStatement(sqlQuery);
			ResultSet rs = pstmt.executeQuery(sqlQuery);
			while (rs.next()) {
				result = rs.getInt(1);
			}
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("MysqlVerify Error : ");
			System.out.println(e.getMessage());
		}
		return result;
	}
}
