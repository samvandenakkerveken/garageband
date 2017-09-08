package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Snare implements Instrument {

    private String soundResource;

    public Snare(String soundResource){
        this.soundResource = soundResource;
    }

    public Snare(){
        this.soundResource = "snare.wav";
    }

    @Override
    public void play() {
        Logger.getLogger(Snare.class).info(this.getClass().getSimpleName() + " plays sound.");
        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

}
