package com.kye.exercise05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.exercise05.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}

/*********************************************
 * Parcelable를 이용한 객체 전달 하기
 *********************************************/
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.exercise05.databinding.ActivitySecondBinding
//
//class SecondActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //Parcelable을 통해 넘어오는 클래스 속성 정보 읽기
//        var t1 = intent.getParcelableExtra<TestClass>("test1")
//
//        binding.textView2.text = "t1.data10 : ${t1?.data10} \n"
//        binding.textView2.append("t1.data20 : ${t1?.data20}")
//
//        //MainActivity로 돌아갈때도 데이터를 넘겨보자
//        binding.button2.setOnClickListener { view ->
//            var t2 = TestClass()
//            t2.data10 = 200
//            t2.data20 = "문자열 2222"
//
//            var intent2 = Intent()
//            intent2.putExtra("test2", t2)
//            setResult(RESULT_OK, intent2)
//            finish()
//        }
//    }
//}