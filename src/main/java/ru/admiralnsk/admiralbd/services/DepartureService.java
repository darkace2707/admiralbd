package ru.admiralnsk.admiralbd.services;

import ru.admiralnsk.admiralbd.models.Departure;
import ru.admiralnsk.admiralbd.models.DeparturesCount;
import ru.admiralnsk.admiralbd.models.DeparturesCountProjection;

import java.util.List;

public interface DepartureService {

    Departure findById(int id);

    List<String> findDistinctDepartureWays();

    List<DeparturesCount> findConsigneeCountWithDepartureWayAndConsignor(String departureWay, String consignor);

    List<String> findDistinctConsignorsByDepartureWay(String departureWay);

    Integer findDeparturesCountByDepartureWayAnAndConsignorAndMonth(String departureWay, String consignor, int month);

    List<DeparturesCount> findDeparturesCountByDepartureWayAnAndConsignorAllMonth(String departureWay, String consignor);

    List<DeparturesCount> findCargoTypeByDepartureWayAndConsignor(String departureWay, String consignor);

    List<DeparturesCount> findCarriageKindByDepartureWayAndConsignor(String departureWay, String consignor);
}
