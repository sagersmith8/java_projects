package pictureUtils;

import android.graphics.Bitmap;
import android.graphics.Color;



/**
 * @author Sage
 * This class is really a convolution matrix with associated math for applying a 
 * filter to an object
 */
public class Filter{
	private double[][] matrix;
	int size, factor = 1, offset=1;
	Bitmap src;
	private Bitmap filteredImage;
	
	/**Filters fed in image with fed in filter
	 * @param config double[][] matrix of filter to be applied
	 * @param src Bitmap image you want to apply the filter to
	 */
	public Filter(double[][] config, Bitmap src) {
		matrix = config;
		size = matrix.length;
		this.src = src;
		filteredImage =applyFilter();
	}

	/**ConvolutionMatrix math for applying a matrix to a bitmap
	 * @return Bitmap sent in to constructor, with matrix sent into constructor
	 */
	public Bitmap applyFilter() {
		Bitmap result = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
		int alpha=0, red=0, green=0, blue=0;
		int sumR=0, sumG=0, sumB=0;
		
		int[][] pixels = new int[size][size];

		for(int y = 0; y < src.getHeight() - 2; y++) {
			for(int x = 0; x < src.getWidth() - 2; x++) {
				
				// get pixel matrix
				for(int i = 0; i < size; i++) {
					for(int j = 0; j < size; j++) {
						pixels[i][j] = src.getPixel(x + i, y + j);
					}
				}

				// get alpha of center pixel
				alpha = Color.alpha(pixels[1][1]);

				// get sum of RGB on matrix
				for(int i = 0; i < size; i++) {
					for(int j = 0; j < size; j++) {
						sumR += (Color.red(pixels[i][j]) * matrix[i][j]);
						sumG += (Color.green(pixels[i][j]) * matrix[i][j]);
						sumB += (Color.blue(pixels[i][j]) * matrix[i][j]);
					}
				}

				// get final Red
				red = (int)(sumR / factor + offset);
				
				if(red < 0){
					red = 0; 
				}
				else if(red > 255)
				{ 
					red = 255; 
				}

				// get final Green
				green = (int)(sumG / factor + offset);
				if(green < 0) 
				{ 
					green = 0; 
				}
				
				else if(green > 255)
				{ 
					green = 255; 
				}

				// get final Blue
				blue = (int)(sumB /factor + offset);
				
				if(blue < 0) 
				{
					blue = 0;
				}
				
				else if(blue > 255)
				{ 
					blue = 255; 
				}

				// apply new pixel
				result.setPixel(x + 1, y + 1, Color.argb(alpha, red, green, blue));
			}
		}

		// final image
		return result;
	}

	/**Returns image filtered in constructor;
	 * @return filteredImage
	 */
	public Bitmap getFilteredImage() {
		return filteredImage;
	}
}
