import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 * 
 * 
 * 
 */
public class OpenGL 
{
	float x=26.6f,y=0,z=-12.6f,xRot= -68.6f,yRot = 90.5f;
	String windowTitle = "Batman";
	public boolean closeRequested = false;
	ObjLoad objLoad = new ObjLoad("models/Cape.obj");
	long lastFrameTime; // used to calculate delta
	float batmanAngle;
	
	public void run() 
	{
		createWindow();
		getDelta(); // Initialise delta timer
		initGL();
		
		while (!closeRequested) 
		{
			pollInput();
			updateLogic(getDelta());
			renderGL();
			if (Display.isCloseRequested()) 
			{
				Display.destroy();
			}
			Display.update();
		}
		
		cleanup();
	}
	
	private void initGL() {

		/* OpenGL */
		int width = Display.getDisplayMode().getWidth();
		int height = Display.getDisplayMode().getHeight();

		GL11.glViewport(0, 0, width, height); // Reset The Current Viewport
		GL11.glMatrixMode(GL11.GL_PROJECTION); // Select The Projection Matrix
		GL11.glLoadIdentity(); // Reset The Projection Matrix
		GL11.glMatrixMode(GL11.GL_MODELVIEW); // Select The Modelview Matrix
		GL11.glLoadIdentity(); // Reset The Modelview Matrix

		GL11.glShadeModel(GL11.GL_SMOOTH); // Enables Smooth Shading
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Black Background
		GL11.glClearDepth(1.0f); // Depth Buffer Setup
		GL11.glEnable(GL11.GL_DEPTH_TEST); // Enables Depth Testing
		GL11.glDepthFunc(GL11.GL_LEQUAL); // The Type Of Depth Test To Do
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST); // Really Nice Perspective Calculations

	}
	
	private void updateLogic(int delta)
	{
		batmanAngle += 0.1f * delta; 
	}


	private void renderGL() 
	{
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); 
		GL11.glLoadIdentity(); 
		GL11.glTranslatef(x,y,z); 
		GL11.glRotatef(yRot, 0.0f, 1.0f, 0.0f);
		GL11.glRotated(xRot, 1.0f, 0f, 0f);
		GL11.glBegin(GL11.GL_POLYGON);
		GL11.glColor3f(1f, 0, 0);
		for(int i = 0; i < objLoad.x().size();i++)
		{
			GL11.glVertex3f(objLoad.x().get(i),objLoad.y().get(i),objLoad.z().get(i));
		}
		GL11.glEnd();
	}

	/**
	 * Poll Input
	 */
	public void pollInput() 
	{
		Keyboard.enableRepeatEvents(true);
		// scroll through key events
		while (Keyboard.next()) 
		{
			if (Keyboard.getEventKeyState()) 
			{
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
				{
					closeRequested = true;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_UP)
				{
					yRot+=.1f;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_DOWN)
				{
					yRot-=.1f;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_LEFT)
				{
					xRot-=.1f;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT)
				{
					xRot+=.1f;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_A)
				{
					x-=.1f;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_D)
				{
					x+=.1f;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_W)
				{
					z+=.1f;
				}
				if (Keyboard.getEventKey() == Keyboard.KEY_X)
				{
					z-=.1f;
				}
				System.out.println(x + " " + xRot);
				System.out.println(z+ " " + yRot);
			}
		}
	}
	
	/** 
	 * Calculate how many milliseconds have passed 
	 * since last frame.
	 * 
	 * @return milliseconds passed since last frame 
	 */
	public int getDelta() 
	{
	    long time = (Sys.getTime() * 1000) / Sys.getTimerResolution();
	    int delta = (int) (time - lastFrameTime);
	    lastFrameTime = time;
	 
	    return delta;
	}

	private void createWindow() 
	{
		try 
		{
			Display.setDisplayMode(new DisplayMode(640, 480));
			Display.setVSyncEnabled(true);
			Display.setTitle(windowTitle);
			Display.create();
		} 
		catch (LWJGLException e) 
		{
			Sys.alert("Error", "Initialization failed!\n\n" + e.getMessage());
			System.exit(0);
		}
	}
	
	/**
	 * Destroy and clean up resources
	 */
	private void cleanup() 
	{
		Display.destroy();
	}
	
	public static void main(String[] args) 
	{
		OpenGL lesson = new OpenGL();
		lesson.run();
	}
}
