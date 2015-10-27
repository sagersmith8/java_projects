package model;

import java.util.ArrayList;

import javax.media.opengl.GL;
import javax.media.opengl.GLDrawable;

public class TestImport
{
	ObjLoad ol = new ObjLoad();
	ArrayList<Float> triangles = ol.parseTriangles("models/The DarkNight.obj");
	
	public TestImport()
	{
		glBegin(GL.GL_TRIANGLES);
		for (int i = 0; i < triangles.size()-3; i+=3) 
		{
			glVertex3f(triangles.get(i), triangles.get(i+1), triangles.get(i+2));
		}
		glEnd();
	}
	public static void main(String[] args) 
	{
		new TestImport();
	}

}
