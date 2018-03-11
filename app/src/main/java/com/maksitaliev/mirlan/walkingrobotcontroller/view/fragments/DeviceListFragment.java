package com.maksitaliev.mirlan.walkingrobotcontroller.view.fragments;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.maksitaliev.mirlan.walkingrobotcontroller.R;
import com.maksitaliev.mirlan.walkingrobotcontroller.bluetooth.BluetoothService;
import com.maksitaliev.mirlan.walkingrobotcontroller.view.adapters.DevicesAdapter;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mirlan on 09.03.18.
 */

public class DeviceListFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private DevicesAdapter.OnListItemClicked listener = (BluetoothDevice device) -> {
        int state = BluetoothService.getInstance().getState();
        if (state != BluetoothService.STATE_CONNECTED || state != BluetoothService.STATE_CONNECTING) {
            BluetoothService.getInstance().connect(device, true);
        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Unbinder unbinder = ButterKnife.bind(this, view);
        setUnbinder(unbinder);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Set<BluetoothDevice> deviceSet = BluetoothService.getInstance().getBluetoothAdapter().getBondedDevices();
        DevicesAdapter adapter = new DevicesAdapter(deviceSet);
        adapter.setListener(listener);
        mRecyclerView.setAdapter(adapter);
    }
}
