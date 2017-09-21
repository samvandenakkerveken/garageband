package com.axxes.garageband.model.measures;

import com.axxes.garageband.Audio.effects.Effect;
import com.axxes.garageband.model.instrument.Instrument;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


public class Beat {

    private ObservableList<Instrument> instruments;
    private Map<Instrument, Effect> instrumentEffectsMap;

    public Beat() {
        this.instruments = FXCollections.observableArrayList();
        this.instrumentEffectsMap = new HashMap<>();

    }

    public void addInstrument(Instrument instrument, Effect effect) {
        this.instruments.add(instrument);
        this.instrumentEffectsMap.put(instrument, effect);
    }

    public void removeInstrument(Instrument instrument) {
        this.instruments.remove(instrument);
        this.instrumentEffectsMap.remove(instrument);
    }

    public void playBeat() {
        Logger.getLogger(Beat.class).info("Playing instruments.");
        for (Instrument i : instruments) {
            Effect effect = this.instrumentEffectsMap.get(i);
            i.play(effect);
        }
    }

    public ObservableList<Instrument> getInstruments() {
        return instruments;
    }

    public void setAudioEffect(Instrument instrument, Effect effect){
        this.instrumentEffectsMap.put(instrument, effect);
    }
}
