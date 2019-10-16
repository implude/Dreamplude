package com.implude.dreamplude.view.bluetooth.utils

import android.bluetooth.BluetoothAdapter.*
import android.bluetooth.BluetoothDevice.ACTION_FOUND
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.implude.dreamplude.view.bluetooth.models.BluetoothStateViewModel

private const val TAG = "testing"

class BluetoothStateReceiver(private val viewModel: BluetoothStateViewModel) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            ACTION_STATE_CHANGED -> {
                when (intent.getIntExtra(EXTRA_STATE, ERROR)) {
                    STATE_OFF -> Log.d(TAG, "STATE_OFF")
                    STATE_ON -> Log.d(TAG, "STATE_ON")
                }
            }
            ACTION_DISCOVERY_STARTED -> viewModel.isDiscovering.set(true)
            ACTION_FOUND -> Log.d(TAG, "ACTION_FOUND")
            ACTION_DISCOVERY_FINISHED -> viewModel.isDiscovering.set(false)
        }
    }
}