package cleanup.work.workcleanup.service;

import cleanup.work.workcleanup.controller.form.ExcelForm;
import cleanup.work.workcleanup.repository.CarInsuranceRepository;
import cleanup.work.workcleanup.repository.CarRepository;
import cleanup.work.workcleanup.repository.dto.CarDto;
import cleanup.work.workcleanup.repository.dto.CarInsuranceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExcelService {


    private final String FilePath = "C:/CarExcel/";
    private final CarRepository carRepository;
    private final CarInsuranceRepository carInsuranceRepository;
    private final CarService carService;

    public void getCarInsuranceExcel(ExcelForm excelForm)  {
        String fileName = excelForm.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "~" + excelForm.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 보험";

        List<CarInsuranceDto> ciDtos = carInsuranceRepository.excelSearch(excelForm);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(excelForm.getInsuranceName());
        createRowAndCell(sheet, ciDtos.size());

        createCarInsuranceTemplate(workbook, sheet, excelForm.getInsuranceName());
        insertCarInsurance(workbook, sheet, ciDtos);

        try {
            extractExcelFile(workbook, fileName);
        }catch (IOException e){
            log.info("error={}", e);
        }
    }

    public void getCarExcel(ExcelForm excelForm) {
        String fileName = excelForm.getStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "~" + excelForm.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 차량";
        List<CarDto> carDtos = carRepository.excelSearch(excelForm);
        List<Long> carIds = carService.getCarIds(carDtos);
        List<CarInsuranceDto> carInsuranceDtos = carInsuranceRepository.findCarInsuranceDtoByCarIds(carIds);
        carService.matchingCarAndInsurance(carDtos, carInsuranceDtos);

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("차량  목록");
        createRowAndCell(sheet, carDtos.size());

        createCarTemplate(workbook, sheet);
        insertCar(workbook, sheet, carDtos);

        try {
            extractExcelFile(workbook, fileName);
        }catch (IOException e){
            log.info("error={}", e);
        }
    }


    private void createRowAndCell(XSSFSheet sheet, int rowSize) {
        for (int rowNum = 0; rowNum < rowSize+5; rowNum++) {
            XSSFRow row = sheet.createRow(rowNum);
            for (int cellNum = 0; cellNum < 11; cellNum++) {
                row.createCell(cellNum);
            }
        }
    }

    public void createCarInsuranceTemplate(XSSFWorkbook workbook, XSSFSheet sheet, String insuranceName) {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));

        XSSFRow row0 = sheet.getRow(0);
        XSSFCell cell0_0 = row0.getCell(0);
        cell0_0.setCellValue("(" + insuranceName + ")" + "보 험 수 리 비 명 세 표");
        cell0_0.setCellStyle(getDefaultStyle(workbook));

        XSSFCell cell1_4 = sheet.getRow(1).getCell(4);
        cell1_4.setCellValue("입금");
        cell1_4.setCellStyle(getDefaultStyle(workbook));

        XSSFCell cell2_4 = sheet.getRow(2).getCell(4);
        cell2_4.setCellValue("일자");
        cell2_4.setCellStyle(getDefaultStyle(workbook));

        createTableHeader(workbook, sheet, 1, 2, 0, 0, "일자");
        createTableHeader(workbook, sheet, 1, 2, 1, 1,  "차종");
        createTableHeader(workbook, sheet, 1, 2, 2,  2,  "차량번호");
        createTableHeader(workbook, sheet, 1, 2, 3, 3,  "청구액");
        createTableHeader(workbook, sheet, 1, 2, 5, 5, "지급액");
        createTableHeader(workbook, sheet, 1, 2, 6, 6,  "면책금");
    }

    private void createCarTemplate(XSSFWorkbook workbook, XSSFSheet sheet) {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));

        XSSFRow row0 = sheet.getRow(0);
        XSSFCell cell0_0 = row0.getCell(0);
        cell0_0.setCellValue("차량 목록");
        cell0_0.setCellStyle(getDefaultStyle(workbook));

        createTableHeader(workbook, sheet, 1, 2, 0, 0, "입고날짜");
        createTableHeader(workbook, sheet, 1, 2, 1, 1, "차종");
        createTableHeader(workbook, sheet, 1, 2, 2, 2, "차량번호");
        createTableHeader(workbook, sheet, 1, 2, 3, 3, "보험여부");
        createTableHeader(workbook, sheet, 1, 2, 4, 4, "연락처");
        createTableHeader(workbook, sheet, 1, 2, 5, 5, "적요");
        createTableHeader(workbook, sheet, 1, 2, 6, 6, "견인유무");
        createTableHeader(workbook, sheet, 1, 2, 7, 7, "부가세");
        createTableHeader(workbook, sheet, 1, 2, 8, 8, "출고날짜");
        createTableHeader(workbook, sheet, 1, 2, 9, 9, "입금날짜");
    }

    public void insertCarInsurance(XSSFWorkbook workbook, XSSFSheet sheet, List<CarInsuranceDto> ciDtos) {
        for (int rowNum = 3; rowNum < ciDtos.size()+3; rowNum++) {
            XSSFRow row = sheet.getRow(rowNum);

            XSSFCell cell0 = row.getCell(0);
            cell0.setCellValue(ciDtos.get(rowNum-3).getBillDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cell0.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell1 = row.getCell(1);
            cell1.setCellValue(ciDtos.get(rowNum-3).getCarType());
            cell1.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell2 = row.getCell(2);
            cell2.setCellValue(ciDtos.get(rowNum-3).getCarNumber());
            cell2.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell3 = row.getCell(3);
            cell3.setCellValue(ciDtos.get(rowNum-3).getBill());
            cell3.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell4 = row.getCell(4);
            cell4.setCellValue(ciDtos.get(rowNum-3).getPaymentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cell4.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell5 = row.getCell(5);
            cell5.setCellValue(ciDtos.get(rowNum-3).getAmount());
            cell5.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell6 = row.getCell(6);
            cell6.setCellValue(ciDtos.get(rowNum-3).getExcess());
            cell6.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell7 = row.getCell(7);
            cell7.setCellValue(ciDtos.get(rowNum-3).getBill() - ciDtos.get(rowNum-3).getAmount());
            cell7.setCellStyle(getDefaultStyle(workbook));
        }
    }

    private void insertCar(XSSFWorkbook workbook, XSSFSheet sheet, List<CarDto> carDtos) {
        for (int rowNum = 3; rowNum < carDtos.size() + 3; rowNum++) {
            XSSFRow row = sheet.getRow(rowNum);
            List<CarInsuranceDto> carInsuranceDtos = carDtos.get(rowNum - 3).getCarInsuranceDtos();

            String insuranceName = "";
            for(CarInsuranceDto ci : carInsuranceDtos){
                insuranceName += ci.getInsuranceName() + "\n";
            }
            String paymanetDate = "";
            for(CarInsuranceDto ci : carInsuranceDtos){
                paymanetDate += ci.getPaymentDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\n";
            }

            XSSFCell cell0 = row.getCell(0);
            cell0.setCellValue(carDtos.get(rowNum-3).getCreateDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cell0.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell1 = row.getCell(1);
            cell1.setCellValue(carDtos.get(rowNum-3).getCarType());
            cell1.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell2 = row.getCell(2);
            cell2.setCellValue(carDtos.get(rowNum-3).getCarNumber());
            cell2.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell3 = row.getCell(3);
            cell3.setCellValue(insuranceName);
            cell3.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell4 = row.getCell(4);
            cell4.setCellValue(carDtos.get(rowNum-3).getPhoneNumber());
            cell4.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell5 = row.getCell(5);
            cell5.setCellValue(carDtos.get(rowNum-3).getComment());
            cell5.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell6 = row.getCell(6);
            cell6.setCellValue(carDtos.get(rowNum-3).getTow());
            cell6.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell7 = row.getCell(7);
            cell7.setCellValue(carDtos.get(rowNum-3).getVat());
            cell7.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell8 = row.getCell(8);
            cell8.setCellValue(carDtos.get(rowNum-3).getReleaseDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            cell8.setCellStyle(getDefaultStyle(workbook));

            XSSFCell cell9 = row.getCell(9);
            cell9.setCellValue(paymanetDate);
            cell9.setCellStyle(getDefaultStyle(workbook));
        }
    }


    private void createTableHeader(XSSFWorkbook workbook, XSSFSheet sheet, int firstRow, int lastRow, int lastCol, int firstCol, String columnName) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
        XSSFRow row = sheet.getRow(firstRow);
        XSSFCell cell = row.getCell(firstCol);
        cell.setCellValue(columnName);
        cell.setCellStyle(getDefaultStyle(workbook));
    }

    private CellStyle getDefaultStyle(XSSFWorkbook workbook) {
        CellStyle defaultStyle = workbook.createCellStyle();
        defaultStyle.setWrapText(true);
        defaultStyle.setAlignment(HorizontalAlignment.CENTER);
        defaultStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return defaultStyle;
    }

    private void extractExcelFile(XSSFWorkbook workbook, String fileName) throws IOException {
        try {
            FileOutputStream out = new FileOutputStream(new File(FilePath, fileName + ".xls"));
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


}

