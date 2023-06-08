
package com.example.chat;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The FileRead class reads a chat file, parses its contents, and displays the chat messages.
 */
public class FileRead {

    /**
     * Scans the given file and extracts chat messages from it.
     *
     * @param file The file to be scanned.
     * @return A list of chat messages extracted from the file.
     */
    public List<ChatMain.Message> FileScan(File file) {
        //creates a new ArrayList called readMessages. Read- like past tense of read, should have picked a better name...
        List<ChatMain.Message> readMessages = new ArrayList<>();

        if (!file.getName().endsWith(".msg")) {//check for wrong file type
            FileFormatError("Wrong file type");
            return readMessages;
        }
        try {
            Scanner fileScanner = new Scanner(file);//make new scanner
            ChatMain.Message msg = new ChatMain.Message(); //make new message object
            if (!fileScanner.hasNextLine()) {// check if file is empty
                fileScanner.close();
                FileFormatError("Empty file");
                return readMessages;
            }
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();//trim removes potential white spaces. not needed for these files but an extra precaution
                parseMessage(msg, line);//call parse method
                if(line.startsWith("Message")) {
                    if (isEmpty(msg.TimeStamp)){
                        fileScanner.close();
                        FileFormatError("Empty time field in message");
                        return readMessages;
                    }
                    if (isEmpty(msg.Name)){
                        fileScanner.close();
                        FileFormatError("Empty name field in message");
                        return readMessages;
                    }
                    //not keeping because I want to keep the option of an empty message
//                    if (isEmpty(msg.Content)){
//                        fileScanner.close();
//                        FileFormatError("Empty content field in message");
//                        return readMessages;
//                    }
                    readMessages.add(msg);
                    msg = new ChatMain.Message();
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            FileFormatError("Unknown file format");
            e.printStackTrace();
        }
        return readMessages;
    }

    /**
     * Checks if a string is empty (null or contains only whitespace).
     *
     * @param value The string to be checked.
     * @return True if the string is empty, false otherwise.
     */
    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    /**
     * Displays an error alert with a given error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    private void FileFormatError(String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR, errorMessage, ButtonType.OK);
        alert.setHeaderText("File Format Error");
        alert.setTitle("Error");
        alert.showAndWait();
    }

    /**
     * Parses a line of text and extracts the message components (time, name, and content).
     *
     * @param msg  The message object to store the extracted components.
     * @param line The line of text to be parsed.
     */
    public static void parseMessage(ChatMain.Message msg, String line) { //called while scanning the file
        try {
            if (line.startsWith("Time:")) {
                msg.TimeStamp = line.substring("Time:".length()).trim();
            } else if (line.startsWith("Name:")) {
                msg.Name = line.substring("Name:".length()).trim();
            } else if (line.startsWith("Message")) { //removed colon to allow for formating errors, could replicate for Name and Time
                int colonIndex = line.indexOf(':'); // index if found
                if (colonIndex != -1) { //check if there is a colon
                    line = line.substring(colonIndex + 1).trim(); // Extract the substring after the colon
                }
                msg.Content = line;
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Displays the messages in a TextFlow container.
     *
     * @param readMessages The list of messages to display.
     * @param textFlow     The TextFlow container to display the messages in.
     */
    public static void displayMessages(List<ChatMain.Message> readMessages, TextFlow textFlow) {
        textFlow.getChildren().clear();//clear existing content
        ChatMain.Message lastMsg = null;//keep track of previous message

        for (ChatMain.Message msg : readMessages) {

            textFlow.setPadding(new Insets(10)); // Set the padding around the text
            Text timeStampText = new Text("[Time: " + msg.TimeStamp + "]");

            Text nameText;
            if (lastMsg != null && msg.Name.equals(lastMsg.Name)) { // ... if the message has the same name as the previous one
                nameText = new Text("...");
            } else { // or display name
                nameText = new Text(msg.Name + ":");
                nameText.setStyle("-fx-fill: blue;");
            }

            String content = msg.Content; // Get the content of the message

            boolean sadEmojiFound = Emojis.containsSadEmoji(content);
            boolean happyEmojiFound = Emojis.containsHappyEmoji(content);

            // Create a new HBox to hold the text and image
            HBox messageBox = new HBox();
            messageBox.getChildren().addAll(timeStampText, nameText); // Add timestamp and name text to the HBox

            if (sadEmojiFound) {
                List<Integer> sadIndexes = Emojis.getSadIndexes(); // Get the indexes of sad emojis in the content if tru
                int startIndex = 0;//used for extracting text before and after emoji
                for (Integer index : sadIndexes) {
                    Text contentText = new Text(content.substring(startIndex, index)); // Extract the text before the sad emoji
                    contentText.setStyle("-fx-font-weight: bold;");
                    //contentText.setWrappingWidth(textFlow.getWidth()); // Set the wrapping width to the width of the TextFlow
                    messageBox.getChildren().add(contentText);

                    ImageView sadEmojiImageView = new ImageView(Emojis.SadEmoji); // Create an ImageView for the sad emoji
                    sadEmojiImageView.setFitHeight(20);
                    sadEmojiImageView.setFitWidth(20);
                    messageBox.getChildren().add(sadEmojiImageView); // Add the sad emoji image to the HBox

                    startIndex = index + 2; // Move the start index after the :( to skip
                }
                Text remainingText = new Text(content.substring(startIndex)); // Set the remaining text after the emoji
                remainingText.setStyle("-fx-font-weight: bold;");
                messageBox.getChildren().add(remainingText);
            } else if (happyEmojiFound) {
                List<Integer> happyIndexes = Emojis.getHappyIndexes();
                int startIndex = 0;
                for (Integer index : happyIndexes) {
                    Text contentText = new Text(content.substring(startIndex, index));
                    contentText.setStyle("-fx-font-weight: bold;");
                    //contentText.setWrappingWidth(textFlow.getWidth()); // Set the wrapping width to the width of the TextFlow
                    messageBox.getChildren().add(contentText);

                    ImageView happyEmojiImageView = new ImageView(Emojis.HappyEmoji);
                    happyEmojiImageView.setFitHeight(20);
                    happyEmojiImageView.setFitWidth(20);
                    messageBox.getChildren().add(happyEmojiImageView);

                    startIndex = index + 2; // Move the start index after the ":("
                }
                Text remainingText = new Text(content.substring(startIndex)); // Set the remaining text after the last emoji
                remainingText.setStyle("-fx-font-weight: bold;");
                messageBox.getChildren().add(remainingText);
            }
            else {//no emoji found
                Text contentText = new Text(content); // Set the content as is if no sad emoji is found
                contentText.setStyle("-fx-font-weight: bold;");
                contentText.setWrappingWidth(textFlow.getWidth()-150); // Set the wrapping width to the width of the TextFlow
                messageBox.getChildren().add(contentText);
            }

            textFlow.getChildren().add(messageBox); //add message box to textFlow
            lastMsg = msg; //updated for next time

            Text newLineText = new Text("\n");
            textFlow.getChildren().add(newLineText);
        }
    }

}



