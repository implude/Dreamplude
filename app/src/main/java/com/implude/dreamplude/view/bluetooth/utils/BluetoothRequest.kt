package com.implude.dreamplude.view.bluetooth.utils

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Context
import android.content.IntentFilter
import com.implude.dreamplude.view.bluetooth.models.BluetoothStateViewModel

class BluetoothRequest(private val context: Context, viewModel: BluetoothStateViewModel) {
    private val bluetoothStateReceiver = BluetoothStateReceiver(viewModel)

    fun registerReceiver() = IntentFilter().run {
        addAction(BluetoothAdapter.ACTION_STATE_CHANGED)
        addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        addAction(BluetoothDevice.ACTION_FOUND)
        addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        context.registerReceiver(bluetoothStateReceiver, this)
    }

    fun unregisterReceiver() = context.unregisterReceiver(bluetoothStateReceiver)

    fun startDiscovery() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        bluetoothAdapter.startDiscovery()
    }
}