package org.falcon.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Publisher {
    public static void main(String[] args) throws IOException {
        System.out.println("\t\n-> WELCOME TO FALCON APP");
        Socket client = new Socket();

        try {
            client.connect(new InetSocketAddress("localhost", 12345));
        } catch (ConnectException e) {
            System.out.println("ERROR 500 - Connection Failed, please try again");
        }

        client.connect(new InetSocketAddress("localhost", 12345));
        OutputStream out = client.getOutputStream();
        PrintWriter print = new PrintWriter(new OutputStreamWriter(out));
        String message = "TEST";
        print.write(message);
        print.flush();
    }
}