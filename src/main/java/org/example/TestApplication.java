package org.example;

import java.io.File;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.media.AudioClip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import javafx.scene.layout.TilePane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Tab;

public class TestApplication extends Application {

    private AudioClip getClipFromPath(String filePath) {
        return new AudioClip(Paths.get(filePath).toUri().toString());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        PaneMaker appMaker = new PaneMaker();
        primaryStage.setTitle("The Fillmore Soundboard");
        TabPane root = appMaker.makeApplication();

        Scene myScene = new Scene(root, 1200, 750);
        primaryStage.setScene(myScene);
        primaryStage.show();
    }

}