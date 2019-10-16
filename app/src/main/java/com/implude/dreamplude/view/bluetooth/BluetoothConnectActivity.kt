package com.implude.dreamplude.view.bluetooth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.implude.dreamplude.R
import com.implude.dreamplude.databinding.ActivityBlutoothConnectBinding
import com.implude.dreamplude.view.bluetooth.models.BluetoothStateViewModel
import com.implude.dreamplude.view.bluetooth.utils.BluetoothRecyclerViewAdapter
import com.implude.dreamplude.view.bluetooth.utils.BluetoothRequest
import kotlinx.android.synthetic.main.activity_blutooth_connect.*

class BluetoothConnectActivity : AppCompatActivity() {
    private val recyclerViewAdapter by lazy { BluetoothRecyclerViewAdapter() }
    private val viewModel by lazy { ViewModelProviders.of(this)[BluetoothStateViewModel::class.java] }
    private val bluetoothRequest by lazy { BluetoothRequest(this, viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blutooth_connect)
        val binding = DataBindingUtil.setContentView<ActivityBlutoothConnectBinding>(this, R.layout.activity_blutooth_connect).apply {
            activity = this@BluetoothConnectActivity
            vm = viewModel
        }

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

    fun onRefresh() {
        swipeRefreshLayout.isRefreshing = false
        bluetoothRequest.startDiscovery()
    }
}