package ru.admiralnsk.admiralbd.parser;


import com.monitorjbl.xlsx.StreamingReader;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.admiralnsk.admiralbd.mappers.DepartureFieldsMapper;
import ru.admiralnsk.admiralbd.models.Departure;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
@Component
public class ExcelParser {

    private final DepartureFieldsMapper departureFieldsMapper;

    public List<Departure> readFromExcel(File excelFile) throws IOException {

        InputStream is = new FileInputStream(excelFile);
        List<Departure> departureList = new ArrayList<>();

        try (Workbook workbook = StreamingReader.builder().rowCacheSize(30).open(is)) {
            for (Sheet sheet : workbook) {
                StreamSupport.stream(sheet.spliterator(), false).skip(1).forEach(row -> {
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



}