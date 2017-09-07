package com.axxes.garageband.presenter;

import com.axxes.garageband.model.loop.Drumloop;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Presenter {

    @FXML
    private GridPane grid;

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
    }

    @FXML
    protected void initialize(){
        createBaseGrid();
    }

    @FXML
    private Label createLabel(String text){
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(55);
        label.setPrefHeight(30);
        label.setStyle("-fx-border-style: solid");
        label.setStyle("-fx-border-width: 2px");
        label.setStyle("-fx-border-color: gray");

        return label;
    }

    @FXML
    private void createBaseGrid(){
        grid.add(createLabel("Beat"), 0, 0);
        grid.add(createLabel("1"), 1, 0);
        grid.add(createLabel("2"), 2, 0);
        grid.add(createLabel("3"), 3, 0);
        grid.add(createLabel("4"), 4, 0);
        grid.add(createLabel("1"), 5, 0);
        grid.add(createLabel("2"), 6, 0);
        grid.add(createLabel("3"), 7, 0);
        grid.add(createLabel("4"), 8, 0);

        grid.setBorder(Border.EMPTY);
    }

    //UI event handlers
    public void saveFile(ActionEvent actionEvent) {
        //TODO: save to xml logic
    }

    public void loadFile(ActionEvent actionEvent) {
        //TODO: load from xml logic
    }

    public void exit() {
        Platform.exit();
    }
}
