package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.FlightDetails;
import com.gl.planesAndAirfileds.domain.Plane;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marcin.majka on 15/2/2017.
 */
@Repository
public interface PlaneRepository extends CrudRepository<Plane,Long>{

    @Query("select f from FlightDetails f where f.isActualPosition = true")
    List<FlightDetails> getCurrentPositionOfAllPlanes();
    @Query("select f from FlightDetails f where f.isActualPosition = true and f.plane.id = :id" )
    List<FlightDetails> getCurrentPositionOfPlane(@Param("id") Long id);
}
