package com.implude.dreamplude.view.bluetooth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.implude.dreamplude.R
import com.implude.dreamplude.databinding.ActivityBlutoothConnectBinding
import com.implude.dreamplude.view.bluetooth.models.BluetoothDeviceItem
import com.implude.dreamplude.view.bluetooth.models.BluetoothStateViewModel
import com.implude.dreamplude.view.bluetooth.utils.BluetoothRecyclerViewAdapter
import com.implude.dreamplude.view.bluetooth.utils.BluetoothRequest
import kotlinx.android.synthetic.main.activity_blutooth_connect.*

class BluetoothConnectActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProviders.of(this)[BluetoothStateViewModel::class.java] }
    private val bluetoothRequest by lazy { BluetoothRequest(this, viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blutooth_connect)
        val binding = DataBindingUtil.setContentView<ActivityBlutoothConnectBinding>(this, R.layout.activity_blutooth_connect).apply {
            isDiscovering = viewModel.isDiscovering
            activity = this@BluetoothConnectActivity
            deviceList = viewModel.deviceList
        }

        binding.deviceRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@BluetoothConnectActivity)
            setHasFixedSize(true)
            adapter = BluetoothRecyclerViewAdapter()
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

    companion object {
        @JvmStatic
        @BindingAdapter("item")
        fun bindItem(recyclerView: RecyclerView, deviceList: ObservableArrayList<BluetoothDeviceItem>) {
            (recyclerView.adapter as BluetoothRecyclerViewAdapter).devices = deviceList
        }
    }
}