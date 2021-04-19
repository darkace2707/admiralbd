package ru.admiralnsk.admiralbd.TestParser;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.testng.Assert;
import ru.admiralnsk.admiralbd.mappers.DepartureFieldsMapper;
import ru.admiralnsk.admiralbd.models.Departure;
import ru.admiralnsk.admiralbd.parser.ExcelParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootTest
public class ExcelParserTest {

    @Test
    public void readFromExcel() throws IOException {

        ExcelParser excelParser = new ExcelParser(new DepartureFieldsMapper());
        MultipartFile file = new MockMultipartFile("TestExcel.xlsx", new FileInputStream("src/main/test/resources/TestExcel.xlsx"));

        List<Departure> departureList = this.createDepartures(new ArrayList<>());

        List<Departure> departureListTest = excelParser.readFromExcel(file);

        Assert.assertEquals(departureListTest.size(), departureList.size());
        for (int i = 0; i < departureList.size(); i++) {
            Assert.assertEquals(departureListTest.get(i).getDepartureDate(), departureList.get(i).getDepartureDate());
            Assert.assertEquals(departureListTest.get(i).getCarriageNumber(), departureList.get(i).getCarriageNumber());
            Assert.assertEquals(departureListTest.get(i).getDocumentNumber(), departureList.get(i).getDocumentNumber());
            Assert.assertEquals(departureListTest.get(i).getArrivalDate(), departureList.get(i).getArrivalDate());
            Assert.assertEquals(departureListTest.get(i).getLendingDate(), departureList.get(i).getLendingDate());
            Assert.assertEquals(departureListTest.get(i).getTransportationType(), departureList.get(i).getTransportationType());
            Assert.assertEquals(departureListTest.get(i).getCargo(), departureList.get(i).getCargo());
            Assert.assertEquals(departureListTest.get(i).getCargoType(), departureList.get(i).getCargoType());
            Assert.assertEquals(departureListTest.get(i).getDepartureCountry(), departureList.get(i).getDepartureCountry());
            Assert.assertEquals(departureListTest.get(i).getDepartureStationCIS(), departureList.get(i).getDepartureStationCIS());
            Assert.assertEquals(departureListTest.get(i).getDepartureStationCISCode(), departureList.get(i).getDepartureStationCISCode());
            Assert.assertEquals(departureListTest.get(i).getDepartureRegion(), departureList.get(i).getDepartureRegion());
            Assert.assertEquals(departureListTest.get(i).getDepartureWay(), departureList.get(i).getDepartureWay());
            Assert.assertEquals(departureListTest.get(i).getDepartureStationRF(), departureList.get(i).getDepartureStationRF());
            Assert.assertEquals(departureListTest.get(i).getDepartureStationRFCode(), departureList.get(i).getDepartureStationRFCode());
            Assert.assertEquals(departureListTest.get(i).getDepartureStationRFCode(), departureList.get(i).getDepartureStationRFCode());
            Assert.assertEquals(departureListTest.get(i).getConsignor(), departureList.get(i).getConsignor());
            Assert.assertEquals(departureListTest.get(i).getConsignorACEO(), departureList.get(i).getConsignorACEO());
            Assert.assertEquals(departureListTest.get(i).getDestinationCountry(), departureList.get(i).getDestinationCountry());
            Assert.assertEquals(departureListTest.get(i).getDestinationRegion(), departureList.get(i).getDestinationRegion());
            Assert.assertEquals(departureListTest.get(i).getDestinationWay(), departureList.get(i).getDestinationWay());
            Assert.assertEquals(departureListTest.get(i).getDestinationStationRF(), departureList.get(i).getDestinationStationRF());
            Assert.assertEquals(departureListTest.get(i).getDestinationStationRFCode(), departureList.get(i).getDestinationStationRFCode());
            Assert.assertEquals(departureListTest.get(i).getDestinationStationCIS(), departureList.get(i).getDestinationStationCIS());
            Assert.assertEquals(departureListTest.get(i).getDestinationStationCISCode(), departureList.get(i).getDestinationStationCISCode());
            Assert.assertEquals(departureListTest.get(i).getConsignee(), departureList.get(i).getConsignee());
            Assert.assertEquals(departureListTest.get(i).getConsigneeACEO(), departureList.get(i).getConsigneeACEO());
            Assert.assertEquals(departureListTest.get(i).getCarriageKind(), departureList.get(i).getCarriageKind());
            Assert.assertEquals(departureListTest.get(i).getCarriageType(), departureList.get(i).getCarriageType());
            Assert.assertEquals(departureListTest.get(i).getPayer(), departureList.get(i).getPayer());
            Assert.assertEquals(departureListTest.get(i).getOwner(), departureList.get(i).getOwner());
            Assert.assertEquals(departureListTest.get(i).getRenter(), departureList.get(i).getRenter());
            Assert.assertEquals(departureListTest.get(i).getOperator(), departureList.get(i).getOperator());
            Assert.assertEquals(departureListTest.get(i).getCarriageKm(), departureList.get(i).getCarriageKm());
            Assert.assertEquals(departureListTest.get(i).getVolume(), departureList.get(i).getVolume());
            Assert.assertEquals(departureListTest.get(i).getRate(), departureList.get(i).getRate());
        }
    }

    private List<Departure> createDepartures(List<Departure> departureList) {
        Departure departureOne = Departure.builder()
                .departureDate((new GregorianCalendar(2020, Calendar.JANUARY, 1)).getTime())
                .carriageNumber(29064888)
                .documentNumber("28392746")
                .arrivalDate((new GregorianCalendar(2020, Calendar.JANUARY, 6)).getTime())
                .lendingDate((new GregorianCalendar(2020, Calendar.JANUARY, 6)).getTime())
                .transportationType("экспорт")
                .cargo(13302)
                .cargoType("ИЗДЕЛИЯ БУМ ПР")
                .departureCountry("РОССИЯ")
                .departureStationCIS("КОЙТЫ")
                .departureStationCISCode(28410)
                .departureRegion("Республика Коми")
                .departureWay("СЕВ")
                .departureStationRF("КОЙТЫ")
                .departureStationRFCode(28410)
                .consignor("ОАО СЫКТЫВКАР ТИССЬЮ ГРУП")
                .consignorACEO(15093929)
                .destinationCountry("КАЗАХСТАН")
                .destinationRegion("Челябинская область")
                .destinationWay("ЮУР")
                .destinationStationRF("КАРТАЛЫ 1-ЭК")
                .destinationStationRFCode(81650)
                .destinationStationCIS("КАРАГАНДЫ")
                .destinationStationCISCode(67390)
                .consignee(null)
                .consigneeACEO(0)
                .carriageKind("Крытые")
                .carriageType("0202 4-ОС КРЫТЫЙ УНИВЕРСАЛ")
                .payer("ОАО  Сыктывкар Тиссью Груп")
                .owner("РГ Лизинг ООО")
                .renter("ООО ЖДК ТРАНЗИТ")
                .operator("ЖДК Транзит")
                .carriageKm(2090)
                .volume(9)
                .rate(87119)
                .build();
        departureList.add(departureOne);

        Departure departureTwo = Departure.builder()
                .departureDate((new GregorianCalendar(2020, Calendar.JANUARY, 1)).getTime())
                .carriageNumber(29067337)
                .documentNumber("ЭЦ131476")
                .arrivalDate((new GregorianCalendar(2020, Calendar.JANUARY, 8)).getTime())
                .lendingDate((new GregorianCalendar(2020, Calendar.JANUARY, 9)).getTime())
                .transportationType("внутренние")
                .cargo(54102)
                .cargoType("КОМБИКОРМА ВС")
                .departureCountry("РОССИЯ")
                .departureStationCIS(null)
                .departureStationCISCode(10)
                .departureRegion("Забайкальский край")
                .departureWay("ЗАБ")
                .departureStationRF("ЗАБАЙКАЛЬСК")
                .departureStationRFCode(94680)
                .consignor("ООО РУС ЛОГИСТИК")
                .consignorACEO(24735203)
                .destinationCountry("РОССИЯ")
                .destinationRegion("Новосибирская область")
                .destinationWay("ЗСБ")
                .destinationStationRF("НОВОСИБ-ВОСТ")
                .destinationStationRFCode(85150)
                .destinationStationCIS(null)
                .destinationStationCISCode(null)
                .consignee("ООО НОВОСИБСКЛАДСЕРВИС")
                .consigneeACEO(71484289)
                .carriageKind("Крытые")
                .carriageType("0202 4-ОС КРЫТЫЙ УНИВЕРСАЛ")
                .payer("Общество с ограниченной ответственностью Рус Логистик")
                .owner("Сибирский Край ООО")
                .renter("вне аренды")
                .operator("ООО Сибирский край")
                .carriageKm(3318)
                .volume(66)
                .rate(109428)
                .build();
        departureList.add(departureTwo);

        Departure departureThree = Departure.builder()
                .departureDate((new GregorianCalendar(2020, Calendar.JANUARY, 1)).getTime())
                .carriageNumber(28037141)
                .documentNumber("Г0104948")
                .arrivalDate((new GregorianCalendar(2020, Calendar.JANUARY, 7)).getTime())
                .lendingDate((new GregorianCalendar(2020, Calendar.JANUARY, 7)).getTime())
                .transportationType("транзит")
                .cargo(42103)
                .cargoType("КАРТОН Д/ГОФРЫ")
                .departureCountry("КАЗАХСТАН")
                .departureStationCIS("СОРОКОВАЯ")
                .departureStationCISCode(69020)
                .departureRegion("Челябинская область")
                .departureWay("ЮУР")
                .departureStationRF("КАРТАЛЫ 1-ЭК")
                .departureStationRFCode(81650)
                .consignor(null)
                .consignorACEO(0)
                .destinationCountry("БЕЛАРУСЬ")
                .destinationRegion("Смоленская область")
                .destinationWay("МСК")
                .destinationStationRF("КРАСНОЕ-ЭКСП")
                .destinationStationRFCode(17140)
                .destinationStationCIS("КОЛЯДИЧИ")
                .destinationStationCISCode(14480)
                .consignee(null)
                .consigneeACEO(0)
                .carriageKind("Крытые")
                .carriageType("0208 4-ОС КР ОБЪЕМ КУЗ 140 КУБ.М С УШИР ДВЕРН ПРОЕМ")
                .payer("Акционерное общество НефтеТрансСервис")
                .owner("инвентарный парк")
                .renter(null)
                .operator("неизвестен")
                .carriageKm(2550)
                .volume(0)
                .rate(50062)
                .build();
        departureList.add(departureThree);

        return departureList;
    }
}