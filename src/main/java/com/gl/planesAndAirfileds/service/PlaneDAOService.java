package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.PlaneId;
import com.gl.planesAndAirfileds.repository.PlaneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaneDAOService {

    private PlaneRepository planeRepository;

    @Autowired
    public PlaneDAOService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public Plane getPlane(Long id) {
        return planeRepository.findOne(id);
    }

    public Plane save(Plane plane) {
        return planeRepository.save(plane);
    }

    public List<PlaneId> getAllPlanesId () {
        return planeRepository.getPlanesId();
    }

    public Iterable<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }
}
