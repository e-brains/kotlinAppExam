package com.kye.excercise01

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.kye.excercise01.databinding.ActivityMainBinding

//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // viewBinding을 사용하기 위해 lazy를 사용해서 처음 호출될 때 초기화 되도록 설정
    // binding이 여기서 프로퍼티로 선언되어 있기 때문에 액티비티 전체에서 호출 가능
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root) //setContentView에는 binding.root를 꼭 전달해야 정상동작 함

        //기본은 해당 리스너 객체를 생성해서 처리
        var listener1: BtnListener = BtnListener()
        //button.setOnClickListener(listener1) //kotlinx 사용 시
        binding.button.setOnClickListener(listener1)  //viewBinding 사용 시

        var listener2 = BtnListener2()
        binding.button2.setOnClickListener(listener2)

        var listener3 = BtnListener3()
        binding.button3.setOnClickListener(listener3)
        binding.button4.setOnClickListener(listener3)

        //람다식을 이용한 이벤트 처리
        binding.button5.setOnClickListener { view ->
            binding.textView.text = "다섯번째 버튼을 눌렀습니다."
        }
        binding.button6.setOnClickListener { view ->
            binding.textView.text = "여섯번째 버튼을 눌렀습니다."
        }

        //하나의 리스너에서 두개 버튼의 이벤트 처리
        var listener4 = View.OnClickListener { view ->
            when(view.id) {
                R.id.button7 -> binding.textView.text = "일곱번째 버튼을 눌렀습니다."
                R.id.button8 -> binding.textView.text = "여덟번째 버튼을 눌렀습니다."
            }
        }
        binding.button7.setOnClickListener(listener4)
        binding.button8.setOnClickListener(listener4)

    }

    //리스너 인터페이스를 구현
    inner class BtnListener: View.OnClickListener{
        override fun onClick(v: View?) {
            binding.textView.text = "첫번째 버튼을 눌렀습니다."
        }
    }

    inner class BtnListener2: View.OnClickListener{
        override fun onClick(v: View?) {
            binding.textView.text = "두번째 버튼을 눌렀습니다."
        }
    }
    //하나의 클래스에서 모든 리스너를 처리할 경우
    inner class BtnListener3: View.OnClickListener{
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.button3 -> binding.textView.text = "세번째 버튼을 눌렀습니다."
                R.id.button4
                -> binding.textView.text = "네번째 버튼을 눌렀습니다."
            }
        }
    }


}