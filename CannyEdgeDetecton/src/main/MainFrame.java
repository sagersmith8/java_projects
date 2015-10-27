package main;

import gaussianFilter.GaussianFilter;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MainFrame extends JFrame{

	Board originalImg;
	Board gaussianImg;
	Board cannyXImg;
	Board cannyYImg;

	public MainFrame() {
		//		String imgName = JOptionPane.showInputDialog("name of image");
		int x = Integer.parseInt(JOptionPane.showInputDialog("Width of filter: "));
		int y = Integer.parseInt(JOptionPane.showInputDialog("Height of filter: "));
		double a = Double.parseDouble(JOptionPane.showInputDialog("Standard deviation constant: "));
		GaussianFilter f = new GaussianFilter(x, y, a);
		System.out.println(f.getValues());
		
		String imgName = "image.png";
		originalImg = new Board(imgName, "No effect");
		try {
			gaussianImg = new Board(f.filterImage(imgName), "Gaussian");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cannyXImg = new Board(new CannyEdgeDetection(imgName,"X", f).img, "Canny non filtered");
		try {
			cannyYImg = new Board(new CannyEdgeDetection(f.filterImage(imgName),"B", f).img, "Canny filtered");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setLayout(new GridLayout(2,2));
		this.add(originalImg);
		this.add(gaussianImg);
		this.add(cannyXImg);
		this.add(cannyYImg);
		this.setTitle("Canny Edge Detection");
		this.setLocation(0, 0);
		this.pack();
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
