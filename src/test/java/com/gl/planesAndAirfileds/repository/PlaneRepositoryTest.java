package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.PlaneSid;
import com.gl.planesAndAirfileds.domain.filter.PlaneFilter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;

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
    public void testGetPlanesSid() {
        Plane plane = TestDomainObjectFactory.getPlane();
        Plane plane2 = TestDomainObjectFactory.getPlane();
        entityManager.persist(plane);
        entityManager.persist(plane2);
        List<PlaneSid> planesIdList = planeRepository.getPlanesSid();
        for (PlaneSid id : planesIdList) {
            assertThat(id.getSid()).isNotEmpty();
        }
    }

    @Test
    public void testGetBySid() {

        Plane plane = TestDomainObjectFactory.getPlane();
        String sid = plane.getSid();
        Plane otherPlane = TestDomainObjectFactory.getPlane();
        String otherSid = otherPlane.getSid();

        entityManager.persist(plane);
        entityManager.persist(otherPlane);

        Plane result = planeRepository.getBySid(sid);
        Assert.assertEquals(plane, result);
    }

    @Test
    public void testFindByName() {
        List<Plane> planes = TestDomainObjectFactory.getPlanes(new Random().nextInt(10) + 2);
        planes.get(0).setName("Test name");
        planes.get(1).setName("Other name");

        planes.forEach(entityManager::persist);

        PlaneFilter planeFilter = new PlaneFilter();
        planeFilter.setName("name");

        List<Plane> result = planeRepository.findBySearchParams(planeFilter, new PageRequest(0, Integer.MAX_VALUE));
        Assert.assertEquals(2, result.size());
        Assert.assertThat(Arrays.asList(planes.get(0), planes.get(1)), is(result));

        planeFilter.setName("Test");
        result = planeRepository.findBySearchParams(planeFilter, new PageRequest(0, Integer.MAX_VALUE));
        Assert.assertEquals(1, result.size());
        Assert.assertThat(Collections.singletonList(planes.get(0)), is(result));
    }

    @Test
    public void testPageable() {
        List<Plane> planes = TestDomainObjectFactory.getPlanes(new Random().nextInt(10) + 2);
        planes.get(0).setName("Test name");
        planes.get(1).setName("Other name");

        planes.forEach(entityManager::persist);

        PlaneFilter planeFilter = new PlaneFilter();
        planeFilter.setName("name");

        List<Plane> result = planeRepository.findBySearchParams(planeFilter, new PageRequest(0, 1));
        Assert.assertEquals(1, result.size());
        Assert.assertThat(Collections.singletonList(planes.get(0)), is(result));

        result = planeRepository.findBySearchParams(planeFilter, new PageRequest(1, 1));
        Assert.assertEquals(1, result.size());
        Assert.assertThat(Collections.singletonList(planes.get(1)), is(result));
    }

    @Test
    public void testOrder() {
        List<Plane> planes = TestDomainObjectFactory.getPlanes(new Random().nextInt(10) + 2);
        planes.get(0).setName("BBB name");
        planes.get(1).setName("AAA name");

        planes.forEach(entityManager::persist);

        PlaneFilter planeFilter = new PlaneFilter();
        planeFilter.setName("name");

        List<Plane> result = planeRepository.findBySearchParams(planeFilter,
                new PageRequest(0, Integer.MAX_VALUE, new Sort(new Sort.Order(Sort.Direction.ASC, Plane.FIELD_NAME))));

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(planes.get(1), result.get(0));
        Assert.assertEquals(planes.get(0), result.get(1));

        result = planeRepository.findBySearchParams(planeFilter,
                new PageRequest(0, Integer.MAX_VALUE, new Sort(new Sort.Order(Sort.Direction.DESC, Plane.FIELD_NAME))));

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(planes.get(0), result.get(0));
        Assert.assertEquals(planes.get(1), result.get(1));
    }

}
