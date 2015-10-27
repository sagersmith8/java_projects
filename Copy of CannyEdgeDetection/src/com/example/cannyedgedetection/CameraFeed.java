package com.example.cannyedgedetection;
import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 
 * @author Sage Smith
 *	Surface Veiw that displays camera preview
 */
public class CameraFeed extends SurfaceView implements SurfaceHolder.Callback{
	Camera camera;
	SurfaceHolder holder;
	
	/**Constructs a new CameraFeed<SurfaceView> displays camera preview  
	 * @param context app
	 * @param camera cam whose preview is to be displayed
	 */
	public CameraFeed(Context context, Camera camera) {
		super(context);
		this.camera = camera;
		  // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        holder = getHolder();
        holder.addCallback(this);
        // deprecated setting, but required on Android versions prior to 3.0
       holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}
	

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (holder.getSurface() == null){
          // preview surface does not exist
          return;
        }

        // stop preview before making changes
        try {
            camera.stopPreview();
        } catch (Exception e){
          // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
           camera.setPreviewDisplay(holder);
            camera.startPreview();

        } catch (Exception e){
            Log.e("Error", "Error starting camera preview: " + e.getMessage());
        }
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		  // The Surface has been created, now tell the camera where to draw the preview.
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        } catch (IOException e) {
            Log.e("Error", "Error setting camera preview: " + e.getMessage());
        }
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		camera.release();
	}

}
