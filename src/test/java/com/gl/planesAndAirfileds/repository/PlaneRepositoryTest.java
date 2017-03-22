package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.filter.PlaneFilter;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;

public class PlaneRepositoryTest extends AbstractIdentifiableEntityRepositoryImplTest<Plane> {

    @Autowired
    private PlaneRepository planeRepository;

    @Override
    protected AbstractIdentifiableEntityRepository<Plane> getRepository() {
        return planeRepository;
    }

    @Override
    protected Plane getEntity() {
        return TestDomainObjectFactory.getPlane();
    }

    @Override
    protected List<Plane> getEntities() {
        return TestDomainObjectFactory.getPlanes(randomCount(10));
    }

    @Test
    public void testGetPlanesSid() {
        List<Plane> planes = TestDomainObjectFactory.getPlanes(randomCount(10));
        persist(planes);
        List<String> sids = planes.stream().map(Plane::getSid).collect(Collectors.toList());

        List<String> planesSid = planeRepository.findPlanesSid();
        deepEquals(sids, planesSid);
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
