package com.axxes.garageband.model.loop;

import com.axxes.garageband.model.measures.Measure;
import org.springframework.stereotype.Component;

@Component
public class Drumloop implements Loop {

    private Measure[] measures;
    private int currentMeasure;
    private int numberOfMeasures;

    public Drumloop() {
        this.numberOfMeasures = 2;
        this.measures = new Measure[numberOfMeasures];
        this.currentMeasure = 0;
    }

    public void addMeasureOnLocation(Measure measure, int location) {
        this.measures[location] = measure;
    }


    public void step() {
        Measure measure = this.measures[currentMeasure];

        if (measure.getEndOfMeasure()){
            if (currentMeasure == (numberOfMeasures - 1)) {
                currentMeasure = 0;
            } else {
                currentMeasure++;
            }
        }
        measure.playMeasure();

    }

}
