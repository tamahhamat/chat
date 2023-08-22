package com.example.chat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import static com.example.chat.FileRead.displayMessages;

public class ChatController implements Initializable {

    private static final String LAST_OPENED_FILE_PREF_KEY = "lastOpenedFilePath";


    @FXML
    private TextArea textArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextFlow flow;

    @FXML
    private AnchorPane flowAnchor;

    private Preferences preferences;

    @FXML
    void chooseFile(MouseEvent event) {
        File file = OpenFile();

        if (file != null) {
            FileRead readFile = new FileRead();
            List<ChatMain.Message> readMessages = readFile.FileScan(file);
            FileRead.displayMessages(readMessages, flow);

            // Scroll to the bottom
            Platform.runLater(() -> scrollPane.setVvalue(1.0));

            // Save the last opened file path
            preferences.put(LAST_OPENED_FILE_PREF_KEY, file.getAbsolutePath());

        }
    }

    public File OpenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Load the last opened file path
        String lastOpenedFilePath = preferences.get(LAST_OPENED_FILE_PREF_KEY, "");
        if (!lastOpenedFilePath.isEmpty()) {
            File lastOpenedFile = new File(lastOpenedFilePath);
            if (lastOpenedFile.exists()) {
                fileChooser.setInitialDirectory(lastOpenedFile.getParentFile());
            }
        }

        File file = fileChooser.showOpenDialog(new Stage());
        return file;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the Preferences instance
        preferences = Preferences.userRoot().node(getClass().getName());
    }
}

//    @FXML
//    protected void onChooseFileClick() {
//        scrollPane.setFitToWidth(true);
//        scrollPane.setVisible(true);
//    }




// combine to one function?
//    public File OpenFile() {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//
//        File file = fileChooser.showOpenDialog(new Stage());
//        return file;
//
//        if (file != null) {
//            FileRead readFile = new FileRead();
//            List<ChatMain.Message> readMessages = readFile.FileScan(file);
//            FileRead.displayMessages(readMessages, flow);
//
////            FileRead readFile = new FileRead();
////            FileRead.parseMessage (ChatMain.Message msg, String line);
////            displayMessages(List<ChatMain.Message> readMessages, TextFlow textFlow);
//
//            //display chat
//
//
//        }
//    }
