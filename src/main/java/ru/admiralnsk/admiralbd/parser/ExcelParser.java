package ru.admiralnsk.admiralbd.parser;



import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.*;
import ru.admiralnsk.admiralbd.models.Departure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

public class ExcelParser {

    public static boolean readFromExcel(String file) throws IOException{

        InputStream is = new FileInputStream(new File(file));
        int i=0;
        ArrayList<Departure> depArr = new ArrayList<Departure>();

        Workbook workbook = StreamingReader.builder()
                .rowCacheSize(5)    // number of rows to keep in memory (defaults to 10) Думаю около 30-50 можно грузить
                .bufferSize(1024)     // buffer size to use when reading InputStream to file (defaults to 1024)
                .open(is);

        for (Sheet sheet : workbook){
            //System.out.println(sheet.getSheetName());
            for (Row r : sheet) {
                i++;
                if(i>1){
                    String s=r.getCell(7).getStringCellValue();
                    if(!s.startsWith("ВАГ"))
                    {
                        Departure d= new Departure();
                        for (Cell c : r) {
                            //Дата отправления
                            if(c.getColumnIndex()==0) {
                                if (c == null || c.getCellType() == CellType.BLANK){
                                    d.setDepartureDate(null);
                                }
                                else
                                    d.setDepartureDate(c.getDateCellValue());
                            }
                            //Номер вагона
                            if(c.getColumnIndex()==1){
                                if (c == null || c.getCellType() == CellType.BLANK){
                                    d.setCarriageNumber(null);
                                }
                                else
                                    d.setCarriageNumber((int) c.getNumericCellValue());
                            }
                            //Номер документа
                            if(c.getColumnIndex()==2){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setDocumentNumber(null);
                                }
                                else
                                    d.setDocumentNumber(c.getStringCellValue());
                            }
                            //Дата прибытия
                            if(c.getColumnIndex()==3){
                                if (c == null || c.getCellType() == CellType.BLANK){
                                    d.setArrivalDate(null);
                                }
                                else
                                    d.setArrivalDate(c.getDateCellValue());
                            }
                            //Дата раскредитования
                            if(c.getColumnIndex()==4){
                                if (c == null || c.getCellType() == CellType.BLANK){
                                    d.setLendingDate(null);
                                }
                                else
                                    d.setLendingDate(c.getDateCellValue());
                            }
                            //Вид перевозки
                            if(c.getColumnIndex()==5){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setTransportationType(null);
                                }
                                else
                                    d.setTransportationType(c.getStringCellValue());
                            }
                            //Код груза
                            if(c.getColumnIndex()==6){
                                if (c == null || c.getCellType() == CellType.BLANK){
                                    d.setCargo(null);
                                }
                                else
                                    d.setCargo((int) c.getNumericCellValue());
                            }
                            //Груз
                            if(c.getColumnIndex()==7){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setCargoType(null);
                                }
                                else
                                    d.setCargoType(c.getStringCellValue());
                            }
                            //Госсударство отправления
                            if(c.getColumnIndex()==8){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setDepartureCountry(null);
                                }
                                else
                                    d.setDepartureCountry(c.getStringCellValue());
                            }
                            //Станция отправления СНГ
                            if(c.getColumnIndex()==9){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setDepartureStationCIS(null);
                                }
                                else
                                    d.setDepartureStationCIS(c.getStringCellValue());
                            }
                            //Код Станции отправления СНГ
                            if(c.getColumnIndex()==10){
                                if (c == null || c.getCellType() == CellType.BLANK ){
                                    d.setDepartureStationCISCode(null);
                                }
                                else
                                    d.setDepartureStationCISCode((int) c.getNumericCellValue());
                            }
                            //Область отправления
                            if(c.getColumnIndex()==11){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setDepartureRegion(null);
                                }
                                else
                                    d.setDepartureRegion(c.getStringCellValue());
                            }
                            //Дорога отправления
                            if(c.getColumnIndex()==12){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setDepartureWay(null);
                                }
                                else
                                    d.setDepartureWay(c.getStringCellValue());
                            }
                            //Станция отправления РФ
                            if(c.getColumnIndex()==13){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setDepartureStationRF(null);
                                }
                                else
                                    d.setDepartureStationRF(c.getStringCellValue());
                            }
                            //Код Станции отправления РФ
                            if(c.getColumnIndex()==14){
                                if (c == null || c.getCellType() == CellType.BLANK ){
                                    d.setDepartureStationRFCode(null);
                                }
                                else
                                    d.setDepartureStationRFCode((int) c.getNumericCellValue());
                            }
                            //Грузоотправтитель
                            if(c.getColumnIndex()==15){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setConsignor(null);
                                }
                                else
                                    d.setConsignor(c.getStringCellValue());
                            }
                            //Грузоотправитель (ОКПО)
                            if(c.getColumnIndex()==16){
                                if (c == null || c.getCellType() == CellType.BLANK ){
                                    d.setConsignorACEO(null);
                                }
                                else
                                    d.setConsignorACEO((int) c.getNumericCellValue());
                            }
                            //Государство назначения
                            if(c.getColumnIndex()==17){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setDestinationCountry(null);
                                }
                                else
                                    d.setDestinationCountry(c.getStringCellValue());
                            }
                            //Регион назначения
                            if(c.getColumnIndex()==18){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setDestinationRegion(null);
                                }
                                else
                                    d.setDestinationRegion(c.getStringCellValue());
                            }
                            //Дорога назначения
                            if(c.getColumnIndex()==19){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setDestinationWay(null);
                                }
                                else
                                    d.setDestinationWay(c.getStringCellValue());
                            }
                            //Станция назначения РФ
                            if(c.getColumnIndex()==20){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setDestinationStationRF(null);
                                }
                                else
                                    d.setDestinationStationRF(c.getStringCellValue());
                            }
                            //Код станции назначения РФ
                            if(c.getColumnIndex()==21){
                                if (c == null || c.getCellType() == CellType.BLANK ){
                                    d.setDestinationStationRFCode(null);
                                }
                                else
                                    d.setDestinationStationRFCode((int) c.getNumericCellValue());
                            }
                            //Станция назначения СНГ
                            if(c.getColumnIndex()==22){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setDestinationStationCIS(null);
                                }
                                else
                                    d.setDestinationStationCIS(c.getStringCellValue());
                            }
                            //Код станции назначения СНГ
                            if(c.getColumnIndex()==23){
                                if (c == null || c.getCellType() == CellType.BLANK ){
                                    d.setDestinationStationCISCode(null);
                                }
                                else
                                    d.setDestinationStationCISCode((int) c.getNumericCellValue());
                            }
                            //Грузополучатель
                            if(c.getColumnIndex()==24){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setConsignee(null);
                                }
                                else
                                    d.setConsignee(c.getStringCellValue());
                            }
                            //Грузополучатель (ОКПО)
                            if(c.getColumnIndex()==25){
                                if (c == null || c.getCellType() == CellType.BLANK ){
                                    d.setConsigneeACEO(null);
                                }
                                else
                                    d.setConsigneeACEO((int) c.getNumericCellValue());
                            }
                            //Род вагона
                            if(c.getColumnIndex()==26){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setCarriageKind(null);
                                }
                                else
                                    d.setCarriageKind(c.getStringCellValue());
                            }
                            //Тип вагона
                            if(c.getColumnIndex()==27){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setCarriageType(null);
                                }
                                else
                                    d.setCarriageType(c.getStringCellValue());
                            }
                            //Плательщик
                            if(c.getColumnIndex()==28){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setPayer(null);
                                }
                                else
                                    d.setPayer(c.getStringCellValue());
                            }
                            //Собственник
                            if(c.getColumnIndex()==29){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setOwner(null);
                                }
                                else
                                    d.setOwner(c.getStringCellValue());
                            }
                            //Арендатор
                            if(c.getColumnIndex()==30){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setRenter(null);
                                }
                                else
                                    d.setRenter(c.getStringCellValue());
                            }
                            //Оператор
                            if(c.getColumnIndex()==31){
                                if (c == null || c.getCellType() == CellType.BLANK || c.getStringCellValue()=="0"){
                                    d.setOperator(null);
                                }
                                else
                                    d.setOperator(c.getStringCellValue());
                            }
                            //ВАГОН-КМ
                            if(c.getColumnIndex()==32){
                                if (c == null || c.getCellType() == CellType.BLANK ){
                                    d.setCarriageKm(null);
                                }
                                else
                                    d.setCarriageKm((int) c.getNumericCellValue());
                            }
                            //Объем
                            if(c.getColumnIndex()==33){
                                if (c == null || c.getCellType() == CellType.BLANK ){
                                    d.setVolume(null);
                                }
                                else
                                    d.setVolume((int) c.getNumericCellValue());
                            }
                            //Тариф
                            if(c.getColumnIndex()==34){
                                if (c == null || c.getCellType() == CellType.BLANK ){
                                    d.setRate(null);
                                }
                                else
                                    d.setRate((int) c.getNumericCellValue());
                            }


                        }
                        depArr.add(d);
                    }
                }
            }
        }
        Departure dTest=depArr.get(2);
        dTest.show();
        System.out.println(depArr.size());
        return false;
    }

}