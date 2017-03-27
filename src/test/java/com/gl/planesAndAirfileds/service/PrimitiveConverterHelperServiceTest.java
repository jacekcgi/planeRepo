package com.gl.planesAndAirfileds.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by jacekcygi on 27.03.17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PrimitiveConverterHelperService.class)
public class PrimitiveConverterHelperServiceTest {

    public static final double TEST_DOUBLE_VALUE = 45.675;

    @Autowired
    private PrimitiveConverterHelperService primitiveConverterHelperService;

    @Test
    public void ifDoubleObjectIsNullThenReturn0() {

        double expectedValue = 0d;
        assertEquals(expectedValue, primitiveConverterHelperService.changeDoubleObjectToPrimitive(null), 0.0);

    }

    @Test
    public void shouldReturnValueIfDoubleObjectIsNotNull() {

        assertEquals(TEST_DOUBLE_VALUE,
                primitiveConverterHelperService.changeDoubleObjectToPrimitive(TEST_DOUBLE_VALUE), 0.0);

    }

}
