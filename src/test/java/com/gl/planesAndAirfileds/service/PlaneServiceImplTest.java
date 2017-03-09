package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.TestDomainObjectFactory;
import com.gl.planesAndAirfileds.domain.Plane;
import com.gl.planesAndAirfileds.repository.PlaneRepository;
import com.gl.planesAndAirfileds.service.impl.PlaneServiceImpl;
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
@WebMvcTest(PlaneServiceImpl.class)
public class PlaneServiceImplTest {

    @MockBean
    private PlaneRepository planeRepository;

    @Autowired
    private PlaneServiceImpl planeServiceImpl;

    @Test
    public void shouldInvokeFindOneInRepository() {

        planeServiceImpl.getPlane(anyLong());

        verify(planeRepository).findOne(anyLong());

    }

    @Test
    public void shouldInvokeFindAllInRepository() {

        planeServiceImpl.getAllPlanes();

        verify(planeRepository).findAll();
    }

    @Test
    public void shouldInvokeSaveMethodInRepository() {

        planeServiceImpl.save(any(Plane.class));

        verify(planeRepository).save(any(Plane.class));
    }

    @Test(expected = Exception.class)
    public void shouldReturnNullWhenPlaneCannotBeSave() {

        Plane plane = TestDomainObjectFactory.getPlane();

        when(planeRepository.save(plane))
                .thenThrow(Exception.class);

        assertNull(planeServiceImpl.save(plane));

    }

}
