package main;
import gaussianFilter.Filter;
import gaussianFilter.GaussianFilter;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class CannyEdgeDetection{

	float [] matrixX = new float[]{ 
			   -1f, 0f, 1f, 
			   -2f, 0f, 2f, 
			   -1f, 0f, 1f, 
	};
	
	float [] matrixY = new float[]{
			   -1f, -2f, -1f, 
			   0f, 0f, 0f, 
			   1f, 2f, 1f, 
	};
	
	float [] matrixX2 = new float[]{ 
			   1f, 0f, -1f, 
			   2f, 0f, -2f, 
			   1f, 0f, -1f, 
	};
	
	float [] matrixY2 = new float[]{
			   1f, 2f, 1f, 
			   0f, 0f, 0f, 
			   -1f, -2f, -1f, 
	};
	
	BufferedImage img, temp;
	GaussianFilter f;
	
	public CannyEdgeDetection(String img, String xy, Filter f){
		this.f = (GaussianFilter)f;
		try 
		{
			temp = ImageIO.read(new File(img));
		}
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	try {
			//temp = f.filterImage(img);
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
		this.img = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_RGB);
		filter(xy);
	}
	
	public CannyEdgeDetection(BufferedImage img, String xy, Filter f){
		this.f = (GaussianFilter) f;
		temp = img;
		//temp = f.filterImage(img);
		this.img = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_RGB);
		filter(xy);
	}
	
	public void filter(String xy){
		if(xy.equalsIgnoreCase("X")){
			BufferedImageOp op = new ConvolveOp(new Kernel(3,3,matrixX));
			op.filter(temp, img);
		}
		
		else if(xy.equalsIgnoreCase("Y")){
			BufferedImageOp op = new ConvolveOp(new Kernel(3,3,matrixY));
			op.filter(temp, img);
		} else if (xy.equalsIgnoreCase("A")){
			BufferedImage shit = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_RGB);
			BufferedImageOp op1 = new ConvolveOp(new Kernel(3,3,matrixY));
			BufferedImageOp op2 = new ConvolveOp(new Kernel(3,3,matrixX));
			op1.filter(temp, shit);
			op2.filter(shit, img);
			
		} else if (xy.equalsIgnoreCase("A")){
			BufferedImage[] shit = new BufferedImage[3];
			for (int i=0; i<shit.length; i++) {
				shit[i] = new BufferedImage(temp.getWidth(), temp.getHeight(), BufferedImage.TYPE_INT_RGB);
			}
			BufferedImageOp op1 = new ConvolveOp(new Kernel(3,3,matrixY));
			BufferedImageOp op2 = new ConvolveOp(new Kernel(3,3,matrixY2));
			BufferedImageOp op3 = new ConvolveOp(new Kernel(3,3,matrixX));
			BufferedImageOp op4 = new ConvolveOp(new Kernel(3,3,matrixX2));
			op1.filter(temp, shit[0]);
			op2.filter(shit[0], shit[1]);
			op3.filter(shit[1], shit[2]);
			op4.filter(shit[2], img);
		}
	}
}
