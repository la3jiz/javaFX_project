package application.srevice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.connexion.connexion;
import application.enteties.employe;
import application.enteties.salarie;
import application.enteties.vendeur;

public class salarieService {

	public List<salarie> betweenSal(double min, double max) {
		List<salarie> Lsalarie = new ArrayList<salarie>();
		try {
			Statement st = connexion.getCon().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM salarie ");
			while (res.next()) {
				if (res.getString("categorie").equals("employe")) {
					employeService es = new employeService();
					employe emp = es.findEmployeById(res.getInt("matricule"));
					double sal=res.getDouble("salaire") + emp.getHsupp() * emp.getPHsupp();
					if(sal>=min && sal<=max) {
					Lsalarie.add(new salarie(res.getInt("matricule"), res.getString("nom"), res.getString("email"),
							res.getString("categorie"), res.getDouble("anneRecruit"),
							res.getDouble("salaire") + emp.getHsupp() * emp.getPHsupp(), res.getInt("idEntreprise")));
					}
				}else
					if(res.getString("categorie").equals("vendeur")) {
						vendeurService vs = new vendeurService();
						vendeur vend = vs.findVendeurById(res.getInt("matricule"));
						double sal=res.getDouble("salaire") + vend.getVente() * vend.getPoucentage();
						if(sal>=min && sal<=max) {
						Lsalarie.add(new salarie(res.getInt("matricule"), res.getString("nom"), res.getString("email"),
								res.getString("categorie"), res.getDouble("anneRecruit"),
								res.getDouble("salaire") + vend.getVente() * vend.getPoucentage(), res.getInt("idEntreprise")));
						}
					}
			}
			System.out.println("salarie listed successfully");
			return Lsalarie;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
	
	
	public List<salarie> anciennete() {
		List<salarie> Lsalarie = new ArrayList<salarie>();
		try {
			Statement st = connexion.getCon().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM salarie ORDER BY  anneRecruit ASC");
			while (res.next()) {
				if (res.getString("categorie").equals("employe")) {
					employeService es = new employeService();
					employe emp = es.findEmployeById(res.getInt("matricule"));
					Lsalarie.add(new salarie(res.getInt("matricule"), res.getString("nom"), res.getString("email"),
							res.getString("categorie"), res.getDouble("anneRecruit"),
							res.getDouble("salaire") + emp.getHsupp() * emp.getPHsupp(), res.getInt("idEntreprise")));
				}else
					if(res.getString("categorie").equals("vendeur")) {
						vendeurService vs = new vendeurService();
						vendeur vend = vs.findVendeurById(res.getInt("matricule"));
						Lsalarie.add(new salarie(res.getInt("matricule"), res.getString("nom"), res.getString("email"),
								res.getString("categorie"), res.getDouble("anneRecruit"),
								res.getDouble("salaire") + vend.getVente() * vend.getPoucentage(), res.getInt("idEntreprise")));
					}
			}
			System.out.println("salarie listed successfully");
			return Lsalarie;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public double minimumSalaire() {
		try {
			employeService es = new employeService();
			vendeurService vs = new vendeurService();
			Statement st = connexion.getCon().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM salarie ");
			double minSal=0;
			res.next();
			if(res.getString("categorie").equals("employe")) {
				employe emp = es.findEmployeById(res.getInt("matricule"));
				 minSal=res.getDouble("salaire") + emp.getHsupp() * emp.getPHsupp();

			}else
				if(res.getString("categorie").equals("vendeur")) {
					vendeur vend = vs.findVendeurById(res.getInt("matricule"));
					 minSal=res.getDouble("salaire") + vend.getVente() * vend.getPoucentage();
				}
		
			while (res.next()) {
				if (res.getString("categorie").equals("employe")) {
					employe empp = es.findEmployeById(res.getInt("matricule"));
					double nextMinSal=res.getDouble("salaire") + empp.getHsupp() * empp.getPHsupp();
					if(nextMinSal<minSal) {
					minSal=nextMinSal;
					}
				}else
					if(res.getString("categorie").equals("vendeur")) {
							vendeur vend = vs.findVendeurById(res.getInt("matricule"));
							double nextMinSal=res.getDouble("salaire") +vend.getVente()* vend.getPoucentage();
							if(nextMinSal<minSal) {
							minSal=nextMinSal;
							}
					}
			}
			System.out.println("salarie listed successfully");
			return minSal;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	public List<salarie> salaireFaible() {
		double minSal=minimumSalaire();
		List<salarie> Lsalarie = new ArrayList<salarie>();

		try {
			Statement st = connexion.getCon().createStatement();
			ResultSet res = st.executeQuery("SELECT * FROM salarie ");
			while (res.next()) {
				if (res.getString("categorie").equals("employe")) {
					employeService es = new employeService();
					employe emp = es.findEmployeById(res.getInt("matricule"));
					double sal=res.getDouble("salaire") + emp.getHsupp() * emp.getPHsupp();
					if(sal==minSal) {
					Lsalarie.add(new salarie(res.getInt("matricule"), res.getString("nom"), res.getString("email"),
							res.getString("categorie"), res.getDouble("anneRecruit"),
							res.getDouble("salaire") + emp.getHsupp() * emp.getPHsupp(), res.getInt("idEntreprise")));
					}
				}else
					if(res.getString("categorie").equals("vendeur")) {
						vendeurService vs = new vendeurService();
						vendeur vend = vs.findVendeurById(res.getInt("matricule"));
						double sal=res.getDouble("salaire") + vend.getVente() * vend.getPoucentage();
						if(sal==minSal) {
						Lsalarie.add(new salarie(res.getInt("matricule"), res.getString("nom"), res.getString("email"),
								res.getString("categorie"), res.getDouble("anneRecruit"),
								res.getDouble("salaire") + vend.getVente() * vend.getPoucentage(), res.getInt("idEntreprise")));
						}
					}
			}
			System.out.println("salarie listed successfully");
			return Lsalarie;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
