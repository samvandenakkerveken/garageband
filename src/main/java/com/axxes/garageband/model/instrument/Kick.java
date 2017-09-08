package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Kick implements Instrument {

    private String soundResource;

    public Kick(String soundResource){
        this.soundResource = soundResource;
    }

    public Kick(){
        this.soundResource = "kick.wav";
    }

    @Override
    public void play() {
        Logger.getLogger(Kick.class).info(this.getClass().getSimpleName() + " plays sound.");
        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

}
