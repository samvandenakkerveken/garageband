package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class Snare implements Instrument {

    private String soundResource;

    public Snare(String soundResource){
        this.soundResource = soundResource;
    }

    @Override
    public void play() {
        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

}
