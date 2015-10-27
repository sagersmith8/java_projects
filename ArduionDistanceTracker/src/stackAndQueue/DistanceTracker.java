package stackAndQueue;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class DistanceTracker implements KeyListener {
	int x;
	Stack<String> stack = new Stack<String>();
	Queue<String> queue = new ArrayDeque<String>();
		
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			//bluetooth . transmit 1
			//x = bluetooth.recieve;
			//System.out.println(x); 
		}
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void keyTyped(KeyEvent e) {
		
	}

	public static void main(String[] args) {
		new DistanceTracker();
	}

}
