package com.gl.planesAndAirfileds.domain.filter;

import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Created by marek.sroga on 2017-03-20.
 */
public class OrderRequest implements Serializable {
    private String field;

    private boolean ascending;

    public OrderRequest() {
    }

    public OrderRequest(String field, boolean ascending) {
        this.field = field;
        this.ascending = ascending;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    public Sort.Order toOrder() {
        Sort.Direction direction = this.ascending ? Sort.Direction.ASC : Sort.Direction.DESC;
        return new Sort.Order(direction, this.field);
    }
}
