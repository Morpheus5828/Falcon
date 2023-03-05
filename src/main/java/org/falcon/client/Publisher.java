package org.falcon.client;

import java.io.*;
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

            System.out.print(">: Enter @user: \t\n--> ");
            Scanner scanner = new Scanner(System.in);
            String user = scanner.nextLine();

            System.out.print("\n>: Enter message: \t\n--> ");
            Scanner scanner1 = new Scanner(System.in);
            String message = scanner1.nextLine();

            command = "PUBLISH author:@" + user + " " + message;

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

