package com.maksitaliev.mirlan.walkingrobotcontroller.view;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.maksitaliev.mirlan.walkingrobotcontroller.bluetooth.BluetoothService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mirlan on 09.03.18.
 */

public class Application extends android.app.Application {

    private String TAG = Application.class.getSimpleName();
    private Handler handler;
    private BluetoothService bluetoothService;

    private List<OnBluetoothEventListener> subscribers = new ArrayList<>();

    public interface OnBluetoothEventListener {
        void onStateChanged(int state);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupBluetoothService();
    }

    private void setupBluetoothService() {
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e(TAG, String.valueOf(msg.arg1));
                for (OnBluetoothEventListener listener : subscribers) {
                    listener.onStateChanged(msg.arg1);
                }
            }
        };
        bluetoothService = new BluetoothService(handler);
        BluetoothService.setInstance(bluetoothService);
    }

    public void subscribeForBtEvent(OnBluetoothEventListener subscriber) {
        subscribers.add(subscriber);
    }
}
