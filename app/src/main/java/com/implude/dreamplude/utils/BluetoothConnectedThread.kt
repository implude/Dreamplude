package com.implude.dreamplude.utils

import android.bluetooth.BluetoothSocket
import android.os.Handler

class BluetoothConnectedThread(private val socket: BluetoothSocket, private val handler: Handler) : Thread() {
    companion object {
        const val RESPONSE_MESSAGE = 10
    }

    override fun run() {
    }
}