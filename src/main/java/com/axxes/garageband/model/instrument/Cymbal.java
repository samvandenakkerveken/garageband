package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Cymbal implements Instrument {

    private String soundResource;

    public Cymbal(String soundResource){
        this.soundResource = soundResource;
    }

    public Cymbal(){
        this.soundResource = "cymbal.wav";
    }

    @Override
    public void play() {
        Logger.getLogger(Cymbal.class).info(this.getClass().getSimpleName() + " plays sound.");

        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

}
