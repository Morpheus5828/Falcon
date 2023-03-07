package org.falcon.client;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Repost {
    public static void main(String[] args) throws IOException {
        System.out.println("\t\n-> WELCOME TO FALCON APP Repost Client");
        Socket client = new Socket();

        try {
            client.connect(new InetSocketAddress("localhost", 12345));
            String command = "";

            System.out.print(">: Enter @user: \t\n--> ");
            Scanner scanner = new Scanner(System.in);
            String user = scanner.nextLine();

            System.out.print("\n>: Enter message_id: \t\n--> ");
            Scanner scanner1 = new Scanner(System.in);
            String message = scanner1.nextLine();

            command = "REPUBLISH author:@" + user + " msg_id:" + message;
            System.out.println(command);
            OutputStream out = client.getOutputStream();
            PrintWriter writer = new PrintWriter(out, true);
            writer.println(command);

            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            System.out.println(reader.readLine());




        } catch (ConnectException e) {
            System.out.println("ERROR 500 - Connection Failed, please try again");
        }


    }
}
