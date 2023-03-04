package org.falcon.server;

public class MessageManagment {
    private final int COMMAND = 0;
    private final int USER_ID = 1;
    private String nativeMessage;
    private String message;
    private String username;

    public MessageManagment(String nativeMessage) {
        this.nativeMessage = nativeMessage;
        this.message = "";
        this.username = "";
        commandAnalyse(cutMessage());

    }

    public String[] cutMessage() {
        return this.nativeMessage.split("\\s+");
    }

    public void commandAnalyse(String[] cuttingMessage) {
        if(cuttingMessage[COMMAND].equals("PUBLISH")) {
            System.out.println(cuttingMessage[USER_ID]);

        }
    }

    /* PUBLISH Command */

    public void setMessage(String[] cuttingMessage) {
        for(int index = 1; index < cuttingMessage.length; index++) this.message += cuttingMessage[index] + " ";
    }

    public String getMessage() {
        return this.message;
    }

    public void checkUser() {

    }


}
