package com.company;

import java.awt.*;
import java.awt.event.InputEvent;
import java.net.URL;

public class Main {
    public Main(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        new Main("http://math.carroll.edu/webwork2/ma131c_f10");
        Robot robot = new Robot();
        robot.mouseMove(200, 410);
        Thread.sleep(2000);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(2000);
        robot.mouseMove(200, 232);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        Thread.sleep(100);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        Thread.sleep(2000);

        while(true)
        {
            robot.mouseMove(200, 515);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            Thread.sleep(100);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(2000);


            robot.mouseMove(230, 475);
            Thread.sleep(500);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            Thread.sleep(100);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            int key = (int) (Math.random() * 9 + 48);
            robot.keyPress(key);
            Thread.sleep(20);
            robot.keyRelease(key);

            robot.mouseMove(230, 435);
            Thread.sleep(500);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            Thread.sleep(100);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            key = (int) (Math.random() * 9 + 48);
            robot.keyPress(key);
            Thread.sleep(20);
            robot.keyRelease(key);

            robot.mouseMove(220, 415);
            Thread.sleep(500);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            Thread.sleep(100);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            key = (int) (Math.random() * 9 + 48);
            robot.keyPress(key);
            Thread.sleep(20);
            robot.keyRelease(key);

            robot.mouseMove(200, 380);
            Thread.sleep(500);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            Thread.sleep(100);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            key = (int) (Math.random() * 9 + 48);
            robot.keyPress(key);
            Thread.sleep(20);
            robot.keyRelease(key);

            robot.mouseMove(200, 340);
            Thread.sleep(500);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            Thread.sleep(100);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            key = (int) (Math.random() * 9 + 48);
            robot.keyPress(key);
            Thread.sleep(20);
            robot.keyRelease(key);

            robot.mouseMove(270, 550);
            Thread.sleep(500);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            Thread.sleep(100);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(2000);

            robot.mouseMove(270, 220);
            Thread.sleep(500);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            Thread.sleep(100);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            Thread.sleep(5000);
         }
    }
}
