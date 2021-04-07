package ru.admiralnsk.admiralbd.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.admiralnsk.admiralbd.dao.DepartureDAO;
import ru.admiralnsk.admiralbd.models.DeparturesCount;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class DeparturesServiceImpl implements DepartureService{

    private final DepartureDAO departureDAO;

    @Override
    public Integer getDeparturesCountWithDepartureWay(String departureWay) {
        return departureDAO.getDeparturesCountWithDepartureWay(departureWay);
    }

    @Override
    public List<String> getDistinctDepartureWays() {
        return departureDAO.getDistinctDepartureWays();
    }

    @Override
    public List<String> getDistinctConsignorsWithDepartureWay(String departureWay) {
        return departureDAO.getDistinctConsignorsWithDepartureWay(departureWay);
    }

    @Override
    public Integer getDeparturesCountWithDepartureWayAndConsignorByMonth(String departureWay, String consignor,
                                                                         int month) {
        return departureDAO.getDeparturesCountWithDepartureWayAndConsignorByMonth(departureWay, consignor, month);
    }

    @Override
    public List<DeparturesCount> getDeparturesCountWithDepartureWayAndConsignorByAllMonth(String departureWay,
                                                                                          String consignor) {
        List<DeparturesCount> departuresCountList =
                departureDAO.getDeparturesCountWithDepartureWayAndConsignorByAllMonth(departureWay, consignor);

        int counter = 0;

        List<DeparturesCount> formattedDeparturesCountList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            formattedDeparturesCountList.add(new DeparturesCount(this.getMonthName(i+1), 0));
        }

        for (DeparturesCount departuresCount : departuresCountList) {
            counter += departuresCount.getValue();
            formattedDeparturesCountList.get(Integer.parseInt(departuresCount.getKey()) - 1).setValue(departuresCount.getValue());
        }

        formattedDeparturesCountList.add(new DeparturesCount("Общий Итог", counter));

        return formattedDeparturesCountList;
    }

    private String getMonthName(int month) {
        switch (month) {
            case -1:
                return "Общий Итог";
            case 1:
                return "Январь";
            case 2:
                return "Февраль";
            case 3:
                return "Март";
            case 4:
                return "Апрель";
            case 5:
                return "Май";
            case 6:
                return "Июнь";
            case 7:
                return "Июль";
            case 8:
                return "Август";
            case 9:
                return "Сентябрь";
            case 10:
                return "Октябрь";
            case 11:
                return "Ноябрь";
            case 12:
                return "Декабрь";
            default:
                return "Не задано";
        }
    }

    @Override
    public List<DeparturesCount> getConsigneeCountWithDepartureWayAndConsignor(String departureWay, String consignor) {
        return departureDAO.getConsigneeCountWithDepartureWayAndConsignor(departureWay, consignor);
    }

    @Override
    public List<DeparturesCount> getCargoTypeWithDepartureWayAndConsignor(String departureWay, String consignor) {
        return departureDAO.getCargoTypeWithDepartureWayAndConsignor(departureWay, consignor);
    }

    @Override
    public List<DeparturesCount> getCarriageKindWithDepartureWayAndConsignor(String departureWay, String consignor) {
        return departureDAO.getCarriageKindWithDepartureWayAndConsignor(departureWay, consignor);
    }


}