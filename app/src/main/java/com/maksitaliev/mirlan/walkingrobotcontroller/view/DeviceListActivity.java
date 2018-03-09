package com.maksitaliev.mirlan.walkingrobotcontroller.view;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.maksitaliev.mirlan.walkingrobotcontroller.R;
import com.maksitaliev.mirlan.walkingrobotcontroller.bluetooth.BluetoothManager;
import com.maksitaliev.mirlan.walkingrobotcontroller.view.adapters.SimpleRecyclerAdapter;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by mirlan on 09.03.18.
 */

public class DeviceListActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_list_activity);

        setTitle(R.string.deviceList);
        Unbinder unbinder = ButterKnife.bind(this);
        setUnBinder(unbinder);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Set<BluetoothDevice> deviceSet = BluetoothManager.getInstance().getBluetoothAdapter().getBondedDevices();
        SimpleRecyclerAdapter adapter = new SimpleRecyclerAdapter(deviceSet);
        mRecyclerView.setAdapter(adapter);
    }
}
