package com.implude.dreamplude.view.bluetooth.utils

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.implude.dreamplude.R
import com.implude.dreamplude.utils.BluetoothConnectedThread
import com.implude.dreamplude.view.bluetooth.models.BluetoothStateViewModel
import java.util.*

class BluetoothRequest(private val context: Activity, private val viewModel: BluetoothStateViewModel) {
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

    fun connectDevice(positionInDeviceList: Int) {
        //맥 주소를 받아왔을 때
        val macAddress = viewModel.deviceList[positionInDeviceList].bluetoothDevice.address
        val socket: BluetoothSocket
        try {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
            val bluetoothDevice = bluetoothAdapter.getRemoteDevice(macAddress)

            socket = bluetoothDevice.createRfcommSocketToServiceRecord(bluetoothUUID)
            socket.connect()

            val handler = object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    if (msg.what == BluetoothConnectedThread.RESPONSE_MESSAGE) {
                        val text = msg.obj
                        Log.d("testing", text.toString())
                    }
                }
            } as Handler
            BluetoothConnectedThread(socket, handler).start()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, R.string.error_occurred, Toast.LENGTH_LONG).show()
        }
    }

    private fun showLongToast(@StringRes id: Int) = Toast.makeText(context, id, Toast.LENGTH_LONG).show()

    companion object {
        private val bluetoothUUID = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb")
    }
}