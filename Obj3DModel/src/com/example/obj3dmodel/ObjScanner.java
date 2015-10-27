package com.example.obj3dmodel;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ObjScanner extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_obj_scanner);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.obj_scanner, menu);
		return true;
	}

}
