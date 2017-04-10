package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.domain.util.SidUtils;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by marek.sroga on 2017-03-29.
 */
public class UserRepositoryImplTest extends AbstractStatefulEntityRepositoryImplTest<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected AbstractStatefulEntityRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    protected User getEntity() {
        return TestDomainObjectFactory.getUser();
    }

    @Override
    protected List<User> getEntities() {
        return TestDomainObjectFactory.findUsers(RANDOM.nextInt(10) + 1);
    }

    @Test
    public void getByLogin() {
        String login = SidUtils.generate();
        User user = TestDomainObjectFactory.getUser();
        user.setLogin(login);
        persist(user);

        List<User> otherUsers = TestDomainObjectFactory.findUsers(randomCount(10));
        persist(otherUsers);

        User result = userRepository.getByLogin(login);
        TestCase.assertEquals(user, result);
    }
}
