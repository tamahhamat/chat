package com.example.chat;


import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileRead {
    //where called? in controller?
    public List<ChatMain.Message> FileScan(File file) {
        List<ChatMain.Message> readMessages = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(file);
            ChatMain.Message msg = new ChatMain.Message();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if(line.startsWith("Message:")) {
                    readMessages.add(msg);
                    msg = new ChatMain.Message();
                }
                    parseMessage(msg, line);
//                //check for previous author
//                if (!readMessages.isEmpty()){
//                    msg.LastMessageName = readMessages.get(readMessages.size() -1).Name;
//                }
//            }
//            else {
                //parseMessage(msg, line);
//
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return readMessages;
    }


    public static void parseMessage(ChatMain.Message msg, String line) {
        try {
            if (line.startsWith("Time:")) {
                msg.TimeStamp = line.substring(5);
            } else if (line.startsWith("Name:")) {
                msg.Name = line.substring(5);
            } else if (line.startsWith("Message:")) {
                msg.Content = line.substring(8);
            }
            //stuff for incorrect format ie empty
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static void displayMessages(List<ChatMain.Message> readMessages, TextFlow textFlow) {
        textFlow.getChildren().clear();
        //row counter needed?

        for (ChatMain.Message msg : readMessages) {

            textFlow.setPadding(new Insets(10)); // Set the padding around the text
            Text timeStampText = new Text("[Time: " + msg.TimeStamp + "]");

            Text nameText;
            

            if (msg.LastMessageName != null && msg.Name.equals(msg.LastMessageName)) {
                nameText = new Text("...");
            } else {
                nameText = new Text(msg.Name + ":");
                nameText.setStyle("-fx-fill: blue;");
            }
            msg.Name = msg.LastMessageName;  // Update the previousName variable

//            Text nameText = new Text(msg.Name + ":");
//            nameText.setStyle("-fx-fill: blue;");

            Text contentText = new Text(msg.Content + "\n\n");
            contentText.setStyle("-fx-font-weight: bold;");

            //appends text segments to the TextFlow container to display them in the order they are added.
            textFlow.getChildren().addAll(timeStampText, nameText, contentText);

        }



        //deal with emojis
    }



}



