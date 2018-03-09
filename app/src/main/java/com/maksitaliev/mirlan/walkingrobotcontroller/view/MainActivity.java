package com.maksitaliev.mirlan.walkingrobotcontroller.view;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.maksitaliev.mirlan.walkingrobotcontroller.R;
import com.maksitaliev.mirlan.walkingrobotcontroller.bluetooth.BluetoothManager;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity {

    private String TAG = this.getClass().getSimpleName();
    private int BT_CODE = 1;
    private int BT_LIST = 2;

    @BindView(R.id.button)
    Button pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Unbinder unbinder = ButterKnife.bind(this);
        setUnBinder(unbinder);

        checkBluetooth();
    }

    private void checkBluetooth() {
        if (!BluetoothManager.getInstance().isBtExists()) {
            // No BT adapter found
            Toast.makeText(this, R.string.noBTAdapter, Toast.LENGTH_SHORT).show();
            pairedDevices.setEnabled(false);
            return;
        }
        if (!BluetoothManager.getInstance().isBtEnabled()) {
            // Request user to turn on BT
            pairedDevices.setEnabled(false);

            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, BT_CODE);
        }
    }

    private void showPairedDevices() {
        BluetoothAdapter adapter = BluetoothManager.getInstance().getBluetoothAdapter();
        if (adapter != null) {
            Intent intent = new Intent(this, DeviceListActivity.class);
            startActivityForResult(intent, BT_LIST);
        } else Toast.makeText(this, R.string.noBTAdapter, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BT_CODE && resultCode == RESULT_OK) {
            pairedDevices.setEnabled(true);
            showPairedDevices();
        }
    }

    @OnClick(R.id.button)
    public void pairedDevicesClick(Button button) {
        showPairedDevices();
    }
}
