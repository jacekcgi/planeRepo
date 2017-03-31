package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.NestedEntity;
import com.gl.planesAndAirfileds.repository.impl.NestedEntityRepository;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class NestedEntityRepositoryTest extends AbstractEntityRepositoryImplTest<NestedEntity> {

    @Autowired
    private NestedEntityRepository nestedEntityRepository;

    @Test
    public void testSort() {
        NestedEntity childNestedEntity1 = TestDomainObjectFactory.getNestedEntity();
        childNestedEntity1.setTestString("AAA");
        NestedEntity childNestedEntity2 = TestDomainObjectFactory.getNestedEntity();
        childNestedEntity2.setTestString("BBB");
        persist(childNestedEntity1, childNestedEntity2);

        NestedEntity nestedEntity1 = TestDomainObjectFactory.getNestedEntity(childNestedEntity1);
        NestedEntity nestedEntity2 = TestDomainObjectFactory.getNestedEntity(childNestedEntity2);
        nestedEntity1.setTestString("AAA x");
        nestedEntity2.setTestString("BBB x");

        persist(nestedEntity1, nestedEntity2);

        List<NestedEntity> result = nestedEntityRepository.findBySearchParams(null,
                new PageRequest(0, Integer.MAX_VALUE,
                        new Sort(new Sort.Order(Sort.Direction.DESC, NestedEntity.FIELD_TEST_STRING))));

        TestCase.assertEquals(4, result.size());
        TestCase.assertEquals(nestedEntity2, result.get(0));

        result = nestedEntityRepository.findBySearchParams(null,
                new PageRequest(0, Integer.MAX_VALUE, new Sort(
                        new Sort.Order(Sort.Direction.DESC,
                                NestedEntity.FIELD_NESTED_ENTITY + "." + NestedEntity.FIELD_TEST_STRING))));

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(nestedEntity2, result.get(0));
        Assert.assertEquals(nestedEntity1, result.get(1));

        result = nestedEntityRepository.findBySearchParams(null,
                new PageRequest(0, Integer.MAX_VALUE,
                        new Sort(new Sort.Order(Sort.Direction.ASC,
                                NestedEntity.FIELD_NESTED_ENTITY + "." + NestedEntity.FIELD_TEST_STRING))));

        Assert.assertEquals(2, result.size());
        Assert.assertEquals(nestedEntity1, result.get(0));
        Assert.assertEquals(nestedEntity2, result.get(1));
    }

    @Override
    protected AbstractEntityRepository<NestedEntity> getRepository() {
        return nestedEntityRepository;
    }

    @Override
    protected NestedEntity getEntity() {
        return TestDomainObjectFactory.getNestedEntity();
    }

    @Override
    protected List<NestedEntity> getEntities() {
        List<NestedEntity> nestedEntities = new ArrayList<>();
        for (int i = 0; i < randomCount(10); i++) {
            nestedEntities.add(TestDomainObjectFactory.getNestedEntity());
        }
        return nestedEntities;
    }
}
