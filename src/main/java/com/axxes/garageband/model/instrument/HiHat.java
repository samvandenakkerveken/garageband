package com.axxes.garageband.model.instrument;

import kuusisto.tinysound.Sound;
import kuusisto.tinysound.TinySound;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class HiHat implements Instrument {

    private final String soundResource;
    private final String image;

    public HiHat(String soundResource, String image){
        this.soundResource = soundResource;
        this.image = image;
    }

    public HiHat(){
        this.soundResource = "hihat.wav";
        this.image = "/images/hihat.png";
    }

    @Override
    public void play() {
        Logger.getLogger(HiHat.class).info(this.getClass().getSimpleName() + " plays sound.");
        Sound sound = TinySound.loadSound(soundResource);
        sound.play();
    }

    public String getImage() {
        return image;
    }
}
