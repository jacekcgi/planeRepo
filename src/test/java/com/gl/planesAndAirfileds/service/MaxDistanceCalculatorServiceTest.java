package com.gl.planesAndAirfileds.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(MaxDistanceCalculatorService.class)
public class MaxDistanceCalculatorServiceTest {

    public static final double DECIMAL_PRECISION = 0.000d;

    public static final double FUEL_REMAINING = 500d;

    @Autowired
    private MaxDistanceCalculatorService maxDistanceCalculatorService;

    @Test
    public void ifFuelRemaining500lAndFullConsumption12lPerKmThenExceptMaxDistance37andHalfKm() {

        double expectedDistanceInKm = 37.5d;

        double actualMaxDistance = maxDistanceCalculatorService.calculateMaxDistance(FUEL_REMAINING, 12d);

        assertEquals(expectedDistanceInKm, actualMaxDistance, DECIMAL_PRECISION);
    }

    @Test
    public void ifFuelRemaining500lAndFullConsumption0lPerKmThenExceptMaxDistance0andHalfKm() {

        double expectedDistanceInKm = 0.0d;

        double actualMaxDistance = maxDistanceCalculatorService.calculateMaxDistance(FUEL_REMAINING, 0d);

        assertEquals(expectedDistanceInKm, actualMaxDistance, DECIMAL_PRECISION);
    }

}
