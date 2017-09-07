package com.axxes.garageband.presenter;

import com.axxes.garageband.model.loop.Drumloop;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Timer;
import java.util.TimerTask;

@Component
public class Presenter {

    private Drumloop drumloop = new Drumloop();

    public void startLoop(int bpm) {
        int timeBetweenBeats = 60000 / bpm;

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(timeBetweenBeats),
                ae -> {
                    Logger.getLogger(Presenter.class).info("Drumloop step.");
                    this.drumloop.step();
                    // UI handling
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void startLoop(int bpm, Drumloop drumloop) {
        int timeBetweenBeats = 60000 / bpm;

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(timeBetweenBeats),
                ae -> {
                    Logger.getLogger(Presenter.class).info("Drumloop step.");
                    drumloop.step();
                    // UI handling
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();


        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                drumloop.step();
            }
        }, 0, timeBetweenBeats);*/
    }

}
