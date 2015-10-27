

import gaussianFilter.CannyEdgeDetector;
import gaussianFilter.Filter;
import gaussianFilter.GaussianFilter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Test extends JFrame {
	
	String imgName = "image3.png";
	Board b;
	BufferedImage img;
	BufferedImage png;
	Filter f = new GaussianFilter(5, 1.8);
	CannyEdgeDetector detector = new CannyEdgeDetector();
	
	public Test() {
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		try {
			img = ImageIO.read(new File(imgName));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Could not find or load image", "I/O Error", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		
		png = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				png.setRGB(i, j, img.getRGB(i, j));
			}
		}
		
		detector.setLowThreshold(0.5f);
		detector.setHighThreshold(1f);

		//apply it to an image
		detector.setSourceImage(f.filterImage(png));
		detector.process();
		BufferedImage edges = detector.getEdgesImage();
		
		b = new Board(f.filterImage(png), " ");
		add(b);
		pack();
	}
	
	public static void main(String[] args) {
		new Test();
	}
}
