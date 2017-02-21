package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.repository.PlaneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by marcin.majka on 15/2/2017.
 */
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
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
        return plane;
    }

    public List<FlightDetails> getCurrentPositionOfPlanes(Long planeId){
       List<FlightDetails> result = null;
        try {
            if(planeId == null) {
                result = planeRepository.getCurrentPositionOfAllPlanes();
            }else {
                result = planeRepository.getCurrentPositionOfPlane(planeId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return result;
    }


    public Iterable<Plane> getAllPlanes() {
        return planeRepository.findAll();
    }

}
