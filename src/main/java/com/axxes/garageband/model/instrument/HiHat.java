package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;

import java.io.File;

public class HiHat implements Instrument {

    private File soundResource;

    public HiHat(String soundResource){
        this.soundResource = new File(soundResource);
    }

    @Override
    public void play() {
        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

}
