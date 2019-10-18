package com.implude.dreamplude.view.function

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.implude.dreamplude.R
import com.implude.dreamplude.view.functiondetail.FunctionDetailActivity
import kotlinx.android.synthetic.main.activity_function.*

class FunctionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function)

        bluetoothImage.setOnClickListener {
            startActivity(Intent(this, FunctionDetailActivity::class.java))
        }
    }
}