package ru.admiral.nsk.admiralbd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departures")
public class DeparturesController {
    @GetMapping("/")
    public String sayHello() {
        return "index";
    }
}