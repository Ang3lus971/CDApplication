package modele;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Workbook {
    HSSFWorkbook workbook;

    // Module de création de workbook
    public Workbook(File fichierExcel){
        try {
            FileInputStream lecteur = new FileInputStream(fichierExcel);
            try {
                workbook = new HSSFWorkbook(lecteur);
            }
            catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
    }

    public HSSFWorkbook getWorkbook() {
        return workbook;
    }
}
