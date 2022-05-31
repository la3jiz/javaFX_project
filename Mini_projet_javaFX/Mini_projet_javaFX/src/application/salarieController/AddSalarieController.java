package application.salarieController;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.enteties.employe;
import application.enteties.entreprise;
import application.enteties.salarie;
import application.enteties.vendeur;

import application.srevice.employeService;
import application.srevice.entrepriseService;
import application.srevice.salarieService;
import application.srevice.vendeurService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddSalarieController implements Initializable {
	@FXML 
	private TextArea tfDetails;
	@FXML
	private ChoiceBox cbEntreprise;
	@FXML
	private RadioButton re;
	@FXML 
	private RadioButton rbLemp;
	@FXML
	private RadioButton rbLvend;
	@FXML
	private RadioButton rv;
	@FXML
	private TextField tfMat;
	@FXML
	private TextField tfNom;
	@FXML
	private TextField tfEmail;
	@FXML
	private TextField tfDateRec;
	@FXML
	private TextField tfSalaireMin;
	@FXML
	private TextField tfSalaireMax;
	@FXML
	private TextField tfTHV;
	@FXML
	private TextField tfHVS;
	@FXML
	private Button addBtn;
	@FXML
	private Button updateBtn;
	@FXML
	private Button exportBtn;
	@FXML
	private Button importBtn;
	@FXML
	private Button removeBtn;
	@FXML
	private Button detailsBtn;
	@FXML
	private Button quitBtn;
	@FXML
	private Button catBtn;
	@FXML
	private Button dateBtn;
	@FXML
	private Button maxTauxBtn;
	@FXML
	private Button minSalaireBtn;
	@FXML
	private Button minMaxBtn;
	@FXML
	private TableView<salarie> table;
	@FXML
	private TableColumn<salarie, Integer> matriculeCol;
	@FXML
	private TableColumn<salarie, String> nomCol;
	@FXML
	private TableColumn<salarie, String> emailCol;
	@FXML
	private TableColumn<salarie, String> catCol;
	@FXML
	private TableColumn<salarie, Double> salaireCol;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		matriculeCol.setCellValueFactory(new PropertyValueFactory<>("matricule"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		catCol.setCellValueFactory(new PropertyValueFactory<>("cat"));
		salaireCol.setCellValueFactory(new PropertyValueFactory<>("salaireFix"));
		

		
		
		employeService es=new employeService();
		vendeurService vs=new vendeurService();
		entrepriseService ents=new entrepriseService();
		for(entreprise i:ents.findAll()) {
		cbEntreprise.getItems().add(i.getNomE()+"-"+i.getIdE());
		//cbEntreprise.setValue(i.getIdE());
		}
		table.getItems().addAll(es.findAll());
		table.getItems().addAll(vs.findAll());

	}
	
	public void setHSuppEmp() {
		File inputFile = new File("D:\\fichiers\\employe_data.txt");
		FileReader fr;
		BufferedReader br;
		String PHSupp="";
		try {
			rv.setSelected(false);
			fr = new FileReader(inputFile);
			 br=new BufferedReader(fr);
				String s;
				boolean ok=false;
				while((s=br.readLine())!=null) {
					String [] fileData=s.split(" ");
					if(fileData[0].equals("PHSupp")) {
						PHSupp=fileData[1];
					}
				}
				br.close();
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tfTHV.setText(PHSupp);
	}
	
	public void setPourcentageVend() {
		File inputFile = new File("D:\\fichiers\\employe_data.txt");
		FileReader fr;
		BufferedReader br;
		String pourcentage="";
		try {
			re.setSelected(false);
			fr = new FileReader(inputFile);
			 br=new BufferedReader(fr);
				String s;
				boolean ok=false;
				while((s=br.readLine())!=null) {
					String [] fileData=s.split(" ");
					if(fileData[0].equals("pourcentage")) {
						pourcentage=fileData[1];
					}
				}
				br.close();
			 
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tfTHV.setText(pourcentage);
	}
	
	public void addSalarie() {
		String str=(String) cbEntreprise.getSelectionModel().getSelectedItem();
		entrepriseService ents=new entrepriseService();
		if(re.isSelected()) {
			employe  emp = new employe(Integer.parseInt(tfMat.getText()), tfNom.getText(), tfEmail.getText(),"employe",Double.parseDouble(tfDateRec.getText()),Integer.parseInt(str.substring(str.indexOf("-")+1)), 0,Double.parseDouble(tfTHV.getText()), Double.parseDouble(tfHVS.getText()));
			employeService es = new employeService();
			es.createEmploye(emp);
			table.getItems().add(es.findEmployeById(emp.getMatricule()));
			ents.findEntrepriseById(emp.getIdEntreprise()).getListeE().put(emp.getMatricule(), emp);
		} else if(rv.isSelected()) {
			vendeur v = new vendeur(Integer.parseInt(tfMat.getText()), tfNom.getText(), tfEmail.getText(),"vendeur",Double.parseDouble(tfDateRec.getText()), Integer.parseInt(str.substring(str.indexOf("-")+1)),0,Double.parseDouble(tfTHV.getText()), Double.parseDouble(tfHVS.getText()));
			vendeurService vs = new vendeurService();
			vs.createVendeur(v);
			table.getItems().add(vs.findVendeurById(v.getMatricule()));
			ents.findEntrepriseById(v.getIdEntreprise()).getListeE().put(v.getMatricule(), v);
		}
	
		
	}
	public void supprimer() {
		if(table.getSelectionModel().getSelectedItem().getCat().equals("employe")) {
			employeService es=new employeService();
			es.deleteEmploye(es.findEmployeById(table.getSelectionModel().getSelectedItem().getMatricule()));
			table.getItems().remove(table.getSelectionModel().getSelectedItem());
		}else
		if(table.getSelectionModel().getSelectedItem().getCat().equals("vendeur")){
			vendeurService vs=new vendeurService();
			vs.deleteVendeur(vs.findVendeurById(table.getSelectionModel().getSelectedItem().getMatricule()));
			table.getItems().remove(table.getSelectionModel().getSelectedItem());
		}
		
	}
	public void update() {
		if(table.getSelectionModel().getSelectedItem().getCat().equals("employe")) {
			employeService es=new employeService();
			employe emp=es.findEmployeById(table.getSelectionModel().getSelectedItem().getMatricule());
			tfMat.setText(String.valueOf(emp.getMatricule()));
			tfNom.setText(emp.getNom());
			tfEmail.setText(emp.getEmail());
			tfDateRec.setText(String.valueOf(emp.getRecruitDate()));
			re.setSelected(true);
			rv.setSelected(false);
			tfTHV.setText(String.valueOf(emp.getPHsupp()));
			tfHVS.setText(String.valueOf(emp.getHsupp()));
		}else
		if(table.getSelectionModel().getSelectedItem().getCat().equals("vendeur")){
			vendeurService vs=new vendeurService();
			entrepriseService ents=new entrepriseService();
			vendeur vend=vs.findVendeurById(table.getSelectionModel().getSelectedItem().getMatricule());
			tfMat.setText(String.valueOf(vend.getMatricule()));
			tfNom.setText(vend.getNom());
			tfEmail.setText(vend.getEmail());
			tfDateRec.setText(String.valueOf(vend.getRecruitDate()));
			//cbEntreprise.getItems();
			rv.setSelected(true);
			re.setSelected(false);
			tfTHV.setText(String.valueOf(vend.getPoucentage()));
			tfHVS.setText(String.valueOf(vend.getVente()));
		}
		
	}
	
	public void ValidateUpdate() {
		String str=(String) cbEntreprise.getSelectionModel().getSelectedItem();
		employeService es = new employeService();
		vendeurService vs = new vendeurService();
		double sal;
		
		if(Double.parseDouble(tfDateRec.getText())<2005)
			 sal=400+Double.parseDouble(tfHVS.getText())*Double.parseDouble(tfTHV.getText());
		else
			 sal=280+Double.parseDouble(tfHVS.getText())*Double.parseDouble(tfTHV.getText());
		
		if(re.isSelected()) {
		
			employe  emp = new employe(Integer.parseInt(tfMat.getText()), tfNom.getText(), tfEmail.getText(),"employe",Double.parseDouble(tfDateRec.getText()),Integer.parseInt(str.substring(str.indexOf("-")+1)),sal,Double.parseDouble(tfHVS.getText()),Double.parseDouble(tfTHV.getText()));
			es.updateEmploye(emp);
			
		} else if(rv.isSelected()) {
			vendeur v = new vendeur(Integer.parseInt(tfMat.getText()), tfNom.getText(), tfEmail.getText(),"vendeur",Double.parseDouble(tfDateRec.getText()), Integer.parseInt(str.substring(str.indexOf("-")+1)),sal,Double.parseDouble(tfHVS.getText()),Double.parseDouble(tfTHV.getText()));
			vs.updateVendeur(v);
		}
		table.getItems().clear();
		table.getItems().addAll(es.findAll());
		table.getItems().addAll(vs.findAll());

		
	}
	
	public void listCat() {
		employeService  es=new employeService();
		vendeurService vs=new vendeurService();
		table.getItems().clear();
		if (rbLemp.isSelected()) {
			table.getItems().addAll(es.findAll());
		}else 
			if(rbLvend.isSelected()) {
			table.getItems().addAll(vs.findAll());
		}
	}
	
	public void details() {
		if(table.getSelectionModel().getSelectedItem().getCat().equals("employe")) {
			employeService es=new employeService();
			employe emp=es.findEmployeById(table.getSelectionModel().getSelectedItem().getMatricule());
			tfDetails.setText(emp.toString());
		}else
		if(table.getSelectionModel().getSelectedItem().getCat().equals("vendeur")){
			vendeurService vs=new vendeurService();
			vendeur vend=vs.findVendeurById(table.getSelectionModel().getSelectedItem().getMatricule());
			tfDetails.setText(vend.toString());
		}
	}
	
	public void listerMinMax() {
		salarieService sv=new salarieService();
		List<salarie> listSalMinMax=sv.betweenSal(Double.parseDouble(tfSalaireMin.getText()),Double.parseDouble(tfSalaireMax.getText()));
		table.getItems().clear();
		table.getItems().addAll(listSalMinMax);
		}
	
	public void listAnciennete() {
		salarieService sv=new salarieService();
		List<salarie> listAnciennete=sv.anciennete();
		table.getItems().clear();
		table.getItems().addAll(listAnciennete);
	}
	
	public void listerMaxTauxVente() {
		vendeurService vs=new vendeurService();
		List<vendeur> Lmtv=vs.maxTauxVente();
		table.getItems().clear();
		table.getItems().addAll(Lmtv);
	}
	
	public void salaireFaible() {
		salarieService sv=new salarieService();
		List<salarie> listFaibleSalaire=sv.salaireFaible();
		table.getItems().clear();
		table.getItems().addAll(listFaibleSalaire);
	}
	
	public void exporter() {
		File expFile = new File("D:\\fichiers\\exportedFile.txt");
		try {
			FileWriter expFileReader=new FileWriter(expFile);
			expFileReader.write(table.getItems().toString());
			expFileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void exit() {
		System.exit(0);
	}


}
