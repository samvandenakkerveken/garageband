package com.axxes.garageband.model.instrument;

public class Kick implements Instrument {

    private String soundResource;

    public Kick(String soundResource){
        this.soundResource = soundResource;
    }

    @Override
    public void play() {

    }

}
