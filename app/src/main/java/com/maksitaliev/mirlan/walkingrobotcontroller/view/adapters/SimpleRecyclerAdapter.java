package com.maksitaliev.mirlan.walkingrobotcontroller.view.adapters;

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maksitaliev.mirlan.walkingrobotcontroller.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by mirlan on 09.03.18.
 */

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    private List<BluetoothDevice> devices;
    private boolean connecting = false;

    public SimpleRecyclerAdapter(Set<BluetoothDevice> devices) {
        this.devices = new ArrayList<>();
        this.devices.addAll(devices);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BluetoothDevice device = devices.get(position);
        holder.deviceName.setText(device.getName());
        holder.deviceMac.setText(device.getAddress());
    }

    @Override
    public int getItemCount() {
        return devices != null ? devices.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.deviceName)
        TextView deviceName;

        @BindView(R.id.deviceMac)
        TextView deviceMac;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener((View v) -> {
                if (!connecting) {
                    connecting = true;
                }
            });
        }
    }
}
