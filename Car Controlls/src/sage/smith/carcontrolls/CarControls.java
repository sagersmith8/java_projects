package sage.smith.carcontrolls;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class CarControls extends Activity implements OnSeekBarChangeListener{
	SeekBar speedControls, steering;
	UsbDevice arduino = null;
	UsbManager usbManager;
	UsbInterface intf;
	UsbEndpoint endpoint;
	UsbDeviceConnection connection;
	byte[] bytes;
	private final int NO_DATA = 181000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_controls);

		//Initializing Servo Controls
		speedControls = (SeekBar)findViewById(R.id.speedControl);
		steering = (SeekBar)findViewById(R.id.turningControl);
		speedControls.setProgress(25);
		steering.setProgress(50);
		speedControls.setOnSeekBarChangeListener(this);
		steering.setOnSeekBarChangeListener(this);

		//Initializing USB Controls
		usbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
		arduino = (UsbDevice) getIntent().getParcelableExtra(UsbManager.EXTRA_DEVICE);
		intf = arduino.getInterface(0);
		endpoint = intf.getEndpoint(0);
		connection = usbManager.openDevice(arduino);
		connection.claimInterface(intf, true);
		new Thread(new Runnable(){
			public void run(){
				connection.controlTransfer(0x21, 34, 0, 0, null, 0, 0);
				connection.controlTransfer(0x21, 32, 0, 0, new byte[] { (byte) 0x80, 0x25, 0x00, 0x00, 0x00, 0x00, 0x08 }, 7, 0);
			}
		}).start();
		//Closes USB if it is disconnected
		new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				String action = intent.getAction(); 

				if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
					UsbDevice device = (UsbDevice)intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
					if (device != null) {
						connection.releaseInterface(intf);
						connection.close();
					}
				}
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.car_controls, menu);
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar s, int progress, boolean user) {
		int dataToSend = NO_DATA;
		if(s == speedControls){
			if(progress>=25){
				dataToSend = (int)(60+(6*progress)/5);
				Log.e("Speed Angle", ""+dataToSend);
			}

			else{
				dataToSend = (int)(90+((18*progress)/5));
				Log.e("Break Angle", ""+dataToSend);
			}
		}

		else if(s == steering){
			dataToSend = (int)((9*progress)/5);
			Log.e("Steering Angle", ""+dataToSend);
		}

		if(dataToSend!=NO_DATA){
			bytes = String.valueOf(dataToSend).getBytes();
			new Thread(new Runnable(){				
				public void run(){
					connection.bulkTransfer(endpoint, bytes, bytes.length, TRIM_MEMORY_RUNNING_MODERATE);
				}
			}).start();
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {}
}
