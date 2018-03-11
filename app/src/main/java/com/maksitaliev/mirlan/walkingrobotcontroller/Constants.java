package com.maksitaliev.mirlan.walkingrobotcontroller;

/**
 * Created by mirlan on 09.03.18.
 */

public enum Constants {

    // Message types sent from the BluetoothService Handler
    MESSAGE_STATE_CHANGE,
    MESSAGE_READ,
    MESSAGE_WRITE,
    MESSAGE_DEVICE_NAME,
    MESSAGE_TOAST;

    // Key names received from the BluetoothService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

    public static final String KEY_LAYOUT = "layout";
}