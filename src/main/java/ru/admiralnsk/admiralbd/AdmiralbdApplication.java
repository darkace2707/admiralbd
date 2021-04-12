package ru.admiralnsk.admiralbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.admiralnsk.admiralbd.models.Departure;
import ru.admiralnsk.admiralbd.parser.ExcelParser;

import java.io.File;
import java.io.IOException;
import java.util.List;


@SpringBootApplication
@EnableWebMvc
public class AdmiralbdApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(AdmiralbdApplication.class, args);
    }

}