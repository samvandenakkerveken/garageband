package com.axxes.garageband.model.loop;

import com.axxes.garageband.model.instrument.Instrument;
import com.axxes.garageband.model.measures.Measure;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Drumloop implements Loop {

    private List<Measure> measures;
    private int currentMeasure;
    private int numberOfMeasures;

    public Drumloop() {
        this.measures = new ArrayList<>();
        this.numberOfMeasures = 2;
        this.currentMeasure = 0;

        for (int i = 0; i < numberOfMeasures; i++) {
            this.measures.add(new Measure());
        }
    }

    public void step() {
        Logger.getLogger(Drumloop.class).info("Step into measure: " + currentMeasure + ".");

        Measure measure = this.measures.get(currentMeasure);

        if (measure.isEndOfMeasure()) {
            if (currentMeasure == (numberOfMeasures - 1)) {
                currentMeasure = 0;
            } else {
                currentMeasure++;
            }
        }

        measure.playMeasure();
    }

    public List<Measure> getMeasures() {
        return this.measures;
    }

    public void setMeasures(List<Measure> measures) {
        this.measures = measures;
    }

    public void addInstrument(Instrument instrument, int measureCount, int beatCount) {
        measures.get(measureCount).addInstrument(instrument, beatCount);
    }

    public void removeInstrument(Instrument instrument, int measureCount, int beatCount) {
        measures.get(measureCount).removeInstrument(instrument, beatCount);

    }

    public boolean hasInstrument(Instrument instrument, int measureCount, int beatCount) {
        return measures.get(measureCount).hasInstrument(instrument, beatCount);
    }

    public int getCurrentMeasure() {
        return currentMeasure;
    }

    public int getCurrentBeat() {
        return measures.get(currentMeasure).getCurrentBeat();
    }

    public void setCurrentMeasure(int currentMeasure) {
        this.currentMeasure = currentMeasure;
    }
}
