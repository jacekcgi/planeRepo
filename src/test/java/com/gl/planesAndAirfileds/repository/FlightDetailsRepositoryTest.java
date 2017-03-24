package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.Airport;
import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.FlightRoute;
import com.gl.planesAndAirfileds.domain.Plane;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightDetailsRepositoryTest extends AbstractEntityRepositoryImplTest<FlightDetails> {

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
    public void testFindLatest(){

        Plane plane = TestDomainObjectFactory.getPlane();
        persist(plane);
        Plane otherPlane = TestDomainObjectFactory.getPlane();
        persist(otherPlane);

        LocalDateTime now = LocalDateTime.now();
        List<FlightRoute> planeFlightRoutes = TestDomainObjectFactory.findPlaneRoutes(plane, sourceAirport, destinationAirport, randomCount(2));
        FlightRoute firstFlightRoute = planeFlightRoutes.get(0);
        firstFlightRoute.setStartDate(now.minusDays(3));
        firstFlightRoute.setIncomingDate(now.plusDays(2));

        FlightRoute secondFlightRoute = planeFlightRoutes.get(1);
        secondFlightRoute.setPlane(otherPlane);
        secondFlightRoute.setStartDate(now.minusDays(3));
        secondFlightRoute.setIncomingDate(now.plusDays(2));
        persist(planeFlightRoutes);


        List<FlightDetails> firstFlightDetails = TestDomainObjectFactory.findFlightDetails(firstFlightRoute, randomCount(5));
        FlightDetails lastDetailOfFirstRoute = firstFlightDetails.get(firstFlightDetails.size()-1);
        lastDetailOfFirstRoute.setActualPosition(true); // excepted this position
        persist(firstFlightDetails);

        List<FlightDetails> secondFlightDetails = TestDomainObjectFactory.findFlightDetails(secondFlightRoute, randomCount(5));
        FlightDetails lastDetailOfSecondRoute = secondFlightDetails.get(secondFlightDetails.size()-1);
        lastDetailOfSecondRoute.setActualPosition(true); // excepted this position
        persist(secondFlightDetails);


//        List<FlightDetails> result = flightDetailsRepository.findLatest();
//        deepEquals(Arrays.asList(lastDetailOfFirstRoute, lastDetailOfSecondRoute), result);
    }
}
