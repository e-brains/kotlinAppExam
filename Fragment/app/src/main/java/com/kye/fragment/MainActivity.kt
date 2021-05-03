package com.kye.fragment

/*********************************************
 * Fragment
 *********************************************/
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.fragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // 먼저 사용할 프래그먼트 들의 객체를 만든다.
    var first = FirstFragment()
    var second = SecondFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // First Fragment
        binding.button6.setOnClickListener { view ->

            // 5.0 이상 지원은 FragmentManager 5.0 이하는 supportFragmentManager 사용
            var tran = supportFragmentManager.beginTransaction()
            //tran.add(com.kye.fragment.R.id.container, first) // add : 화면이 겹쳐서 보임
            tran.replace(com.kye.fragment.R.id.container, first) // 기존 화면이 없으면 그냥 화면 배치

            // 뒤로가기 버튼에 반은하도록 Back Stack에 넣어 준다.
            tran.addToBackStack(null)
            tran.commit()

        }

        // Second Fragment
        binding.button7.setOnClickListener { view ->
            var tran = supportFragmentManager.beginTransaction()
            //tran.add(com.kye.fragment.R.id.container, second) // 화면이 겹쳐서 보임
            tran.replace(com.kye.fragment.R.id.container, second) // 기존 화면이 있으면 지우고 재배치

            // 뒤로가기 버튼에 반은하도록 Back Stack에 넣어 준다.
            tran.addToBackStack(null)
            tran.commit()
        }

    }
}