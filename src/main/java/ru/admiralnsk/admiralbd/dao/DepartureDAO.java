package ru.admiralnsk.admiralbd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartureDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartureDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getDeparturesCountWithDepartureWay(String departureWay) {
        System.out.println("depW: " + departureWay);
        return jdbcTemplate.queryForObject("SELECT count(*) FROM departures WHERE DepartureWay = ?",
                new Object[]{departureWay}, Integer.class);
    }
}
