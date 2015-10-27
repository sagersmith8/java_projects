package pictureUtils;

import android.graphics.Bitmap;

/**
 * @author Sage
 *	This class detects edges on images in y direction
 */
public class CannyEdgeDetectorY extends Filter {
	static double[][] yDirectionMatrix ={	{1,2,1},
											{0,0,0},
											{-1,-2,-1}
										};
	/**Detects edges of desired image
	 * @param config 
	 * @param src Bitmap image to detect image of
	 * @param gaussianDeviationConstant
	 * @param gaussianSize
	 */
	public CannyEdgeDetectorY(Bitmap src) {
		super(yDirectionMatrix, src);	
	}
}