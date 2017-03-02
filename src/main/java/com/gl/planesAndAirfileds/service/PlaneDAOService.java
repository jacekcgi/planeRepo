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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private PlaneRepository planeRepository;

    @Autowired
    public PlaneDAOService(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    public Plane getPlane(Long id) {
        return planeRepository.findOne(id);
    }

    public Plane save(Plane plane) {

        try {
            plane = planeRepository.save(plane);
        } catch (Exception e) {
            logger.error("Database Exception",e);
            return null;
        }
        return plane;
    }

    public List<PlaneId> getAllPlanesId () {

        List<PlaneId> planes = null;
        try {
            planes = planeRepository.getPlanesId();
        }
        catch (Exception e) {
            logger.error("Database Exception",e);
            return null;
        }
        return planes;
    }

    public Iterable<Plane> getAllPlanes() {
        Iterable<Plane> planes = null;
        try {
            planes = planeRepository.findAll();
        } catch (Exception e) {
            logger.error("Database Exception",e);
            return null;
        }
        return planes;
    }


}
