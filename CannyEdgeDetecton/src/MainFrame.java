import gaussianFilter.BrightnessFilter;
import gaussianFilter.EdgeDetection;
import gaussianFilter.Filter;
import gaussianFilter.GaussianFilter;
import gaussianFilter.MedianFilter;
import gaussianFilter.ValueEditor;

import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class MainFrame extends JFrame{

	Board[] board = new Board[4];
	BufferedImage img;
	String imgName;
	Filter[] filter = new Filter[4];
	EdgeDetection[] edges = new EdgeDetection[4];
	
	Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

	public MainFrame() {
		setLayout(new GridLayout(2,2));
		setTitle("Canny Edge Detection");
		setBounds(0, 0, (int)d.getWidth()-350, (int)d.getHeight()-40);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		
		new ControlFrame();
		for (int i = 0; i < board.length; i++) {
			board[i] = new Board();
			add(board[i]);
		}
	}

	public static void main(String[] args) {
		new MainFrame();
	}
	
	@SuppressWarnings({"rawtypes"})
	public class ControlFrame extends JFrame implements ActionListener {
		
		JMenuBar m = new JMenuBar();
			JMenu file = new JMenu("File)");
				JMenuItem about = new JMenuItem("About");
				JMenuItem exit = new JMenuItem("Exit");
			JMenu filterM = new JMenu("Filter");
				JMenuItem gaussian = new JMenuItem("Gaussian Values");
				JMenuItem brightness = new JMenuItem("Brightness Values");
		
		JPanel topP = new JPanel();
		JLabel imageL = new JLabel("Image name: ");
		JPanel p = new JPanel();
		JTextField imageF = new JTextField(15);
		JButton goB = new JButton("Select");
		JPanel[] frames = new JPanel[4];
		JComboBox[] filters = new JComboBox[4];
		EdgeDetectionPanel[] angles = new EdgeDetectionPanel[4];
		
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();	
		String[] filterOptions = {"None", "Gaussian", "Median", "Brightness"};
		Filter[] filterO = {null, new GaussianFilter("Filter"), new MedianFilter(1), new BrightnessFilter(1)};
		
		@SuppressWarnings("unchecked")
		public ControlFrame(){
			setBounds((int)d.getWidth()-350, 0, 350, (int)d.getHeight()-40);
			setTitle("Control Panel");
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLayout(new GridLayout(5,1));
			setJMenuBar(m);
			setVisible(true);
			
			m.add(file);
				file.add(about);
				about.addActionListener(this);
				file.add(exit);
				exit.addActionListener(this);
			m.add(filterM);
				filterM.add(gaussian);
				gaussian.addActionListener(this);
				filterM.add(brightness);
				brightness.addActionListener(this);
			
			add(topP);
			topP.add(imageL);
			topP.add(p);
				p.add(imageF);
				p.add(goB);
					goB.addActionListener(this);
			
			for (int i = 0; i < frames.length; i++) {
				frames[i] = new JPanel();
				frames[i].setBorder(new TitledBorder("Frame " + (i+1)));
				frames[i].setLayout(new GridLayout(2,1));
				filters[i] = new JComboBox(filterOptions);
				frames[i].add(filters[i]);
				angles[i] = new EdgeDetectionPanel();
				frames[i].add(angles[i]);
				add(frames[i]);
			}
			revalidate();
		}
		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == goB) {
				imgName = imageF.getText();
				try {
					img = ImageIO.read(new File(imgName));
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Could not find or load image", "I/O Error", JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				
				for (int i = 0; i < frames.length; i++) {
//					filter[i] = TODO use a hashmap to set the filter
					filter[i] = (Filter)filterO[filters[i].getSelectedIndex()];
					edges[i] = new EdgeDetection(angles[i].xB.getState(), angles[i].x2B.getState(), angles[i].yB.getState(), angles[i].y2B.getState());
					if(filter[i] == null) {
						board[i].setBoard(img, "No filter, " + "");
					} else if(img != null) {
						board[i].setBoard(filter[i].filterImage(img), filters[i].getSelectedItem() + ", " + "");
					}
				}
			} else if(e.getSource() == exit) {
				System.exit(0);
			} else if(e.getSource() == about) {
				JOptionPane.showConfirmDialog(null, "Created by Nick Burkland\nMarch 2014\nFor testing filters used in computer vision", "About", JOptionPane.INFORMATION_MESSAGE);
			} else if(e.getSource() == gaussian) {
				String[] s = {"1", "2", "3", "4"};
				@SuppressWarnings("unchecked")
				int num = Integer.parseInt(JOptionPane.showInputDialog(null, new JComboBox(s), "Pick Filter", JOptionPane.QUESTION_MESSAGE));
				new ValueEditor(filter[num].getValues());
			} else if(e.getSource() == brightness) {
				
			}
		}
		
		public class EdgeDetectionPanel extends JPanel {
			Checkbox xB = new Checkbox("X");
			Checkbox x2B = new Checkbox("-X");
			Checkbox yB = new Checkbox("Y");
			Checkbox y2B = new Checkbox("-Y");
			
			public EdgeDetectionPanel() {
				add(xB);
				add(x2B);
				add(yB);
				add(y2B);
			}
		}
	}
}
