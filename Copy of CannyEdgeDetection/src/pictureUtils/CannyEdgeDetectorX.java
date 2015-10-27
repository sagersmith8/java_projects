package pictureUtils;

import android.graphics.Bitmap;

/**
 * @author Sage
 *	This class detects edges on images in x direction
 */
public class CannyEdgeDetectorX extends Filter {
	static double[][] xDirectionMatrix ={	{-1,0,1},
											{-2,0,2},
											{-1,0,1}
								 		};

	/**Detects edges of desired image
	 * @param config 
	 * @param src Bitmap image to detect image of
	 * @param gaussianDeviationConstant
	 * @param gaussianSize
	 */
	public CannyEdgeDetectorX(Bitmap src) {
		super(xDirectionMatrix, src);	
	}
}
