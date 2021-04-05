package ru.admiralnsk.admiralbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication
public class AdmiralbdApplication extends WebMvcAutoConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(AdmiralbdApplication.class, args);
    }

}