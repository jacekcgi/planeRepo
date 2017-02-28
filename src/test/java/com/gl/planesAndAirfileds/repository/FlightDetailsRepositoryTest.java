package com.gl.planesAndAirfileds.repository;

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

/**
 * Created by marcin.majka on 28/2/2017.
 */
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
        Plane p1 = new Plane();
        Plane p2 = new Plane();
        Plane p3 = new Plane();
        p1.setId(1l);
        p2.setId(2l);
        p3.setId(3l);

        p1 = entityManager.merge(p1);
        p2 = entityManager.merge(p2);
        p3 = entityManager.merge(p3);

        FlightDetails fd = new FlightDetails(1l,"21","17",30l,500f,true,p1);
        FlightDetails fd2 = new FlightDetails(2l,"22","18",45l,800f,true,p2);
        FlightDetails fd3 = new FlightDetails(3l,"23","19",130l,300f,false,p2);
        FlightDetails fd4 = new FlightDetails(4l,"23","19",130l,300f,false,p3);

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
            assertThat(fd.getGpsLatitude()).isNotEmpty();
            assertThat(fd.getGpsLongitude()).isNotEmpty();
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
            assertThat(fd.getGpsLatitude()).isEqualTo("22");
            assertThat(fd.getGpsLongitude()).isEqualTo("18");
            assertThat(fd.getCourse()).isEqualTo(45);
            assertThat(fd.isActualPosition()).isTrue();
        }

        flightDetailsList = flightDetailsRepository.getLatestFlightDetailForPlane(3l);
        assertThat(flightDetailsList).isEmpty();
    }
}
