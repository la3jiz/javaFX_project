package application.srevice;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import application.connexion.connexion;
import application.dao.IDaoEmploye;
import application.enteties.employe;
import application.enteties.entreprise;
import application.enteties.vendeur;

public class employeService implements IDaoEmploye{

	@Override
	public boolean createEmploye(employe e) {
		try {
			if(e.getRecruitDate()<2005)
				e.setSalaireFix(400);
			else
				e.setSalaireFix(280);
			
			Statement st =connexion.getCon().createStatement();
			
			int res=st.executeUpdate("INSERT INTO salarie VALUES ('"+e.getMatricule()+"','"+e.getNom()+"','"+e.getEmail()+"','"+e.getRecruitDate()+"','"+e.getSalaireFix()+"','"+e.getIdEntreprise()+"','"+e.getCat()+"')");
			int res1=st.executeUpdate("INSERT INTO employee VALUES('"+e.getMatricule()+"','"+e.getHsupp()+"','"+e.getPHsupp()+"')");


			if(res!=0 && res1!=0) {
				System.out.println("employe created successfully");
				return true;
			}
		} catch (SQLException exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
		return false;
	}

	
	@Override
	public boolean deleteEmploye(employe e) {
		try {
			PreparedStatement pst=connexion.getCon().prepareStatement("DELETE  FROM salarie WHERE matricule=?");
			pst.setLong(1, e.getMatricule());
			
			PreparedStatement pst1=connexion.getCon().prepareStatement("DELETE  FROM employee WHERE matricule=?");
			pst1.setLong(1, e.getMatricule());
			
			int res=pst.executeUpdate();
			int res1=pst1.executeUpdate();
			
			if(res != 0 && res1 != 0) {
				System.out.println("employee deleted successfully");
				return true;
			}
		} catch (SQLException exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateEmploye(employe e) {
		try {
			PreparedStatement pst=connexion.getCon().prepareStatement("UPDATE salarie SET nom=?, email=?, categorie=?, anneRecruit=?, salaire=?,idEntreprise=? WHERE matricule=?");
			pst.setString(1,e.getNom());
			pst.setString(2, e.getEmail());
			pst.setString(3,e.getCat());
			pst.setDouble(4, e.getRecruitDate());
			pst.setDouble(5, e.getSalaireFix());
			pst.setInt(6,e.getIdEntreprise());
			pst.setInt(7, e.getMatricule());
			
			PreparedStatement pst1=connexion.getCon().prepareStatement("UPDATE employee SET HSupp=?, PHSupp=? WHERE matricule=?");
			pst1.setDouble(1, e.getHsupp());
			pst1.setDouble(2,e.getPHsupp());
			pst1.setInt(3, e.getMatricule());
			
			int res=pst.executeUpdate();
			int res1=pst1.executeUpdate();
			
			if(res!=0 && res1!=0) {
				System.out.println("employe Updated successfully");
				return true;
			}
		} catch (SQLException exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
		return false;
	}

	@Override
	public employe findEmployeById(int mat) {
		try {
			Statement st =connexion.getCon().createStatement();
			Statement st1 =connexion.getCon().createStatement();

			ResultSet res=st.executeQuery("SELECT * FROM salarie WHERE matricule="+mat);
			ResultSet res1=st1.executeQuery("SELECT * FROM employee WHERE matricule="+mat);

			if(res.next() && res1.next()) {
				System.out.println("employee finded successfully");
				return new employe(res.getInt("matricule"),res.getString("nom"),res.getString("email"),res.getString("categorie"),res.getDouble("anneRecruit"),res.getInt("idEntreprise"),res.getDouble("salaire")+res1.getDouble("HSupp")*res1.getDouble("PHSupp"),res1.getDouble("HSupp"),res1.getDouble("PHSupp"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<employe> findAll() {
		try {
			List<employe> listEmp=new ArrayList<employe>();
			Statement st =connexion.getCon().createStatement();
			ResultSet res=st.executeQuery("SELECT * FROM employee ");

			while(res.next()) {
				listEmp.add(findEmployeById(res.getInt("matricule")));
			}
			System.out.println("employee listed successfully");
			return listEmp;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error in listing entreprise items occured !!");
			return null;
	}}
	


	}