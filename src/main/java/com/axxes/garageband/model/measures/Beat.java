package com.axxes.garageband.model.measures;

import com.axxes.garageband.model.instrument.Instrument;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.openal.EXTEfx.AL_EFFECT_NULL;

@Component
public class Beat {


    private ObservableList<Instrument> instruments;
    private Map<Instrument, Integer> instrumentEffectsMap;

    public Beat() {
        this.instruments = FXCollections.observableArrayList();
        this.instrumentEffectsMap = new HashMap<>();
    }

    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
        this.instrumentEffectsMap.put(instrument, AL_EFFECT_NULL);
    }

    public void removeInstrument(Instrument instrument) {
        this.instruments.remove(instrument);
    }

    public void playBeat() {
        Logger.getLogger(Beat.class).info("Playing instruments.");
        for (Instrument i : instruments) {
            int audioEffect = this.instrumentEffectsMap.get(i);
            i.play(audioEffect);
        }
    }

    public ObservableList<Instrument> getInstruments() {
        return instruments;
    }

    public void setAudioEffect(Instrument instrument, int audioEffect){
        this.instrumentEffectsMap.put(instrument, audioEffect);
    }
}
