package ru.admiralnsk.admiralbd.parser;


import com.monitorjbl.xlsx.StreamingReader;
import org.apache.poi.ss.usermodel.*;
import ru.admiralnsk.admiralbd.models.Departure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelParser {

    public static List<Departure> readFromExcel(File file) throws IOException {

        InputStream is = new FileInputStream(file);
        List<Departure> departureList = new ArrayList<>();

        Workbook workbook = StreamingReader.builder()
                .rowCacheSize(30)
                .open(is);

        boolean firstFlag = true;
        for (Sheet sheet : workbook) {
            for (Row row : sheet) {
                if (firstFlag) {
                    firstFlag = false;
                    continue;
                }
                boolean departureEmpty = row.getCell(7).getStringCellValue().startsWith("ВАГ");
                if (!departureEmpty) {
                    Departure departure = new Departure();
                    for (Cell cell : row) {
                        //Дата отправления
                        if (cell.getColumnIndex() == 0) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setDepartureDate(null);
                            } else
                                departure.setDepartureDate(cell.getDateCellValue());
                        }
                        //Номер вагона
                        if (cell.getColumnIndex() == 1) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setCarriageNumber(null);
                            } else
                                departure.setCarriageNumber((int) cell.getNumericCellValue());
                        }
                        //Номер документа
                        if (cell.getColumnIndex() == 2) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setDocumentNumber(null);
                            } else
                                departure.setDocumentNumber(cell.getStringCellValue());
                        }
                        //Дата прибытия
                        if (cell.getColumnIndex() == 3) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setArrivalDate(null);
                            } else
                                departure.setArrivalDate(cell.getDateCellValue());
                        }
                        //Дата раскредитования
                        if (cell.getColumnIndex() == 4) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setLendingDate(null);
                            } else
                                departure.setLendingDate(cell.getDateCellValue());
                        }
                        //Вид перевозки
                        if (cell.getColumnIndex() == 5) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setTransportationType(null);
                            } else
                                departure.setTransportationType(cell.getStringCellValue());
                        }
                        //Код груза
                        if (cell.getColumnIndex() == 6) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setCargo(null);
                            } else
                                departure.setCargo((int) cell.getNumericCellValue());
                        }
                        //Груз
                        if (cell.getColumnIndex() == 7) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setCargoType(null);
                            } else
                                departure.setCargoType(cell.getStringCellValue());
                        }
                        //Государство отправления
                        if (cell.getColumnIndex() == 8) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setDepartureCountry(null);
                            } else
                                departure.setDepartureCountry(cell.getStringCellValue());
                        }
                        //Станция отправления СНГ
                        if (cell.getColumnIndex() == 9) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setDepartureStationCIS(null);
                            } else
                                departure.setDepartureStationCIS(cell.getStringCellValue());
                        }
                        //Код Станции отправления СНГ
                        if (cell.getColumnIndex() == 10) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setDepartureStationCISCode(null);
                            } else
                                departure.setDepartureStationCISCode((int) cell.getNumericCellValue());
                        }
                        //Область отправления
                        if (cell.getColumnIndex() == 11) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setDepartureRegion(null);
                            } else
                                departure.setDepartureRegion(cell.getStringCellValue());
                        }
                        //Дорога отправления
                        if (cell.getColumnIndex() == 12) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setDepartureWay(null);
                            } else
                                departure.setDepartureWay(cell.getStringCellValue());
                        }
                        //Станция отправления РФ
                        if (cell.getColumnIndex() == 13) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setDepartureStationRF(null);
                            } else
                                departure.setDepartureStationRF(cell.getStringCellValue());
                        }
                        //Код Станции отправления РФ
                        if (cell.getColumnIndex() == 14) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setDepartureStationRFCode(null);
                            } else
                                departure.setDepartureStationRFCode((int) cell.getNumericCellValue());
                        }
                        //Грузоотправтитель
                        if (cell.getColumnIndex() == 15) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setConsignor(null);
                            } else
                                departure.setConsignor(cell.getStringCellValue());
                        }
                        //Грузоотправитель (ОКПО)
                        if (cell.getColumnIndex() == 16) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setConsignorACEO(null);
                            } else
                                departure.setConsignorACEO((int) cell.getNumericCellValue());
                        }
                        //Государство назначения
                        if (cell.getColumnIndex() == 17) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setDestinationCountry(null);
                            } else
                                departure.setDestinationCountry(cell.getStringCellValue());
                        }
                        //Регион назначения
                        if (cell.getColumnIndex() == 18) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setDestinationRegion(null);
                            } else
                                departure.setDestinationRegion(cell.getStringCellValue());
                        }
                        //Дорога назначения
                        if (cell.getColumnIndex() == 19) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setDestinationWay(null);
                            } else
                                departure.setDestinationWay(cell.getStringCellValue());
                        }
                        //Станция назначения РФ
                        if (cell.getColumnIndex() == 20) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setDestinationStationRF(null);
                            } else
                                departure.setDestinationStationRF(cell.getStringCellValue());
                        }
                        //Код станции назначения РФ
                        if (cell.getColumnIndex() == 21) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setDestinationStationRFCode(null);
                            } else
                                departure.setDestinationStationRFCode((int) cell.getNumericCellValue());
                        }
                        //Станция назначения СНГ
                        if (cell.getColumnIndex() == 22) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setDestinationStationCIS(null);
                            } else
                                departure.setDestinationStationCIS(cell.getStringCellValue());
                        }
                        //Код станции назначения СНГ
                        if (cell.getColumnIndex() == 23) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setDestinationStationCISCode(null);
                            } else
                                departure.setDestinationStationCISCode((int) cell.getNumericCellValue());
                        }
                        //Грузополучатель
                        if (cell.getColumnIndex() == 24) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setConsignee(null);
                            } else
                                departure.setConsignee(cell.getStringCellValue());
                        }
                        //Грузополучатель (ОКПО)
                        if (cell.getColumnIndex() == 25) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setConsigneeACEO(null);
                            } else
                                departure.setConsigneeACEO((int) cell.getNumericCellValue());
                        }
                        //Род вагона
                        if (cell.getColumnIndex() == 26) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setCarriageKind(null);
                            } else
                                departure.setCarriageKind(cell.getStringCellValue());
                        }
                        //Тип вагона
                        if (cell.getColumnIndex() == 27) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setCarriageType(null);
                            } else
                                departure.setCarriageType(cell.getStringCellValue());
                        }
                        //Плательщик
                        if (cell.getColumnIndex() == 28) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setPayer(null);
                            } else
                                departure.setPayer(cell.getStringCellValue());
                        }
                        //Собственник
                        if (cell.getColumnIndex() == 29) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setOwner(null);
                            } else
                                departure.setOwner(cell.getStringCellValue());
                        }
                        //Арендатор
                        if (cell.getColumnIndex() == 30) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setRenter(null);
                            } else
                                departure.setRenter(cell.getStringCellValue());
                        }
                        //Оператор
                        if (cell.getColumnIndex() == 31) {
                            if (cell.getCellType() == CellType.BLANK || cell.getStringCellValue().equals("0")) {
                                departure.setOperator(null);
                            } else
                                departure.setOperator(cell.getStringCellValue());
                        }
                        //ВАГОН-КМ
                        if (cell.getColumnIndex() == 32) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setCarriageKm(null);
                            } else
                                departure.setCarriageKm((int) cell.getNumericCellValue());
                        }
                        //Объем
                        if (cell.getColumnIndex() == 33) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setVolume(null);
                            } else
                                departure.setVolume((int) cell.getNumericCellValue());
                        }
                        //Тариф
                        if (cell.getColumnIndex() == 34) {
                            if (cell.getCellType() == CellType.BLANK) {
                                departure.setRate(null);
                            } else
                                departure.setRate((int) cell.getNumericCellValue());
                        }
                    }
                    departureList.add(departure);
                }
            }
        }
        return departureList;
    }

}