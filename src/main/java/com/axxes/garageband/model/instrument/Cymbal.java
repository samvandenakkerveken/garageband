package com.axxes.garageband.model.instrument;

public class Cymbal implements Instrument {

    private String soundResource;

    public Cymbal(String soundResource){
        this.soundResource = soundResource;
    }

    @Override
    public void play() {

    }

}
