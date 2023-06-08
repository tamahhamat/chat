package com.example.chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class of the Chat application.
 */
public class ChatMain extends Application {

    /**
     * The entry point for the JavaFX application.
     *
     * @param stage The primary stage for this application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {

        // Load the FXML file for the chat view
        FXMLLoader fxmlLoader = new FXMLLoader(ChatMain.class.getResource("chat-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Chat");

        //generate emoji images
        Emojis.MakeEmoji(this.getClass());

        // Set the scene and display the stage
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
    }

    /**
     * Represents a chat message.
     */
    public static class Message {

        public  String Name;
        public String TimeStamp;
        public String Content;
    }

    /**
     * The main method of the Chat application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}