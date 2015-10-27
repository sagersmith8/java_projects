package gaussianFilter;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Filter {
	
	protected float[][] values = null;
	protected int x;
	protected int y;
	protected float total;
	
	/**Creates a blank filter
	 */
	public Filter() {
		
	}
	
	/**Creates a rectangular filter used for modifying images
	 * 
	 * @param x		width of filter
	 * @param y		height of filter
	 */
	public Filter(int x, int y) {
		this.x = x;
		this.y = y;
	}
	

	/**Creates a square filter used to modify images
	 * 
	 * @param dimmension	square size of filter
	 */
	public Filter(int dimension) {
		x = dimension;
		y = dimension;
	}
	
	
	public float[][] getValues() {
		return values;
	}

	public void setValues(float[][] values) {
		this.values = values;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	/**Returns the side length of a square filter. Throws <code>DimmensionException</code> if filter is not square.
	 * @return the dimension of a square filter
	 * @throws DimmensionException
	 */
	public int getDimension() throws DimmensionException {
		if(x == y) {
			return x;
		} else {
			throw new DimmensionException();
		}
	}
	
	/**Changes a two dimensional array into a single dimension in row major order
	 * 
	 * @param a		array to be converted
	 * @return		single dimension representation of <code>a</code>
	 */
	public float[] toSingle(float[][] a) {
		float[] b = new float[a.length*a[0].length];
		int pointer = 0;
		
		for(int i=0; i<a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				b[pointer] = a[j][i];
				pointer++;
			}
		}
		
		return b;
	}
	
	/**
	 * @return the total of all values in the filter
	 */
	public float getTotal() {
		return total;
	}
	
	public void editValues() {
		new ValueEditor(values);
	}
	
	/**
	 * @param imageName		file name of the image to be filtered
	 * @return 				the filtered <code>BufferedImage</code>
	 * @throws IOException	if the image cannot be found
	 */
	public BufferedImage filterImage(String imageName) throws IOException {
		BufferedImage original = newImage(imageName);
		BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
		filter(original, result);
		return result;
	}
	
	/**
	 * @param imageName		file name of the image to be filtered
	 * @return 				the filtered <code>BufferedImage</code>
	 */
	public BufferedImage filterImage(BufferedImage image) {
		BufferedImage original = image;
		BufferedImage result = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_RGB);
		filter(original, result);
		return result;
	}
	
	/**Creates a filtered copy of a <code>BufferedImage</code>
	 * 
	 * @param original	the image to be filtered
	 * @param result	the destination of the filtered image
	 */
	private void filter(BufferedImage original, BufferedImage result) {
		if(this.values == null) {
			throw new IllegalArgumentException("Values cannot be null");
		}
		BufferedImageOp op = new ConvolveOp(new Kernel(3,3,this.toSingle(values)));
		op.filter(original, result);
	}
	
	private BufferedImage newImage(String imgName){
		try {
			return ImageIO.read(new File(imgName));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Could not find or load " + imgName + ".", "Read/Write Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
}
