package org.falcon.server;

public class MessageManagment {
    private final int COMMAND = 0;
    private String nativeMessage;
    private String message;

    public MessageManagment(String nativeMessage) {
        this.nativeMessage = nativeMessage;
        this.message = "";
    }

    public String[] cutMessage() {
        return this.nativeMessage.split("\\s+");
    }

    public void commandAnalyse(String[] cuttingMessage) {
        if(cuttingMessage[COMMAND].equals("PUBLISH")) {
            setMessage(cuttingMessage);
            System.out.println(this.message);
        }
    }

    /* PUBLISH Command */

    public void setMessage(String[] cuttingMessage) {
        for(int index = 1; index < cuttingMessage.length; index++) this.message += cuttingMessage[index] + " ";
    }

    public String getMessage() {
        return this.message;
    }
}
