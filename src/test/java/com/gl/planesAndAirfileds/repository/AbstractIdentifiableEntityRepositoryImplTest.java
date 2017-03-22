package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractIdentifiableEntity;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@Ignore
public abstract class AbstractIdentifiableEntityRepositoryImplTest<T extends AbstractIdentifiableEntity>
        extends AbstractEntityRepositoryImplTest<T> {

    @Override
    protected abstract AbstractIdentifiableEntityRepository<T> getRepository();

    @Test
    public void getBySidUsingCriteria() {
        List<T> entities = getEntities();
        persist(entities);
        int randomIndex = RANDOM.nextInt(entities.size());
        T entity = entities.get(randomIndex);
        T result = getRepository().getBySid(entity.getSid());
        TestCase.assertNotNull(result);
        TestCase.assertEquals(entity, result);
    }

    @Test
    public void getBySid() {
        T expected = getEntity();
        persist(expected);
        T result = getRepository().getBySid(expected.getSid());
        TestCase.assertEquals(expected, result);
    }

    @Test
    public void findBySid() {
        List<T> expected = getEntities();
        persist(expected);

        List<String> sids = expected.stream().map(T::getSid).collect(Collectors.toList());
        List<T> result = getRepository().findBySid(sids);

        deepEquals(expected, result);
    }

    @Test(expected = NoResultException.class)
    public void getByNullSid() {
        getRepository().getBySid(null);
        TestCase.fail();
    }

    @Test(expected = NoResultException.class)
    public void getByBlankSid() {
        getRepository().getBySid("");
        TestCase.fail();
    }
}
