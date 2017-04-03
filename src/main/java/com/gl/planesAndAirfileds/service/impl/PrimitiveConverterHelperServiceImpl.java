package com.gl.planesAndAirfileds.service.impl;

import com.gl.planesAndAirfileds.service.PrimitiveConverterHelperService;
import org.springframework.stereotype.Service;

/**
 * Created by jacekcygi on 27.03.17.
 */
@Service
public class PrimitiveConverterHelperServiceImpl implements PrimitiveConverterHelperService {

    @Override
    public double changeDoubleObjectToPrimitive(Double value) {

        if (value == null) {
            return 0d;
        }

        return value;
    }
}
