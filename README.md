# Chat Application

This is a JavaFX chat application that reads messages from a file and displays them in a text flow.
It displays messages with timestamps, names, and content.
Emojis are also supported in the message content.

## Installation

1. Clone the repository to your local machine.
2. Open the project in IntelliJ IDEA.

## How to Use

1. Launch the application by running the program from ChatMain.
2. Click the "Choose File" button to open a chat file.
3. Select the chat file (in .msg format) to load and display the chat messages.
4. The chat messages will be displayed in the text area.
5. Emojis within the message content will be highlighted.
6. If you would like to open a new file, click the "Choose File" button again.
7. If you open a file that is the wrong file type, or if the file is in the wrong format
   an error message will appear. 

## Dependencies

- JavaFX: This application uses JavaFX for the user interface components.
- OpenJFX: Ensure that the JavaFX runtime is properly installed and configured.

## Prerequisites

- Java Development Kit (JDK) 8 or higher 
- IntelliJ IDEA (or any Java IDE of your choice)
- Maven

## Note

The chat message file should follow to this format:

- Must be a msg file
- The message details should formated this way:
  - Time: [Timestamp]
  - Name: [Name]
  - Message: [Content]
- Empty fields or missing components in a message will result in error handling.
- Fields for the message content can be empty.
- Emojis in the message content will be detected if supported.

## Troubleshooting
- If you encounter any issues with the chat message file format or content, error messages will be displayed.
- Make sure the chat message file has the correct format and is located in the application's working directory.

## Classes and Methods

### ChatMain
Contains the main method to launch the chat application.

#### Methods

- **main(String[] args):** The entry point of the application. Launches the JavaFX application.

### Emojis
Provides utility methods to detect and handle emojis in chat messages.

#### Methods

- **containsHappyEmoji(String text):** Checks if the given text contains a happy emoji.
  - Parameters:
    - `text` (String): The text to check.
  - Returns:
    - `boolean`: True if a happy emoji is found, false otherwise.

- **containsSadEmoji(String text):** Checks if the given text contains a sad emoji.
  - Parameters:
    - `text` (String): The text to check.
  - Returns:
    - `boolean`: True if a sad emoji is found, false otherwise.

### ChatController
The controller class for the chat application UI.

#### Methods

- **chooseFile(MouseEvent event):** Event handler for the "Choose File" button click. Opens a file chooser dialog and loads the selected file.
  - Parameters:
    - `event` (MouseEvent): The mouse event.

- **OpenFile():** Opens a file chooser dialog and returns the selected file.
  - Returns:
    - `File`: The selected file, or null if no file is selected.

- **initialize(URL url, ResourceBundle resourceBundle):** Initializes the controller. Called after the FXML file has been loaded.
  - Parameters:
    - `url` (URL): The location used to resolve relative paths.
    - `resourceBundle` (ResourceBundle): The resources used for localization.

### FileRead
Reads a chat file, parses its contents, and displays the chat messages.

#### Methods

- **FileScan(File file):** Scans the given file and extracts chat messages from it.
  - Parameters:
    - `file` (File): The file to be scanned.
  - Returns:
    - `List<ChatMain.Message>`: A list of chat messages extracted from the file.

- **parseMessage(ChatMain.Message msg, String line):** Parses a line of text and extracts the message components (time, name, and content).
  - Parameters:
    - `msg` (ChatMain.Message): The message object to store the extracted components.
    - `line` (String): The line of text to be parsed.

- **displayMessages(List<ChatMain.Message> readMessages, TextFlow textFlow):** Displays the messages in a TextFlow container.
  - Parameters:
    - `readMessages` (List<ChatMain.Message>): The list of messages to display.
    - `textFlow` (TextFlow): The TextFlow container to display the messages in.



**Author:** [Tamarah Newman]  
**Date:** [9/62023]
