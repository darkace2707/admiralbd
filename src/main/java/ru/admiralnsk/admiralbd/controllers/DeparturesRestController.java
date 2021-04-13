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
        nodes.add(new Node("Root", "0", "Root", "http://frontbackend.com/"));
        nodes.add(new Node("Child1", "Root", "Child1", "http://frontbackend.com/child1"));
        nodes.add(new Node("Child2", "Root", "Child2", "http://frontbackend.com/child2"));
        nodes.add(new Node("Child3", "Root", "Child3", "http://frontbackend.com/child3"));
        nodes.add(new Node("Child3.1", "Child3", "Child3.1", "http://frontbackend.com/child3/child1"));
        nodes.add(new Node("Child3.2", "Child3", "Child3.2", "http://frontbackend.com/child3/child2"));
        nodes.add(new Node("Child4", "Root", "Child4", "http://frontbackend.com/child4"));
        return nodes;
    }

    private List<Node> getOwnersNodes() {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("Root", "0", "Roqweqweot", "http://frontbackend.com/"));
        nodes.add(new Node("Child1", "Root", "Child1", "http://frontbackend.com/child1"));
        nodes.add(new Node("Child2", "Root", "Child2", "http://frontbackend.com/child2"));
        nodes.add(new Node("Child3", "Root", "Child3", "http://frontbackend.com/child3"));
        nodes.add(new Node("Child3.1", "Child3", "Child3.1", "http://frontbackend.com/child3/child1"));
        nodes.add(new Node("Child3.2", "Child3", "Child3.2", "http://frontbackend.com/child3/child2"));
        nodes.add(new Node("Child4", "Root", "Child4", "http://frontbackend.com/child4"));
        return nodes;
    }
}

