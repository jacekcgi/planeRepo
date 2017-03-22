package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.NestedEntity;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.repository.impl.NestedEntityRepository;
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

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class NestedEntityRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NestedEntityRepository nestedEntityRepository;

    @Test
    public void testSave() {
        NestedEntity nestedEntity = new NestedEntity();
        Plane plane = TestDomainObjectFactory.getPlane();
        nestedEntity.setPlane(plane);
        entityManager.persist(plane);
        entityManager.persist(nestedEntity);

        Assert.assertEquals(1, nestedEntityRepository.count());
    }

    @Test
    public void testSort() {
        Plane plane1 = TestDomainObjectFactory.getPlane();
        plane1.setName("AAA");
        Plane plane2 = TestDomainObjectFactory.getPlane();
        plane2.setName("BBB");
        entityManager.persist(plane1);
        entityManager.persist(plane2);

        NestedEntity nestedEntity1 = TestDomainObjectFactory.getNestedEntity(plane1);
        NestedEntity nestedEntity2 = TestDomainObjectFactory.getNestedEntity(plane2);
        nestedEntity1.setTestString("AAA x");
        nestedEntity2.setTestString("BBB x");

        entityManager.persist(nestedEntity1);
        entityManager.persist(nestedEntity2);

        List<NestedEntity> result = nestedEntityRepository.findBySearchParams(null,
                new PageRequest(0, Integer.MAX_VALUE,
                        new Sort(new Sort.Order(Sort.Direction.DESC, NestedEntity.FIELD_TEST_STRING))));

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(nestedEntity2, result.get(0));
        Assert.assertEquals(nestedEntity1, result.get(1));

        result = nestedEntityRepository.findBySearchParams(null,
                new PageRequest(0, Integer.MAX_VALUE, new Sort(
                        new Sort.Order(Sort.Direction.DESC, NestedEntity.FIELD_PLANE + "." + Plane.FIELD_NAME))));

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(nestedEntity2, result.get(0));
        Assert.assertEquals(nestedEntity1, result.get(1));

        result = nestedEntityRepository.findBySearchParams(null,
                new PageRequest(0, Integer.MAX_VALUE,
                        new Sort(new Sort.Order(Sort.Direction.ASC,
                                NestedEntity.FIELD_PLANE + "." + Plane.FIELD_NAME))));

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(nestedEntity1, result.get(0));
        Assert.assertEquals(nestedEntity2, result.get(1));
    }

}
