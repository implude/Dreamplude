package com.implude.dreamplude.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.implude.dreamplude.R
import com.implude.dreamplude.databinding.ActivityBlutoothConnectBinding

class BluetoothConnectActivity : AppCompatActivity() {
    private val recyclerViewAdapter by lazy { BluetoothRecyclerViewAdapter() }
    private val bluetoothRequest = BluetoothRequest(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blutooth_connect)
        val binding = DataBindingUtil.setContentView<ActivityBlutoothConnectBinding>(this, R.layout.activity_blutooth_connect)

        binding.deviceRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@BluetoothConnectActivity)
            setHasFixedSize(true)
            adapter = recyclerViewAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        bluetoothRequest.registerReceiver()
    }

    override fun onPause() {
        super.onPause()
        bluetoothRequest.unregisterReceiver()
    }
}