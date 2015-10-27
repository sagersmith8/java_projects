import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class GaussianFilter{
	
	BufferedImage temp, img;
	float [] matrix = new float[]{
		   1/16f, 1/8f, 1/16f, 
		   1/8f, 1/4f, 1/8f, 
		   1/16f, 1/8f, 1/16f, 
	};
	
	public GaussianFilter(String img){
		try {
			this.temp = ImageIO.read(new File(img));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.img = new BufferedImage(this.temp.getWidth(), this.temp.getHeight(), BufferedImage.TYPE_INT_RGB);
		filter();
	}
	
	public GaussianFilter(BufferedImage img){
		this.temp = img;
		this.img = new BufferedImage(this.temp.getWidth(), this.temp.getHeight(), BufferedImage.TYPE_INT_RGB);
		filter();
	}
	
	
	public void filter(){
		BufferedImageOp op = new ConvolveOp(new Kernel(3,3,matrix));
		op.filter(temp, img);
	}
}
