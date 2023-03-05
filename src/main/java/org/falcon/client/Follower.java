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

            /* Ask which command does user want */
            System.out.print(">: Do you want to receive message from one (1) or many user (2): \t\n--> ");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();

            if(answer.equals("1")) {
                System.out.print(">: Enter @user that you want to see message: \t\n--> ");
                Scanner scanner1 = new Scanner(System.in);
                String user = scanner1.nextLine();

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
            }

            if(answer.equals("2")) {
                System.out.print(">: Enter all user like this: @user1-@User2-...- @UserN:\t\n--> ");
                Scanner scanner1 = new Scanner(System.in);
                String[] users = scanner1.nextLine().split("-");
                for(String user : users) {
                    command = "RCV_MSG author:@" + user + " ";

                    OutputStream out = client.getOutputStream();
                    PrintWriter writer = new PrintWriter(out, true);
                    writer.println(command);

                    InputStream input = client.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    assert reader.readLine() != null;
                    String[] message = reader.readLine().split(";");
                    System.out.println("\n>: Message from: " + user + "\n");
                    for(String sentence : message) System.out.println(sentence);
                    System.out.println();
                    client.close();
                    client = new Socket();
                    client.connect(new InetSocketAddress("localhost", 12345));
                }
                client.close();
            }


            else {
                System.out.print(">: ERROR - You didn't choose any choices, please try again \t\n--> ");
                client.close();
            }




        } catch (ConnectException e) {
            System.out.println("ERROR 500 - Connection Failed, please try again");
        } catch (NullPointerException e) {

        }


    }
}
