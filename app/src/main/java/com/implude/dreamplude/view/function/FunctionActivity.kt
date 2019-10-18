package com.implude.dreamplude.view.function

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.implude.dreamplude.R
import com.implude.dreamplude.view.bluetooth.models.FunctionItem
import com.implude.dreamplude.view.bluetooth.utils.FunctionRecyclerViewAdapter
import com.implude.dreamplude.view.functiondetail.FunctionDetailActivity
import kotlinx.android.synthetic.main.activity_function.*

class FunctionActivity : AppCompatActivity() {
    private lateinit var viewAdapter: FunctionRecyclerViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function)
        val temp: ArrayList<FunctionItem> = ArrayList()

        temp.add(FunctionItem("음악 재생/중지", R.drawable.musicismylife))
        temp.add(FunctionItem("이전 음악", R.drawable.musicismylife))
        temp.add(FunctionItem("다음 음악", R.drawable.musicismylife))

        bluetoothImage.setOnClickListener {
            startActivity(Intent(this, FunctionDetailActivity::class.java))
        }

        viewManager = GridLayoutManager(parent, 2)
        viewAdapter = FunctionRecyclerViewAdapter(this, temp)

        functionList.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
            isNestedScrollingEnabled = false
        }

    }
}