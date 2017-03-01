package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.PlaneId;
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
public class PlaneRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlaneRepository planeRepository;

    @Test
    public void testGetPlanesId() {
        Plane plane = new Plane(null,"plane 1","123","first plain test");
        Plane plane2 = new Plane(null,"plane 1","123","second plain test");
        entityManager.persist(plane);
        entityManager.persist(plane2);
        List<PlaneId> planesIdList = planeRepository.getPlanesId();
        for(PlaneId id :planesIdList) {
            assertThat(id.getId()).isNotNull();
        }


    }

}
