package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.User;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.domain.filter.UserFilter;
import com.gl.planesAndAirfileds.repository.UserRepository;
import com.gl.planesAndAirfileds.repository.util.JpaUtils;
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
public class UserRepositoryImpl extends AbstractStatefulEntityRepositoryImpl<User> implements UserRepository {
    @Override
    public CriteriaQuery<User> createCriteriaFromSearchParams(Filter filter) {

        CriteriaQuery<User> criteria = super.createCriteriaFromSearchParams(filter);
        Root<User> root = JpaUtils.findOrCreateRoot(criteria, User.class);
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();

        Predicate where = builder.conjunction();

        if (filter != null && UserFilter.class.isAssignableFrom(filter.getClass())) {
            UserFilter userFilter = (UserFilter) filter;

            if (StringUtils.isNotBlank(userFilter.getLogin())) {
                where = builder.and(where, builder.like(
                        builder.lower(root.get(User.FIELD_LOGIN)),
                        "%" + userFilter.getLogin().toLowerCase() + "%"));
            }

            if (StringUtils.isNotBlank(userFilter.getName())) {
                Predicate nameOrSurname = builder.or(
                        builder.like(
                                builder.lower(root.get(User.FIELD_NAME)),
                                "%" + userFilter.getName().toLowerCase() + "%"),
                        builder.like(
                                builder.lower(root.get(User.FIELD_SURNAME)),
                                "%" + userFilter.getName().toLowerCase() + "%"));
                where = builder.and(where, nameOrSurname);
            }

            criteria.where(where);
        }

        return criteria;
    }

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

    @Override
    public User getByLogin(String login) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.where(builder.equal(root.get(User.FIELD_LOGIN), login));
        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }
}
