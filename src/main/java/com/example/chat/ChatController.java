package com.example.chat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

/**
 * The controller class for the Chat application.
 */
public class ChatController implements Initializable {

    private static final String LastOpenedFilePath = "lastOpenedFilePath"; // For storing the last opened file path in preferences


    @FXML
    private TextField showOpenPath;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextFlow flow;

    private Preferences preferences; // Preferences instance for storing application settings

    /**
     * Event handler for the "Choose File" button click.
     * Opens a file chooser dialog and loads the selected file.
     *
     * @param event The mouse event.
     */

    @FXML
    void chooseFile(MouseEvent event) {
        File file = OpenFile();

        if (file != null) {
            showOpenPath.setText(file.getAbsolutePath());

            FileRead readFile = new FileRead();
            List<ChatMain.Message> readMessages = readFile.FileScan(file);
            FileRead.displayMessages(readMessages, flow);

            // Allows scrollPane to scroll to the bottom
            Platform.runLater(() -> scrollPane.setVvalue(1.0));

            // Saves the last opened file path
            preferences.put(LastOpenedFilePath, file.getAbsolutePath());

        }
    }

    /**
     * Opens a file chooser dialog and returns the selected file.
     *
     * @return The selected file, or null if no file is selected.
     */
    public File OpenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))); //Starts at users home directory

        // Load the last opened file path
        String lastOpenedFilePath = preferences.get(LastOpenedFilePath, "");
        if (!lastOpenedFilePath.isEmpty()) {
            File lastOpenedFile = new File(lastOpenedFilePath);
            if (lastOpenedFile.exists()) {
                fileChooser.setInitialDirectory(lastOpenedFile.getParentFile());
            }
        }

        File file = fileChooser.showOpenDialog(new Stage());//open fileChooser in a new window
        return file;
    }

    /**
     * Initializes the controller.
     * Called after the FXML file has been loaded.
     *
     * @param url            The location used to resolve relative paths.
     * @param resourceBundle The resources used for localization.
     */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the Preferences instance
        preferences = Preferences.userRoot().node(getClass().getName());
    }
}

