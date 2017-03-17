package com.gl.planesAndAirfileds.domain.util;

import java.util.UUID;

/**
 * Created by krzysztof.gonia on 3/6/2017.
 */
public class SidUtils {

    private static final String MINUS = "-";

    public static String generate() {
        return UUID.randomUUID().toString().replace(MINUS, "");
    }
}
