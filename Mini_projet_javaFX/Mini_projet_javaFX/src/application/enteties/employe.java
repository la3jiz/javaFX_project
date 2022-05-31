package application.enteties;

public class employe extends salarie {

	private double Hsupp;
	private double PHsupp;

	public employe(int matricule, String nom, String email, String Cat, double recruitDate, int idEntreprise,
			double salaireFix, double hsupp, double pHsupp) {
		super(matricule, nom, email, Cat, recruitDate, salaireFix, idEntreprise);
		Hsupp = hsupp;
		PHsupp = pHsupp;
	}

	public double getHsupp() {
		return Hsupp;
	}

	public void setHsupp(double hsupp) {
		Hsupp = hsupp;
	}

	public double getPHsupp() {
		return PHsupp;
	}

	public void setPHsupp(double pHsupp) {
		PHsupp = pHsupp;
	}

	@Override
	public String toString() {
		return "\n employe: "+"\n"+" matricule: " + this.getMatricule() + "\n"+" nom:"  + this.getNom() + "\n email: "
				+ this.getEmail() + "\n salaire: " + this.getSalaireFix() + "\n date embauche: " + this.getRecruitDate()
				+ "\n nombre heures supplementaires: " + this.getHsupp()+"\n";
	}

}
