package com.gl.planesAndAirfileds.domain.util;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;

/**
 * Created by marcin.majka on 4/4/2017.
 */
public class GeodeticUtil {

    public static double calculateDistanceBetweenPoints(double srcLat,double srcLong,double destLat,double destLong) {
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;
        GlobalPosition pointA = new GlobalPosition(srcLat, srcLong, 0.0);
        GlobalPosition userPos = new GlobalPosition(destLat, destLong, 0.0);

        return geoCalc.calculateGeodeticCurve(reference, userPos, pointA).getEllipsoidalDistance() / 1000;
    }
}
