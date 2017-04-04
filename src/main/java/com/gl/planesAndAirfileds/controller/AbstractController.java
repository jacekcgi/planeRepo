package com.gl.planesAndAirfileds.controller;

import com.gl.planesAndAirfileds.domain.AbstractEntity;
import com.gl.planesAndAirfileds.domain.dto.SearchResult;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.domain.filter.PagingRequest;
import com.gl.planesAndAirfileds.domain.filter.SearchRequest;
import com.gl.planesAndAirfileds.service.AbstractEntityService;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by marek.sroga on 2017-03-06.
 */
public abstract class AbstractController {

    protected void addValidator(WebDataBinder binder, Class<?> clazz, Validator... validators) {
        Optional
                .ofNullable(binder.getTarget())
                .filter(notNullBinder -> clazz.isAssignableFrom(notNullBinder.getClass()))
                .ifPresent(o -> binder.addValidators(validators));
    }

    protected interface ResultCallback<T extends Serializable> {
        long count(Filter filter);

        List<T> find(Filter filter, PagingRequest pagingRequest);
    }

    protected <T extends AbstractEntity> SearchResult<T> findBySearchParams(SearchRequest searchRequest,
                                                                            final AbstractEntityService<T> service) {
        return findBySearchParams(searchRequest.getFilter(), searchRequest.getPageRequest(), service);
    }

    protected <T extends AbstractEntity> SearchResult<T> findBySearchParams(Filter filter,
                                                                            PagingRequest pagingRequest,
                                                                            final AbstractEntityService<T> service) {
        return findBySearchParams(filter, pagingRequest, new ResultCallback<T>() {
            @Override
            public long count(Filter filter) {
                return service.countBySearchParams(filter);
            }

            @Override
            public List<T> find(Filter filter, PagingRequest pagingRequest) {
                return service.findBySearchParams(filter, pagingRequest.toPageRequest());
            }
        });
    }

    protected <T extends Serializable> SearchResult<T> findBySearchParams(SearchRequest searchRequest,
                                                                          ResultCallback<T> resultCallback) {
        return findBySearchParams(searchRequest.getFilter(), searchRequest.getPageRequest(), resultCallback);
    }

    protected <T extends Serializable> SearchResult<T> findBySearchParams(Filter filter, PagingRequest pagingRequest,
                                                                          ResultCallback<T> resultCallback) {
        long count = resultCallback.count(filter);
        if (count > 0) {
            int page = pagingRequest.getPage();
            int size = pagingRequest.getSize();
            int pages = (int) Math.ceil((double) count / (double) size);
            if (page >= pages) {
                pagingRequest.setPage(pages - 1);
            }
            List<T> entities = resultCallback.find(filter, pagingRequest);
            return new SearchResult<>(entities, count, pagingRequest);
        }
        return new SearchResult<>(new ArrayList<T>(), count, pagingRequest);
    }
}
