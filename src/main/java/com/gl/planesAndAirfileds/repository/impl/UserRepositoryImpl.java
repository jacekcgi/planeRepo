package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.repository.AbstractStatefulEntityRepository;
import com.gl.planesAndAirfileds.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by marek.sroga on 2017-03-29.
 */
@Repository("userRepository")
public class UserRepositoryImpl extends AbstractStatefulEntityRepositoryImpl<User> implements UserRepository
{
   @Override
   public long countByLogin(String login, String ignoreSid) {
      CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
      CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
      Root<User> root = criteriaQuery.from(User.class);
      criteriaQuery.select(builder.count(root));

      Predicate where = builder
              .equal(builder.lower(root.get(User.FIELD_LOGIN)), StringUtils.lowerCase(login));
      if (!StringUtils.isBlank(ignoreSid)) {
         where = builder.and(where, builder.notEqual(root.get(User.FIELD_SID), ignoreSid));
      }
      criteriaQuery.where(where);

      return getEntityManager().createQuery(criteriaQuery).getSingleResult();
   }
}
