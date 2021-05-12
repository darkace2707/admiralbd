package ru.admiralnsk.admiralbd.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.admiralnsk.admiralbd.services.DepartureService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/departures")
public class DeparturesRestController {

    private final DepartureService departureService;

    @GetMapping("/consignors")
    @PreAuthorize("hasAuthority('read')")
    public List<String> getDistinctConsignors(@RequestParam("departure-way") String departureWay) {
        return departureService.findDistinctConsignorsByDepartureWay(departureWay);
    }
}

