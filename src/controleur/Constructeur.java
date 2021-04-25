package controleur;

import modele.DAP;
import modele.Workbook;

import java.io.File;

public class Constructeur {

    public Constructeur(){}

    //Initialisation d'un constructeur pour DAP
    public void construireWorkbookDAP(File fichierExcelSource, File fichierExcelRelation){

        //On constuit nos deux workook pour les DAP
        Workbook workbookSource = new Workbook(fichierExcelSource);
        Workbook workbookRelation = new Workbook(fichierExcelRelation);

        // On lance la pseudonymisation des données
        new DAP(workbookSource,workbookRelation);
    }
}
