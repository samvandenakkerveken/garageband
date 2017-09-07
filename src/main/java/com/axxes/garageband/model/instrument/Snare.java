package com.axxes.garageband.model.instrument;

public class Snare implements Instrument {

    private String soundResource;

    public Snare(String soundResource){
        this.soundResource = soundResource;
    }

    @Override
    public void play() {

    }

}
