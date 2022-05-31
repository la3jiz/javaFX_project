package application.enteties;

public class salarie {

	private int matricule;
	private String nom;
	private String email;
	private double recruitDate;
	private double salaireFix;
	private String cat;
	private int idEntreprise;
	
	public salarie(int matricule, String nom, String email,String cat, double recruitDate, double salaireFix,int idEntreprise) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.email = email;
		this.cat=cat;
		this.recruitDate = recruitDate;
		this.salaireFix = salaireFix;
		this.idEntreprise=idEntreprise;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public int getMatricule() {
		return matricule;
	}

	public void setMatricule(int matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getRecruitDate() {
		return recruitDate;
	}

	public void setRecruitDate(double recruitDate) {
		this.recruitDate = recruitDate;
	}

	public double getSalaireFix() {
		return salaireFix;
	}

	public void setSalaireFix(double salaireFix) {
		this.salaireFix = salaireFix;
	}

	public int getIdEntreprise() {
		return idEntreprise;
	}

	public void setIdEntreprise(int idEntreprise) {
		this.idEntreprise = idEntreprise;
	}

	@Override
	public String toString() {
		return "salaire [matricule=" + matricule + ", nom=" + nom + ", email=" + email + ", recruitDate=" + recruitDate
				+ ", salaireFix=" + salaireFix + "]";
	}
	
	
}
