package mario;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MarioComponents
{
	final static int yellow 	= 	-3584;
	final static int purple 	= 	-6075996;
	final static int lightBrown = 	-4621737;
	final static int darkBrown 	= 	-7864299;
	final static int lightGreen = 	-4856291;
	final static int darkGreen 	= 	-14503604;
	final static int lightGray 	= 	-3947581;
	final static int darkGrey 	= 	-8421505;
	final static int darkBlue 	= 	-12629812;
	final static int lightBlue 	= 	-16735512;
	final static int red 		= 	-1237980;
	
	static BufferedImage castle1, flag, flagPole, starFlag; 
	
	public static void readImages()
	{
		try
		{
			castle1 = ImageIO.read(new File("Castle1.png"));
			flag = ImageIO.read(new File("Flag.png"));
			flagPole = ImageIO.read(new File("FlagPole.png"));
			starFlag = ImageIO.read(new File("StarFlag.png"));
		}
		catch(IOException ioe)
		{
			System.err.println("Could not read images: ");
		}
	}
	public static void brick(int x1, int y1, BufferedImage buf)
	{
		for(int x = x1; x< x1+20; x++)
		{
			for(int y = y1+20; y> y1; y--)
			{
				if(x%6 == 0 || y%4 == 0)
				{
					buf.setRGB(x, y, lightBrown);
				}
				else
				{
					buf.setRGB(x, y, darkBrown);
				}
			}
		}
	}
	public static void blockRed(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int x = x1; x< x1+20; x++)
		{
			for(int y = y1+20; y> y1; y--)
			{
				buf.setRGB(x, y,red);
			}
		}
	}
	public static void blockYellow(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int x = x1; x< x1+20; x++)
		{
			for(int y = y1+20; y> y1; y--)
			{
				buf.setRGB(x, y,yellow);
			}
		}
	}
	public static void blockLBrown(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int i = x1; i< x1+20; i++)
		{
			for(int j = y1+20; j> y1; j--)
			{
				buf.setRGB(i, j, lightBrown);
			}
		}
	}
	public static void blockDBrown(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int i = x1; i< x1+20; i++)
		{
			for(int j = y1+20; j> y1; j--)
			{
				buf.setRGB(i, j, darkBrown);
			}
		}
	}
	public static void blockDGray(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int i = x1; i< x1+20; i++)
		{
			for(int j = y1+20; j> y1; j--)
			{
				buf.setRGB(i, j, lightBrown);
			}
		}
	}
	public static void blockLBlue(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int i = x1; i< x1+20; i++)
		{
			for(int j = y1+20; j>y1; j--)
			{
				buf.setRGB(j,i, lightBlue);
			}
		}
	}
	public static void blockPurple(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int i = x1; i< x1+20; i++)
		{
			for(int j = y1; j< y1+20; j++)
			{
				buf.setRGB(i,j, purple);
			}
		}
	}
	public static void blockDBlue(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int i = x1; i< x1+20; i++)
		{
			for(int j = y1; j< y1+20; j++)
			{
				buf.setRGB(i,j, darkBlue);
			}
		}
	}
	public static void blockLGray(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int i = x1; i< x1+20; i++)
		{
			for(int j = y1; j< y1+20; j++)
			{
				buf.setRGB(i,j, lightGray);
			}
		}
	}
	public static void blockLGreen(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int i = x1; i< x1+20; i++)
		{
			for(int j = y1; j< y1+20; j++)
			{
				buf.setRGB(i,j, lightGreen);
			}
		}
	}
	public static void blockDGreen(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int i = x1; i< x1+20; i++)
		{
			for(int j = y1+20; j> y1; j--)
			{
				buf.setRGB(i, j, darkGreen);
			}
		}
	}
	public static void tube(int x1, int y1, BufferedImage buf) throws ArrayIndexOutOfBoundsException
	{
		for(int i = x1-20; i< x1+20; i++)
		{
			for(int j = y1+1; j<y1+4 ; j++)
			{
				buf.setRGB(i, j, darkGreen);
			}
		}
		for(int i = x1-15; i< x1+15; i++)
		{
			for(int j = y1+1; j<y1+25 ; j++)
			{
				if(i == x1-15 || i == x1+15)
					buf.setRGB(i, j, lightGreen);
				else
					buf.setRGB(i, j, darkGreen);
			}
		}
	}
	
	public static void castle1(int x1, int y1, BufferedImage buf)
	{
		readImages();
		for(int i = x1-65; i< x1+64; i++)
		{
			for(int j = y1+1; j<y1+146 ; j++)
			{
				buf.setRGB(i, j, castle1.getRGB(i,j));
			}
		}
	}
	
	public  static void flagPole(int x1, int y1, BufferedImage buf)
	{
			readImages();
			for(int i = 0; i< x1+13; i++)
			{
				for(int j = y1+1; j<y1+199 ; j++)
				{
					buf.setRGB(i, j, flagPole.getRGB(i,j));
				}
			}
	}
	
	public static void flag(int x1, int y1, BufferedImage buf)
	{
		readImages();
		for(int i = x1-32; i< x1; i++)
		{
			for(int j = 0; j<y1+32 ; j++)
			{
				buf.setRGB(i, j, flagPole.getRGB(i,j));
			}
		}
	}
}
