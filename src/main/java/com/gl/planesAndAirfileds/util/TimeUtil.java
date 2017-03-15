package com.gl.planesAndAirfileds.util;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class TimeUtil {
    public static Long getCurrentTimeInMillisecondsUTC() {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        return utc.toEpochSecond() * 1000;
    }
}
