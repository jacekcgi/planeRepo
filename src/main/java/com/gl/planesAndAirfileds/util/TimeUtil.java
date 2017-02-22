package com.gl.planesAndAirfileds.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Created by marcin.majka on 22/2/2017.
 */
public class TimeUtil {
    public static Long getCurrentTimeInMillisecondsUTC() {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        return utc.toEpochSecond()*1000;
    }
}
