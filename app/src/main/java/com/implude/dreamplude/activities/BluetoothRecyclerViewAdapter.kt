package com.implude.dreamplude.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.implude.dreamplude.R
import com.implude.dreamplude.databinding.BluetoothDeviceItemBinding

class BluetoothRecyclerViewAdapter : RecyclerView.Adapter<BluetoothRecyclerViewAdapter.DeviceViewHolder>() {
    var devices = ArrayList<BluetoothDeviceItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        DataBindingUtil.inflate<BluetoothDeviceItemBinding>(LayoutInflater.from(parent.context),
            R.layout.bluetooth_device_item, parent, false).let {
            return DeviceViewHolder(it)
        }
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.deviceItemBinding.deviceItem = devices[position]
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    class DeviceViewHolder(val deviceItemBinding: BluetoothDeviceItemBinding) : RecyclerView.ViewHolder(deviceItemBinding.root)
}