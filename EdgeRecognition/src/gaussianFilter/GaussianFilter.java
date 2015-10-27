package gaussianFilter;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class GaussianFilter extends JFrame implements ActionListener {
	
	double[][] v;
	Filter f;
	
	Image i;
	HashMap hm = new HashMap();
	BufferedImage buf = new BufferedImage(500,500,BufferedImage.TYPE_INT_RGB);
	String imageName = null;
	
	JMenuBar m = new JMenuBar();
		JMenu file = new JMenu("File");
			JMenuItem about = new JMenuItem("About");
			JMenuItem exit = new JMenuItem("Exit");
		JMenu picture = new JMenu("Picture");
			JMenuItem select = new JMenuItem("Select Picture");
			JMenuItem filterM = new JMenuItem("Apply Filter");
			
	public GaussianFilter() {
		f = new Filter(Integer.parseInt(JOptionPane.showInputDialog("Dimmension: ")), Double.parseDouble(JOptionPane.showInputDialog("Deviation constant: ")));
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == about) {
			
		} else if(e.getSource() == exit) {
			System.exit(0);
		} else if(e.getSource() == select) {
			imageName = JOptionPane.showInputDialog("Enter the name of the image you would like to select.");
			try {
				buf = ImageIO.read(new File(imageName));
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(this, "Could not find or load " + imageName +".");
				e1.printStackTrace();
			}
		} else if(e.getSource() == filterM) {
			
		}

	}

	public static void main(String[] args) {
		new GaussianFilter();
	}
}
