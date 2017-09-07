package com.axxes.garageband.model.loop;

import com.axxes.garageband.model.measures.Measure;
import org.springframework.stereotype.Component;

@Component
public class Drumloop implements Loop {

    private Measure[] measures;

    public Drumloop() {
        this.measures = new Measure[2];
    }

    public void step() {

    }

}
