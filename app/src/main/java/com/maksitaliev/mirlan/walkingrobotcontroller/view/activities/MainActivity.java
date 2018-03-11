package com.maksitaliev.mirlan.walkingrobotcontroller.view.activities;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.maksitaliev.mirlan.walkingrobotcontroller.R;
import com.maksitaliev.mirlan.walkingrobotcontroller.bluetooth.BluetoothService;
import com.maksitaliev.mirlan.walkingrobotcontroller.view.fragments.BaseFragment;
import com.maksitaliev.mirlan.walkingrobotcontroller.view.fragments.BluetoothWrapperFragment;
import com.maksitaliev.mirlan.walkingrobotcontroller.view.fragments.DeviceListFragment;
import com.maksitaliev.mirlan.walkingrobotcontroller.view.fragments.MainFragment;

public class MainActivity extends BaseActivity implements MainFragment.OnEventListener {

    private MainFragment mainFragment;
    private DeviceListFragment deviceListFragment;
    private BluetoothWrapperFragment btFragment;

    private Handler handler;
    private BluetoothService bluetoothService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e(TAG, "savedInstanceState ---- " + savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();
        btFragment = (BluetoothWrapperFragment) fm.findFragmentByTag(BluetoothWrapperFragment.class.getSimpleName());
        if (btFragment == null)
            btFragment = new BluetoothWrapperFragment();

        mainFragment = (MainFragment) fm.findFragmentByTag(MainFragment.class.getSimpleName());
        if (mainFragment == null)
            mainFragment = BaseFragment.newInstance(MainFragment.class, R.layout.fragment_main);

        if (savedInstanceState == null) {
            fm.beginTransaction()
                    .add(btFragment, BluetoothWrapperFragment.class.getSimpleName())
                    .add(R.id.main_container, mainFragment, MainFragment.class.getSimpleName())
                    .commit();

            setupBluetoothService();
            btFragment.setService(bluetoothService);
        } else bluetoothService = btFragment.getService();
    }

    private void setupBluetoothService() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e(TAG, String.valueOf(msg.arg1));
            }
        };
        bluetoothService = new BluetoothService(handler);
        BluetoothService.setInstance(bluetoothService);
    }

    @Override
    public void onBackPressed() {
        if (deviceListFragment == null || mainFragment.isVisible())
            super.onBackPressed();

        else if (deviceListFragment.isVisible()) {
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().beginTransaction().show(mainFragment).commit();
        }
    }

    @Override
    public void onConnectClicked() {
        BluetoothAdapter adapter = BluetoothService.getInstance().getBluetoothAdapter();
        if (adapter != null) {
            if (deviceListFragment == null)
                deviceListFragment = BaseFragment.newInstance(DeviceListFragment.class, R.layout.fragment_device_list);

            getSupportFragmentManager()
                    .beginTransaction()
                    .hide(mainFragment)
                    .add(R.id.main_container, deviceListFragment, DeviceListFragment.class.getSimpleName())
                    .addToBackStack(null)
                    .commit();

        } else Toast.makeText(this, R.string.noBTAdapter, Toast.LENGTH_SHORT).show();
    }
}
