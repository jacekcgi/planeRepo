package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.Password;
import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.repository.PasswordRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Created by marek.sroga on 2017-03-29.
 */
@Repository("passwordRepository")
public class PasswordRepositoryImpl extends AbstractEntityRepositoryImpl<Password> implements PasswordRepository {
    @Override
    public Password getByUser(User user) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Password> criteriaQuery = builder.createQuery(Password.class);
        Root<Password> root = criteriaQuery.from(Password.class);
        criteriaQuery.where(builder.equal(root.get(Password.FIELD_USER), user));
        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }
}
