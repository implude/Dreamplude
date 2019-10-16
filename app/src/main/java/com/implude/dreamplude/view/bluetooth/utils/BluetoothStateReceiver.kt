package com.implude.dreamplude.view.bluetooth.utils

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothAdapter.*
import android.bluetooth.BluetoothDevice.*
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
                when (intent.getIntExtra(EXTRA_STATE, BluetoothAdapter.ERROR)) {
                    STATE_OFF -> Log.d(TAG, "STATE_OFF")
                    STATE_TURNING_OFF -> Log.d(TAG, "STATE_TURNING_OFF")
                    STATE_ON -> Log.d(TAG, "STATE_ON")
                    STATE_TURNING_ON -> Log.d(TAG, "STATE_TURNING_ON")
                }
            }

            ACTION_ACL_CONNECTED -> Log.d(TAG, "ACTION_ACL_CONNECTED")
            ACTION_BOND_STATE_CHANGED -> Log.d(TAG, "ACTION_BOND_STATE_CHANGED")
            ACTION_ACL_DISCONNECTED -> Log.d(TAG, "ACTION_ACL_DISCONNECTED")

            ACTION_DISCOVERY_STARTED -> viewModel.isDiscovering.set(true)
            ACTION_FOUND -> Log.d(TAG, "ACTION_FOUND")
            ACTION_DISCOVERY_FINISHED -> viewModel.isDiscovering.set(false)

            ACTION_PAIRING_REQUEST -> Log.d(TAG, "ACTION_PAIRING_REQUEST")
        }
    }
}