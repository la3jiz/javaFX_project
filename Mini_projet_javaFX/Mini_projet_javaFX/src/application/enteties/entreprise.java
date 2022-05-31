package application.enteties;

import java.util.HashMap;

public class entreprise {
	private int idE;
	private String nomE;
	private HashMap<Integer,salarie>  listeE;
	
	
	
	public entreprise(String nomE) {
		super();
		this.nomE = nomE;
		this.listeE=new HashMap<Integer,salarie>();
	}
	
	public entreprise(int idE,String nomE) {
		super();
		this.idE=idE;
		this.nomE = nomE;
		this.listeE=new HashMap<Integer,salarie>();
	}
	
	public int getIdE() {
		return idE;
	}
	public void setIdE(int idE) {
		this.idE = idE;
	}
	public String getNomE() {
		return nomE;
	}
	public void setNomE(String nomE) {
		this.nomE = nomE;
	}
	public HashMap<Integer, salarie> getListeE() {
		return listeE;
	}
	public void setListeE(HashMap<Integer, salarie> listeE) {
		this.listeE = listeE;
	}

	@Override
	public String toString() {
		return "entreprise [idE=" + idE + ", nomE=" + nomE + ", listeE=" + listeE + "]";
	}
	

}
