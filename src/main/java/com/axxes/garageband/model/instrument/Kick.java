package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Kick implements Instrument {

    private final String soundResource;
    private final String image;

    public Kick(String soundResource, String image){
        this.soundResource = soundResource;
        this.image = image;
    }

    public Kick(){
        this.soundResource = "kick.wav";
        image = "/images/kick.png";
    }

    @Override
    public void play() {
        Logger.getLogger(Kick.class).info(this.getClass().getSimpleName() + " plays sound.");
        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

    public String getImage() {
        return image;
    }
}
