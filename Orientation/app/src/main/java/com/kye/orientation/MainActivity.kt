package com.kye.orientation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.orientation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // 화면이 회전할때도 호출되는데 이때에는 Bundle에 값이 들어온다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 화면회전 시 onCreate메서드가 실행되면 savedInstanceState에 값이 들어 있다.
        // 복원작업을 진행한다.
        if (savedInstanceState != null) {
            binding.textView.text = savedInstanceState?.getString("data1")
        }

        binding.button.setOnClickListener { view ->

            // 화면이 회전하면 textView컴포넌트는 초기화 된다.
            binding.textView.text = binding.editText.text
        }

    }

    // 화면이 생성될때 호출된다.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // textView컴포넌트 같은 화면회전 시 복원해야 하는 정보를 Bundle객체에 담아두면
        // onCreate의 savedInstanceState 파라미터에 담겨서 전달된다.
        outState?.putString("data1", binding.textView.text.toString())
    }
}