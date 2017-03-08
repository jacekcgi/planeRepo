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

    @Before
    public void initialize() {
        Plane p1 = TestDomainObjectFactory.getPlane();
        Plane p2 = TestDomainObjectFactory.getPlane();
        Plane p3 = TestDomainObjectFactory.getPlane();
        p1.setId(1l);
        p2.setId(2l);
        p3.setId(3l);

        p1 = entityManager.merge(p1);
        p2 = entityManager.merge(p2);
        p3 = entityManager.merge(p3);


        FlightDetails fd = TestDomainObjectFactory.getFlightDetails(p1);
        fd.setId(1L);
        fd.setGpsLatitude(21d);
        fd.setGpsLongitude(17d);
        fd.setCourse(30d);
        fd.setVelocity(500f);
        fd.setActualPosition(true);

        FlightDetails fd2 = TestDomainObjectFactory.getFlightDetails(p2);
        fd2.setId(2L);
        fd2.setGpsLatitude(22d);
        fd2.setGpsLongitude(18d);
        fd2.setCourse(45d);
        fd2.setVelocity(800f);
        fd2.setActualPosition(true);

        FlightDetails fd3 = TestDomainObjectFactory.getFlightDetails(p3);
        fd3.setId(3L);
        fd3.setGpsLatitude(23d);
        fd3.setGpsLongitude(19d);
        fd3.setCourse(130d);
        fd3.setVelocity(300f);
        fd3.setActualPosition(false);

        FlightDetails fd4 = TestDomainObjectFactory.getFlightDetails(p3);
        fd4.setId(4L);
        fd4.setGpsLatitude(23d);
        fd4.setGpsLongitude(19d);
        fd4.setCourse(130d);
        fd4.setVelocity(300f);
        fd4.setActualPosition(false);

        entityManager.merge(fd);
        entityManager.merge(fd2);
        entityManager.merge(fd3);
        entityManager.merge(fd4);
    }

    @Test
    public void testGetLatestFlightDetailsForAllPlanes() {
        List<FlightDetails> flightDetailsList = flightDetailsRepository.getLatestFlightDetailsForAllPlanes();
        assertThat(flightDetailsList).hasSize(2);
        for(FlightDetails fd:flightDetailsList) {
            assertThat(fd.getGpsLatitude()).isNotNull();
            assertThat(fd.getGpsLongitude()).isNotNull();
            assertThat(fd.getCourse()).isNotNull();
            assertThat(fd.getId()).isNotEqualTo(3l);
            assertThat(fd.isActualPosition()).isTrue();

        }
    }

    @Test
    public void testGetLatestFlightDetailForPlane() {
        List<FlightDetails> flightDetailsList = flightDetailsRepository.getLatestFlightDetailForPlane(2l);
        assertThat(flightDetailsList).hasSize(1);
        if(!flightDetailsList.isEmpty()) {
            FlightDetails fd = flightDetailsList.get(0);
            assertThat(fd.getGpsLatitude()).isEqualTo(22);
            assertThat(fd.getGpsLongitude()).isEqualTo(18);
            assertThat(fd.getCourse()).isEqualTo(45);
            assertThat(fd.isActualPosition()).isTrue();
        }

        flightDetailsList = flightDetailsRepository.getLatestFlightDetailForPlane(3l);
        assertThat(flightDetailsList).isEmpty();
    }
}
