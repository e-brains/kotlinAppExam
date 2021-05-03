package com.kye.activityapp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.activityapp3.databinding.ActivityMainBinding
import com.kye.activityapp3.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    val binding by lazy { ActivityTestBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}