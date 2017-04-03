package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Ignore
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"com.gl.planesAndAirfileds.repository"})
@Rollback
@Transactional
public abstract class AbstractRepositoryTest {

    protected static final Random RANDOM = new Random();

    @Autowired
    protected TestEntityManager entityManager;

    protected void persist(AbstractEntity... entries) {
        for (AbstractEntity entry : entries) {
            entityManager.persist(entry);
            entityManager.flush();
        }
    }

    protected <T extends AbstractEntity> void persist(List<T> entries) {
        for (T entry : entries) {
            entityManager.persist(entry);
            entityManager.flush();
        }
    }

    protected <S> void deepEquals(List<S> expected, List<S> result) {
        TestCase.assertNotNull(expected);
        TestCase.assertNotNull(result);
        TestCase.assertEquals(expected.size(), result.size());
        for (S o : expected) {
            TestCase.assertTrue(result.contains(o));
        }
    }

    public static int randomCount() {
        return randomCount(1);
    }

    public static int randomCount(int min) {
        return RANDOM.nextInt(10) + min;
    }
}
