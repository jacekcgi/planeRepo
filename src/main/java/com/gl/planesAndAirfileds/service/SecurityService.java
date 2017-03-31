package com.gl.planesAndAirfileds.service;

import com.gl.planesAndAirfileds.domain.User;

public interface SecurityService {
    User getLoggedInUser();
}
