package com.axxes.garageband.view;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.springframework.stereotype.Component;


@Component
public class GarbageBandUI extends Group{

    private Canvas canvas;

    public GarbageBandUI(){
        inititaliseNodes();
        layoutNodes();
    }

    private void inititaliseNodes(){

        canvas = new Canvas(1600, 900);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.GRAY);
        gc.fillRect(0,0,canvas.getWidth(), canvas.getHeight());
    }

    private void layoutNodes(){
        getChildren().add(canvas);
    }
}
