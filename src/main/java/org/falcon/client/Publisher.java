package org.falcon.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Publisher {
    public static void main(String[] args) throws IOException {
        System.out.println("\t\n-> WELCOME TO FALCON APP");
        Socket client = new Socket();

        try {
            client.connect(new InetSocketAddress("localhost", 12345));
            String command = "";

            while(true) {
                Scanner scanner = new Scanner(System.in);
                // when user finished, he must enter '-end'
                while(scanner.hasNext()) {
                    command = scanner.nextLine();
                    OutputStream out = client.getOutputStream();
                    PrintWriter writer = new PrintWriter(out, true);
                    writer.println(command);
                    if(client.equals("-end")) break;
                }

            }
            //client.close();

        } catch (ConnectException e) {
            System.out.println("ERROR 500 - Connection Failed, please try again");
        }


    }
}