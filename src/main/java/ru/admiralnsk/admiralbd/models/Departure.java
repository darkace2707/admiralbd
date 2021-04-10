package ru.admiralnsk.admiralbd.models;

import lombok.*;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Departure {

    private Integer id;
    private Date departureDate;
    private Integer carriageNumber;
    private String documentNumber;
    private Date arrivalDate;
    private Date lendingDate;
    private String transportationType;
    private Integer cargo;
    private String cargoType;
    private String departureCountry;
    private String departureStationCIS;
    private Integer departureStationCISCode;
    private String departureRegion;
    private String departureWay;
    private String departureStationRF;
    private Integer departureStationRFCode;
    private String consignor;
    private Integer consignorACEO;
    private String destinationCountry;
    private String destinationRegion;
    private String destinationWay;
    private String destinationStationRF;
    private Integer destinationStationRFCode;
    private String destinationStationCIS;
    private Integer destinationStationCISCode;
    private String consignee;
    private Integer consigneeACEO;
    private String carriageKind;
    private String carriageType;
    private String payer;
    private String owner;
    private String renter;
    private String operator;
    private Integer carriageKm;
    private Integer volume;
    private Integer rate;

    public void show(){
        System.out.println(departureDate + " " +transportationType+ " "+departureWay+ " "+ payer+ " " + rate );
    }

}
