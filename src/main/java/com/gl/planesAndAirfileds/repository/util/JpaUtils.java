package com.gl.planesAndAirfileds.repository.util;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class JpaUtils extends org.jdal.dao.jpa.JpaUtils{

    public static <T> Root<T> findRoot(CriteriaQuery<?> query, Class<T> clazz) {

        for (Root<?> r : query.getRoots()) {
            if (clazz.equals(r.getJavaType())) {
                return (Root<T>) r.as(clazz);
            }
        }
        return null;
    }

    public static <T> Root<T> findOrCreateRoot(CriteriaQuery<?> query, Class<T> clazz){
        Root<T> root = findRoot(query, clazz);
        return root != null ? root :  query.from(clazz);
    }
}