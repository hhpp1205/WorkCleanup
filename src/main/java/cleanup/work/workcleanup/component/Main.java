package cleanup.work.workcleanup.component;


import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.IOException;
public class Main {


    private static Long  CONTENTSIZE = 9L;

    public static void main(String[] args) throws IOException {
        ExcelHelper eh = new ExcelHelper();
        XSSFSheet sheet = eh.createSheet("삼성");

        //Row 9개, Cell 9개 생성
        for (int rowNum = 0; rowNum < CONTENTSIZE; rowNum++) {
            XSSFRow row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 9; cellNum++) {
                row.createCell(cellNum);
            }
        }

        eh.createSheetTemplate(sheet);

        eh.extractExcelFile(eh.getWorkbook());



    }

}

