package application.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import application.enteties.employe;
import application.enteties.entreprise;
import application.enteties.vendeur;
import application.srevice.employeService;
import application.srevice.entrepriseService;
import application.srevice.vendeurService;


public class connexion {
	static String URL="jdbc:mysql://localhost:3306/javafx_mini_projet";
	static String db_name="root";
	static String db_pwd="";
	public static Connection con  = null;   
    static Statement  stmt  = null;
    
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			 con = DriverManager.getConnection(URL, db_name, db_pwd);
			System.out.println("Connection successfully established with database. . .");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		return con;
	}
public static void main(String args[]) {
	connexion.getCon();
	entreprise e=new entreprise("laziz");
	entrepriseService es=new entrepriseService();
	//es.createEntreprise(e);
	
	//e.setIdE(1);
	//es.deleteEntreprise(e);
	
	//entreprise e1=new entreprise(2,"lazi");
	//es.updateEntreprise(e1);
	
	//System.out.println(es.findEntrepriseById(2));
	
	//System.out.println(es.findAll());
	
	vendeur v=new vendeur(44,"Laaziz","test@email.com","vendeur",2004,2,2.2,1833,1744);
	vendeurService vs=new vendeurService();
	vs.createVendeur(v);
//System.out.println(vs.findAll());
}
}