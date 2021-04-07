package ru.admiralnsk.admiralbd.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.admiralnsk.admiralbd.dao.DepartureDAO;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/departures")
public class DeparturesRestController {

    private final DepartureDAO departureDAO;

    @GetMapping("/consignors")
    public List<String> getDistinctConsignors(@RequestParam("departureWay") String departureWay) {
        return departureDAO.getDistinctConsignorsWithDepartureWay(departureWay);
    }
}
