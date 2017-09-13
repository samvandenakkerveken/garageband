package com.axxes.garageband.presenter;

import com.axxes.garageband.model.instrument.*;
import com.axxes.garageband.model.loop.Drumloop;
import com.axxes.garageband.model.measures.Beat;
import com.axxes.garageband.model.measures.Measure;
import com.axxes.garageband.util.MusicXmlParser;
import com.axxes.garageband.util.MusicXmlWriter;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
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
    @FXML
    Pane root;

    private Timeline loopTimeline;
    @Autowired
    private Drumloop drumloop;
    private IntegerProperty bpm;

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

    private Rectangle highLighter;
    private int highlighterPosition;

    private int beats;

    private void createLoop() {
        if (this.loopTimeline != null) {
            this.loopTimeline.stop();
        }
        bpm.set(Integer.parseInt(bpmTextField.getText()));
        int timeBetweenBeats = 60000 / this.bpm.get();

        this.loopTimeline = new Timeline(new KeyFrame(
                Duration.millis(timeBetweenBeats),
                ae -> {
                    Logger.getLogger(Presenter.class).info("Drumloop step.");
                    this.drumloop.step();
                    stepHighlighter();
                    // UI handling
                }));
        this.loopTimeline.setCycleCount(Animation.INDEFINITE);
    }

    @FXML
    protected void initialize() {
        this.beats = drumloop.getMeasures().stream().map(Measure::getBeats).mapToInt(Collection::size).sum();
        createBaseGrid();
        this.bpm = new SimpleIntegerProperty();
        this.bpm.bindBidirectional(drumloop.getBpm());
        this.bpm.set(180);
        this.bpmTextField.setText(String.valueOf(bpm.get()));
        createHighlighter();
        createLoop();
    }

    private void createHighlighter() {
        highLighter = new Rectangle(85, 65, 60, 30);
        highlighterPosition = 0;
        highLighter.setMouseTransparent(true);
        highLighter.setFill(Color.RED);
        highLighter.setOpacity(0.5);
        root.getChildren().add(highLighter);
    }

    private void stepHighlighter(){
        if (highlighterPosition == beats){
            highlighterPosition = 0;
        }
        highLighter.setX(85 + (60*highlighterPosition));
        highlighterPosition++;

    }

    @FXML
    private Label createLabel(String text) {
        Label label = new Label(text);
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(60);
        label.setPrefHeight(30);
        return label;
    }

    @FXML
    private void createBaseGrid() {
        grid.add(createLabel("Beat"), 0, 0);
        for (int i = 1; i <= beats; i++) {
            int currentBeat = ((i - 1) % 4) + 1;
            grid.add(createLabel(String.valueOf(currentBeat)),i, 0);
        }

        grid.setBorder(Border.EMPTY);
    }

    private void disableAddInstrumentButton(Instrument instrument) {

        if (instrument.getClass().equals(HiHat.class)) {
            imageHihat.setDisable(true);
        } else if (instrument.getClass().equals(Snare.class)) {
            imageSnare.setDisable(true);
        } else if (instrument.getClass().equals(Kick.class)) {
            imageKick.setDisable(true);
        } else if (instrument.getClass().equals(Cymbal.class)) {
            imageCymbal.setDisable(true);
        }
    }

    private void enableAddInstrumentButton() {
        imageHihat.setDisable(false);
        imageSnare.setDisable(false);
        imageKick.setDisable(false);
        imageCymbal.setDisable(false);
    }

    private void addInstrumentLine(Instrument instrument) {
        highLighter.setHeight(highLighter.getHeight()+50);
        disableAddInstrumentButton(instrument);
        Image image = new Image(instrument.getImage());
        grid.addRow(gridRow, createLabel(instrument.getClass().getSimpleName()));

        for (int i = 0; i < beats; i++) {
            Button button = new Button();
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            imageView.setFitWidth(40);
            imageView.setFitHeight(40);
            button.setGraphic(imageView);
            int measureCount = i / 4;
            int beatCount = i % 4;
            button.setOnAction(event -> instrumentToggle(instrument, measureCount, beatCount));
            bindBeatToButton(instrument, button, measureCount, beatCount);

            grid.addRow(gridRow, button);
        }
        gridRow++;
    }

    private void bindBeatToButton(Instrument instrument, Button button, int measureCount, int beatCount) {
        Beat beat = drumloop.getMeasures().get(measureCount).getBeats().get(beatCount);
        BooleanBinding hasInstrument = Bindings.createBooleanBinding(() -> beat.getInstruments().contains(instrument), beat.getInstruments());
        button.styleProperty().bind(Bindings.when(hasInstrument).then("-fx-background-color: darkgray").otherwise(""));
    }


    private void instrumentToggle(Instrument instrument, int measureCount, int beatCount) {
        if (drumloop.hasInstrument(instrument, measureCount, beatCount)) {
            drumloop.removeInstrument(instrument, measureCount, beatCount);
        } else {
            drumloop.addInstrument(instrument, measureCount, beatCount);
        }
    }

    public void menuButtonSave() {
        final Stage dialog = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("drumloops"));
        fileChooser.setInitialFileName("drumloop.xml");
        fileChooser.setTitle("Save music file");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("xml", "*.xml");
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setSelectedExtensionFilter(filter);
        File file = fileChooser.showSaveDialog(dialog);
        if (file != null) {
            MusicXmlWriter.writeXMLFromDrumloop(drumloop, file);
        }
    }


    public void menuButtonLoad() {
        final Stage dialog = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("drumloops"));
        fileChooser.setTitle("Open music file");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("xml", "*.xml");
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setSelectedExtensionFilter(filter);

        File file = fileChooser.showOpenDialog(dialog);

        highlighterPosition = 0;
        highLighter.setX(85);
        highLighter.setHeight(30);
        deleteInstrumentLines();
        if (file != null) {
            parser.parserDrumloopFromXml(file);
        }
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

    public void createInstrumentLines(Set<Instrument> instrumentSet) {
        enableAddInstrumentButton();
        for (Instrument i : instrumentSet) {
            this.addInstrumentLine(i);
        }
    }

    //UI event handlers

    public void exit() {
        Platform.exit();
    }

    public void imageKickPressed() {
        addInstrumentLine(kick);
    }

    public void imageSnarePressed() {
        addInstrumentLine(snare);
    }

    public void imageHihatPressed() {
        addInstrumentLine(hiHat);
    }

    public void imageCymbalPressed() {
        addInstrumentLine(cymbal);
    }

    public void playLoop() {
        createLoop();
        this.loopTimeline.play();
    }

    public void stopLoop() {
        this.loopTimeline.stop();
    }
}
