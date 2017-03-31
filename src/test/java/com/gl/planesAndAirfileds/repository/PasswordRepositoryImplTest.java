package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.Password;
import com.gl.planesAndAirfileds.domain.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by marek.sroga on 2017-03-29.
 */
public class PasswordRepositoryImplTest extends AbstractEntityRepositoryImplTest<Password> {
    @Autowired
    private PasswordRepository passwordRepository;

    private List<User> users;

    @Override
    protected AbstractEntityRepository<Password> getRepository() {
        return passwordRepository;
    }

    @Before
    public void setup() {
        users = TestDomainObjectFactory.findUsers(RANDOM.nextInt(10) + 1);
        persist(users);
    }

    @Override
    protected Password getEntity() {
        return TestDomainObjectFactory.getPassword(users.get(0));
    }

    @Override
    protected List<Password> getEntities() {
        return TestDomainObjectFactory.findPasswords(users);
    }

    @Test
    public void getByUser() {
        User user = TestDomainObjectFactory.getUser();
        persist(user);
        Password password = TestDomainObjectFactory.getPassword(user);
        persist(password);

        List<Password> otherPasswords = TestDomainObjectFactory.findPasswords(users);
        persist(otherPasswords);

        Password result = passwordRepository.getByUser(user);
        TestCase.assertEquals(password, result);
    }
}
