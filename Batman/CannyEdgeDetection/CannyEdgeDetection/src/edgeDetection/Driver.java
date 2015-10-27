package edgeDetection;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.ImagingOpException;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;


public class Driver extends JFrame {

	float [] matrixX1 = new float[]{
			-1f, 	0f, 	1f, 
			-1f, 	0f, 	1f, 
			-1f, 	0f, 	1f
	};

	float[] matrixY1 = new float[]{
			-1f, 	-1f, 	-1f, 
			0f, 	0f, 	0f, 
			1f, 	1f, 	1f
	};
	
	float [] matrixX2 = new float[]{
			1f, 	0f, 	-1f, 
			1f, 	0f, 	-1f, 
			1f, 	0f, 	-1f
	};

	float[] matrixY2 = new float[]{
			1f, 	1f, 	1f, 
			0f, 	0f, 	0f, 
			-1f, 	-1f, 	-1f
	};

	float[] matrix = new float[]{
			0f, 	0f, 	0f, 	0f, 	0f,
			0f, 	1/16f, 	1/8f, 	1/16f, 	0f,
			0f, 	1/8f, 	1/4f, 	1/8f, 	0f,
			0f, 	1/16f, 	1/8f, 	1/16f, 	0f,
			0f, 	0f, 	0f, 	0f, 	0f
	};
	
	BufferedImage original, edgesX1, edgesY1, edgesX2, edgesY2, edgesComposite, edgesComposite1, edgesComposite2, filtered;
	boolean gaussianB = false, cannyB = false, thresholdB = false, imageChanged = true;
	int screenH, screenW;
	String imgName;
	Board b = new Board();
	ControlFrame controls;
	
	public Driver() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenW = gd.getDisplayMode().getWidth();
		screenH = gd.getDisplayMode().getHeight();
		setBounds(301, 0, screenW-300, screenH);
		setTitle("Image: " + imgName);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		controls = new ControlFrame();
		add(b);
	}
	
	public void redraw() {
		BufferedImageOp gaussianFilter = new ConvolveOp(new Kernel(5, 5, matrix));
		BufferedImageOp cannyEdgeX1 = new ConvolveOp(new Kernel(3,3,matrixX1));
		BufferedImageOp cannyEdgeY1 = new ConvolveOp(new Kernel(3,3,matrixY1));	
		BufferedImageOp cannyEdgeX2 = new ConvolveOp(new Kernel(3,3,matrixX2));
		BufferedImageOp cannyEdgeY2 = new ConvolveOp(new Kernel(3,3,matrixY2));
		
		try {
			BufferedImage buff = ImageIO.read(new File(imgName));
			original = new BufferedImage(buff.getWidth(), buff.getHeight(), BufferedImage.TYPE_INT_RGB);
			original.getGraphics().drawImage(buff, 0, 0, null);
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Could not read from " + imgName + ".\n" + ex.getMessage(), "Read Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
		
		BufferedImage temp = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		if(gaussianB) {
			try {
				gaussianFilter.filter(original, temp);
				filtered = temp;
			} catch(ImagingOpException ex) {
				JOptionPane.showMessageDialog(null, "Could not filter " + imgName + ".\n" + ex.getMessage(), "File type error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
			
		} else {
			temp = original;
		}
		
		if(cannyB) {
			try {
				edgesComposite1 = merge(cannyEdgeX1.filter(temp, edgesX1), cannyEdgeY1.filter(temp, edgesY1));
				edgesComposite2 = merge(cannyEdgeX2.filter(temp, edgesX2), cannyEdgeY2.filter(temp, edgesY2));
				edgesComposite = merge(edgesComposite1, edgesComposite2);
				temp = edgesComposite;
			} catch(ImagingOpException ex) {
				JOptionPane.showMessageDialog(null, "Could not filter " + imgName + ".\n" + ex.getMessage(), "File type error", JOptionPane.ERROR_MESSAGE);
				ex.printStackTrace();
			}
		}
		
		if(thresholdB) {
			
		}
		
		printMatricies();
		if(!gaussianB && !cannyB && !thresholdB) temp = original;
		b.setImage(temp);
		this.revalidate();
	}
	
	public BufferedImage merge(BufferedImage img1, BufferedImage img2) {
		int w = Math.max(img1.getWidth(), img2.getWidth());
		int h = Math.max(img1.getHeight(), img2.getHeight());
		BufferedImage merged = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = merged.createGraphics();
		g.drawImage(img1, null, 0, 0);
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f));
		g.drawImage(img2, null, 0, 0);
		g.dispose();
		
		return merged;
	}
	
	public void threshold() {
		// TODO
	}
	
	public void printMatricies() {
		System.out.print("Gaussian: ");
		for(int i=0; i<matrix.length; i++) {
			if(i%5 == 0)System.out.println();
			System.out.print(matrix[i] + ",\t");
		}
		System.out.println();
		
		System.out.print("CannyX1: ");
		for(int i=0; i<matrixX1.length; i++) {
			if(i%3 == 0)System.out.println();
			System.out.print(matrixX1[i] + ",\t");
		}
		System.out.println();
		
		System.out.print("CannyY1: ");
		for(int i=0; i<matrixY1.length; i++) {
			if(i%3 == 0)System.out.println();
			System.out.print(matrixY1[i] + ",\t");
		}
		System.out.println();

		System.out.print("CannyX2: ");
		for(int i=0; i<matrixX2.length; i++) {
			if(i%3 == 0)System.out.println();
			System.out.print(matrixX2[i] + ",\t");
		}
		System.out.println();
		
		System.out.print("CannyY2: ");
		for(int i=0; i<matrixY2.length; i++) {
			if(i%3 == 0)System.out.println();
			System.out.print(matrixY2[i] + ",\t");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		new Driver();
	}
	
	public class ControlFrame extends JFrame implements ActionListener {

		Border blackBorder = new LineBorder(Color.black, 2, true);
		Border redBorder = new LineBorder(Color.red, 2, true);
		Border blueBorder = new LineBorder(Color.blue, 2, true);
		Border greenBorder = new LineBorder(Color.green, 2, true);
		
		JPanel nameP = new JPanel();
			JLabel nameL = new JLabel("Image name: ");
			JTextField nameF = new JTextField(10);
		JPanel midP = new JPanel(new BorderLayout());
		JPanel gaussianP = new JPanel(new BorderLayout());
		TitledBorder gaussianBorder = new TitledBorder(redBorder, "Gaussian Filter");
			Checkbox gaussianC = new Checkbox("Apply");
			JPanel gMatrixP = new JPanel(new GridLayout(5,5));
				JTextField[][] gMatrix = new JTextField[5][5];
		JPanel cannyP = new JPanel(new BorderLayout());
		TitledBorder cannyBorder = new TitledBorder(blueBorder, "Canny Edge Filter");
			Checkbox cannyC = new Checkbox("Apply");
			JPanel cMatrixP = new JPanel(new GridLayout(2,2));
			JPanel cx1MatrixP = new JPanel(new GridLayout(3,3));
			TitledBorder cx1Border = new TitledBorder(blackBorder, "X1");
				JTextField[][] cx1Matrix = new JTextField[3][3];
			JPanel cy1MatrixP = new JPanel(new GridLayout(3,3));
			TitledBorder cy1Border = new TitledBorder(blackBorder, "Y1");
				JTextField[][] cy1Matrix = new JTextField[3][3];
			JPanel cx2MatrixP = new JPanel(new GridLayout(3,3));
			TitledBorder cx2Border = new TitledBorder(blackBorder, "X2");
				JTextField[][] cx2Matrix = new JTextField[3][3];
			JPanel cy2MatrixP = new JPanel(new GridLayout(3,3));
			TitledBorder cy2Border = new TitledBorder(blackBorder, "Y2");
				JTextField[][] cy2Matrix = new JTextField[3][3];
		JPanel thresholdP = new JPanel(new GridLayout(4,1));
		TitledBorder thresholdBorder = new TitledBorder(greenBorder, "Edge threshold");
			Checkbox thresholdC = new Checkbox("Apply");
			JPanel minP = new JPanel();
				JLabel minL = new JLabel(" Minumun threshold: ");
				JTextField minF = new JTextField(3);
			JPanel maxP = new JPanel();
				JLabel maxL = new JLabel("Maximum threshold: ");
				JTextField maxF = new JTextField(3);
			JPanel conP = new JPanel();
				JLabel conL = new JLabel("   Connection radius: ");
				JTextField conF = new JTextField(3);
		JPanel buttonP = new JPanel(new GridLayout(1,2));
			JButton applyB = new JButton("Apply");
			JButton resetB = new JButton("Reset");
			
		public ControlFrame() {
			setBounds(0, 0, 300, 715);
			setTitle("Tools");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			
			add(nameP, BorderLayout.NORTH);
			add(midP, BorderLayout.CENTER);
			add(buttonP, BorderLayout.SOUTH);
			
			nameP.add(nameL);
			nameP.add(nameF);
			
			midP.add(gaussianP, BorderLayout.NORTH);
			midP.add(cannyP, BorderLayout.CENTER);
			midP.add(thresholdP, BorderLayout.SOUTH);
			
			gaussianP.setBorder(gaussianBorder);
			gaussianP.add(gaussianC, BorderLayout.NORTH);
			gaussianP.add(gMatrixP, BorderLayout.CENTER);
				int i=0;
				for(int y=0; y<gMatrix.length; y++) {
					for(int x=0; x<gMatrix[0].length; x++) {
						gMatrix[x][y] = new JTextField();
						gMatrixP.add(gMatrix[x][y]);
						gMatrix[x][y].setText(String.valueOf(matrix[i]));
						i++;
					}
				}
			
			cannyP.setBorder(cannyBorder);
			cannyP.add(cannyC, BorderLayout.NORTH);
			cannyP.add(cMatrixP, BorderLayout.CENTER);
				cMatrixP.add(cx1MatrixP);
				cx1MatrixP.setBorder(cx1Border);
				i=0;
				for(int y=0; y<cx1Matrix.length; y++) {
					for(int x=0; x<cx1Matrix[0].length; x++) {
						cx1Matrix[x][y] = new JTextField();
						cx1MatrixP.add(cx1Matrix[x][y]);
						cx1Matrix[x][y].setText(String.valueOf(matrixX1[i]));
						i++;
					}
				}
				cMatrixP.add(cy1MatrixP);
				cy1MatrixP.setBorder(cy1Border);
				i=0;
				for(int y=0; y<cy1Matrix.length; y++) {
					for(int x=0; x<cy1Matrix[0].length; x++) {
						cy1Matrix[x][y] = new JTextField();
						cy1MatrixP.add(cy1Matrix[x][y]);
						cy1Matrix[x][y].setText(String.valueOf(matrixY1[i]));
						i++;
					}
				}
				cMatrixP.add(cx2MatrixP);
				cx2MatrixP.setBorder(cx2Border);
				i=0;
				for(int y=0; y<cx2Matrix.length; y++) {
					for(int x=0; x<cx2Matrix[0].length; x++) {
						cx2Matrix[x][y] = new JTextField();
						cx2MatrixP.add(cx2Matrix[x][y]);
						cx2Matrix[x][y].setText(String.valueOf(matrixX2[i]));
						i++;
					}
				}
				cMatrixP.add(cy2MatrixP);
				cy2MatrixP.setBorder(cy2Border);
				i=0;
				for(int y=0; y<cy2Matrix.length; y++) {
					for(int x=0; x<cy2Matrix[0].length; x++) {
						cy2Matrix[x][y] = new JTextField();
						cy2MatrixP.add(cy2Matrix[x][y]);
						cy2Matrix[x][y].setText(String.valueOf(matrixY2[i]));
						i++;
					}
				}
			
			thresholdP.setBorder(thresholdBorder);
			thresholdP.add(thresholdC);
			thresholdP.add(minP);
				minP.add(minL);
				minP.add(minF);
			thresholdP.add(maxP);
				maxP.add(maxL);
				maxP.add(maxF);
			thresholdP.add(conP);				
				conP.add(conL);
				conP.add(conF);
			
			buttonP.add(resetB);
				resetB.addActionListener(this);
			buttonP.add(applyB);
				applyB.addActionListener(this);
				
			revalidate();
		}

		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == applyB) {
				gaussianB = gaussianC.getState();
				cannyB = cannyC.getState();
				thresholdB = thresholdC.getState();
				
				imgName = nameF.getText();
				
				try { // Updates Gaussian Matrix
					int i=0;
					for(int y=0; y<gMatrix.length; y++) {
						for(int x=0; x<gMatrix[0].length; x++) {
							matrix[i] = Float.parseFloat(gMatrix[x][y].getText());
							i++;
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Gaussian matrix input must be numeric.", "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				
				try { // Updates Canny X1 Matrix
					int i=0;
					for(int y=0; y<cx1Matrix.length; y++) {
						for(int x=0; x<cx1Matrix[0].length; x++) {
							matrixX1[i] = Float.parseFloat(cx1Matrix[x][y].getText());
							i++;
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Canny X matrix input must be numeric.", "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				
				try { // Updates Canny Y1 Matrix
					int i=0;
					for(int y=0; y<cx1Matrix.length; y++) {
						for(int x=0; x<cx1Matrix[0].length; x++) {
							matrixY1[i] = Float.parseFloat(cy1Matrix[x][y].getText());
							i++;
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Canny Y matrix input must be numeric.", "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				
				try { // Updates Canny X2 Matrix
					int i=0;
					for(int y=0; y<cx2Matrix.length; y++) {
						for(int x=0; x<cx2Matrix[0].length; x++) {
							matrixX2[i] = Float.parseFloat(cx2Matrix[x][y].getText());
							i++;
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Canny X matrix input must be numeric.", "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				
				try { // Updates Canny Y2 Matrix
					int i=0;
					for(int y=0; y<cx2Matrix.length; y++) {
						for(int x=0; x<cx2Matrix[0].length; x++) {
							matrixY2[i] = Float.parseFloat(cy2Matrix[x][y].getText());
							i++;
						}
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Canny Y matrix input must be numeric.", "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				redraw();
				
			} else if(e.getSource() == resetB) {
				
				nameF.setText(imgName);
				
				int i=0;
				for(int y=0; y<gMatrix.length; y++) { // reset gaussian fields
					for(int x=0; x<gMatrix[0].length; x++) {
						gMatrix[x][y].setText(String.valueOf(matrix[i]));
						i++;
					}
				}
			
				i=0;
				for(int y=0; y<cx1Matrix.length; y++) { // reset canny x1 fields
					for(int x=0; x<cx1Matrix[0].length; x++) {
						cx1Matrix[x][y].setText(String.valueOf(matrixX1[i]));
						i++;
					}
				}
				
				i=0;
				for(int y=0; y<cy1Matrix.length; y++) { // reset canny y1 fields
					for(int x=0; x<cy1Matrix[0].length; x++) {
						cy1Matrix[x][y].setText(String.valueOf(matrixY1[i]));
						i++;
					}
				}
				
				i=0;
				for(int y=0; y<cx2Matrix.length; y++) { // reset canny x2 fields
					for(int x=0; x<cx2Matrix[0].length; x++) {
						cx2Matrix[x][y].setText(String.valueOf(matrixX2[i]));
						i++;
					}
				}
				
				i=0;
				for(int y=0; y<cy2Matrix.length; y++) { // reset canny y2 fields
					for(int x=0; x<cy2Matrix[0].length; x++) {
						cy2Matrix[x][y].setText(String.valueOf(matrixY2[i]));
						i++;
					}
				}
			}
		}
	}
}