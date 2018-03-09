package com.maksitaliev.mirlan.walkingrobotcontroller.bluetooth;

import android.bluetooth.BluetoothAdapter;

/**
 * Created by mirlan on 09.03.18.
 */

public class BluetoothManager {

    private static BluetoothManager bluetoothManager;
    private BluetoothAdapter mBluetoothAdapter;

    public BluetoothManager() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean isBtExists() {
        return mBluetoothAdapter != null;
    }

    public boolean isBtEnabled() {
        return mBluetoothAdapter.isEnabled();
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return mBluetoothAdapter;
    }

    public static BluetoothManager getInstance() {
        if (bluetoothManager == null) {
            bluetoothManager = new BluetoothManager();
        }
        return bluetoothManager;
    }
}
