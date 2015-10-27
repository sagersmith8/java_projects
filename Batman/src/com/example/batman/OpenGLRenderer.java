package com.example.batman;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.EGLConfig;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

class OpenGLRenderer implements Renderer 
{
	float x = 0;
    private Batman batman = new Batman();
    private float batmanRotation;
    @Override
    public void onDrawFrame(GL10 gl)
    {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);        
        gl.glLoadIdentity();        
        gl.glTranslatef(0.0f, 0.0f, -6.0f);
        batman.draw(gl);
        gl.glRotatef(batmanRotation, 1.0f, 1.0f, 1.0f);
        gl.glLoadIdentity();                                       
        batmanRotation -= 0.15f; 
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height)
    {
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        GLU.gluPerspective(gl, 45.0f, (float)width / (float)height, 0.1f, 100.0f);
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

	@Override
	public void onSurfaceCreated(GL10 gl,javax.microedition.khronos.egl.EGLConfig config) 
	{
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);  
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_NICEST);		
	}
}