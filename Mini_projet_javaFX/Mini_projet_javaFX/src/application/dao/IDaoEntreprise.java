package application.dao;

import java.util.List;

import application.enteties.entreprise;


public interface IDaoEntreprise {
	boolean createEntreprise(entreprise e);
boolean deleteEntreprise(entreprise e );
boolean updateEntreprise(entreprise e);
entreprise findEntrepriseById(int id);
List<entreprise > findAll();
entreprise lastEntreprise();
}
