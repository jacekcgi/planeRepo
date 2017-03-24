package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.Plane;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FlightDetailsRepositoryTest extends AbstractEntityRepositoryImplTest<FlightDetails> {

    @Autowired
    private FlightDetailsRepository flightDetailsRepository;

    private Plane plane1;

    private Plane plane2;

    private Plane plane3;

    private Plane plane4;

    @Override
    protected AbstractEntityRepository<FlightDetails> getRepository() {
        return flightDetailsRepository;
    }

    @Override
    protected FlightDetails getEntity() {
        Plane plane = TestDomainObjectFactory.getPlane();
        persist(plane);
        return TestDomainObjectFactory.getFlightDetails(plane);
    }

    @Override
    protected List<FlightDetails> getEntities() {
        Plane plane = TestDomainObjectFactory.getPlane();
        persist(plane);
        List<FlightDetails> flightDetailss = new ArrayList<>();
        for (int i = 0; i < randomCount(10); i++) {
            flightDetailss.add(TestDomainObjectFactory.getFlightDetails(plane));
        }
        return flightDetailss;
    }

    @Before
    public void initialize() {
        plane1 = TestDomainObjectFactory.getPlane();
        plane2 = TestDomainObjectFactory.getPlane();
        plane3 = TestDomainObjectFactory.getPlane();
        plane4 = TestDomainObjectFactory.getPlane();
        persist(plane1, plane2, plane3, plane4);
    }

    @Test
    public void testFindLatest(){

        List<FlightDetails> flightDetails = flightDetailsRepository.findLatest();

    }
}
