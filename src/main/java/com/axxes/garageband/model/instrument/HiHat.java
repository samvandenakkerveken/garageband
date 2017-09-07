package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import org.apache.log4j.Logger;

import java.io.File;

public class HiHat implements Instrument {

    private File soundResource;

    public HiHat(String soundResource){
        this.soundResource = new File(soundResource);
    }

    @Override
    public void play() {
        Logger.getLogger(HiHat.class).info(this.getClass().getSimpleName() + " plays sound.");
        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

}
