package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.*;
import com.gl.planesAndAirfileds.domain.dto.FlightDetailsDto;
import com.gl.planesAndAirfileds.domain.simulator.GetFlightDetailsDto;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlightDetailsRepositoryImplTest extends AbstractEntityRepositoryImplTest<FlightDetails> {

    @Autowired
    private FlightDetailsRepository flightDetailsRepository;

    private Airport sourceAirport;

    private Airport destinationAirport;

    @Override
    protected AbstractEntityRepository<FlightDetails> getRepository() {
        return flightDetailsRepository;
    }

    @Override
    protected FlightDetails getEntity() {
        Plane plane = TestDomainObjectFactory.getPlane();
        persist(plane);
        FlightRoute flightRoute = TestDomainObjectFactory.getFlightRoute(plane, sourceAirport, destinationAirport);
        persist(flightRoute);
        return TestDomainObjectFactory.getFlightDetails(flightRoute);
    }

    @Override
    protected List<FlightDetails> getEntities() {
        Plane plane = TestDomainObjectFactory.getPlane();
        persist(plane);
        FlightRoute flightRoute = TestDomainObjectFactory.getFlightRoute(plane, sourceAirport, destinationAirport);
        persist(flightRoute);
        List<FlightDetails> flightDetailss = new ArrayList<>();
        for (int i = 0; i < randomCount(10); i++) {
            flightDetailss.add(TestDomainObjectFactory.getFlightDetails(flightRoute));
        }
        return flightDetailss;
    }

    @Before
    public void initialize() {
        sourceAirport = TestDomainObjectFactory.getAirport();
        destinationAirport = TestDomainObjectFactory.getAirport();
        persist(sourceAirport, destinationAirport);
    }

    @Test
    public void testFindLatest() {

        Plane plane = TestDomainObjectFactory.getPlane();
        persist(plane);
        Plane otherPlane = TestDomainObjectFactory.getPlane();
        persist(otherPlane);

        LocalDateTime now = LocalDateTime.now();
        List<FlightRoute> planeFlightRoutes = TestDomainObjectFactory
                .findPlaneRoutes(plane, sourceAirport, destinationAirport, randomCount(2));
        FlightRoute firstFlightRoute = planeFlightRoutes.get(0);
        firstFlightRoute.setStartDate(now.minusDays(3));
        firstFlightRoute.setIncomingDate(now.plusDays(2));
        firstFlightRoute.setFlightPhase(FlightPhase.CRUISE);

        FlightRoute secondFlightRoute = planeFlightRoutes.get(1);
        secondFlightRoute.setPlane(otherPlane);
        secondFlightRoute.setStartDate(now.minusDays(3));
        secondFlightRoute.setIncomingDate(now.plusDays(2));
        secondFlightRoute.setFlightPhase(FlightPhase.CRUISE);
        persist(planeFlightRoutes);

        List<FlightDetails> firstFlightDetails = TestDomainObjectFactory
                .findFlightDetails(firstFlightRoute, randomCount(5));
        FlightDetails lastDetailOfFirstRoute = firstFlightDetails.get(firstFlightDetails.size() - 1);
        lastDetailOfFirstRoute.setActualPosition(true); // excepted this position
        persist(firstFlightDetails);

        List<FlightDetails> secondFlightDetails = TestDomainObjectFactory
                .findFlightDetails(secondFlightRoute, randomCount(5));
        FlightDetails lastDetailOfSecondRoute = secondFlightDetails.get(secondFlightDetails.size() - 1);
        lastDetailOfSecondRoute.setActualPosition(true); // excepted this position
        persist(secondFlightDetails);

        List<FlightDetails> excepted = Arrays.asList(lastDetailOfFirstRoute, lastDetailOfSecondRoute);
        List<FlightDetailsDto> result = flightDetailsRepository.findLatest();

        TestCase.assertEquals(excepted.size(), result.size());
        for (FlightDetailsDto fdd : result) {
            FlightDetails flightDetails = getFlightDetails(fdd, excepted);
            assertDto(fdd, flightDetails);
        }
    }

    private void assertDto(FlightDetailsDto dto, FlightDetails flightDetails) {
        TestCase.assertNotNull(dto);
        TestCase.assertEquals(dto.getCurrentLatitude(), flightDetails.getGpsLatitude());
        TestCase.assertEquals(dto.getCurrentLongitude(), flightDetails.getGpsLongitude());
        TestCase.assertEquals(dto.getDestinationLatitude(),
                flightDetails.getFlightRoute().getDestination().getLatitude());
        TestCase.assertEquals(dto.getDestinationLongitude(),
                flightDetails.getFlightRoute().getDestination().getLongitude());
        TestCase.assertEquals(dto.getVelocity(), flightDetails.getVelocity());
    }

    private FlightDetails getFlightDetails(FlightDetailsDto dto, List<FlightDetails> fds) {
        for (FlightDetails fd : fds) {
            if (dto.getFlightRouteSid().equals(fd.getFlightRoute().getSid())) {
                return fd;
            }
        }
        return null;
    }

    @Test
    public void updateActualPosition() {
        Plane plane = TestDomainObjectFactory.getPlane();
        persist(plane);
        FlightRoute firstFlightRoute = TestDomainObjectFactory.getFlightRoute(plane, sourceAirport, destinationAirport);
        FlightRoute secondFlightRoute = TestDomainObjectFactory
                .getFlightRoute(plane, sourceAirport, destinationAirport);
        persist(firstFlightRoute, secondFlightRoute);

        List<FlightDetails> flightDetailss = TestDomainObjectFactory
                .findFlightDetails(firstFlightRoute, randomCount(10));
        flightDetailss.forEach(fd -> fd.setActualPosition(true));
        persist(flightDetailss);

        List<FlightDetails> otherFlightDetails = TestDomainObjectFactory
                .findFlightDetails(secondFlightRoute, randomCount(10));
        otherFlightDetails.forEach(fd -> fd.setActualPosition(false));
        persist(otherFlightDetails);

        int result = flightDetailsRepository.updateActualPosition(Collections.singletonList(firstFlightRoute.getId()));
        TestCase.assertEquals(flightDetailss.size(), result);
    }

    @Test
    public void findLatestForSimulator() {
        Plane plane = TestDomainObjectFactory.getPlane();
        persist(plane);
        FlightRoute firstFlightRoute = TestDomainObjectFactory.getFlightRoute(plane, sourceAirport, destinationAirport);
        FlightRoute secondFlightRoute = TestDomainObjectFactory
                .getFlightRoute(plane, sourceAirport, destinationAirport);
        persist(firstFlightRoute, secondFlightRoute);

        List<FlightDetails> flightDetailss = TestDomainObjectFactory
                .findFlightDetails(firstFlightRoute, randomCount(10));
        flightDetailss.forEach(fd -> fd.setActualPosition(true));
        persist(flightDetailss);
        // todo finish test ...
        List<GetFlightDetailsDto> result = flightDetailsRepository.findLatestForSimulator(Collections.singletonList(firstFlightRoute));
        TestCase.assertNotNull(result);
        TestCase.assertEquals(flightDetailss.size(), result.size());

    }
}
