package sage.smith.FirstGame;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    BluetoothAdapter bluetoothAdapter = getBluetoothAdapter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void getDevices(){
        bluetoothAdapter.startDiscovery();
        BroadcastReceiver mReceiver = new BroadcastReceiver(){
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    Log.d("Device Found", device.getName() + "\n" + device.getAddress());
                }
            }
        };
        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
    }

    public BluetoothAdapter getBluetoothAdapter(){
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bluetoothAdapter!=null){
            Log.d("BluetoothAdapter","Device has adapter");

            if(!bluetoothAdapter.isEnabled()) {
                Intent enableBluetoothIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetoothIntent, 2);
            }

            return bluetoothAdapter;
        }

        Log.e("BluetoothAdapter", "No Bluetooth Adapter Found");
        return bluetoothAdapter;
    }


}
