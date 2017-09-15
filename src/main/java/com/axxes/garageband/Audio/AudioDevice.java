package com.axxes.garageband.Audio;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;
import org.springframework.stereotype.Component;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.AL11.alSource3i;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.openal.EXTEfx.*;

@Component
public class AudioDevice {

    private long device;
    private long context;
    private int bufferPointer;
    private int sourcePointer;
    private int effect;
    private int effectSlot;

    public AudioDevice(){

    }

    public void init(){
        device = alcOpenDevice(alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER));

        int[] attributes = {0};
        context = alcCreateContext(device, attributes);
        alcMakeContextCurrent(context);
        ALCCapabilities alcCapabilities = ALC.createCapabilities(device);
        ALCapabilities alCapabilities = AL.createCapabilities(alcCapabilities);

    }

    public void createBuffer(String soundResource){
        bufferPointer = alGenBuffers();
        WaveData waveData = WaveData.create(soundResource);
        alBufferData(bufferPointer, waveData.format, waveData.data, waveData.samplerate);
        waveData.dispose();
    }

    public void play(float volume, int effectType){
        alDeleteSources(sourcePointer);
        alDeleteEffects(effect);
        alDeleteAuxiliaryEffectSlots(effectSlot);

        sourcePointer = alGenSources();

        effect = alGenEffects();
        effectSlot = alGenAuxiliaryEffectSlots();

        alEffecti(effect, AL_EFFECT_TYPE, effectType);

        alAuxiliaryEffectSloti(effectSlot, AL_EFFECTSLOT_EFFECT, effect);

        alSourcei(sourcePointer, AL_BUFFER, bufferPointer);
        alSource3i(sourcePointer, AL_AUXILIARY_SEND_FILTER, effectSlot, 0, AL_FILTER_NULL);
        alSourcef(sourcePointer, AL_GAIN, volume);

        alSourcePlay(sourcePointer);

    }

    public void destroy() {
        alDeleteBuffers(bufferPointer);
        alcDestroyContext(context);
        alcCloseDevice(device);
    }
}



