package com.example.chat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

   // FileChooser fileChooser = new FileChooser();

    //@Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
//    }

    @FXML
    private TextArea textArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextFlow flow;

    @FXML
    private AnchorPane flowAnchor;

    @FXML
    void chooseFile(MouseEvent event) {
        //File file = fileChooser.showOpenDialog(new Stage());
        File file = OpenFile();

        if (file != null) {
            FileRead readFile = new FileRead();
        }
    }

    public File OpenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File file = fileChooser.showOpenDialog(new Stage());
        return file;


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

//    @FXML
//    protected void onChooseFileClick() {
//        scrollPane.setFitToWidth(true);
//        scrollPane.setVisible(true);
//    }
}