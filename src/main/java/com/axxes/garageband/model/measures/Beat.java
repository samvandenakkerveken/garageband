package com.axxes.garageband.model.measures;

import com.axxes.garageband.model.instrument.Instrument;

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
        //AccesLogger.getInstance().info("Beat: Playing instrument");
        for (Instrument i : instruments) {
            i.play();
        }
    }

}
