package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

@Ignore
public abstract class AbstractEntityRepositoryImplTest<T extends AbstractEntity>
        extends AbstractRepositoryTest<T> {

    protected abstract AbstractEntityRepository<T> getRepository();

    protected abstract T getEntity();

    protected abstract List<T> getEntities();

    @Test
    public void getByIdUsingCriteria() {
        List<T> entities = getEntities();
        persist(entities);
        int randomIndex = RANDOM.nextInt(entities.size());
        T entity = entities.get(randomIndex);

        T result = getRepository().getById(entity.getId());

        TestCase.assertNotNull(result);
        TestCase.assertEquals(entity, result);
    }

    @Test
    public void save() {
        T expected = getEntity();
        getRepository().save(expected);

        AbstractEntity result = getRepository().getById(expected.getId());

        TestCase.assertNotNull(result.getId());
        TestCase.assertEquals(expected, result);
    }

    @Test
    public void saveListAndFindAll() {
        List<T> expected = getEntities();
        getRepository().saveList(expected);

        List<T> result = getRepository().findAll(null, true);
        deepEquals(expected, result);
    }

    @Test
    public void countAll() {
        List<T> expected = getEntities();
        persist(expected);

        long result = getRepository().countAll();
        TestCase.assertEquals(expected.size(), result);
    }

    @Test
    public void getById() {
        T expected = getEntity();
        persist(expected);

        T result = getRepository().getById(expected.getId());

        TestCase.assertEquals(expected, result);
    }

    @Test
    public void delete() {
        T entity = getEntity();
        persist(entity);

        T result = getRepository().getById(entity.getId());
        TestCase.assertNotNull(result);

        getRepository().delete(entity);

        result = getRepository().getById(entity.getId());
        TestCase.assertNull(result);
    }

    @Test
    public void countBySearchParams() {
        T entity = getEntity();
        persist(entity);

        long result = getRepository().countBySearchParams(new Filter() {
        });
        TestCase.assertEquals(1L, result);
    }

    @Test
    public void findBySearchParams() {
        T entity = getEntity();
        persist(entity);

        List<T> result = getRepository().findBySearchParams(new Filter() {
        }, new PageRequest(0, Integer.MAX_VALUE));
        deepEquals(Collections.singletonList(entity), result);

        result = getRepository().findBySearchParams(new Filter() {
        }, new PageRequest(0, Integer.MAX_VALUE));
        deepEquals(Collections.singletonList(entity), result);
    }
}
