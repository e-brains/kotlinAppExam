package com.kye.preferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.preferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        // 저장
        binding.button.setOnClickListener { view ->

            // private 모드로 data 라는 이름의 preferences 객체 생성
            var pref = getSharedPreferences("data", Context.MODE_PRIVATE)

            // 데이터를 셋팅하기 위해 edit 메서드를 가져온다.
            var editor = pref.edit()

            editor.putBoolean("data1", true)
            editor.putFloat("data2", 11.11f)
            editor.putInt("data3", 100)
            editor.putLong("data4", 10000L)
            editor.putString("data5", "문자열 데이터")

            // 문자열 HashSet 도 저장 가능
            var set = HashSet<String>()
            set.add("문자열1")
            set.add("문자열2")
            set.add("문자열3")
            editor.putStringSet("data6", set)

            // 저장
            editor.commit()

            binding.textView.text = "저장완료"

        }

        // 조회
        binding.button2.setOnClickListener { view ->

            // private 모드로 data 라는 이름의 preferences 객체 생성
            var pref = getSharedPreferences("data", Context.MODE_PRIVATE)

            // 데이터 읽기
            var data1 = pref.getBoolean("data1", false)
            var data2 = pref.getFloat("data2", 0.0f)
            var data3 = pref.getInt("data3", 0)
            var data4 = pref.getLong("data4", 0L)
            var data5 = pref.getString("data5", "초기값")
            var data6 = pref.getStringSet("data6", null)

            binding.textView.text = "data1 : ${data1}\n"
            binding.textView.append("data2 : ${data2}\n")
            binding.textView.append("data3 : ${data3}\n")
            binding.textView.append("data4 : ${data4}\n")
            binding.textView.append("data5 : ${data5}\n")

            for (str in data6!!){
                binding.textView.append("data6 : ${str}\n")
            }

        }
    }
}