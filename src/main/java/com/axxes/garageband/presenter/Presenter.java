package com.axxes.garageband.presenter;

import com.axxes.garageband.model.loop.Drumloop;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;

public class Presenter {

    @Autowired
    private Drumloop drumloop;

    public void startLoop(int bpm) {
        int timeBetweenBeats = 60000 / bpm;

        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(timeBetweenBeats),
                ae -> {
                    this.drumloop.step();
                    // UI handling
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}
