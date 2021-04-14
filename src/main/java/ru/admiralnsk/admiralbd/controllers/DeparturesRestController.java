package ru.admiralnsk.admiralbd.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.admiralnsk.admiralbd.models.Node;
import ru.admiralnsk.admiralbd.services.DepartureService;

import java.util.ArrayList;
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
        return getDepartureStationRfNodes();
    }

    @GetMapping("/ownersTreeTable")
    public List<Node> owners() {
        return getOwnersNodes();
    }

    private List<Node> getDepartureStationRfNodes() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("Root", "0", "АО ГАЛОПОЛИМЕР ПЕРМЬ", "123"));
        nodes.add(new Node("Child1", "Root", "ООО ПИОНЕР ТРЕЙД", "3222"));
        nodes.add(new Node("Child3.1", "Child1", "ОАО ЛУЖСКИЙ АБРАЗИВНЫЙ ЗАВОД", "4444"));
        nodes.add(new Node("Child3.2", "Child3.1", "ЗАО ЖЕЛЕЗНОГОРСКИЙ ВАГОНОРЕМОНТНЫЙ ЗАВОД", "2323"));
        nodes.add(new Node("Child2", "Root", "ЛИКВИДАЦИОННАЯ КОМИССИЯ ООО РЕГИОНАЛЬНАЯ КОМПАНИЯ", "1233"));
        nodes.add(new Node("Child3", "Root", "ИП ТОЛСТЫХ ЕЛЕНА ГЕННАДЬЕВНА", "12333"));
        nodes.add(new Node("Child4", "Root", "ООО ИРКУТСКВОДСТРОЙ", "43224"));
        return nodes;
    }

    private List<Node> getOwnersNodes() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("Root", "0", "Roqweqweot", "500000"));
        nodes.add(new Node("Child1", "Root", "Child1", "6800000"));
        nodes.add(new Node("Child2", "Root", "Child2", "76000000"));
        nodes.add(new Node("Child3", "Root", "Child3", "456456456"));
        nodes.add(new Node("Child3.1", "Child3", "Child3.1", "500000"));
        nodes.add(new Node("Child3.2", "Child3", "Child3.2", "456486"));
        nodes.add(new Node("Child4", "Root", "Child4", "789789"));
        return nodes;
    }
}

