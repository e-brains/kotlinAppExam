package com.kye.dialogfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.dialogfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener { view ->
            var dialog = TestDialogFragment()  // 다이얼로그 객체 생성
            dialog.show(supportFragmentManager, "dlg1")  // 다이얼로그 창 띄우기
        }

    }
}