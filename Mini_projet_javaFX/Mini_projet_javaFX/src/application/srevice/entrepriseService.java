package application.srevice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.connexion.connexion;
import application.dao.IDaoEntreprise;
import application.enteties.entreprise;



public class entrepriseService implements IDaoEntreprise{
	
	 public entrepriseService() {}
	
	public boolean createEntreprise(entreprise e) {
		try {
			Statement st =connexion.getCon().createStatement();
			int res=st.executeUpdate("INSERT INTO entreprise(nom)VALUES('"+e.getNomE()+"')");
			if(res!=0) {
				System.out.println("entreprise created successfully");
				return true;
			}
		} catch (SQLException exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteEntreprise(entreprise e) {
		try {
			PreparedStatement pst=connexion.getCon().prepareStatement("DELETE  FROM entreprise WHERE idEntrepise=?");
			pst.setLong(1, e.getIdE());
			int res=pst.executeUpdate();
			if(res!=0) {
				System.out.println("entreprise deleted successfully");
				return true;
			}
		} catch (SQLException exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEntreprise(entreprise e) {
		try {
			PreparedStatement pst=connexion.getCon().prepareStatement("UPDATE entreprise SET nom=? WHERE idEntrepise=?");
			pst.setString(1,e.getNomE());
			pst.setLong(2, e.getIdE());
			int res=pst.executeUpdate();
			if(res!=0) {
				System.out.println("entreprise Updated successfully");
				return true;
			}
		} catch (SQLException exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
		return false;
	}

	@Override
	public entreprise findEntrepriseById(int id) {
		try {
			Statement st =connexion.getCon().createStatement();
			ResultSet res=st.executeQuery("SELECT * FROM entreprise WHERE idEntrepise="+id);
			if(res.next()) {
				System.out.println("entreprise finded successfully");
				return new entreprise(res.getInt("idEntrepise"),res.getString("nom"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<entreprise> findAll() {
		try {
			List<entreprise> listEnt=new ArrayList<entreprise>();
			Statement st =connexion.getCon().createStatement();
			ResultSet res=st.executeQuery("SELECT * FROM entreprise ");
			
			while(res.next()) {
				listEnt.add(new entreprise(res.getInt("idEntrepise"),res.getString("nom")));
			}
			System.out.println("entreprises listed successfully");
			return listEnt;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error in listing entreprise items occured !!");
			return null;
		}
		
	}

	@Override
	public entreprise lastEntreprise() {
		try {
			entreprise ent =new entreprise("dummyent");
			
			Statement st =connexion.getCon().createStatement();
			ResultSet res=st.executeQuery("SELECT * FROM entreprise ");
			while(res.next()) {
				 ent =new entreprise(res.getInt("idEntrepise"),res.getString("nom"));
			}
			System.out.println("entreprises listed successfully");
			return ent;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error in listing entreprise items occured !!");
			return null;
		}
	}

}
