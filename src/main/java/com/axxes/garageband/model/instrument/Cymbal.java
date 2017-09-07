package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class Cymbal implements Instrument {

    private String soundResource;

    public Cymbal(String soundResource){
        this.soundResource = soundResource;
    }

    @Override
    public void play() {
        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

}
