package com.company;

import java.io.*;

/**
 * Created by Sage on 4/24/2014.
 */
public class BlackJackHandler extends Thread {
    PrintWriter printWriter;
    BufferedReader bufferedReader;

    public BlackJackHandler(OutputStream outputStream, InputStream inputStream){
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        this.printWriter = new PrintWriter(outputStream);
        this.start();
    }

    @Override
    public void run() {
        BlackjackGame bjg = new BlackjackGame();
        printWriter.println("Welcome to 21");
        printWriter.println("Begin A New Game");

        if (bjg.playGame(bufferedReader, printWriter)) {
            printWriter.println("Player Wins");
        } else {
            printWriter.println("Dealer Wins");
        }
    }
}
