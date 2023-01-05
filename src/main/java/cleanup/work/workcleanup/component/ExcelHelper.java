package cleanup.work.workcleanup.component;

import cleanup.work.workcleanup.repository.dto.CarInsuranceDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ExcelHelper {

    private static Long  CONTENTSIZE = 9L;
    private final String FilePath = "C:/CarExcel/";
    private final String FileName = "CarExcel";
    private final XSSFWorkbook workbook = new XSSFWorkbook();


    public CellStyle getDefaultStyle() {
        CellStyle defaultStyle = this.workbook.createCellStyle();
        defaultStyle.setWrapText(true);
        defaultStyle.setAlignment(HorizontalAlignment.CENTER);
        defaultStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return defaultStyle;
    }

    public XSSFSheet createSheet(String sheetName) {
        return this.workbook.createSheet(sheetName);
    }

    public XSSFSheet createSheetTemplate(XSSFSheet sheet) {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

        XSSFRow row0 = sheet.getRow(0);
        XSSFCell cell0_0 = row0.getCell(0);
        cell0_0.setCellValue("(" + sheet.getSheetName() + ")" + "보 험 수 리 비 명 세 표");
        cell0_0.setCellStyle(getDefaultStyle());

        XSSFCell cell1_4 = sheet.getRow(1).getCell(4);
        cell1_4.setCellValue("입금");
        cell1_4.setCellStyle(getDefaultStyle());

        XSSFCell cell2_4 = sheet.getRow(2).getCell(4);
        cell2_4.setCellValue("일자");
        cell2_4.setCellStyle(getDefaultStyle());

        createColumn(sheet, 1, 2, 0, 0, "일자");
        createColumn(sheet, 1, 2, 1, 1,  "차종");
        createColumn(sheet, 1, 2, 2,  2,  "차량번호");
        createColumn(sheet, 1, 2, 3, 3,  "청구액");
        createColumn(sheet, 1, 2, 5, 5, "지급액");
        createColumn(sheet, 1, 2, 6, 6,  "면책금");


        return sheet;
    }

    private void createColumn(XSSFSheet sheet, int firstRow, int lastRow, int lastCol, int firstCol, String columnName) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
        XSSFRow row = sheet.getRow(firstRow);
        XSSFCell cell = row.getCell(firstCol);
        cell.setCellValue(columnName);
        cell.setCellStyle(getDefaultStyle());
    }

    public void insertContent(XSSFSheet sheet, List<CarInsuranceDto> ciDtos) {
        for (int rowNum = 0; rowNum < ciDtos.size(); rowNum++) {
            XSSFRow row = sheet.getRow(rowNum);

            XSSFCell cell0 = row.getCell(0);
            cell0.setCellValue(ciDtos.get(rowNum).getBillDate());
            cell0.setCellStyle(getDefaultStyle());

            XSSFCell cell1 = row.getCell(1);
            cell1.setCellValue(ciDtos.get(rowNum).getCarType());
            cell1.setCellStyle(getDefaultStyle());

            XSSFCell cell2 = row.getCell(2);
            cell2.setCellValue(ciDtos.get(rowNum).getCarNumber());
            cell2.setCellStyle(getDefaultStyle());

            XSSFCell cell3 = row.getCell(3);
            cell3.setCellValue(ciDtos.get(rowNum).getBill());
            cell3.setCellStyle(getDefaultStyle());

            XSSFCell cell4 = row.getCell(4);
            cell4.setCellValue(ciDtos.get(rowNum).getPaymentDate());
            cell4.setCellStyle(getDefaultStyle());

            XSSFCell cell5 = row.getCell(5);
            cell5.setCellValue(ciDtos.get(rowNum).getAmount());
            cell5.setCellStyle(getDefaultStyle());

            XSSFCell cell6 = row.getCell(6);
            cell6.setCellValue(ciDtos.get(rowNum).getExcess());
            cell6.setCellStyle(getDefaultStyle());

            XSSFCell cell7 = row.getCell(7);
            cell7.setCellValue(ciDtos.get(rowNum).getBill() - ciDtos.get(rowNum).getAmount());
            cell7.setCellStyle(getDefaultStyle());
        }
    }




    public void extractExcelFile(XSSFWorkbook workbook) throws IOException {
        try {
            FileOutputStream out = new FileOutputStream(new File(FilePath, FileName + ".xls"));
            workbook.write(out);
            out.close();
        } catch (FileNotFoundException e){
            log.info("error={}", e);
        }catch (IOException e){
            log.info("error={}", e);
        } finally {
            workbook.close();
        }
    }


    public XSSFWorkbook getWorkbook() {
        return workbook;
    }
}
