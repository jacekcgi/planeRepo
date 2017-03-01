package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.repository.PlaneRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(PlaneDAOService.class)
public class PlaneDAOServiceTest {

    @MockBean
    private PlaneRepository planeRepository;

    @Autowired
    private PlaneDAOService planeDAOService;

    @Test
    public void shouldInvokeFindOneInRepository() {

        planeDAOService.getPlane(anyLong());

        verify(planeRepository).findOne(anyLong());

    }

    @Test
    public void shouldInvokeFindAllInRepository() {

        planeDAOService.getAllPlanes();

        verify(planeRepository).findAll();
    }

    @Test
    public void shouldInvokeSaveMethodInRepository() {

        planeDAOService.save(any(Plane.class));

        verify(planeRepository).save(any(Plane.class));
    }

    @Test
    public void shouldReturnNullWhenPlaneCannotBeSave() {

        Plane plane = new Plane();

        when(planeRepository.save(plane))
                .thenThrow(Exception.class);

        assertNull(planeDAOService.save(plane));

    }

}
