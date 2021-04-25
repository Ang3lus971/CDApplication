package modele;

import controleur.Constructeur;
import controleur.Controleur;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class DAP {

    public DAP(){

        // On récupère les fichiers nous servant à pseudonymiser
        File fichierExcelSource = new File("C:\\Users\\Lsvai\\Desktop\\Conception et déveleppement d'une application\\Jeu de données\\exemple_ce.xls");
        File fichierExcelRelation = new File("C:\\Users\\Lsvai\\Desktop\\Conception et déveleppement d'une application\\Relation.xls");

        // On lance la creation du workook pour les DAP
        Constructeur constructeurDAP = new Constructeur();
        constructeurDAP.construireWorkbookDAP(fichierExcelSource,fichierExcelRelation);
    }

    public DAP(Workbook workbookSource,Workbook workbookRelation) {

        Relation relation = new Relation();
        Controleur controleur = new Controleur();

        // On récupère la première page des deux workbook
        HSSFSheet sheetSource = workbookSource.getWorkbook().getSheetAt(0);
        HSSFSheet sheetRelation = workbookRelation.getWorkbook().getSheetAt(0);

        // On initialise deux itérateurs, ils vont nous permettre de parcourir nos lignes
        Iterator<Row> itRowSource1 = sheetSource.rowIterator();
        Iterator<Row> itRowSource2 = sheetSource.rowIterator();

        int i = 1;
        int nombreDeLigneSource = 0;
        int taillePseudo = 3;
        String pseudo;

        // On commence par compter le nombre de nos lignes
        while (itRowSource1.hasNext()) {
            Row rowTest = itRowSource1.next();
            if (rowTest.getRowNum()!=0 && (rowTest.getCell(0).getCellType() != CellType.BLANK || rowTest.getCell(0).getCellType() != CellType._NONE)) {
                nombreDeLigneSource+=1;
            }
        }

        // Selon le nombre de nos lignes, la taille du pseudo va changer
        while (nombreDeLigneSource >= Math.pow(26,taillePseudo)) {
            taillePseudo += 1;
        }

        // On utilise notre second iterateur pour reparcourir notre fichier et créer nos pseudos
         while (itRowSource2.hasNext()){
             Row rowSource = itRowSource2.next();
             if (rowSource.getRowNum()!=0 && (rowSource.getCell(0).getCellType() != CellType.BLANK || rowSource.getCell(0).getCellType() != CellType._NONE)) {
                 pseudo = relation.Pseudo((taillePseudo));

                 // On teste si notre nouveau pseudo existe déja ou non dans le workbook, si oui, on en crée un nouveau
                 while (controleur.isPseudoIdentique(sheetRelation, pseudo)) {
                     pseudo = relation.Pseudo((taillePseudo));
                 }
                 // On récupère le nom
                 String nom = rowSource.getCell(0).toString();

                 // On injècte ce dernier ainsi que le pseudo associé dans le workbook des ralation
                 workbookRelation.getWorkbook().getSheetAt(0).createRow(i).createCell(0).setCellValue(nom);
                 workbookRelation.getWorkbook().getSheetAt(0).getRow(i).createCell(1).setCellValue(pseudo);

                 // Enfin, on remplace le nom par le pseudo
                 rowSource.getCell(0).setCellValue(pseudo);
                 i++;
             }
        }
        try {

            // On fini par exporter nos workook dans les fichiers associés
            FileOutputStream outWorkbookSource = new FileOutputStream("C:\\Users\\Lsvai\\Desktop\\Conception et déveleppement d'une application\\Jeu de données\\exemple_ce.xls");
            FileOutputStream outWorkbookRelation = new FileOutputStream("C:\\Users\\Lsvai\\Desktop\\Conception et déveleppement d'une application\\Relation.xls");
            workbookSource.getWorkbook().write(outWorkbookSource);
            workbookRelation.getWorkbook().write(outWorkbookRelation);
            outWorkbookSource.close();
            outWorkbookRelation.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
