package com.example.chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatMain.class.getResource("chat-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("Chat");
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    public static class Message {

        public static String Name;
        public String LastMessageName;
        public String TimeStamp;
        public String Content;
    }


    public static void main(String[] args) {
        launch();
    }
}