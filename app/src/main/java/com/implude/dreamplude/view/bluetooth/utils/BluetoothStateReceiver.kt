package com.implude.dreamplude.view.bluetooth.utils

import android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_FINISHED
import android.bluetooth.BluetoothAdapter.ACTION_DISCOVERY_STARTED
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothDevice.ACTION_FOUND
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.implude.dreamplude.view.bluetooth.models.BluetoothDeviceItem
import com.implude.dreamplude.view.bluetooth.models.BluetoothStateViewModel

class BluetoothStateReceiver(private val viewModel: BluetoothStateViewModel) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            ACTION_DISCOVERY_STARTED -> viewModel.isDiscovering.set(true)
            ACTION_FOUND -> {
                val device = intent.getParcelableExtra<BluetoothDevice>(BluetoothDevice.EXTRA_DEVICE) ?: return
                device.name?.let { viewModel.deviceList.add(BluetoothDeviceItem(it, "")) }
            }
            ACTION_DISCOVERY_FINISHED -> viewModel.isDiscovering.set(false)
        }
    }
}