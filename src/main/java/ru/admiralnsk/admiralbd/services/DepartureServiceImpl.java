package ru.admiralnsk.admiralbd.services;

import org.springframework.stereotype.Service;
import ru.admiralnsk.admiralbd.models.Departure;
import ru.admiralnsk.admiralbd.models.DeparturesCount;
import ru.admiralnsk.admiralbd.models.DeparturesCountProjection;
import ru.admiralnsk.admiralbd.repository.DepartureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class DepartureServiceImpl implements DepartureService {

    private final DepartureRepository departureRepository;

    public DepartureServiceImpl(DepartureRepository departureRepository) {
        this.departureRepository = departureRepository;
    }

    @Override
    public Departure findById(int id) {
        return departureRepository.findById(id);
    }

    @Override
    public List<String> findDistinctDepartureWays() {
        return departureRepository.findDistinctDepartureWays();
    }

    @Override
    public List<String> findDistinctConsignorsByDepartureWay(String departureWay) {
        List<String> consignorList = new ArrayList<>();
        for (String consignor : departureRepository.findDistinctConsignorsByDepartureWay(departureWay)) {
            consignorList.add(Objects.requireNonNullElse(consignor, "Не выбрано"));
        }
        return consignorList;
    }

    @Override
    public Integer findDeparturesCountByDepartureWayAnAndConsignorAndMonth(String departureWay, String consignor,
                                                                           int month) {
        return departureRepository.findDeparturesCountByDepartureWayAnAndConsignorAndMonth(departureWay, consignor, month);
    }

    @Override
    public List<DeparturesCount> findDeparturesCountByDepartureWayAnAndConsignorAllMonth(String departureWay,
                                                                                         String consignor) {

        List<DeparturesCountProjection> departuresCountList;
        if (consignor.equals("Не выбрано")) {
            departuresCountList = departureRepository.findDeparturesCountByDepartureWayAnAndConsignorIsNullAllMonth(departureWay);
        } else {
            departuresCountList = departureRepository.findDeparturesCountByDepartureWayAnAndConsignorAllMonth(departureWay, consignor);
        }

        int counter = 0;

        List<DeparturesCount> formattedDeparturesCountList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            formattedDeparturesCountList.add(new DeparturesCount(this.getMonthName(i+1), 0));
        }

        for (DeparturesCountProjection departuresCount : departuresCountList) {
            counter += departuresCount.getValue();
            formattedDeparturesCountList
                    .get(Integer.parseInt(departuresCount.getName()) - 1).setValue(departuresCount.getValue());
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
    public List<DeparturesCount> findConsigneeCountWithDepartureWayAndConsignor(String departureWay,
                                                                                String consignor) {
        List<DeparturesCountProjection> departuresCountProjectionList;
        if (consignor.equals("Не выбрано")) {
            departuresCountProjectionList = departureRepository.findConsigneeCountByDepartureWayAndConsignorIsNull(departureWay);
        } else {
            departuresCountProjectionList = departureRepository.findConsigneeCountByDepartureWayAndConsignor(departureWay, consignor);
        }

        List<DeparturesCount> departuresCountList = new ArrayList<>();
        for (DeparturesCountProjection departuresCountProjection : departuresCountProjectionList) {
            if (departuresCountProjection.getName() == null) {
                departuresCountList
                        .add(new DeparturesCount("Не выбрано", departuresCountProjection.getValue()));
            } else {
                departuresCountList
                        .add(new DeparturesCount(departuresCountProjection.getName(), departuresCountProjection.getValue()));
            }
        }
        return departuresCountList;
    }

    @Override
    public List<DeparturesCount> findCargoTypeByDepartureWayAndConsignor(String departureWay,
                                                                         String consignor) {
        List<DeparturesCountProjection> departuresCountProjectionList;
        if (consignor.equals("Не выбрано")) {
            departuresCountProjectionList = departureRepository.findCargoTypeByDepartureWayAndConsignorIsNull(departureWay);
        } else {
            departuresCountProjectionList = departureRepository.findCargoTypeByDepartureWayAndConsignor(departureWay, consignor);
        }

        List<DeparturesCount> departuresCountList = new ArrayList<>();
        for (DeparturesCountProjection departuresCountProjection : departuresCountProjectionList) {
            departuresCountList
                    .add(new DeparturesCount(departuresCountProjection.getName(), departuresCountProjection.getValue()));
        }
        return departuresCountList;
    }

    @Override
    public List<DeparturesCount> findCarriageKindByDepartureWayAndConsignor(String departureWay, String consignor) {
        List<DeparturesCountProjection> departuresCountProjectionList;
        if (consignor.equals("Не выбрано")) {
            departuresCountProjectionList = departureRepository.findCarriageKindByDepartureWayAndConsignorIsNull(departureWay);
        } else {
            departuresCountProjectionList = departureRepository.findCarriageKindByDepartureWayAndConsignor(departureWay, consignor);
        }

        List<DeparturesCount> departuresCountList = new ArrayList<>();
        for (DeparturesCountProjection departuresCountProjection : departuresCountProjectionList) {
            departuresCountList
                    .add(new DeparturesCount(departuresCountProjection.getName(), departuresCountProjection.getValue()));
        }
        return departuresCountList;
    }
}
