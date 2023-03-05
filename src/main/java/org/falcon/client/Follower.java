package org.falcon.client;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Follower {
    public static void main(String[] args) throws IOException {
        System.out.println("\t\n-> WELCOME TO FALCON APP");
        Socket client = new Socket();

        try {
            client.connect(new InetSocketAddress("localhost", 12345));
            String command = "";

            System.out.print(">: Enter @user that you want to see message: \t\n--> ");
            Scanner scanner = new Scanner(System.in);
            String user = scanner.nextLine();

            command = "RCV_MSG author:@" + user + " ";

            OutputStream out = client.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(command);

            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String[] message = reader.readLine().split(";");
            System.out.println("\n>: Message from: " + user + "\n");
            for(String sentence : message) System.out.println(sentence);
            System.out.println();


        } catch (ConnectException e) {
            System.out.println("ERROR 500 - Connection Failed, please try again");
        }


    }
}
