package com.kye.actionbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.kye.actionbar.databinding.ActivityMainBinding
import com.kye.actionbar.databinding.CustomActionbarBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // custom_actionbar.xml을 바인딩 한다.
    val binding2 by lazy { CustomActionbarBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 커스터마이징 된 AactionBar를 보이게 한다.
        supportActionBar?.setDisplayShowCustomEnabled(true)

        // 홈 버튼을 안 보여준다. (해주지 않으면 버전에 따라서 커스텀바가 안보이는 경우가 있다)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        // 타이틀을 안 보여준다. (해주지 않으면 버전에 따라서 커스텀바가 안보이는 경우가 있다)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // layout 을 통해 view 를 생성한다.
        // val topBar = layoutInflater.inflate(R.layout.custom_actionbar, binding2.root) // binding방식
        val topBar = layoutInflater.inflate(R.layout.custom_actionbar, null)
        supportActionBar?.customView = topBar

        // custom_actionbar.xml의 view 컴포넌트에 접근 (옛날 방식)
        val btn = topBar.findViewById<Button>(R.id.button)
        var txt = topBar.findViewById<TextView>(R.id.textView2)

        // CustomBar에 접근해서 여러가지 설정을 해준다.
        topBar.run {

            txt.text = "커스텀 액션바"
            txt.setTextColor(Color.WHITE)
            btn.setOnClickListener { view ->
                binding.textView.text = "액션바의 버튼을 눌렀습니다."
            }
            // 겹치는 버그는 찾아서 없앨 것 (binding 방식)
            //binding2.textView2.text = "커스텀 액션바"
            //binding2.textView2.setTextColor(Color.WHITE)
            //binding2.button.setOnClickListener { view ->
            //    binding.textView.text = "액션바의 버튼을 눌렀습니다."
            //}

        }
    }

}