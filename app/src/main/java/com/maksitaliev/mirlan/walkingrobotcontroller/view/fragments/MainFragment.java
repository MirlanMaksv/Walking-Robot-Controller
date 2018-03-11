package com.maksitaliev.mirlan.walkingrobotcontroller.view.fragments;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.maksitaliev.mirlan.walkingrobotcontroller.R;
import com.maksitaliev.mirlan.walkingrobotcontroller.bluetooth.BluetoothService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by mirlan on 09.03.18.
 */

public class MainFragment extends BaseFragment {

    private OnEventListener listener;

    public interface OnEventListener {
        void onConnectClicked();
    }

    private int BT_CODE = 1;

    @BindView(R.id.pairedDevices)
    Button btnConnect;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Unbinder unbinder = ButterKnife.bind(this, view);
        setUnbinder(unbinder);

        checkBluetooth();
    }

    private void checkBluetooth() {
        if (!BluetoothService.getInstance().isBtExists()) {
            // No BT adapter found
            Toast.makeText(getContext(), R.string.noBTAdapter, Toast.LENGTH_SHORT).show();
            btnConnect.setEnabled(false);
            return;
        }
        if (!BluetoothService.getInstance().isBtEnabled()) {
            // Request user to turn on BT
            btnConnect.setEnabled(false);

            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, BT_CODE);
        }
    }

    @OnClick({R.id.pairedDevices, R.id.btnUp, R.id.btnDown, R.id.btnRight, R.id.btnLeft, R.id.btn0, R.id.btn180})
    public void onClick(Button button) {
        int id = button.getId();
        if (id == R.id.pairedDevices)
            listener.onConnectClicked();

        else if (id == R.id.btnUp)
            BluetoothService.getInstance().write("U".getBytes());
        else if (id == R.id.btnDown)
            BluetoothService.getInstance().write("D".getBytes());
        else if (id == R.id.btnRight)
            BluetoothService.getInstance().write("R".getBytes());
        else if (id == R.id.btnLeft)
            BluetoothService.getInstance().write("L".getBytes());
        else if (id == R.id.btn0)
            BluetoothService.getInstance().write("0".getBytes());
        else if (id == R.id.btn180)
            BluetoothService.getInstance().write("1".getBytes());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BT_CODE && resultCode == Activity.RESULT_OK) {
            btnConnect.setEnabled(true);
            listener.onConnectClicked();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEventListener) {
            listener = (OnEventListener) context;
        }
    }
}
