package gaussianFilter;

import javax.swing.JOptionPane;

/**Gaussian filters smooth and remove noise from images. They do this by taking a weighted average of the points surrounding the pixel being modified.
 * The filter weights each pixel based upon the discrete values of a bell curve.
 * 
 * @author Nick
 *
 */
public class GaussianFilter extends Filter{
	
	private double a;
	
	/**Creates a blank guassian filter.
	 * 
	 */
	public GaussianFilter() {
		
	}
	
	/**Creates a blank guassian filter.
	 * 
	 */
	public GaussianFilter(String name) {
		super(Integer.parseInt(JOptionPane.showInputDialog("Dimension of " + name + ": ")));
		a = Double.parseDouble(JOptionPane.showInputDialog("Standard deviation of " + name + ": "));
		recalculate();
	}
	
	/**Creates a new square gaussian filter which can be used to smooth or remove noise from images.
	 * By changing the standard deviation and dimension the filter will smooth more or less.
	 * Smaller standard deviation constants and smaller dimensions will smooth an image less than large constants and large dimmensions.
	 * 
	 * @param dimension 			square dimension of the filter
	 * @param deviationConstant		constant of standard deviation
	 */
	public GaussianFilter(int dimension, double deviationConstant) {
		super(dimension);
		a = deviationConstant;
		values = new float[x][y];
		recalculate();
	}
	
	/**Creates a new rectangular gaussian filter which can be used to smooth or remove noise from images.
	 * By changing the standard deviation and dimension the filter will smooth more or less.
	 * Smaller standard deviation constants and smaller dimensions will smooth an image less than large constants and large dimmensions.
	 * @param x1					width of filter
	 * @param y1					height of filter
	 * @param deviationConstant		standard deviation of the filter
	 */
	public GaussianFilter(int x1, int y1, double deviationConstant) {
		super(x1, y1);
		a = deviationConstant;
		values = new float[x][y];
		recalculate();
	}
	
	public void recalculate() {
		for (int y = 0; y < values.length; y++) {
			for (int x = 0; x < values[0].length; x++) {
				values[x][y] = (float) ((1/(2*Math.PI*Math.pow(a,2)))*Math.pow(Math.E, -((Math.pow(x-(this.x/2), 2)+Math.pow(y-(this.y/2), 2))/(2*Math.pow(a, 2)))));
				//			     (1/2PIa^2)								e^			-x^2						+y^2			/			2a^2
				total += values[x][y];
			}
		}
	}
	
	public double getDeviation() {
		return a;
	}

	
	public void setDeviation(double a) {
		this.a = a;
	}
}
