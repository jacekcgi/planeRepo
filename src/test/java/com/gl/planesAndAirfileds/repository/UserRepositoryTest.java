package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by marek.sroga on 2017-03-29.
 */
public class UserRepositoryTest extends AbstractStatefulEntityRepositoryImplTest<User>
{
   @Autowired
   private UserRepository userRepository;

   @Override
   protected AbstractStatefulEntityRepository<User> getRepository()
   {
      return userRepository;
   }

   @Override
   protected User getEntity()
   {
      return TestDomainObjectFactory.getUser();
   }

   @Override
   protected List<User> getEntities()
   {
      return TestDomainObjectFactory.findUsers(RANDOM.nextInt(10) + 1);
   }
}
