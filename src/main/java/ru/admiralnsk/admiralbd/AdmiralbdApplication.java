package ru.admiralnsk.admiralbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.admiralnsk.admiralbd.parser.ExcelParser;

import java.io.IOException;


@SpringBootApplication
@EnableWebMvc
public class AdmiralbdApplication {

    public static void main(String[] args) throws IOException {

        ExcelParser.readFromExcel("excel1.xlsx");
        SpringApplication.run(AdmiralbdApplication.class, args);
    }

}