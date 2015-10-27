package com.company;
/*
 * author@ Audra Landis
 */

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class BlackJack extends javax.swing.JFrame {
    private boolean connected;
    private JLabel jlCard2;
    private JLabel jlCard1;
    private JLabel jlTotal;
    private JLabel jLabel2;
    private JLabel jLabel1;
    private JButton jbDeal;
    private JButton jbStand;
    private JButton jbHit;
    private JLabel jlCard10;
    private JLabel jlCard9;
    private JLabel jlCard8;
    private JLabel jlCard7;
    private JLabel jlCard6;
    private JLabel jlCard5;
    private JLabel jlCard4;
    private JLabel jlCard3;
    private JLabel dealerCards[] = new JLabel[5];
    private JLabel playerCards[] = new JLabel[5];
    private JLabel jlServer;

    private static final int PORT = 1234;     // server details
    private static final String HOST = "localhost";

    private Socket sock;
    private PrintWriter out;  // output to the server
    private BufferedReader in;

    private int randomCard;
    private int numDealerCard = 2;
    private int numPlayerCard = 2;

    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BlackJack inst = new BlackJack();
                inst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    private void makeContact() {
        try {
            BlackJackServer blackJackServer = new BlackJackServer(PORT);
            blackJackServer.start();
            sock = new Socket(HOST, PORT);
            in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            out = new PrintWriter(sock.getOutputStream(), true);  // autoflush
            BlackjackWatcher bjw = new BlackjackWatcher(this, in, out);    // start watching for server msgs
            bjw.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        connected = true;
    }  // end of makeContact()

    public BlackJack() {
        super();
        initGUI();
    }

    public void processGameInput(String fromGame) {
        int cardNum;
        char whoCard;
        String cardInfo;

        if (fromGame.charAt(0) == 'c') {
            whoCard = fromGame.charAt(1);
            JLabel cards[];
            if (whoCard == 'd') {
                cards = dealerCards;
            } else {
                cards = playerCards;
            }
            cardNum = Integer.parseInt(Character.toString(fromGame.charAt(2)));
            cardInfo = fromGame.substring(3);
            cards[cardNum - 1].setText(cardInfo);
        }

        jlServer.setText(fromGame);
    }

    private void initGUI() {
        try {
            {
                getContentPane().setLayout(null);
                {
                    jbHit = new JButton();
                    getContentPane().add(jbHit);
                    jbHit.setText("Hit");
                    jbHit.setBounds(55, 361, 80, 35);
                    jbHit.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                            jbHitMouseClicked(evt);
                        }
                    });
                }
                {
                    jbStand = new JButton();
                    getContentPane().add(jbStand);
                    jbStand.setText("Stand");
                    jbStand.setBounds(175, 361, 81, 35);
                    jbStand.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                            jbStandMouseClicked(evt);
                        }
                    });
                }
                {
                    jbDeal = new JButton();
                    getContentPane().add(jbDeal);
                    jbDeal.setText("Deal");
                    jbDeal.setBounds(300, 361, 80, 35);
                    jbDeal.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent evt) {
                            jbDealMouseClicked(evt);
                        }
                    });
                }
                {
                    jLabel1 = new JLabel();
                    getContentPane().add(jLabel1);
                    jLabel1.setText("Dealer");
                    jLabel1.setBounds(15, 15, 65, 25);
                }
                {
                    jLabel2 = new JLabel();
                    getContentPane().add(jLabel2);
                    jLabel2.setText("Player");
                    jLabel2.setBounds(15, 193, 65, 25);
                }
                {
                    jlTotal = new JLabel();
                    getContentPane().add(jlTotal);
                    jlTotal.setText("Total: ");
                    jlTotal.setBounds(328, 193, 65, 25);
                }
                {
                    jlCard1 = new JLabel();
                    getContentPane().add(jlCard1);
                    jlCard1.setBounds(32, 47, 65, 95);
                    jlCard1.setBackground(new java.awt.Color(255, 255, 255));
                    jlCard1.setBorder(BorderFactory.createTitledBorder(""));
                    jlCard1.setFont(new java.awt.Font("Tahoma", 0, 9));
                }
                {
                    jlCard2 = new JLabel();
                    getContentPane().add(jlCard2);
                    jlCard2.setBounds(103, 47, 65, 95);
                    jlCard2.setBorder(BorderFactory.createTitledBorder(""));
                }
                {
                    jlCard3 = new JLabel();
                    getContentPane().add(jlCard3);
                    jlCard3.setBounds(174, 47, 65, 95);
                    jlCard3.setBorder(BorderFactory.createTitledBorder(""));
                }
                {
                    jlCard4 = new JLabel();
                    getContentPane().add(jlCard4);
                    jlCard4.setBounds(245, 47, 65, 95);
                    jlCard4.setBorder(BorderFactory.createTitledBorder(""));
                }
                {
                    jlCard5 = new JLabel();
                    getContentPane().add(jlCard5);
                    jlCard5.setBounds(316, 47, 65, 95);
                    jlCard5.setBorder(BorderFactory.createTitledBorder(""));
                }
                {
                    jlCard6 = new JLabel();
                    getContentPane().add(jlCard6);
                    jlCard6.setBounds(33, 223, 65, 95);
                    jlCard6.setBorder(BorderFactory.createTitledBorder(""));
                }
                {
                    jlCard7 = new JLabel();
                    getContentPane().add(jlCard7);
                    jlCard7.setBounds(104, 222, 65, 95);
                    jlCard7.setBorder(BorderFactory.createTitledBorder(""));
                }
                {
                    jlCard8 = new JLabel();
                    getContentPane().add(jlCard8);
                    jlCard8.setBounds(175, 222, 65, 95);
                    jlCard8.setBorder(BorderFactory.createTitledBorder(""));
                }
                {
                    jlCard9 = new JLabel();
                    getContentPane().add(jlCard9);
                    jlCard9.setBounds(246, 223, 65, 95);
                    jlCard9.setBorder(BorderFactory.createTitledBorder(""));
                }
                {
                    jlCard10 = new JLabel();
                    getContentPane().add(jlCard10);
                    jlCard10.setBounds(317, 223, 65, 95);
                    jlCard10.setBorder(BorderFactory.createTitledBorder(""));
                    dealerCards[0] = jlCard1;
                    dealerCards[1] = jlCard2;
                    dealerCards[2] = jlCard3;
                    dealerCards[3] = jlCard4;
                    dealerCards[4] = jlCard5;

                    playerCards[0] = jlCard6;
                    playerCards[1] = jlCard7;
                    playerCards[2] = jlCard8;
                    playerCards[3] = jlCard9;
                    playerCards[4] = jlCard10;
                }
                {
                    jlServer = new JLabel();
                    getContentPane().add(jlServer);
                    jlServer.setText("From Server");
                    jlServer.setBounds(140, 166, 123, 27);
                }
            }
            this.setSize(450, 453);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbHitMouseClicked(MouseEvent evt) {
        System.out.println("jbHit.mouseClicked");

        out.println("hit");
    }

    private void jbStandMouseClicked(MouseEvent evt) {
        System.out.println("jbStand.mouseClicked");
        out.println("stand");
    }

    private void jbDealMouseClicked(MouseEvent evt) {
        System.out.println("jbDeal.mouseClicked");
        for (int x = 0; x < 5; x++) {
            dealerCards[x].setText("");
            playerCards[x].setText("");
        }

        if (!connected)
            makeContact();
    }
}
