package ru.admiralnsk.admiralbd.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.admiralnsk.admiralbd.models.DepartureWayAndConsignorFormRequest;
import ru.admiralnsk.admiralbd.services.DepartureService;


@RequiredArgsConstructor
@Controller
@RequestMapping("/departures")
public class DeparturesController {

    private final DepartureService departureService;

    @GetMapping("")
    public String main(Model model) {
        model.addAttribute("formData", new DepartureWayAndConsignorFormRequest());
        model.addAttribute("departureWays", departureService.findDistinctDepartureWays());
        return "main";
    }

    @GetMapping("/consignorView")
    public String show(@RequestParam(name = "departureWay") String departureWay,
                       @RequestParam(name = "consignor") String consignor, Model model) {

        model.addAttribute("departureWay", departureWay);
        model.addAttribute("consignor", consignor);
        model.addAttribute("monthsCount", departureService.findDeparturesCountByDepartureWayAnAndConsignorAllMonth(departureWay, consignor));
        model.addAttribute("consigneesCount", departureService.findConsigneeCountWithDepartureWayAndConsignor(departureWay, consignor));
        model.addAttribute("CargoTypesCount", departureService.findCargoTypeByDepartureWayAndConsignor(departureWay, consignor));
        model.addAttribute("CarriageKindsCount", departureService.findCarriageKindByDepartureWayAndConsignor(departureWay, consignor));
        return "consignorView";
    }
}