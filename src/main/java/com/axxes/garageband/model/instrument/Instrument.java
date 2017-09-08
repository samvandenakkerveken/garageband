package com.axxes.garageband.model.instrument;

import org.springframework.stereotype.Component;

@Component
public interface Instrument {

    void play();

    String getImage();
}
