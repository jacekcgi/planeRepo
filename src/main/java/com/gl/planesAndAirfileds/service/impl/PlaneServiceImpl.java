package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.filter.Filter;
import com.gl.planesAndAirfileds.repository.AbstractIdentifiableEntityRepository;
import com.gl.planesAndAirfileds.repository.PlaneRepository;
import com.gl.planesAndAirfileds.service.PlaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("planeService")
public class PlaneServiceImpl extends AbstractIdentifiableEntityServiceImpl<Plane> implements PlaneService {
    private PlaneRepository planeRepository;

    @Override
    protected String[] getUpdateIgnoreFields()
    {
        return new String[] {
                Plane.FIELD_ID,
                Plane.FIELD_SID,
                Plane.FIELD_CREATE_DATE
        };
    }

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    protected AbstractIdentifiableEntityRepository<Plane> getRepository() {
        return planeRepository;
    }

    @Override
    public List<Plane> findBySearchParams(Filter filter, Pageable pageRequest) {
        return planeRepository.findBySearchParams(filter, pageRequest);
    }

    @Override
    public long countBySearchParams(Filter filter) {
        return planeRepository.countBySearchParams(filter);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existRegistration(String registration, String ignoreSid) {
        return planeRepository.countByRegistration(registration, ignoreSid) > 0;
    }
}
