package ru.admiralnsk.admiralbd.parser;



import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class ExcelParser {

    /*public static String parse(String fileName) {
        //инициализируем потоки
        String result = "";
        InputStream inputStream = null;
        XSSFWorkbook workBook = null;
        try {
            inputStream = new FileInputStream(fileName);
            workBook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //разбираем первый лист входного файла на объектную модель
        Sheet sheet = workBook.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        //проходим по всему листу
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                CellType cellType = cell.getCellType();
                //перебираем возможные типы ячеек
                switch (cellType) {
                    case STRING:
                        result += cell.getStringCellValue() + "=";
                        break;
                    case NUMERIC:
                        result += "[" + cell.getNumericCellValue() + "]";
                        break;

                    case FORMULA:
                        result += "[" + cell.getNumericCellValue() + "]";
                        break;
                    default:
                        result += "|";
                        break;
                }
            }
            result += "\n";
        }

        return result;
    }*/
    public static boolean readFromExcel(String file) throws IOException{
//        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file));
//        XSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
//        XSSFRow row = myExcelSheet.getRow(0);
//
//        if(row.getCell(0).getCellType() == CellType.STRING){
//            String name = row.getCell(0).getStringCellValue();
//            System.out.println("name : " + name);
//        }
//
//        if(row.getCell(0).getCellType() == CellType.NUMERIC){
//            Date birthdate = row.getCell(0).getDateCellValue();
//            System.out.println("birthdate :" + birthdate);
//        }
//
//        myExcelBook.close();

        InputStream is = new FileInputStream(new File(file));
        Workbook workbook = StreamingReader.builder()
                .rowCacheSize(5)    // number of rows to keep in memory (defaults to 10) Думаю около 30-50 можно грузить
                .bufferSize(1024)     // buffer size to use when reading InputStream to file (defaults to 1024)
                .open(is);

        for (Sheet sheet : workbook){
            System.out.println(sheet.getSheetName());
            for (Row r : sheet) {
                for (Cell c : r) {
                    System.out.print(c.getStringCellValue() + " ");
                }
                System.out.println();
            }
        }
        return false;
    }

}