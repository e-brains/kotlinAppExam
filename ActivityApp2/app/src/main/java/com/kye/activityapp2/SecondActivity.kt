package com.kye.activityapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.activityapp2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //ActivityApp1에서 호출할때 셋팅한 데이터 읽기
        var data1 = intent.getIntExtra("data1", 0)
        var data2 = intent.getDoubleExtra("data2", 0.0)
        var data3 = intent.getStringExtra("data3")
        binding.textView.text = "data1 : ${data1}\n"
        binding.textView.append("data2 : ${data2}\n")
        binding.textView.append("data3 : ${data3}")

        //종료버튼으로 종료하면 Back Stack에 쌓인 순서대로 되돌아 갈때 넘길 데이터 정의
        binding.button.setOnClickListener { view ->
            var intent2 = Intent()
            intent2.putExtra("value1", 200)
            intent2.putExtra("value2", 22.22)
            intent2.putExtra("value3", "문자열2222")
            setResult(RESULT_OK, intent2)
            finish()
        }

    }
}