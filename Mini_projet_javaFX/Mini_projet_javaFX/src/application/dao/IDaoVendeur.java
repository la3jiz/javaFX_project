package application.dao;

import java.util.List;

import application.enteties.employe;
import application.enteties.vendeur;

public interface IDaoVendeur {
	boolean createVendeur(vendeur v);
	boolean deleteVendeur(vendeur v );
	boolean updateVendeur(vendeur v);
	vendeur findVendeurById(int matricule);
	List<vendeur > findAll();
}
