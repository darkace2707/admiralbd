package ru.admiralnsk.admiralbd.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.admiralnsk.admiralbd.constants.Constants;
import ru.admiralnsk.admiralbd.exceptions.ExcelNotStructuredException;
import ru.admiralnsk.admiralbd.mappers.Months;
import ru.admiralnsk.admiralbd.models.Departure;
import ru.admiralnsk.admiralbd.models.DeparturesCount;
import ru.admiralnsk.admiralbd.models.DeparturesCountProjection;
import ru.admiralnsk.admiralbd.parser.ExcelParser;
import ru.admiralnsk.admiralbd.repository.DepartureRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class DepartureServiceImpl implements DepartureService {

    private final DepartureRepository departureRepository;
    private final ExcelParser excelParser;

    @Override
    public List<String> findDistinctDepartureWays() {
        return departureRepository.findDistinctDepartureWays();
    }

    @Override
    public List<String> findDistinctConsignorsByDepartureWay(String departureWay) {
        List<String> consignorList = new ArrayList<>();
        for (String consignor : departureRepository.findDistinctConsignorsByDepartureWay(departureWay)) {
            consignorList.add(Objects.requireNonNullElse(consignor, Constants.NOT_CHOSEN));
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
        if (Constants.NOT_CHOSEN.equals(consignor)) {
            departuresCountList =
                    departureRepository.findDeparturesCountByDepartureWayAnAndConsignorIsNullAllMonth(departureWay);
        } else {
            departuresCountList =
                    departureRepository.findDeparturesCountByDepartureWayAnAndConsignorAllMonth(departureWay, consignor);
        }

        int counter = 0;

        List<DeparturesCount> formattedDeparturesCountList = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            formattedDeparturesCountList.add(new DeparturesCount(Months.getMonthName(i + 1), 0));
        }

        for (DeparturesCountProjection departuresCount : departuresCountList) {
            counter += departuresCount.getValue();
            formattedDeparturesCountList
                    .get(Integer.parseInt(departuresCount.getName()) - 1).setValue(departuresCount.getValue());
        }

        formattedDeparturesCountList.add(new DeparturesCount(Constants.OVERALL, counter));

        return formattedDeparturesCountList;
    }

    @Override
    public List<DeparturesCount> findConsigneeCountWithDepartureWayAndConsignor(String departureWay,
                                                                                String consignor) {
        List<DeparturesCountProjection> departuresCountProjectionList;
        if (Constants.NOT_CHOSEN.equals(consignor)) {
            departuresCountProjectionList =
                    departureRepository.findConsigneeCountByDepartureWayAndConsignorIsNull(departureWay);
        } else {
            departuresCountProjectionList =
                    departureRepository.findConsigneeCountByDepartureWayAndConsignor(departureWay, consignor);
        }

        List<DeparturesCount> departuresCountList = new ArrayList<>();
        for (DeparturesCountProjection departuresCountProjection : departuresCountProjectionList) {
            departuresCountList.add(new DeparturesCount(
                    Objects.requireNonNullElse(departuresCountProjection.getName(), Constants.NOT_CHOSEN),
                    departuresCountProjection.getValue()));
        }
        return departuresCountList;
    }

    @Override
    public List<DeparturesCount> findCargoTypeByDepartureWayAndConsignor(String departureWay,
                                                                         String consignor) {
        List<DeparturesCountProjection> departuresCountProjectionList;
        if (Constants.NOT_CHOSEN.equals(consignor)) {
            departuresCountProjectionList =
                    departureRepository.findCargoTypeByDepartureWayAndConsignorIsNull(departureWay);
        } else {
            departuresCountProjectionList =
                    departureRepository.findCargoTypeByDepartureWayAndConsignor(departureWay, consignor);
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
        if (Constants.NOT_CHOSEN.equals(consignor)) {
            departuresCountProjectionList =
                    departureRepository.findCarriageKindByDepartureWayAndConsignorIsNull(departureWay);
        } else {
            departuresCountProjectionList =
                    departureRepository.findCarriageKindByDepartureWayAndConsignor(departureWay, consignor);
        }

        List<DeparturesCount> departuresCountList = new ArrayList<>();
        for (DeparturesCountProjection departuresCountProjection : departuresCountProjectionList) {
            departuresCountList
                    .add(new DeparturesCount(departuresCountProjection.getName(), departuresCountProjection.getValue()));
        }
        return departuresCountList;
    }

    @Override
    public void putDepartures(MultipartFile file) throws IOException, ExecutionException, InterruptedException, ExcelNotStructuredException {

        try {
            List<Departure> departures = excelParser.readFromExcel(file);
            ForkJoinPool myPool = new ForkJoinPool(Constants.PARALLELISM_LEVEL);
            List<Departure> filteredDepartures = myPool.submit(() -> departures.parallelStream()
                    .filter(departure ->
                            !departureRepository.existsDepartureByDepartureDateAndCarriageNumberAndDocumentNumberAndCargo(
                            departure.getDepartureDate(),
                            departure.getCarriageNumber(),
                            departure.getDocumentNumber(),
                            departure.getCargo()))
                    .collect(Collectors.toList())).get();
            departureRepository.saveAll(filteredDepartures);
        } catch (ExcelNotStructuredException E) {
            System.out.println(E.getMessage());
        }


    }
}
