package com.implude.dreamplude.view.functiondetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.implude.dreamplude.R
import kotlinx.android.synthetic.main.activity_function_detail.*

class FunctionDetailActivity : AppCompatActivity() {
    private val items = listOf(
        DetailItem("음악 재생/중지", "음악을 재생하고 중지합니다."),
        DetailItem("이전 음악", "이전 음악을 재생합니다."),
        DetailItem("다음 음악", "다음 음악을 재생합니다.")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function_detail)

        val position = intent.getIntExtra("position", 0)
        titleText.text = items[position].title
        descriptionText.text = items[position].description
    }
}
