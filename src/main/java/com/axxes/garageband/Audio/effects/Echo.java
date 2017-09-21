package com.axxes.garageband.Audio.effects;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.lwjgl.openal.EXTEfx.*;

@Component
public class Echo extends Effect{

    public Echo(){}

    @PostConstruct
    private void init(){
        this.effect = audioDevice.createEffect(AL_EFFECT_ECHO);
        this.effectSlot = audioDevice.createEffectSlot(this.effect);
    }
}
