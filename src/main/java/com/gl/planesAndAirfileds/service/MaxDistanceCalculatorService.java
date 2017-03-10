package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.service.impl.FlightDetailsServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class MaxDistanceCalculatorService {

    private static final int PRECISION_NUMBER = 2;

    private FlightDetailsServiceImpl flightDetailsServiceImp;

    public FlightDetailsServiceImpl getFlightDetailsServiceImp() {
        return flightDetailsServiceImp;
    }

    public void setFlightDetailsServiceImp(FlightDetailsServiceImpl flightDetailsServiceImp) {
        this.flightDetailsServiceImp = flightDetailsServiceImp;
    }

    public Double calculateMaxDistance(Double fuelRemaining, Double currentFuelConsumption) {

        BigDecimal maxDistanceInKm = BigDecimal.ZERO;
        if (currentFuelConsumption != 0) {

            maxDistanceInKm = maxDistanceInKm.valueOf((fuelRemaining / currentFuelConsumption));
            maxDistanceInKm = takeFuelReserveIntoAccount(maxDistanceInKm);
            maxDistanceInKm = maxDistanceInKm.setScale(PRECISION_NUMBER, RoundingMode.HALF_DOWN);

        }

        return maxDistanceInKm.doubleValue();
    }

    private BigDecimal takeFuelReserveIntoAccount(BigDecimal maxDistanceInKm) {
        return maxDistanceInKm.multiply(new BigDecimal(0.9));
    }
}
