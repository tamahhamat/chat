package com.example.chat;

import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * The Emojis class has methods for working with emojis in the chat application.
 */
public class Emojis {
    //to keep track of where emojis are found and if there is more than one
    private static List<Integer> sadIndexes;
    private static List<Integer> happyIndexes;

    public static Image HappyEmoji;
    public static Image SadEmoji;

    /**
     * Loads the emoji images from the specified class resources.
     *
     * @param originalClass The original class to load resources from.
     */
    public static void MakeEmoji(Class originalClass) {
        HappyEmoji = new Image(originalClass.getResourceAsStream("smile_happy.gif"));
        SadEmoji = new Image(originalClass.getResourceAsStream("smile_sad.gif"));
    }

    /**
     * Checks if the given line contains a sad emoji.
     * stores emoji indexes
     *
     * @param line The line to check for a sad emoji.
     * @return {@code true} if a sad emoji is found, {@code false} otherwise.
     */
    public static boolean containsSadEmoji(String line) {
        sadIndexes = new ArrayList<>();
        for (int i = 0; i < line.length() - 1; i++) {
            if (line.charAt(i) == ':' && line.charAt(i + 1) == '(') {
                sadIndexes.add(i);
                }
            }
        if (!sadIndexes.isEmpty()){ // Return true if sadIndexes is not empty
            return true;
        }
        return false; // :( emoticon not found
    }


    /**
     * Returns the list of indexes where sad emojis are found in the last checked line.
     *
     * @return The list of indexes where sad emojis are found.
     */
    public static List<Integer> getSadIndexes() {
        return sadIndexes;
    }

    /**
     * Checks if the given line contains a happy emoji (":)").
     *
     * @param line The line to check for a happy emoji.
     * @return {@code true} if a happy emoji is found, {@code false} otherwise.
     */
    public static boolean containsHappyEmoji(String line) {
        happyIndexes = new ArrayList<>();
        for (int i = 0; i < line.length() - 1; i++) {
            if (line.charAt(i) == ':' && line.charAt(i + 1) == ')') {
                happyIndexes.add(i);
            }
        }
        if (!happyIndexes.isEmpty()){ // Return true if happyIndexes is not empty
            return true;
        }
        return false; // :) emoticon not found
    }

    /**
     * Returns the list of indexes where happy emojis (":)") are found in the last checked line.
     *
     * @return The list of indexes where happy emojis are found.
     */
    public static List<Integer> getHappyIndexes() {
        return happyIndexes;
    }

    }


