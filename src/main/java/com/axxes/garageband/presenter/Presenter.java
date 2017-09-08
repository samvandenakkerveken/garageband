package com.axxes.garageband.presenter;

import com.axxes.garageband.model.instrument.*;
import com.axxes.garageband.model.loop.Drumloop;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class Presenter {

    @FXML
    private GridPane grid;
    private int gridRow = 1;

    @FXML
    AnchorPane imageKick;
    @FXML
    AnchorPane imageSnare;
    @FXML
    AnchorPane imageHihat;
    @FXML
    AnchorPane imageCymbal;

    private Timeline loopTimeline;
    @Autowired
    private Drumloop drumloop;
    private int bpm;

    @Autowired
    private Kick kick;
    @Autowired
    private Cymbal cymbal;
    @Autowired
    private HiHat hiHat;
    @Autowired
    private Snare snare;

    private void createLoop() {
        this.bpm = 60;
        int timeBetweenBeats = 60000 / this.bpm;

        this.loopTimeline = new Timeline(new KeyFrame(
                Duration.millis(timeBetweenBeats),
                ae -> {
                    Logger.getLogger(Presenter.class).info("Drumloop step.");
                    this.drumloop.step();
                    // UI handling
                }));
        this.loopTimeline.setCycleCount(Animation.INDEFINITE);
    }

    @FXML
    protected void initialize(){
        createBaseGrid();
        createLoop();
    }

    @FXML
    private Label createLabel(String text){
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(55);
        label.setPrefHeight(30);
//        label.setStyle("-fx-border-style: solid");
//        label.setStyle("-fx-border-width: 2px");
//        label.setStyle("-fx-border-color: gray");

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

    private void addInstrumentLine(String imagePath, Instrument instrument){
        Image image = new Image(imagePath);
        grid.addRow(gridRow, createLabel(instrument.getClass().getSimpleName()));

        for (int i = 0; i < 8; i++){
            Button button = new Button();
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            button.setGraphic(imageView);
            int finalI = i;
            button.setOnAction(event -> instrumentToggle(button, instrument, finalI));
            grid.addRow(gridRow, button);
        }
        gridRow++;
    }

    //UI event handlers

    private void instrumentToggle(Button button, Instrument instrument, int gridCol){
        int measureCount =  gridCol / 4;
        int beatCount = gridCol % 4;
        if (button.getStyle().contains("-fx-background-color: darkgray")){
            button.setStyle("");
            drumloop.removeInstrument(instrument, measureCount, beatCount);
        }
        else {
            button.setStyle("-fx-background-color: darkgray");
            drumloop.addInstrument(instrument, measureCount, beatCount);
        }
    }
    public void saveFile(ActionEvent actionEvent) {
        //TODO: save to xml logic
    }

    public void loadFile(ActionEvent actionEvent) {
        //TODO load from xml logic
    }

    public void exit() {
        Platform.exit();
    }

    public void imageKickPressed() {
        imageKick.setDisable(true);
        String image = "images/kick.png";
        addInstrumentLine(image, kick);
    }

    public void imageSnarePressed() {
        imageSnare.setDisable(true);
        String image = "images/snare.png";
        addInstrumentLine(image, snare);
    }

    public void imageHihatPressed() {
        imageHihat.setDisable(true);
        String image = "/images/hihat.png";
        addInstrumentLine(image, hiHat);
    }

    public void imageCymbalPressed() {
        imageCymbal.setDisable(true);
        String image = "/images/cymbal.png";
        addInstrumentLine(image, cymbal);
    }

    public void playLoop(ActionEvent actionEvent) {
        this.loopTimeline.play();
    }

    public void stopLoop(ActionEvent actionEvent) {
        this.loopTimeline.stop();
    }
}
