package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.Password;
import com.gl.planesAndAirfileds.domain.User;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.gl.planesAndAirfileds.repository.AbstractRepositoryTest.RANDOM;

/**
 * Created by marek.sroga on 2017-03-29.
 */
public class PasswordRepositoryTest extends AbstractEntityRepositoryImplTest<Password>
{
   @Autowired
   private PasswordRepository passwordRepository;

   private List<User> users;

   @Override
   protected AbstractEntityRepository<Password> getRepository()
   {
      return passwordRepository;
   }

   @Before
   public void setup() {
      users = TestDomainObjectFactory.findUsers(RANDOM.nextInt(10) + 1);
      persist(users);
   }

   @Override
   protected Password getEntity()
   {
      return TestDomainObjectFactory.getPassword(users.get(0));
   }

   @Override
   protected List<Password> getEntities()
   {
      return TestDomainObjectFactory.findPasswords(users);
   }
}
