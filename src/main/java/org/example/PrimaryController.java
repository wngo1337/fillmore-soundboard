package org.example;

import java.io.IOException;
import java.nio.file.Paths;
import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;

public class PrimaryController {

    private AudioClip smallClip;
    private File dir = new File("src/main/resources/assets/audio");
    private File[] audioFiles = dir.listFiles();

    private AudioClip getClipFromPath(String filePath) {
        return new AudioClip(Paths.get(filePath).toUri().toString());
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void activateAmazing(ActionEvent click) throws IOException {
        smallClip = getClipFromPath("src/main/resources/assets/audio/Amazing.wav");
        smallClip.play();
    }
}
