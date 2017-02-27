package com.gl.planesAndAirfileds.repository;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.domain.PlaneId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaneRepository extends CrudRepository<Plane,Long>{
    @Query("select p from  Plane p")
    List<PlaneId> getPlanesId();
}
