package com.axxes.garageband.Audio.effects;

import com.axxes.garageband.Audio.AudioDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Effect {

    @Autowired
    protected AudioDevice audioDevice;

    protected int effect;
    protected int effectSlot;

    public int getEffectSlot() {
        return effectSlot;
    }
}
