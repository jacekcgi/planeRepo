package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.Plane;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    private void prepareFlightDetails() {
        FlightDetails fd1 = TestDomainObjectFactory.getFlightDetails(plane1);
        fd1.setActualPosition(true);

        FlightDetails fd2 = TestDomainObjectFactory.getFlightDetails(plane2);
        fd2.setActualPosition(true);

        FlightDetails fd3 = TestDomainObjectFactory.getFlightDetails(plane3);
        fd3.setActualPosition(false);

        FlightDetails fd3_1 = TestDomainObjectFactory.getFlightDetails(plane3);
        fd3.setActualPosition(false);

        FlightDetails fd4 = TestDomainObjectFactory.getFlightDetails(plane4);
        fd4.setActualPosition(true);
        fd4.setLanded(true);

        persist(fd1, fd2, fd3, fd3_1, fd4);
    }

    @Test
    public void testGetLatestFlightDetailsForAllPlanes() {
        prepareFlightDetails();
        List<FlightDetails> flightDetailsList = flightDetailsRepository.getLatestFlightDetails(null, false);
        assertThat(flightDetailsList).hasSize(2);
        for (FlightDetails fd : flightDetailsList) {
            assertThat(fd.getGpsLatitude()).isNotNull();
            assertThat(fd.getGpsLongitude()).isNotNull();
            assertThat(fd.getCourse()).isNotNull();
            assertThat(fd.getId()).isNotNull();
            assertThat(fd.isActualPosition()).isTrue();
            assertThat(fd.isLanded()).isFalse();

        }

        flightDetailsList = flightDetailsRepository.getLatestFlightDetails(null, true);
        assertThat(flightDetailsList).hasSize(3);
    }

    @Test
    public void testGetLatestFlightDetailForPlane() {
        prepareFlightDetails();
        List<FlightDetails> flightDetailsList = flightDetailsRepository.getLatestFlightDetails(plane2.getSid(), false);
        assertThat(flightDetailsList).hasSize(1);
        if (!flightDetailsList.isEmpty()) {
            FlightDetails fd = flightDetailsList.get(0);
            assertThat(fd.getGpsLatitude()).isNotNull();
            assertThat(fd.getGpsLongitude()).isNotNull();
            assertThat(fd.getCourse()).isNotNull();
            assertThat(fd.isActualPosition()).isTrue();
            assertThat(fd.isLanded()).isFalse();
        }

        flightDetailsList = flightDetailsRepository.getLatestFlightDetails(plane3.getSid(), true);
        assertThat(flightDetailsList).isEmpty();
        flightDetailsList = flightDetailsRepository.getLatestFlightDetails(plane4.getSid(), false);
        assertThat(flightDetailsList).isEmpty();
        flightDetailsList = flightDetailsRepository.getLatestFlightDetails(plane4.getSid(), true);
        assertThat(flightDetailsList).hasSize(1);

    }
}
