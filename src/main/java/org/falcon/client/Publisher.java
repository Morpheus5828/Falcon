package org.falcon.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Publisher {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket();
        client.connect(new InetSocketAddress("localhost", 12345));
        OutputStream out = client.getOutputStream();
        PrintWriter print = new PrintWriter(new OutputStreamWriter(out));
        String message = "TEST";
        print.write(message);
        print.flush();
    }
}