package com.axxes.garageband.model.measures;

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
    }

    public void addBeatOnLocation(Beat beat, int location) {
        this.beats[location] = beat;
    }

    public void playMeasure() {
        this.beats[currentBeat].playBeat();

        if (this.currentBeat == beatsPerMeasure) {
            this.currentBeat = 0;
        } else {
            this.currentBeat++;
        }
    }

    public Boolean getEndOfMeasure(){
        return currentBeat == (beatsPerMeasure - 1);
    }
}
