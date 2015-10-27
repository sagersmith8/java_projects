package pictureUtils;

import android.graphics.Bitmap;

/**
 * @author Sage
 *	This class needs to be fixed up
 */
public class CannyEdgeDetection {
	
	private Bitmap filteredImage;
	
	/**This class runs image through gaussian filter then detects edges in bothe the x and y directions 
	 * needs to do better
	 * @param src Whose edges to be detected
	 * @param gaussianDeviationConstant 
	 * @param gaussianSize
	 * @deprecated this class needs to be made better, just in developmental stages
	 */
	public CannyEdgeDetection(Bitmap src, double gaussianDeviationConstant, int gaussianSize) {
		filteredImage = new CannyEdgeDetectorY(new CannyEdgeDetectorX(new GaussianFilter(src, gaussianDeviationConstant, gaussianSize).getFilteredImage()).getFilteredImage()).getFilteredImage();
	}
	
	/**
	 * @return filteredImage image with edges in both x and y directions detected
	 */
	public Bitmap getFilteredImage(){
		return filteredImage;
	}
}
