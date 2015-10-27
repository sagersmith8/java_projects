package com.example.cannyedgedetection;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.FrameLayout;

/**
 * 
 * @author Sage Smith
 * Activity gets default camera and displays live feed from it
 *
 */
public class MainActivity extends Activity {
	
	Camera camera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*
		 *Won't see camera on the front 
		 * 
		if(hasCamera(this))
		{
			Log.d("Here", "has Camera");
		}
		
		else{
			Log.e("Error", "No camera");
		}*/
		
		camera = getCameraInstance();
		Log.e("Here", camera.toString());
		CameraFeed camFeed = new CameraFeed(this, camera);
		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
	    preview.addView(camFeed);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**Returns true if device has a camera, returns false if device doesn't have a camera
	 * @param context app
	 * @return boolean true or false
	 * @deprecated wont see front camera could use some fixing
	 */
	private boolean hasCamera(Context context) {
	    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
	        // this device has a camera
	        return true;
	    } else {
	        // no camera on this device
	        return false;
	    }
	}
	
	/**Returns an instance of the devices default camera 
	 *	@return Camera device Camera 
	 */
	public static Camera getCameraInstance(){
	    Camera c = null;
	    try {
	        c = Camera.open(0); // attempt to get a Camera instance
	    }
	    catch (Exception e){
	        // Camera is not available (in use or does not exist)
	    }
	    return c; // returns null if camera is unavailable
	}


}
