package pictureUtils;

import android.graphics.Bitmap;

/**
 * @author Sage and Nick 
 *	This class generates values for Gaussian filter using the deviation constant and size
 *	then gives user access to filter math to apply a filter
 */
public class GaussianFilter extends Filter{
	
	/**Creates a Gaussian filter of desired size with desired deviation constant and filters it
	 * @param bitmap filter is to be applied to
	 * @param deviationConstant that the filter should use
	 * @param filterSize size of the filter
	 */
	
	public GaussianFilter(Bitmap bitmap, double deviationConstant, int filterSize) {
		super(calculateValues(deviationConstant, filterSize), bitmap);
	}
	
	/**Generates the values to be used in the Gaussian filter using the desired values from the constructor
	 * @param deviationConstant that filter should use taken in constructor
	 * @param filterSize size of the filter taken in constructor
	 * @return
	 */
	private static double[][] calculateValues(double deviationConstant, int filterSize){
		double[][] values = new double[filterSize][filterSize];
		
		for (int y = 0; y < values.length; y++) {
			for (int x = 0; x < values[0].length; x++) {
				values[x][y] = (float) ((1/(2*Math.PI*Math.pow(deviationConstant,2)))*Math.pow(Math.E, -((Math.pow(x-(values.length/2), 2)+Math.pow(y-(values.length/2), 2))/(2*Math.pow(deviationConstant, 2)))));
				//			     (1/2PIa^2)								e^			-x^2						+y^2			/			2a^2
			}
		}
		
		return values;
	}
	
}
