package com.company;

import java.io.BufferedReader;
import java.io.PrintWriter;


public class BlackjackWatcher extends Thread {
    private BlackJack gui;   // ref to top-level client
    private BufferedReader in;   // stream coming from the server
    private PrintWriter out;


    public BlackjackWatcher(BlackJack g, BufferedReader i, PrintWriter out) {
        gui = g;
        in = i;
        this.out = out;
    }

    public void run() {
        String line = "";
        boolean done = false;

        try {
            while ((line = in.readLine()) != null || !done) {
/*	    	line = "Player Wins";
            System.out.println(line.substring(7,11));*/
                if ((line.length() == 11) && (line.substring(7, 11).equals("Wins"))) {
//                    System.out.println(line);
                    done = true;
                    gui.processGameInput(line);
                } else {
//                    System.out.println(line);
                    gui.processGameInput(line);
                }
            }

        }

        catch (Exception e)    // socket closure will cause termination of while
        {
           e.printStackTrace();

        }
    } // end of run()
}

