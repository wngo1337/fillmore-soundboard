package org.example;

import java.io.File;
import java.nio.file.Paths;
import java.io.FileInputStream;
import java.util.regex.Pattern;

import javafx.application.Application;
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
        primaryStage.setTitle("The Fillmore Soundboard");

        // Setting up root node which is the outer window and tabs
        TabPane root = new TabPane();
        Tab shortAudioTab = new Tab("Short Audio");
        Tab extendedAudioTab = new Tab("Extended Audio");
        root.getTabs().addAll(shortAudioTab, extendedAudioTab);

        // Border pane is the main window: will display title top, buttons center, image...
        VBox shortAudioWindow = new VBox();
        shortAudioWindow.setSpacing(15.0);
        TilePane shortAudioCenterPane = new TilePane();
        shortAudioCenterPane.setVgap(15.0);
        VBox titleHolder = new VBox();
        titleHolder.setAlignment(Pos.CENTER);
        Text title = new Text("The Fillmore Soundboard");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 36));
        titleHolder.getChildren().add(title);
        shortAudioTab.setContent(shortAudioWindow);
        shortAudioWindow.getChildren().addAll(titleHolder, shortAudioCenterPane);

        // add background image to pane
        Image displayImage = new Image(new FileInputStream(
                "src/main/resources/assets/images/FillmoreSlap.jpg"));
        ImageView myViewer = new ImageView(displayImage);
        myViewer.setFitHeight(300);
        myViewer.setFitWidth(400);
        // Want to put image under text, but it isn't working yet
        titleHolder.getChildren().add(myViewer);

        // Make a button for each wav file and populate the pane with it
        File dir = new File("src/main/resources/assets/audio");
        File[] directoryFiles = dir.listFiles();
        if (directoryFiles != null) {
            int i = 0;
            String[] nameComponents;
            for (File audioFile: directoryFiles) {
                nameComponents = audioFile.getName().split(Pattern.quote("."));
                Button audioButton = new Button(nameComponents[0]);
                audioButton.setOnAction(actionEvent -> {
                    AudioClip sound = getClipFromPath(audioFile.getPath());
                    sound.play();
                });
                shortAudioCenterPane.getChildren().add(audioButton);
            }
        }

        Scene myScene = new Scene(root, 1200, 750);
        primaryStage.setScene(myScene);

        primaryStage.show();
    }

}