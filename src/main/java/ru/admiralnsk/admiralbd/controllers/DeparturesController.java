package ru.admiralnsk.admiralbd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.admiralnsk.admiralbd.dao.DepartureDAO;
import ru.admiralnsk.admiralbd.models.StringWrapper;

@Controller
@RequestMapping("/departures")
public class DeparturesController {

    private final DepartureDAO departureDAO;

    @Autowired
    public DeparturesController(DepartureDAO departureDAO) {
        this.departureDAO = departureDAO;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("departureWay", new StringWrapper());
        return "index";
    }

    @GetMapping("/departureWay")
    public String show(@RequestParam(name = "value") String departureWay, Model model) {
        model.addAttribute("count", departureDAO.getDeparturesCountWithDepartureWay(departureWay));
        return "departureView";
    }
}