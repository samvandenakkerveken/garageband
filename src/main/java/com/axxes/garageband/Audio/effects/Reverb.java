package com.axxes.garageband.Audio.effects;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static org.lwjgl.openal.EXTEfx.AL_EFFECT_REVERB;

@Component
public class Reverb extends Effect {

    public Reverb(){}

    @PostConstruct
    private void init(){
        this.effect = audioDevice.createEffect(AL_EFFECT_REVERB);
        this.effectSlot = audioDevice.createEffectSlot(this.effect);
    }
}
