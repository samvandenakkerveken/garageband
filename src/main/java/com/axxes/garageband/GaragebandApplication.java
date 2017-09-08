package com.axxes.garageband;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kuusisto.tinysound.TinySound;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class GaragebandApplication extends Application {

    private ConfigurableApplicationContext context;

    public static void main(String[] args) {
        launch(GaragebandApplication.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        TinySound.init();

        context = SpringApplication.run(GaragebandApplication.class);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ui/garbageband.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);

        Parent root = fxmlLoader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
