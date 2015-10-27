import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class CannyEdgeDetection{

	/**
	 * @param args
	 */

	float [] matrixX = new float[]{
			-1f, 0f, 1f, 
			-1f, 0f, 1f, 
			-1f, 0f, 1f, 
	};

	float [] matrixY = new float[]{
			-1f, -1f, -1f, 
			0f, 0f, 0f, 
			1f, 1f, 1f, 
	};

	BufferedImage img, temp;

	public CannyEdgeDetection(String img, String xy){
		try 
		{
			this.temp = ImageIO.read(new File(img));
		}

		catch (IOException e) {
			System.err.println("Could nor read from " + img + ":\n" + e.getMessage());
			e.printStackTrace();
		}
		temp = new GaussianFilter(temp).img;
		this.img = new BufferedImage(this.temp.getWidth(), this.temp.getHeight(), BufferedImage.TYPE_INT_RGB);
		filter(xy);
	}

	public CannyEdgeDetection(BufferedImage img, String xy){
		this.temp = img;
		temp = new GaussianFilter(temp).img;
		this.img = new BufferedImage(this.temp.getWidth(), this.temp.getHeight(), BufferedImage.TYPE_INT_RGB);
		filter(xy);
	}

	public void filter(String xy){
		if(xy.equalsIgnoreCase("X")){
			BufferedImageOp op = new ConvolveOp(new Kernel(3,3,matrixX));
			op.filter(temp, img);
		}

		else if(xy.equalsIgnoreCase("Y")){
			BufferedImageOp op = new ConvolveOp(new Kernel(3,3,matrixX));
			op.filter(temp, img);
		}
	}
}
