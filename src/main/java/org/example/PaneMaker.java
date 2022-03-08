package org.example;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class PaneMaker {
    // Idea: make methods that construct our panes so app doesn't need to do it

    public TabPane makeAppRoot() {
        // Makes the main application window (the root pane) that holds all other nodes
        TabPane root = new TabPane();
        return root;
    }

    public Tab makeTab(String tabText) {
        // Makes a tab that will be added to the top of the main application window
        Tab myTab = new Tab(tabText);
        return myTab;
    }

    private AudioClip getClipFromPath(String filePath) {
        return new AudioClip(Paths.get(filePath).toUri().toString());
    }

    public TilePane makeButtonContainer(String folderPath) {
        // Makes a container and populates it with buttons linked to audio files from a folder
        TilePane buttonContainer = new TilePane();
        buttonContainer.setVgap(15.0);

        File dir = new File(folderPath);
        File[] directoryFiles = dir.listFiles();
        if (directoryFiles != null) {
            String[] nameComponents;
            for (File audioFile: directoryFiles) {
                nameComponents = audioFile.getName().split(Pattern.quote("."));
                Button audioButton = new Button(nameComponents[0]);
                audioButton.setOnAction(actionEvent -> {
                    AudioClip sound = getClipFromPath(audioFile.getPath());
                    sound.play();
                });
                buttonContainer.getChildren().add(audioButton);
            }
        }
        return buttonContainer;
    }

    public VBox makeBasicWindow() throws IOException {
        // Makes a vertical container that comes with a title and image
        // Useful because text/images are nodes ->can't be added to two windows, so make copies
        VBox myWindow = new VBox();
        myWindow.setAlignment(Pos.CENTER);
        myWindow.setSpacing(15.0);

        Text title = new Text("The Fillmore Soundboard");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 36));

        Image displayImage = new Image(new FileInputStream(
                "src/main/resources/assets/images/FillmoreSlap.jpg"));
        ImageView imageDisplayer = new ImageView(displayImage);
        imageDisplayer.setFitHeight(300);
        imageDisplayer.setFitWidth(400);

        myWindow.getChildren().addAll(title, imageDisplayer);
        return myWindow;
    }

    public TabPane makeApplication() throws IOException {
        TabPane root = makeAppRoot();
        Tab shortAudioTab = makeTab("Short Audio");
        Tab extendedAudioTab = makeTab("Extended Audio");
        root.getTabs().addAll(shortAudioTab, extendedAudioTab);

        VBox shortAudioWindow = makeBasicWindow();
        TilePane shortAudioContainer = makeButtonContainer(
                "src/main/resources/assets/audio/shortaudio");
        shortAudioWindow.getChildren().add(shortAudioContainer);
        shortAudioTab.setContent(shortAudioWindow);

        VBox extendedAudioWindow = makeBasicWindow();
        TilePane extendedAudioContainer = makeButtonContainer(
                "src/main/resources/assets/audio/extendedaudio"
        );
        extendedAudioWindow.getChildren().add(extendedAudioContainer);
        extendedAudioTab.setContent(extendedAudioWindow);

        return root;
    }
}
