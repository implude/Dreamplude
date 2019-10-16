package com.implude.dreamplude.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.implude.dreamplude.view.bluetooth.BluetoothConnectActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            startActivity(Intent(this, BluetoothConnectActivity::class.java))
            finish()
        }, 1000)

    }
}