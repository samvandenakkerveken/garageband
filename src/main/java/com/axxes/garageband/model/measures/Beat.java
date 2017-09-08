package com.axxes.garageband.model.measures;

import com.axxes.garageband.model.instrument.Instrument;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Beat {

    private ObservableList<Instrument> instruments;

    public Beat() {
        this.instruments = FXCollections.observableArrayList();
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

    public ObservableList<Instrument> getInstruments() {
        return instruments;
    }

}
