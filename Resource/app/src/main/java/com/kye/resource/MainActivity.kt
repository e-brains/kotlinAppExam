package com.kye.resource

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.kye.resource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 문자열 리소스 활용
        binding.button.setOnClickListener { view ->
            var str2 = resources.getString(R.string.str2)
            binding.textView.text = str2
        }

        // 문자열 배열 활용
        binding.button2.setOnClickListener { view ->
            binding.textView.text = ""
            var str_array = resources.getStringArray(R.array.data_array)

            for (str : String in str_array){
                binding.textView.append("${str}\n")
            }
        }

        // 색상 관리
        binding.button3.setOnClickListener { view ->
            // xml의 설정값을 읽어서 셋팅
            var color = ContextCompat.getColor(this, R.color.color1)
            binding.textView.setTextColor(color)
        }

        //현재 디바이스 크기
        binding.button4.setOnClickListener { view ->
            var px = resources.getDimension(R.dimen.px)
            var dp = resources.getDimension(R.dimen.dp)
            var sp = resources.getDimension(R.dimen.sp)
            var inch = resources.getDimension(R.dimen.inch)
            var mm = resources.getDimension(R.dimen.mm)
            var pt = resources.getDimension(R.dimen.pt) // 출판에서 많이 사용

            binding.textView.text = "px : ${px}\n"
            binding.textView.append("dp : ${dp}\n")
            binding.textView.append("sp : ${sp}\n")
            binding.textView.append("inch : ${inch}\n")
            binding.textView.append("mm : ${mm}\n")
            binding.textView.append("pt : ${pt}")

            // setTextSize를 사용하면 모든 디바이스에서 비슷한 사이즈로 보이게 됨
            binding.textView.setTextSize(20*dp)
        }
    }
}