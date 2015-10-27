package gaussianFilter;

/**Median filters smooth images and reduce ambient noise in an image.
 * The filter compares the values of the pixels around the pixel to be modified and takes the median value.
 * 
 * @author Nick
 *
 */
public class MedianFilter extends Filter {
	/**Creates a blank filter
	 */
	public MedianFilter() {
		super();
	}
	
	/**Creates a new square median filter.
	 * 
	 * @param dimension		the square dimension of the filter to be created
	 */
	public MedianFilter(int dimension) {
		super(dimension);
	}
	
	/**Creates a new rectangular median filter.
	 * @param x		width of the filter
	 * @param y		height of the filter
	 */
	public MedianFilter(int x, int y) {
		super(x, y);
	}
}
