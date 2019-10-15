package com.implude.dreamplude.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.implude.dreamplude.R
import com.implude.dreamplude.databinding.ActivityBlutoothConnectBinding
import kotlinx.android.synthetic.main.activity_blutooth_connect.*

class BluetoothConnectActivity : AppCompatActivity() {
    private val recyclerViewAdapter by lazy { BluetoothRecyclerViewAdapter() }
    private val bluetoothRequest = BluetoothRequest(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blutooth_connect)
        val binding = DataBindingUtil.setContentView<ActivityBlutoothConnectBinding>(this, R.layout.activity_blutooth_connect).apply {
            activity = this@BluetoothConnectActivity
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
    }
}