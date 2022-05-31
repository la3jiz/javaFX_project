package application.entrepriseController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.enteties.entreprise;
import application.srevice.entrepriseService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddEntrepriseController implements Initializable {
	@FXML
	private TextField tfEntName;
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
	private Button quitBtn;
	@FXML
	private TableView<entreprise> table;
	//@FXML
	//private TableColumn<entreprise, Integer> emailCol;
	@FXML
	private TableColumn<entreprise, Integer> idCol;
	@FXML
	private TableColumn<entreprise, String> entNameCol;
	private DataClass data;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//emailCol.setCellValueFactory(new PropertyValueFactory<>("idE"));
		entrepriseService es =new entrepriseService();

		idCol.setCellValueFactory(new PropertyValueFactory<>("idE"));
		entNameCol.setCellValueFactory(new PropertyValueFactory<>("nomE"));
		this.data = new DataClass();
		table.getItems().addAll(es.findAll());
	}

	public void importer() {
		table.getItems().clear();
		table.getItems().addAll(data.getImportList());

	}
	public void addEntreprise() {
		entrepriseService es =new entrepriseService();
		
		entreprise e=new entreprise(tfEntName.getText());
		es.createEntreprise(e);
		e.setIdE(es.lastEntreprise().getIdE()+1);
		
		table.getItems().add(e);
		data.getImportList().add(e);
	
		
	}
	
	public void update() {
		entreprise e=table.getSelectionModel().getSelectedItem();
		data.getImportList().remove(table.getSelectionModel().getSelectedItem());
		e.setNomE(tfEntName.getText());
		data.getImportList().add(e);
		entrepriseService es =new entrepriseService();
		es.updateEntreprise(table.getSelectionModel().getSelectedItem());
		table.getItems().clear();
		table.getItems().addAll(es.findAll());

	}
	
	public void supprimer() {
	table.getItems().remove(table.getSelectionModel().getSelectedItem());
	entrepriseService es=new entrepriseService();
	es.deleteEntreprise(table.getSelectionModel().getSelectedItem());
	data.getImportList().remove(table.getSelectionModel().getSelectedItem());
	}
	
	
	public void exporter() {
		File expFile = new File("D:\\fichiers\\entrepriseExportedFile.txt");
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
