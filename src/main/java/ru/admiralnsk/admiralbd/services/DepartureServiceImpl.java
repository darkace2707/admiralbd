package ru.admiralnsk.admiralbd.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.admiralnsk.admiralbd.constants.Constants;
import ru.admiralnsk.admiralbd.mappers.Months;
import ru.admiralnsk.admiralbd.models.*;
import ru.admiralnsk.admiralbd.repository.DepartureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class DepartureServiceImpl implements DepartureService {

    private final DepartureRepository departureRepository;

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
            formattedDeparturesCountList.add(new DeparturesCount(Months.getMonthName(i+1), 0));
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
    public List<Node> findDepartureStationRFCountTreeByDepartureWayAndConsignor(String departureWay, String consignor) {
        List<DestinationStationRFCountProjection> departureStationRFCountTree =
                departureRepository.findDepartureStationRFCountTreeByDepartureWayAndConsignor(departureWay, consignor);

        Set<String> departureStationRFSet = departureStationRFCountTree.stream()
                .map(DestinationStationRFCountProjection::getDepartureStationRF).collect(Collectors.toSet());

        AtomicInteger overall = new AtomicInteger(0);
        departureStationRFCountTree.forEach(d -> overall.addAndGet(d.getCount()));

        List<Node> nodes = new ArrayList<>();

        for (String departureStationRF : departureStationRFSet) {

            AtomicInteger departureStationRFCount = new AtomicInteger(0);
            List<String> destinationWays = departureStationRFCountTree.stream()
                    .filter(d -> d.getDepartureStationRF().equals(departureStationRF))
                    .peek(d -> departureStationRFCount.addAndGet(d.getCount()))
                    .map(DestinationStationRFCountProjection::getDestinationWay)
                    .distinct()
                    .collect(Collectors.toList());

            nodes.add(new Node(
                    departureStationRF + Constants.FIRST_LAYER,
                    "0",
                    departureStationRF,
                    String.valueOf(departureStationRFCount)));

            for (String destinationWay : destinationWays) {

                AtomicInteger destinationWayCount = new AtomicInteger(0);
                List<String> destinationStationRFList = departureStationRFCountTree.stream()
                        .filter(d -> d.getDepartureStationRF().equals(departureStationRF) &&
                                d.getDestinationWay().equals(destinationWay))
                        .peek(d -> destinationWayCount.addAndGet(d.getCount()))
                        .map(DestinationStationRFCountProjection::getDestinationStationRF)
                        .distinct()
                        .collect(Collectors.toList());

                nodes.add(new Node(
                        destinationWay + Constants.SECOND_LAYER,
                        departureStationRF + Constants.FIRST_LAYER,
                        destinationWay,
                        String.valueOf(destinationWayCount)));

                for (String destinationStationRF : destinationStationRFList) {
                    nodes.add(new Node(
                            destinationStationRF + Constants.THIRD_LAYER,
                            destinationWay + Constants.SECOND_LAYER,
                            destinationStationRF,
                            String.valueOf(departureStationRFCountTree.stream()
                                    .filter( d -> d.getDepartureStationRF().equals(departureStationRF) &&
                                            d.getDestinationWay().equals(destinationWay) &&
                                            d.getDestinationStationRF().equals(destinationStationRF))
                                    .map(DestinationStationRFCountProjection::getCount).findFirst().orElse(0))));
                }
            }
        }

        return nodes;
    }

    @Override
    public List<Node> findOwnersCountTreeByDepartureWayAndConsignor(String departureWay, String consignor) {
        List<OwnersCountProjection> ownersCountProjectionList =
                departureRepository.findOwnersCountTreeByDepartureWayAndConsignor(departureWay, consignor);

        Set<String> ownersSet = ownersCountProjectionList.stream()
                .map(OwnersCountProjection::getOwner).collect(Collectors.toSet());

        List<Node> nodes = new ArrayList<>();

        for (String owner : ownersSet) {
            AtomicInteger ownersCount = new AtomicInteger(0);
            List<String> operatorList = ownersCountProjectionList.stream()
                    .filter(o -> o.getOwner().equals(owner))
                    .peek(o -> ownersCount.addAndGet(o.getCount()))
                    .map(OwnersCountProjection::getOperator)
                    .distinct()
                    .collect(Collectors.toList());

            nodes.add(new Node(
                    owner + Constants.FIRST_LAYER,
                    "0",
                    owner,
                    String.valueOf(ownersCount)));

            for (String operator : operatorList) {
                nodes.add(new Node(
                        operator + Constants.SECOND_LAYER,
                        owner + Constants.FIRST_LAYER,
                        operator,
                        String.valueOf(ownersCountProjectionList.stream()
                                .filter( d -> d.getOwner().equals(owner) &&
                                        d.getOperator().equals(operator))
                                .map(OwnersCountProjection::getCount).findFirst().orElse(0))));
            }
        }
        return nodes;
    }
}
