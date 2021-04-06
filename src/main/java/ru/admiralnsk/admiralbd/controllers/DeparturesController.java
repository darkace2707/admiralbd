package ru.admiralnsk.admiralbd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.admiralnsk.admiralbd.models.DepartureWayAndConsignorPickHelper;
import ru.admiralnsk.admiralbd.models.DeparturesCount;
import ru.admiralnsk.admiralbd.services.DepartureService;


@Controller
@RequestMapping("/departures")
public class DeparturesController {

    private final DepartureService departureService;

    @Autowired
    public DeparturesController(DepartureService departureService) {
        this.departureService = departureService;
    }

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
        for (DeparturesCount el : departureService.getConsigneeCountWithDepartureWayAndConsignor(departureWay, consignor)) {
            System.out.println(el.getKey() + " " + el.getValue());
        }
        System.out.println("-----------------------------------------------");
        long start = System.nanoTime();
        for (DeparturesCount el : departureService.getDeparturesCountWithDepartureWayAndConsignorByAllMonth(departureWay, consignor)) {
            System.out.println(el.getKey() + " " + el.getValue());
        }
        System.out.println(System.nanoTime() - start);
        return "consignorView";
    }
}