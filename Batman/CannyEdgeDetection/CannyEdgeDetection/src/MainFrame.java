import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MainFrame extends JFrame{
	
	/**
	 * @param args
	 */
	
	Board originalImg;
	Board gaussianImg;
	Board cannyXImg;
	Board cannyYImg;
	
	public MainFrame(){
		String image = JOptionPane.showInputDialog("name of image");
//		String image = "image.png";
		originalImg = new Board(image, "No effect");
		gaussianImg = new Board(new GaussianFilter(image).img, "Gaussian");
		cannyXImg = new Board(new CannyEdgeDetection(image,"X").img, "Canny X");
		cannyYImg = new Board(new CannyEdgeDetection(image,"Y").img, "Canny Y");
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
