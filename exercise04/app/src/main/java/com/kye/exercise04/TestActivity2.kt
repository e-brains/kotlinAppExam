package com.kye.exercise04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.exercise04.databinding.ActivityTest2Binding

class TestActivity2 : AppCompatActivity() {

    val binding by lazy { ActivityTest2Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var data1 = intent.getStringExtra("data1")
        var data2 = intent.getIntExtra("data2", 0)
        binding.textView2.text = "data1 : [${data1}]  ||  data2 : [${data2}]"
    }
}