package ru.admiralnsk.admiralbd.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.admiralnsk.admiralbd.models.DepartureWayAndConsignorPickHelper;
import ru.admiralnsk.admiralbd.models.DeparturesCount;
import ru.admiralnsk.admiralbd.services.DepartureService;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/departures")
public class DeparturesController {

    private final DepartureService departureService;

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("formData", new DepartureWayAndConsignorPickHelper());
        model.addAttribute("departureWays", departureService.getDistinctDepartureWays());
        return "main";
    }

    @GetMapping("/consignorView")
    public String show(@RequestParam(name = "departureWay") String departureWay,
                       @RequestParam(name = "consignor") String consignor, Model model) {
        model.addAttribute("count", departureService.getDeparturesCountWithDepartureWay(departureWay));

        return "consignorView";
    }
}