package com.implude.dreamplude.view.bluetooth.utils

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.implude.dreamplude.R
import com.implude.dreamplude.view.bluetooth.models.BluetoothStateViewModel

class BluetoothRequest(private val context: Activity, viewModel: BluetoothStateViewModel) {
    init {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(context, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 1)
        }
    }

    private val bluetoothStateReceiver = BluetoothStateReceiver(viewModel)

    fun registerReceiver() = IntentFilter().run {
        addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED)
        addAction(BluetoothDevice.ACTION_FOUND)
        addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
        context.registerReceiver(bluetoothStateReceiver, this)
    }

    fun unregisterReceiver() = context.unregisterReceiver(bluetoothStateReceiver)

    fun startDiscovery() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        when {
            bluetoothAdapter == null -> showLongToast(R.string.bluetooth_not_supported)
            !bluetoothAdapter.isEnabled -> showLongToast(R.string.bluetooth_not_enabled)
            else -> bluetoothAdapter.startDiscovery()
        }
    }

    private fun showLongToast(@StringRes id: Int) = Toast.makeText(context, id, Toast.LENGTH_LONG).show()
}