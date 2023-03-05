package org.falcon.server;

import org.falcon.server.database.Message;
import org.falcon.server.database.User;

import java.sql.SQLException;

public class MessageManagment {
    private final int COMMAND = 0;
    private final int USER_ID = 1;
    private String messageToClient;
    private String nativeMessage;
    private String message;
    private String username;

    public MessageManagment(String nativeMessage) throws SQLException, ClassNotFoundException {
        this.nativeMessage = nativeMessage;
        this.message = "";
        this.username = "";
        this.messageToClient = "";
        commandAnalyse(cutMessage());
    }

    public String[] cutMessage() {
        return this.nativeMessage.split("\\s+");
    }

    public void cutMessageToExtractUsername(String userSentence) {
        this.username = userSentence.split("@")[USER_ID];
    }

    public void commandAnalyse(String[] cuttingMessage) throws SQLException, ClassNotFoundException {
        try {
            System.out.println("Starting command analyse ... please hold on");
            if(cuttingMessage[COMMAND].equals("PUBLISH")) {
                cutMessageToExtractUsername(cutMessage()[USER_ID]);
                if(checkUser()) {
                    setMessage(cuttingMessage);
                    new Message(this.username, this.message).insertMessage();
                    this.messageToClient = "Message has been add successfully in database";
                }

            }
        } catch (Exception e) {
            System.err.println("ERROR WHEN ANALYSING");
            e.printStackTrace();
        }
    }

    public String messageToClient() {
        return this.messageToClient;
    }

/* PUBLISH Command */

    public void setMessage(String[] cuttingMessage) {
        for(int index = 2; index < cuttingMessage.length; index++) this.message += cuttingMessage[index] + " ";
    }

    public String getMessage() {
        return this.message;
    }

    public boolean checkUser() throws SQLException, ClassNotFoundException {
        return new User(this.username).alreadyInDb();
    }

    public String getUsername() {
        return username;
    }
}
