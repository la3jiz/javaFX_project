package application.enteties;

public class vendeur  extends salarie{

	private double vente;
	private double poucentage;
	public vendeur(int matricule, String nom, String email, String cat,double recruitDate, int idEntreprise, double salaireFix ,double vente,double poucentage) {
		super(matricule, nom, email, cat,recruitDate, salaireFix,idEntreprise);
		this.vente = vente;
		this.poucentage = poucentage;
	}
	public double getVente() {
		return vente;
	}
	public void setVente(double vente) {
		this.vente = vente;
	}
	public double getPoucentage() {
		return poucentage;
	}
	public void setPoucentage(double poucentage) {
		this.poucentage = poucentage;
	}
	@Override
	public String toString() {
		return "\n employe: \n matricule: " + this.getMatricule() + "\n nom: " + this.getNom() + "\n email: "
				+ this.getEmail() + "\n salaire: " + this.getSalaireFix() + "\n date embauche: " + this.getRecruitDate()
				+ "\n taux de vente: " + this.getVente()+"\n";
	}

}
