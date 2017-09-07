package com.axxes.garageband.model.measures;

import com.axxes.garageband.model.instrument.Instrument;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Beat {

    private List<Instrument> instruments;

    public Beat() {
        this.instruments = new ArrayList<>();
    }

    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);
    }

    public void removeInstrument(Instrument instrument) {
        this.instruments.remove(instrument);
    }

    public void playBeat() {
        Logger.getLogger(Beat.class).info("Playing instruments.");
        for (Instrument i : instruments) {
            i.play();
        }
    }

    public List<Instrument> getInstruments() {
        return instruments;
    }
}
