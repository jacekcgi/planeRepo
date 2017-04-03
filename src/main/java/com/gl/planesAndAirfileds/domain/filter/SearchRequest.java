package com.gl.planesAndAirfileds.domain.filter;

import java.io.Serializable;

public class SearchRequest<F extends Filter> implements Serializable {
    private PagingRequest pageRequest = new PagingRequest(0, 1);

    private F filter;

    public F getFilter() {
        return filter;
    }

    public void setFilter(F filter) {
        this.filter = filter;
    }

    public PagingRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PagingRequest pageRequest) {
        this.pageRequest = pageRequest;
    }
}
