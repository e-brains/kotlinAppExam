package com.kye.exercise05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.exercise05.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {

    val binding by lazy { ActivityThirdBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button4.setOnClickListener { view ->
            finish()
        }

    }
}