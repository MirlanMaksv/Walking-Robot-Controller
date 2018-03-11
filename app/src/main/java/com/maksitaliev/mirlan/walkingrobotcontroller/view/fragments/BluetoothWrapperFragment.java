package com.maksitaliev.mirlan.walkingrobotcontroller.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.maksitaliev.mirlan.walkingrobotcontroller.bluetooth.BluetoothService;

/**
 * Created by mirlan on 10.03.18.
 */

public class BluetoothWrapperFragment extends Fragment {

    private BluetoothService service;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public BluetoothService getService() {
        return service;
    }

    public void setService(BluetoothService service) {
        this.service = service;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (service != null)
            service.stop();
    }
}
