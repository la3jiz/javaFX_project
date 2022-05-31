package application.entrepriseController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import application.enteties.entreprise;
import application.srevice.entrepriseService;

public class DataClass {
	private List<entreprise> importList;
	private List<entreprise> exportList;
public DataClass() {
	entrepriseService es =new entrepriseService();
	importList=new ArrayList<entreprise>(es.findAll());
	
	exportList=new ArrayList<entreprise>();
}
public List<entreprise> getImportList(){
	return importList; 
}

public List<entreprise> getExportList(){
	return exportList; 
}

public void setExportList(List<entreprise> exportList) {
	this.exportList.addAll(exportList);
	for (entreprise p:this.exportList)
		System.out.println(p);
}



}
