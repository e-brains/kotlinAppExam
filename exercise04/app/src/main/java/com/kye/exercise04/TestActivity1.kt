package com.kye.exercise04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.exercise04.databinding.ActivityTest1Binding

class TestActivity1 : AppCompatActivity() {

    val binding by lazy { ActivityTest1Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //MainActivity.kt에서 설정한 indent 값 얻어오기
        var data1 = intent.getStringExtra("data1")
        var data2 = intent.getIntExtra("data2", 0)
        binding.textView.text = "data1 : ${data1} || data2 : ${data2}"
    }
}