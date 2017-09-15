package com.axxes.garageband.model.instrument;

import com.axxes.garageband.Audio.AudioDevice;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class HiHat implements Instrument {

    private final String soundResource;
    private final String image;
    private final float volume;

    private AudioDevice audioDevice;
    private ExecutorService executor;


    public HiHat(String soundResource, String image){
        this.soundResource = soundResource;
        this.image = image;
        this.volume = 1;
        audioInit(soundResource);
    }

    public HiHat(){
        this.soundResource = "hihat.wav";
        this.image = "/images/hihat.png";
        this.volume = 1;
        audioInit(soundResource);
    }

    private void audioInit(String soundResource) {
        executor = Executors.newSingleThreadExecutor();
        Runnable audioInit = () -> {
            this.audioDevice = new AudioDevice();
            this.audioDevice.init();
            this.audioDevice.createBuffer(soundResource);
        };
        executor.execute(audioInit);
    }

    @Override
    public void play(int audioEffect) {
        Logger.getLogger(HiHat.class).info(this.getClass().getSimpleName() + " plays sound.");

        Runnable playTask = () -> this.audioDevice.play(volume, audioEffect);
        executor.execute(playTask);

    }

    public String getImage() {
        return image;
    }

    @Override
    public void shutdownExecutor() {
        audioDevice.destroy();
        executor.shutdown();
    }

}
