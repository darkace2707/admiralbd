package ru.admiralnsk.admiralbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;



@SpringBootApplication
@EnableWebMvc
public class AdmiralbdApplication {

    public static void main(String[] args) {
        //ExcelParserTest test=new ExcelParserTest();
        SpringApplication.run(AdmiralbdApplication.class, args);
    }

}