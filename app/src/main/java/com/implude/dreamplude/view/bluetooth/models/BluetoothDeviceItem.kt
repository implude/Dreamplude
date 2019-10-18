package com.implude.dreamplude.view.bluetooth.models

import android.bluetooth.BluetoothDevice

data class BluetoothDeviceItem(val deviceName: String, val status: String, val bluetoothDevice: BluetoothDevice)