package com.kye.activitycontroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.activitycontroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // fragment 객체 생성
    var input_fragment = InputFragment()
    var result_fragment = ResultFragment()

    // Fragment간 데이터를 넘기기 위한 변수 선언
    var value1 : String? = null
    var value2 : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //프래그먼트 교체하는 메서드 호출
        setFragment("input") // InputFragment 화면 실행

    }

    // fragment는 Activity에서 관리를 하기 때문에 Activity가 fragment의 참조 변수를 가지고 있다.
    // fragment는 자신이 속한 Activity에 접근할 수 있기 때문에 Activity에 함수나 변수를 만들면
    // fragment에서 접근이 가능하다.

    // Fragment를 교체하는 메서드
    fun setFragment(name:String){
        var tran = supportFragmentManager.beginTransaction()
        when(name){
            "input" -> {
                // 첫번째 화면은 이전가기 버튼 클릭 시 back stack이 비어있기 때문에 화면을 닫는다.
                tran.replace(com.kye.activitycontroller.R.id.container, input_fragment)
            }
            "result" -> {
                // 두번째 화면은 이전가기 버튼 클릭 시 back stack에 있는 첫번째 화면을 띄우도록 스택에 담는다.
                tran.replace(com.kye.activitycontroller.R.id.container, result_fragment)
                tran.addToBackStack(null)
            }
        }
        tran.commit()
    }
}