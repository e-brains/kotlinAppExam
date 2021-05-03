package com.kye.button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //기본은 해당 리스너 객체를 생성해서 처리
        var listener1 = BtnListener()
        button.setOnClickListener(listener1)

        var listener2 = BtnListener2()
        button2.setOnClickListener(listener2)

        var listener3 = BtnListener3()
        button3.setOnClickListener(listener3)
        button4.setOnClickListener(listener3)

        //람다식을 이용한 이벤트 처리
        button5.setOnClickListener { view ->
            textView.text = "다섯번째 버튼을 눌렀습니다."
        }
        button6.setOnClickListener { view ->
            textView.text = "여섯번째 버튼을 눌렀습니다."
        }

        //하나의 리스너에서 두개 버튼의 이벤트 처리
        var listener4 = View.OnClickListener { view ->
            when(view.id) {
                R.id.button7 -> textView.text = "일곱번째 버튼을 눌렀습니다."
                R.id.button8 -> textView.text = "여덟번째 버튼을 눌렀습니다."
            }
        }
        button7.setOnClickListener(listener4)
        button8.setOnClickListener(listener4)

    }

    //리스너 인터페이스를 구현
    inner class BtnListener:View.OnClickListener{
        override fun onClick(v: View?) {
           textView.text = "첫번째 버튼을 눌렀습니다."
        }
    }

    inner class BtnListener2:View.OnClickListener{
        override fun onClick(v: View?) {
            textView.text = "두번째 버튼을 눌렀습니다."
        }
    }
    //하나의 클래스에서 모든 리스너를 처리할 경우
    inner class BtnListener3:View.OnClickListener{
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.button3 -> textView.text = "세번째 버튼을 눌렀습니다."
                R.id.button4
                        -> textView.text = "네번째 버튼을 눌렀습니다."
            }
        }
    }






}