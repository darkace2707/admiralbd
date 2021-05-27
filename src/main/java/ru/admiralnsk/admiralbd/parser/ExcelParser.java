package ru.admiralnsk.admiralbd.parser;


import com.monitorjbl.xlsx.StreamingReader;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.admiralnsk.admiralbd.exceptions.ExcelNotStructuredException;
import ru.admiralnsk.admiralbd.mappers.DepartureFieldsMapper;
import ru.admiralnsk.admiralbd.models.Departure;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
public class ExcelParser {

    private final DepartureFieldsMapper departureFieldsMapper;

    public List<Departure> readFromExcel(MultipartFile excelFile) throws IOException, ExcelNotStructuredException {

        InputStream is = excelFile.getInputStream();
        List<Departure> departureList = new ArrayList<>();
        try (Workbook workbook = StreamingReader.builder().rowCacheSize(30).open(is)) {
            for (Sheet sheet : workbook) {
                if (!(this.isExcelFileStructureRight((
                        Objects.requireNonNull(StreamSupport.stream(sheet.spliterator(), false)
                                .findFirst()
                                .orElse(null))
                )))) {
                    throw new ExcelNotStructuredException("Incorrect file structure");
                }

                StreamSupport.stream(sheet.spliterator(), false).forEach(row -> {
                    if (this.isDepartureEmpty(row)) return;
                    Departure departure = new Departure();
                    departureFieldsMapper.map(row, departure);
                    departureList.add(departure);
                });
            }
        }

        return departureList;
    }

    private boolean isDepartureEmpty(Row row) {
        return row.getCell(7).getStringCellValue().startsWith("ВАГ");
    }

    private boolean isExcelFileStructureRight(Row row) {

        String cellZero = row.getCell(0).getStringCellValue();
        if (!(cellZero.equals("Дата отправления")))
            return false;
        String cellOne = row.getCell(1).getStringCellValue();
        if (!(cellOne.equals("Номер вагона")))
            return false;
        String cellTwo = row.getCell(2).getStringCellValue();
        if (!(cellTwo.equals("Номер документа")))
            return false;
        String cellThree = row.getCell(3).getStringCellValue();
        if (!(cellThree.equals("Дата прибытия")))
            return false;
        String cellFour = row.getCell(4).getStringCellValue();
        if (!(cellFour.equals("Дата раскредитования")))
            return false;
        String cellFive = row.getCell(5).getStringCellValue();
        if (!(cellFive.equals("Вид перевозки")))
            return false;
        String cellSix = row.getCell(6).getStringCellValue();
        if (!(cellSix.equals("Код груза")))
            return false;
        String cellSeven = row.getCell(7).getStringCellValue();
        if (!(cellSeven.equals("Груз")))
            return false;
        String cellEight = row.getCell(8).getStringCellValue();
        if (!(cellEight.equals("Государство отправления")))
            return false;
        String cellNine = row.getCell(9).getStringCellValue();
        if (!(cellNine.equals("Станция отправления СНГ")))
            return false;
        String cellTen = row.getCell(10).getStringCellValue();
        if (!(cellTen.equals("Код станции отправления СНГ")))
            return false;
        String cellEleven = row.getCell(11).getStringCellValue();
        if (!(cellEleven.equals("Область отправления")))
            return false;
        String cellTwelve = row.getCell(12).getStringCellValue();
        if (!(cellTwelve.equals("Дорога отправления")))
            return false;
        String cellThirteen = row.getCell(13).getStringCellValue();
        if (!(cellThirteen.equals("Станция отправления РФ")))
            return false;
        String cellFourteen = row.getCell(14).getStringCellValue();
        if (!(cellFourteen.equals("Код станции отправления РФ")))
            return false;
        String cellFifteen = row.getCell(15).getStringCellValue();
        if (!(cellFifteen.equals("Грузоотправитель")))
            return false;
        String cellSixteen = row.getCell(16).getStringCellValue();
        if (!(cellSixteen.equals("Грузоотправитель (ОКПО)")))
            return false;
        String cellSeventeen = row.getCell(17).getStringCellValue();
        if (!(cellSeventeen.equals("Государство назначения")))
            return false;
        String cellEighteen = row.getCell(18).getStringCellValue();
        if (!(cellEighteen.equals("Область назначения")))
            return false;
        String cellNineteen = row.getCell(19).getStringCellValue();
        if (!(cellNineteen.equals("Дорога назначения")))
            return false;
        String cellTwenty = row.getCell(20).getStringCellValue();
        if (!(cellTwenty.equals("Станция назначения РФ")))
            return false;
        String cellTwentyOne = row.getCell(21).getStringCellValue();
        if (!(cellTwentyOne.equals("Код станции назначения РФ")))
            return false;
        String cellTwentyTwo = row.getCell(22).getStringCellValue();
        if (!(cellTwentyTwo.equals("Станция назначения СНГ")))
            return false;
        String cellTwentyThree = row.getCell(23).getStringCellValue();
        if (!(cellTwentyThree.equals("Код станции назначения СНГ")))
            return false;
        String cellTwentyFour = row.getCell(24).getStringCellValue();
        if (!(cellTwentyFour.equals("Грузополучатель")))
            return false;
        String cellTwentyFive = row.getCell(25).getStringCellValue();
        if (!(cellTwentyFive.equals("Грузополучатель (ОКПО)")))
            return false;
        String cellTwentySix = row.getCell(26).getStringCellValue();
        if (!(cellTwentySix.equals("Род вагона")))
            return false;
        String cellTwentySeven = row.getCell(27).getStringCellValue();
        if (!(cellTwentySeven.equals("Тип вагона")))
            return false;
        String cellTwentyEight = row.getCell(28).getStringCellValue();
        if (!(cellTwentyEight.equals("Плательщик")))
            return false;
        String cellTwentyNine = row.getCell(29).getStringCellValue();
        if (!(cellTwentyNine.equals("Собственник")))
            return false;
        String cellThirty = row.getCell(30).getStringCellValue();
        if (!(cellThirty.equals("Арендатор")))
            return false;
        String cellThirtyOne = row.getCell(31).getStringCellValue();
        if (!(cellThirtyOne.equals("Оператор")))
            return false;
        String cellThirtyTwo = row.getCell(32).getStringCellValue();
        if (!(cellThirtyTwo.equals("Вагоно-км")))
            return false;
        String cellThirtyThree = row.getCell(33).getStringCellValue();
        if (!(cellThirtyThree.equals("Объем")))
            return false;
        String cellThirtyFour = row.getCell(34).getStringCellValue();
        return cellThirtyFour.equals("Тариф");
    }


}