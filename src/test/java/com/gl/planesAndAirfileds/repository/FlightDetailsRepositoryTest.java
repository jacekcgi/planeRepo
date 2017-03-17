package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.Plane;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlightDetailsRepositoryTest {
    public static boolean setup = false;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FlightDetailsRepository flightDetailsRepository;

    private String sid2;

    private String sid3;

    private String sid4;

    @Before
    public void initialize() {
        Plane p1 = TestDomainObjectFactory.getPlane();
        Plane p2 = TestDomainObjectFactory.getPlane();
        Plane p3 = TestDomainObjectFactory.getPlane();
        Plane p4 = TestDomainObjectFactory.getPlane();
        sid2 = p2.getSid();
        sid3 = p3.getSid();
        sid4 = p4.getSid();

        p1 = entityManager.merge(p1);
        p2 = entityManager.merge(p2);
        p3 = entityManager.merge(p3);
        p4 = entityManager.merge(p4);

        FlightDetails fd1 = TestDomainObjectFactory.getFlightDetails(p1);
        fd1.setActualPosition(true);

        FlightDetails fd2 = TestDomainObjectFactory.getFlightDetails(p2);
        fd2.setActualPosition(true);

        FlightDetails fd3 = TestDomainObjectFactory.getFlightDetails(p3);
        fd3.setActualPosition(false);

        FlightDetails fd3_1 = TestDomainObjectFactory.getFlightDetails(p3);
        fd3.setActualPosition(false);

        FlightDetails fd4 = TestDomainObjectFactory.getFlightDetails(p4);
        fd4.setActualPosition(true);
        fd4.setLanded(true);

        entityManager.merge(fd1);
        entityManager.merge(fd2);
        entityManager.merge(fd3);
        entityManager.merge(fd3_1);
        entityManager.merge(fd4);
    }

    @Test
    public void testGetLatestFlightDetailsForAllPlanes() {
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
        List<FlightDetails> flightDetailsList = flightDetailsRepository.getLatestFlightDetails(sid2, false);
        assertThat(flightDetailsList).hasSize(1);
      if (!flightDetailsList.isEmpty())
      {
         FlightDetails fd = flightDetailsList.get(0);
         assertThat(fd.getGpsLatitude()).isNotNull();
         assertThat(fd.getGpsLongitude()).isNotNull();
         assertThat(fd.getCourse()).isNotNull();
         assertThat(fd.isActualPosition()).isTrue();
          assertThat(fd.isLanded()).isFalse();
      }

        flightDetailsList = flightDetailsRepository.getLatestFlightDetails(sid3, true);
        assertThat(flightDetailsList).isEmpty();
        flightDetailsList = flightDetailsRepository.getLatestFlightDetails(sid4, false);
        assertThat(flightDetailsList).isEmpty();
        flightDetailsList = flightDetailsRepository.getLatestFlightDetails(sid4, true);
        assertThat(flightDetailsList).hasSize(1);

    }
}
