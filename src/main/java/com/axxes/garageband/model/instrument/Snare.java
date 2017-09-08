package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Snare implements Instrument {

    private final String soundResource;
    private final String image;

    public Snare(String soundResource, String image){
        this.soundResource = soundResource;
        this.image = image;
    }

    public Snare(){
        this.soundResource = "snare.wav";
        image = "/images/snare.png";
    }

    @Override
    public void play() {
        Logger.getLogger(Snare.class).info(this.getClass().getSimpleName() + " plays sound.");
        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

    public String getImage() {
        return image;
    }
}
