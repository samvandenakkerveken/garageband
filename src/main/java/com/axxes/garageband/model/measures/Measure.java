package com.axxes.garageband.model.measures;

import com.axxes.garageband.model.instrument.Instrument;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Measure {

    private int beatsPerMeasure;
    private Beat[] beats;
    private int currentBeat;

    public Measure() {
        this.beatsPerMeasure = 4;
        this.beats = new Beat[beatsPerMeasure];
        this.currentBeat = 0;

        for (int i = 0; i < beatsPerMeasure; i++) {
            this.beats[i] = new Beat();
        }
    }

    public void addBeatOnLocation(Beat beat, int location) {
        this.beats[location] = beat;
    }

    public void playMeasure() {
        Logger.getLogger(Measure.class).info("Playing beat: " + currentBeat + ".");
        this.beats[currentBeat].playBeat();

        if (this.currentBeat == (beatsPerMeasure - 1)) {
            this.currentBeat = 0;
        } else {
            this.currentBeat++;
        }
    }

    public Boolean isEndOfMeasure(){
        return currentBeat == (beatsPerMeasure - 1);
    }

    public Beat[] getBeats() {
        return this.beats;
    }

    public void setBeats(Beat[] beats) {
        this.beats = beats;
    }

    public void addInstrument(Instrument instrument, int beatCount) {
        beats[beatCount].addInstrument(instrument);
    }

    public void removeInstrument(Instrument instrument, int beatCount) {
        beats[beatCount].removeInstrument(instrument);

    }
}
