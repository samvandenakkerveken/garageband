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

        for (int i = 0; i < beatsPerMeasure; i++) {
            this.beats[i] = new Beat();
        }
    }

    public void addBeatOnLocation(Beat beat, int location) {
        this.beats[location] = beat;
    }

    public void playMeasure() {
        //AccesLogger.getInstance().info("Measure: playing beat: " + currentBeat + ".");
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

}
