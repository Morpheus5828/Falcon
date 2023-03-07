package org.falcon.server;

import org.falcon.server.database.Message;
import org.falcon.server.database.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageManagment {
    private final int COMMAND = 0;
    private final int USER_ID = 1;
    private final int MESSAGE_ID = 1;
    private final int REPLY_ID = 2;
    private final int REPLY_ID_HEADER = 0;
    private List<String> messageToClient;
    private String nativeMessage;
    private String message;
    private String username;
    private String replyId;


    public MessageManagment(String nativeMessage) throws SQLException, ClassNotFoundException {
        this.nativeMessage = nativeMessage;
        this.message = "";
        this.username = "";
        this.replyId = "";
        this.messageToClient = new ArrayList<>();
        commandAnalyse(cutMessage());
    }

    public String[] cutMessage() {
        return this.nativeMessage.split("\\s+");
    }

    public void cutMessageToExtractUsername(String userSentence) {
        this.username = userSentence.split("@")[USER_ID];
    }

    public String[] cutReplyId(String userSentence) {
        return userSentence.split(":");
    }

    public void commandAnalyse(String[] cuttingMessage) throws SQLException, ClassNotFoundException {
        try {
            System.out.println("Starting command analyse ... please hold on");
            if(cuttingMessage[COMMAND].equals("PUBLISH")) {
                cutMessageToExtractUsername(cutMessage()[USER_ID]);
                if(checkUser()) {
                    setMessage(cuttingMessage);
                    new Message(this.username, this.message).insertMessage();
                    this.messageToClient.add("Message has been add successfully in database");
                }
                else this.messageToClient.add("User not in database");
            }

            else if(cuttingMessage[COMMAND].equals("RCV_MSG")) {
                cutMessageToExtractUsername(cutMessage()[USER_ID]);
                if(checkUser())
                    this.messageToClient = new Message(this.username).getMessageFromUser();
                else this.messageToClient.add("User not in database");
            }

            else if (cuttingMessage[COMMAND].equals("REPLY")) {
                cutMessageToExtractUsername(cutMessage()[USER_ID]);
                if(checkUser()) {
                    String reply = cutMessage()[REPLY_ID];
                    this.replyId = cutReplyId(reply)[MESSAGE_ID];
                    if(cutReplyId(reply)[REPLY_ID_HEADER].equals("reply_to_id")) {
                        System.out.println("id: " + this.replyId);
                        Message message = new Message(Integer.parseInt(this.replyId));
                        String msg_to_reply = message.getMessageFromId();
                        Message repliedMessage = new Message(this.username, msg_to_reply, Integer.parseInt(this.replyId));
                        repliedMessage.insertMessage();
                    }
                }
                else this.messageToClient.add("User not in database");
            }


        } catch (Exception e) {
            System.err.println("ERROR WHEN ANALYSING");
            e.printStackTrace();
        }
    }

    public List<String> messageToClient() {
        return this.messageToClient;
    }

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
