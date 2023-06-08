package com.example.chat;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileRead {
    public List<ChatMain.Message> FileRead(File file) {
        List<ChatMain.Message> readMessages = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(file);
            ChatMain.Message msg = new ChatMain.Message();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();

                if(line.startsWith("Message:"));
                readMessages.add(msg);
                msg = new ChatMain.Message();
                parseMessage(msg, line);
//                //check for previous author
//                if (!readMessages.isEmpty()){
//                    msg.LastMessageName = readMessages.get(readMessages.size() -1).Name;
//                }
//            }
//            else {
                parseMessage(msg, line);
//
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return readMessages;
    }


    public void parseMessage (ChatMain.Message msg, String line) {
        try {
            if (line.startsWith("Time:")) {
                msg.TimeStamp = line.substring(5);
            } else if (line.startsWith("Name:")) {
                msg.Name = line.substring(5);
            } else if (line.startsWith("Message:") && line.length() > 8) {
                msg.Content = line.substring(8);
            }
            //stuff for incorrect format
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}



