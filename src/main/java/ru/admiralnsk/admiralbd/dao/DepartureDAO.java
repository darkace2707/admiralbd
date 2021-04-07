package ru.admiralnsk.admiralbd.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DepartureDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartureDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer getDeparturesCountWithDepartureWay(String departureWay) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM departures WHERE DepartureWay = ?",
                new Object[]{departureWay}, Integer.class);
    }
    public List<String> getMonthes() {
        List<String> monthes = new ArrayList();
        for (int i = 1; i < 13; i++) {
            monthes.add("month"+i);
        }
        return monthes;
    }
    public List<String> getDistinctDepartureWays() {
        return jdbcTemplate.queryForList("SELECT DISTINCT departureWay FROM departures", String.class);
    }

    public List<String> getDistinctConsignorsWithDepartureWay(String departureWay) {
        return jdbcTemplate.queryForList("SELECT DISTINCT consignor FROM departures WHERE departureWay = ?",
                new Object[]{departureWay},String.class);
    }
}
