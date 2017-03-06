package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.PlaneId;
import com.gl.planesAndAirfileds.repository.AbstractEntityRepository;
import com.gl.planesAndAirfileds.repository.PlaneRepository;
import com.gl.planesAndAirfileds.service.PlaneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlaneServiceImpl extends AbstractEntityServiceImpl<Plane, Long> implements PlaneService
{
    private PlaneRepository planeRepository;

    @Autowired
    public PlaneServiceImpl(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    protected AbstractEntityRepository<Plane, Long> getRepository() {
        return planeRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Plane getPlane(Long id) {
        return planeRepository.findOne(id);
    }

    @Override
    public Plane save(Plane plane) {
        return planeRepository.save(plane);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlaneId> getAllPlanesId() {
        return planeRepository.getPlanesId();
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }
}
