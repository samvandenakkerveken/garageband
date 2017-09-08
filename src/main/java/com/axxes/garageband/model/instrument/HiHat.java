package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HiHat implements Instrument {

    private String soundResource;

    public HiHat(String soundResource){
        this.soundResource = soundResource;
    }

    public HiHat(){
        this.soundResource = "hihat.wav";
    }

    @Override
    public void play() {
        Logger.getLogger(HiHat.class).info(this.getClass().getSimpleName() + " plays sound.");
        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

}
