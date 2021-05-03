package com.kye.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // LinearLayoutManager 를 this 가 즉 MainActivity 가 통제하도록 하고 이를 recyclerView 에 넘겨준다.
        // recyclerView 안에 LinearLayout 을 넣은 것과 같은 효과가 있다. (수직으로 이동 가능)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 핸들링하기 위한 어댑터를 만들어야 하는데 커스텀 어댑터 클래스가 필요하다.
        // 패키지명 폴더 우클릭 -> new -> kotlin class/file -> CustomAdapter 클래스 생성
        recyclerView.adapter = CustomAdapter(this)

    }
}