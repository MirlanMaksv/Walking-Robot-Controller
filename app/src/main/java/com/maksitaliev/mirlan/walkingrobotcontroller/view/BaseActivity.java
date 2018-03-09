package com.maksitaliev.mirlan.walkingrobotcontroller.view;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;

import butterknife.Unbinder;

/**
 * Created by mirlan on 09.03.18.
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    private Unbinder unBinder;

    public void setUnBinder(Unbinder unbinder) {
        unBinder = unbinder;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unBinder != null)
            unBinder.unbind();
    }
}
