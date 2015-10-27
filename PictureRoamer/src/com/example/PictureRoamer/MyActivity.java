package com.example.PictureRoamer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import java.io.File;
import java.util.ArrayList;

public class MyActivity extends Activity implements GLSurfaceView.Renderer{
    private GLSurfaceView glView;
    ArrayList<String> paths = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File directory = new File("storage/emulated/0/DCIM/Camera/");
        File[] files = directory.listFiles();

        for (int i = 0; i < files.length; ++i) {
            paths.add(files[i].getAbsolutePath());
        }

        glView = new GLSurfaceView(this);
        glView.setRenderer(this);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(picPath(),options);
        glView.setBackground(new BitmapDrawable(getResources(),bitmap));
        setContentView(glView);
    }

    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        gl.glClearDepthf(1.0f);
        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
    }

    public String picPath(){
        int picNum = (int)(Math.random()*paths.size());
        return paths.get(picNum);
    }

    public void onDrawFrame(GL10 gl) {
    }

    public void onSurfaceChanged(GL10 gl, int width, int height) {
    }
}
