package com.example.picturerumor;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.media.audiofx.Visualizer;

public class GameActivity extends Activity implements GLSurfaceView.Renderer {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

}
