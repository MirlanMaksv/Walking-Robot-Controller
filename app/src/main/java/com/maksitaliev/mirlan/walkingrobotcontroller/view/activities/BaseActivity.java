package com.maksitaliev.mirlan.walkingrobotcontroller.view.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.maksitaliev.mirlan.walkingrobotcontroller.bluetooth.BluetoothService;

import butterknife.Unbinder;

/**
 * Created by mirlan on 09.03.18.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();
    protected Unbinder unbinder;

    public void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }
}
