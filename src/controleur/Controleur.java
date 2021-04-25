package controleur;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.util.Iterator;

public class Controleur {

    public Controleur(){}

    /*
    public boolean isEntier(String entree) {
        try {
            Integer.valueOf(entree);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    */

    /*
    public boolean SheetExist(HSSFWorkbook workbook,int numPage) {
        try {
            HSSFSheet page = workbook.getSheetAt(numPage);
            return true;
        } catch (IllegalArgumentException a) {
            return false;
        }
    }
     */

    // Module qui test si il y déja un pseudo identique à celui da=onné en paramètre dans le workbook
    public boolean isPseudoIdentique(HSSFSheet sheetRelation, String pseudo) {
        Iterator<Row> itRowRelation = sheetRelation.rowIterator();
        while (itRowRelation.hasNext()) {
            Row rowRelation = itRowRelation.next();
            if (rowRelation.getRowNum() != 0 && (rowRelation.getCell(0).getCellType() != CellType.BLANK || rowRelation.getCell(0).getCellType() != CellType.BLANK)) {
                if (rowRelation.getCell(1).toString().equals(pseudo)) {
                    return true;
                }
            }
        }
        return false;
    }
}
