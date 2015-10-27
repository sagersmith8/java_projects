package gaussianFilter;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GaussianValues extends JFrame {
	
	int x=1, y=1; // height and width of the map
	double a=1;// standard deviation of the curve
	JTextField[][] value;
	double[][] v;

	public GaussianValues() throws HeadlessException {
		x = Integer.parseInt(JOptionPane.showInputDialog("Enter the x value of your picture:"));
		y = Integer.parseInt(JOptionPane.showInputDialog("Enter the y value of your picture:"));
		a = Double.parseDouble(JOptionPane.showInputDialog("Enter the standard deviation constant:"));
		
		setBounds(100,100,250,250);
		setTitle("Values of a gausian filter for specified size");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(y,x,10,10));
		setVisible(true);
		
		v = new double[x][y];
		for (int y = 0; y < v.length; y++) {
			for (int x = 0; x < v[0].length; x++) {
				v[x][y] = (1/(2*Math.PI*Math.pow(a,2)))*Math.pow(Math.E, -((Math.pow(x-(this.x/2), 2)+Math.pow(y-(this.y/2), 2))/(2*Math.pow(a, 2))));
				//			     (1/2PIa^2)								e^			-x^2						+y^2			/			2a^2
			}
		}
		
		// scale the values so the corners are 1
		double k = 1/v[0][0];
		System.out.println("K: " + k);
		
		// scales color values to start at black
		double h = 255/(v[this.x/2][this.y/2]*k);
		System.out.println("H: " + h);
		
		value = new JTextField[x][y];
		for (int y = 0; y < value.length; y++) {
			for (int x = 0; x < value[0].length; x++) {
				value[x][y] = new JTextField();
				add(value[x][y]);
				value[x][y].setText(String.valueOf(Math.round(v[x][y] * k)));
				value[x][y].setBackground(new Color((int)(255-h*v[x][y] * k), (int)(255-h*v[x][y] * k), (int)(255-h*v[x][y] * k)));
				value[x][y].setEditable(false);
			}
		}
		revalidate();
	}

	public static void main(String[] args) {
		new GaussianValues();
	}

}
