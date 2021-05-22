package ru.admiralnsk.admiralbd.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.admiralnsk.admiralbd.exceptions.ExcelNotStructuredException;
import ru.admiralnsk.admiralbd.models.DepartureWayAndConsignorFormRequest;
import ru.admiralnsk.admiralbd.services.DepartureService;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


@RequiredArgsConstructor
@Controller
@RequestMapping("/departures")
public class DeparturesController {

    private final DepartureService departureService;

    @GetMapping("")
    @PreAuthorize("hasAuthority('read')")
    public String main(Model model, Authentication authentication) {
        model.addAttribute("authorities", authentication.getAuthorities().toString());
        model.addAttribute("formData", new DepartureWayAndConsignorFormRequest());
        model.addAttribute("departureWays", departureService.findDistinctDepartureWays());
        return "main";
    }

    @GetMapping("/consignor-view")
    @PreAuthorize("hasAuthority('read')")
    public String show(@RequestParam(name = "departureWay") String departureWay,
                       @RequestParam(name = "consignor") String consignor, Model model) {

        model.addAttribute("departureWay", departureWay);
        model.addAttribute("consignor", consignor);
        model.addAttribute("monthsCount",
                departureService.findDeparturesCountByDepartureWayAnAndConsignorAllMonth(departureWay, consignor));
        model.addAttribute("consigneesCount",
                departureService.findConsigneeCountWithDepartureWayAndConsignor(departureWay, consignor));
        model.addAttribute("CargoTypesCount",
                departureService.findCargoTypeByDepartureWayAndConsignor(departureWay, consignor));
        model.addAttribute("CarriageKindsCount",
                departureService.findCarriageKindByDepartureWayAndConsignor(departureWay, consignor));

        model.addAttribute("departureStationRFTree",
                departureService.findDepartureStationRFCountTreeByDepartureWayAndConsignor(departureWay, consignor));
        model.addAttribute("ownersTree",
                departureService.findOwnersCountTreeByDepartureWayAndConsignor(departureWay, consignor));

        return "consignor-view";
    }

    @GetMapping("/upload-excel")
    @PreAuthorize("hasAuthority('write')")
    public String uploadExcelGET(Model model) {
        return "upload-excel";
    }

    @PostMapping("/upload-excel")
    @PreAuthorize("hasAuthority('write')")
    public String submit(@RequestParam("excelFile") MultipartFile file) throws IOException, ExecutionException, InterruptedException, ExcelNotStructuredException {
        departureService.putDepartures(file);
        return "redirect:/departures";
    }


}