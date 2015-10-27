package com.example.MusicGame;

import android.app.Activity;
import android.media.audiofx.Visualizer;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends Activity implements GLSurfaceView.Renderer, Visualizer.OnDataCaptureListener,View.OnTouchListener {
    SurfaceView glView;
    private Visualizer visualizer = new Visualizer(0);
    int numTowers = 10;
    Elf elf = new Elf();
    Tower[] towers = new Tower[numTowers];
    float []y = new float[numTowers];
    MotionEvent lastEvent = null;
    public float initx = 2.0f;
    public float inity = 2.0f;
    public Point _touchingPoint = new Point(-.5f,2.75f);
    public Point _pointerPosition = new Point(-.5f,2.75f);
    boolean dragging = false;
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){

       if(motionEvent == null && lastEvent == null){

       }

       else if(motionEvent == null && lastEvent !=null){
           motionEvent = lastEvent;
       }

       else{
           lastEvent = motionEvent;
       }

       if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
           dragging = true;
       }

       else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
           dragging = false;
       }

       if(dragging){
           Log.e("Dragging", "Dragging");
           // get the pos
           _touchingPoint.x = motionEvent.getX();
           _touchingPoint.y = motionEvent.getY();

           // bound to a box
           if( _touchingPoint.x < 400){
               _touchingPoint.x = 400;
           }
           if ( _touchingPoint.x > 450){
               _touchingPoint.x = 450;
           }
           if (_touchingPoint.y < 240){
               _touchingPoint.y = 240;
           }
           if ( _touchingPoint.y > 290 ){
               _touchingPoint.y = 290;
           }


           //get the angle
           double angle = Math.atan2(_touchingPoint.y - inity,_touchingPoint.x - initx)/(Math.PI/180);

           // Move the beetle in proportion to how far
           // the joystick is dragged from its center
           _pointerPosition.y += Math.sin(angle*(Math.PI/180))*(_touchingPoint.y/10000);
           _pointerPosition.x += Math.cos(angle*(Math.PI/180))*(_touchingPoint.x/10000);

           //make the pointer go thru
           if ( _pointerPosition.x > 2.0f)
           {
               _pointerPosition.x= -2.0f;
           }

           if ( _pointerPosition.x < -2.0f){
               _pointerPosition.x = 2.0f;
           }

           if (_pointerPosition.y > 3.0f ){
               _pointerPosition.y = 2.25f;
           }
           if (_pointerPosition.y < 2.25f){
               _pointerPosition.y = 3.0f;
           }
       }

       else if(!dragging) {
           // Snap back to center when the joystick is released
           _touchingPoint.x = (int) initx;
           _touchingPoint.y = (int) inity;
           //shaft.alpha = 0;
       }

      // Log.e("Loc", ""+ _pointerPosition.x + " " + _pointerPosition.y);
       return true;
    }

    @Override
    public void onWaveFormDataCapture(Visualizer visualizer, byte[] waveform, int samplingRate){
    }

    @Override
    public void onFftDataCapture(Visualizer visualizer, byte[] fft, int samplingRate){
        if(this.visualizer == visualizer){
            if(fft.length >0){
               for(int i = 0; i < towers.length; i++){
                   if(y[i]<=5.0f) {
                       y[i] += Math.abs(fft[i + 3]) / 50;
                   }
                   else{
                      // Log.e("Game Over", "Towers are too tall");
                   }
               }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i = 0; i < towers.length; i++){
            towers[i] = new Tower((float)i);
            y[i] = .25f;
        }

        glView = new GLSurfaceView(this);
        glView.setOnTouchListener(this);
        glView.setOnC(this);
        setContentView(glView);
        visualizer.setDataCaptureListener(this, 10000, false, true);
        visualizer.setEnabled(true);
    }

    // Call back when the activity is going into the background
    @Override
    protected void onPause() {
        super.onPause();
        glView.onPause();
    }

    // Call back after onPause()
    @Override
    protected void onResume() {
        super.onResume();
        glView.onResume();
    }

    // Call back when the surface is first created or re-created
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);  // Set color's clear-value to black
        gl.glClearDepthf(1.0f);            // Set depth's clear-value to farthest
        gl.glEnable(GL10.GL_DEPTH_TEST);   // Enables depth-buffer for hidden surface removal
        gl.glDepthFunc(GL10.GL_LEQUAL);    // The type of depth testing to do
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);  // nice perspective view
        gl.glShadeModel(GL10.GL_SMOOTH);   // Enable smooth shading of color
        gl.glDisable(GL10.GL_DITHER);      // Disable dithering for better performance
    }

    // Call back after onSurfaceCreated() or whenever the window's size changes
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        if (height == 0) height = 1;   // To prevent divide by zero
        float aspect = (float)width / height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL10.GL_PROJECTION); // Select projection matrix
        gl.glLoadIdentity();                 // Reset projection matrix
        GLU.gluPerspective(gl, 45, aspect, 0.1f, 100.f);
        gl.glMatrixMode(GL10.GL_MODELVIEW);  // Select model-view matrix
        gl.glLoadIdentity();                 // Reset
    }

    // Call back to draw the current frame.
    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();                 // Reset model-view matrix ( NEW )
        gl.glTranslatef(-1.5f, 0.0f, -6.0f); // Translate left and into the screen ( NEW )
        gl.glScalef(0.8f, 0.8f, 0.8f);      // Scale down (NEW)
        //gl.glRotatef  (angle, 1.0f, 1.0f, 1.0f); // rotate about the axis (1,1,1) (NEW)
        for(int i = 0; i < towers.length; i++) {
            towers[i].draw(gl, y[i]);                   // Draw Square
        }

        elf.draw(gl,_pointerPosition);
    }
}
