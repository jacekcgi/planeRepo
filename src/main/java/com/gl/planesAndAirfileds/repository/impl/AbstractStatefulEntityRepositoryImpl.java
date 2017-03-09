package com.gl.planesAndAirfileds.repository.impl;

import com.gl.planesAndAirfileds.domain.AbstractStatefulEntity;
import com.gl.planesAndAirfileds.domain.StateEnum;
import com.gl.planesAndAirfileds.repository.CustomStatefulEntityRepository;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


/**
 * Created by krzysztof.gonia on 3/7/2017.
 */
@Component
public abstract class AbstractStatefulEntityRepositoryImpl<T extends AbstractStatefulEntity>
        extends AbstractIdentifiableEntityRepositoryImpl<T>
        implements CustomStatefulEntityRepository<T> {

    protected Predicate applyStatefulCriteria(CriteriaBuilder builder, Root<T> root, StateEnum stateful,
                                              Predicate where) {
        if (stateful != null) {
            return builder.and(
                    where,
                    builder.equal(root.get(AbstractStatefulEntity.FIELD_ACTIVE), stateful == StateEnum.ACTIVE));
        } else {
            return where;
        }
    }
}
