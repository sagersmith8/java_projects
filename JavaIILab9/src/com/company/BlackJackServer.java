package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Sage on 4/22/2014.
 */

public class BlackJackServer extends Thread {
    ServerSocket serverSocket;
    Socket client;
    String input, output;
    PrintWriter printWriter;
    BufferedReader bufferedReader;

    public BlackJackServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }


    }

    public void run() {
        while(true) {
            client = null;

            try {
                client = serverSocket.accept();
                new BlackJackHandler(client.getOutputStream(), client.getInputStream());

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
