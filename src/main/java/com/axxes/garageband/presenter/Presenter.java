package com.axxes.garageband.presenter;

import com.axxes.garageband.model.instrument.*;
import com.axxes.garageband.model.loop.Drumloop;
import com.axxes.garageband.model.measures.Beat;
import com.axxes.garageband.util.MusicXmlParser;
import com.axxes.garageband.util.MusicXmlWriter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    @FXML
    TextField bpmTextField;

    private Timeline loopTimeline;
    @Autowired
    private Drumloop drumloop;
    private int bpm;

    @Autowired
    MusicXmlParser parser;

    @Autowired
    private Kick kick;
    @Autowired
    private Cymbal cymbal;
    @Autowired
    private HiHat hiHat;
    @Autowired
    private Snare snare;

    private void createLoop() {
        if (this.loopTimeline != null) {
            this.loopTimeline.stop();
        }
        bpm = Integer.parseInt(bpmTextField.getText());
        int timeBetweenBeats = 60000/this.bpm;

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
        this.bpm = 180;
        bpmTextField.setText(String.valueOf(bpm));
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

    private void addInstrumentLine(Instrument instrument){
        Image image = new Image(instrument.getImage());
        grid.addRow(gridRow, createLabel(instrument.getClass().getSimpleName()));

        for (int i = 0; i < 8; i++){
            Button button = new Button();
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            button.setGraphic(imageView);
            int measureCount =  i / 4;
            int beatCount = i % 4;
            Beat beat = drumloop.getMeasures().get(measureCount).getBeats().get(beatCount);
            button.setOnAction(event -> instrumentToggle(instrument, measureCount, beatCount));
            BooleanBinding hasInstrument = Bindings.createBooleanBinding(() -> beat.getInstruments().contains(instrument), beat.getInstruments());
            button.styleProperty().bind(Bindings.when(hasInstrument).then("-fx-background-color: darkgray").otherwise(""));
            grid.addRow(gridRow, button);
        }
        gridRow++;
    }

    //UI event handlers

    private void instrumentToggle(Instrument instrument, int measureCount, int beatCount){
        if (drumloop.hasInstrument(instrument, measureCount, beatCount)){
            drumloop.removeInstrument(instrument, measureCount, beatCount);
        }
        else {
            drumloop.addInstrument(instrument, measureCount, beatCount);
        }
    }
    public void menuButtonSave() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.addRow(0, new Label("Filename"));
        TextField textField = new TextField("drumloop");
        gridPane.addRow(1,textField);
        Button saveButton = new Button("Save");
        saveButton.setOnAction(event -> saveFile(textField.getText(), dialog));
        gridPane.addRow(2, saveButton);
        Scene dialogScene = new Scene(gridPane, 200, 150);
        dialog.setScene(dialogScene);
        dialog.show();

    }

    private void saveFile(String filename, Stage dialog){
        if (MusicXmlWriter.writeXMLFromDrumloop(drumloop, filename)){
            dialog.close();
        }
    }

    public void menuButtonLoad() {
        final Stage dialog = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("drumloops"));
        fileChooser.setTitle("Open music file");
        fileChooser.getExtensionFilters();

        File file = fileChooser.showOpenDialog(dialog);

        deleteInstrumentLines();
       parser.parserDrumloopFromXml(file);
    }

    private void deleteInstrumentLines() {
        List<Node> deleteNodes = new ArrayList<>();
        for (Node node : grid.getChildren()) {
            if (GridPane.getRowIndex(node) > 0) {
                deleteNodes.add(node);
            }
        }
        grid.getChildren().removeAll(deleteNodes);
    }

    public void exit() {
        Platform.exit();
    }

    public void imageKickPressed() {
        imageKick.setDisable(true);
        addInstrumentLine(kick);
    }

    public void imageSnarePressed() {
        imageSnare.setDisable(true);
        addInstrumentLine(snare);
    }

    public void imageHihatPressed() {
        imageHihat.setDisable(true);
        addInstrumentLine(hiHat);
    }

    public void imageCymbalPressed() {
        imageCymbal.setDisable(true);
        addInstrumentLine(cymbal);
    }

    public void playLoop(ActionEvent actionEvent) {
        createLoop();
        this.loopTimeline.play();
    }

    public void stopLoop(ActionEvent actionEvent) {
        this.loopTimeline.stop();
    }

    public void createInstrumentLines(Set<Instrument> instrumentSet) {
        for (Instrument i : instrumentSet) {
            this.addInstrumentLine(i);
        }
    }
}
