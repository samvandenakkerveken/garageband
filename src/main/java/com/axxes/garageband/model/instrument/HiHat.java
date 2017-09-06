package com.axxes.garageband.model.instrument;

public class HiHat implements Instrument {

    private String soundResource;

    public HiHat(String soundResource){
        this.soundResource = soundResource;
    }

    @Override
    public void play() {

    }
}
