package com.axxes.garageband.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccesLogger {

    private static Logger INSTANCE = LoggerFactory.getLogger("GARAGEBAND");

    public static Logger getInstance() {
        return INSTANCE;
    }

}
