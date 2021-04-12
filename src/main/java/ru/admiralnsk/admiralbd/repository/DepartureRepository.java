package ru.admiralnsk.admiralbd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.admiralnsk.admiralbd.models.Departure;
import ru.admiralnsk.admiralbd.models.DeparturesCountProjection;

import java.util.List;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, Long> {

    @Query(nativeQuery = true, value = "SELECT DISTINCT d.DepartureWay FROM departures d ORDER BY d.DepartureWay")
    List<String> findDistinctDepartureWays();

    @Query(nativeQuery = true, value = "SELECT DISTINCT d.Consignor FROM departures d " +
            "WHERE d.DepartureWay = ? ORDER BY d.Consignor")
    List<String> findDistinctConsignorsByDepartureWay(String departureWay);

    @Query(nativeQuery = true, value = "SELECT count(d.id) FROM departures d " +
            "WHERE d.departureWay = ? AND d.Consignor = ? AND MONTH(d.DepartureDate) = ?")
    Integer findDeparturesCountByDepartureWayAnAndConsignorAndMonth(String departureWay, String consignor, int month);

    @Query(nativeQuery = true, value = "SELECT MONTH(d.departureDate) AS name, count(d.id) AS value " +
            "FROM departures d " +
            "WHERE d.departureWay = ? AND d.Consignor = ? GROUP BY MONTH(d.departureDate)")
    List<DeparturesCountProjection> findDeparturesCountByDepartureWayAnAndConsignorAllMonth(String departureWay,
                                                                                            String consignor);

    @Query(nativeQuery = true, value = "SELECT MONTH(d.departureDate) AS name, count(d.id) AS value " +
            "FROM departures d " +
            "WHERE d.departureWay = ? AND d.Consignor IS NULL GROUP BY MONTH(d.departureDate)")
    List<DeparturesCountProjection> findDeparturesCountByDepartureWayAnAndConsignorIsNullAllMonth(String departureWay);

    @Query(nativeQuery = true, value = "SELECT d.Consignee AS name, count(d.id) AS value FROM departures d " +
            "WHERE d.DepartureWay = ? AND d.Consignor = ? GROUP BY d.Consignee ORDER BY count(d.id) DESC")
    List<DeparturesCountProjection> findConsigneeCountByDepartureWayAndConsignor(String departureWay, String consignor);

    @Query(nativeQuery = true, value = "SELECT d.Consignee AS name, count(d.id) AS value FROM departures d " +
            "WHERE d.DepartureWay = ? AND d.Consignor IS NULL GROUP BY d.Consignee ORDER BY count(d.id) DESC")
    List<DeparturesCountProjection> findConsigneeCountByDepartureWayAndConsignorIsNull(String departureWay);

    @Query(nativeQuery = true, value = "SELECT d.CargoType AS name, count(d.id) AS value FROM departures d " +
            "WHERE d.DepartureWay = ? AND d.Consignor = ? GROUP BY d.CargoType ORDER BY count(d.id) DESC")
    List<DeparturesCountProjection> findCargoTypeByDepartureWayAndConsignor(String departureWay, String consignor);

    @Query(nativeQuery = true, value = "SELECT d.CargoType AS name, count(d.id) AS value FROM departures d " +
            "WHERE d.DepartureWay = ? AND d.Consignor IS NULL GROUP BY d.CargoType ORDER BY count(d.id) DESC")
    List<DeparturesCountProjection> findCargoTypeByDepartureWayAndConsignorIsNull(String departureWay);

    @Query(nativeQuery = true, value = "SELECT d.CarriageKind AS name, count(d.id) AS value FROM departures d " +
            "WHERE d.DepartureWay = ? AND d.Consignor = ? GROUP BY d.CarriageKind ORDER BY count(d.id) DESC")
    List<DeparturesCountProjection> findCarriageKindByDepartureWayAndConsignor(String departureWay, String consignor);

    @Query(nativeQuery = true, value = "SELECT d.CarriageKind AS name, count(d.id) AS value FROM departures d " +
            "WHERE d.DepartureWay = ? AND d.Consignor IS NULL GROUP BY d.CarriageKind ORDER BY count(d.id) DESC")
    List<DeparturesCountProjection> findCarriageKindByDepartureWayAndConsignorIsNull(String departureWay);
}
