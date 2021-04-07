package ru.admiralnsk.admiralbd.services;

import ru.admiralnsk.admiralbd.models.DeparturesCount;
import java.util.List;

public interface DepartureService {

    Integer getDeparturesCountWithDepartureWay(String departureWay);

    List<String> getDistinctDepartureWays();

    List<String> getDistinctConsignorsWithDepartureWay(String departureWay);

    Integer getDeparturesCountWithDepartureWayAndConsignorByMonth(String departureWay,
                                                                  String consignor, int month);

    List<DeparturesCount> getDeparturesCountWithDepartureWayAndConsignorByAllMonth(String departureWay,
                                                                                   String consignor);

    List<DeparturesCount> getConsigneeCountWithDepartureWayAndConsignor(String departureWay, String consignor);

    List<DeparturesCount> getCargoTypeWithDepartureWayAndConsignor(String departureWay, String consignor);

    List<DeparturesCount> getCarriageKindWithDepartureWayAndConsignor(String departureWay, String consignor);
}
