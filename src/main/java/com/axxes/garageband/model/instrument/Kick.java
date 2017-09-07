package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

public class Kick implements Instrument {

    private String soundResource;

    public Kick(String soundResource){
        this.soundResource = soundResource;
    }

    @Override
    public void play() {
        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

}
