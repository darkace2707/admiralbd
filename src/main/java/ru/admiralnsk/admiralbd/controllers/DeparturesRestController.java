package ru.admiralnsk.admiralbd.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.admiralnsk.admiralbd.models.Node;
import ru.admiralnsk.admiralbd.services.DepartureService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/departures")
public class DeparturesRestController {

    private final DepartureService departureService;

    @GetMapping("/consignors")
    public List<String> getDistinctConsignors(@RequestParam("departureWay") String departureWay) {
        return departureService.findDistinctConsignorsByDepartureWay(departureWay);
    }

    @GetMapping("/departureStationRfTreeTable")
    public List<Node> departureStationRf() {
        return departureService.findDepartureStationRFCountTreeByDepartureWayAndConsignor("ДВС", "ГЛАВНЫЙ МАТЕРИАЛЬНЫЙ СКЛАД");
    }

    @GetMapping("/ownersTreeTable")
    public List<Node> owners() {
        return departureService.findOwnersCountTreeByDepartureWayAndConsignor("ДВС", "ГЛАВНЫЙ МАТЕРИАЛЬНЫЙ СКЛАД");
    }
}

