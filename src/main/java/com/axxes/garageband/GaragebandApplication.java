package com.axxes.garageband;

import com.axxes.garageband.model.instrument.Instrument;
import com.axxes.garageband.model.instrument.Snare;
import com.axxes.garageband.model.loop.Drumloop;
import com.axxes.garageband.model.measures.Beat;
import com.axxes.garageband.model.measures.Measure;
import com.axxes.garageband.presenter.Presenter;
import javafx.application.Application;
import javafx.stage.Stage;
import kuusisto.tinysound.TinySound;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GaragebandApplication extends Application {

    public static void main(String[] args) {
        TinySound.init();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(GaragebandApplication.class);

        Instrument snare = new Snare("snare.wav");
        Instrument kick = new Snare("kick.wav");
        Instrument hihat = new Snare("hihat.wav");
        Instrument cymbal = new Snare("cymbal.wav");

        Beat beat1 = new Beat();
        Beat beat2 = new Beat();
        Beat beat3 = new Beat();
        Beat beat4 = new Beat();
        Beat beat5 = new Beat();
        Beat beat6 = new Beat();
        Beat beat7 = new Beat();
        Beat beat8 = new Beat();

        beat1.addInstrument(hihat);
        beat2.addInstrument(hihat);
        beat3.addInstrument(hihat);
        beat4.addInstrument(hihat);
        beat5.addInstrument(hihat);
        beat6.addInstrument(hihat);
        beat7.addInstrument(hihat);
        beat8.addInstrument(hihat);
        beat1.addInstrument(kick);
        beat2.addInstrument(kick);
        beat4.addInstrument(snare);
        beat5.addInstrument(kick);
        beat6.addInstrument(kick);
        beat8.addInstrument(snare);

        Measure measure1 = new Measure();
        Measure measure2 = new Measure();

        measure1.addBeatOnLocation(beat1, 0);
        measure1.addBeatOnLocation(beat2, 1);
        measure1.addBeatOnLocation(beat3, 2);
        measure1.addBeatOnLocation(beat4, 3);

        measure2.addBeatOnLocation(beat5, 0);
        measure2.addBeatOnLocation(beat6, 1);
        measure2.addBeatOnLocation(beat7, 2);
        measure2.addBeatOnLocation(beat8, 3);

        Drumloop drumloop = new Drumloop();
        drumloop.setMeasures(new Measure[] {measure1, measure2});

        context.getBean(Presenter.class).startLoop(180, drumloop);
    }
}
