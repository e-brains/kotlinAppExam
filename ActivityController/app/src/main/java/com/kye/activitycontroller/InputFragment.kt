package com.kye.activitycontroller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kye.activitycontroller.databinding.ActivityMainBinding
import com.kye.activitycontroller.databinding.FragmentInputBinding

/**
 * A simple [Fragment] subclass.
 * Use the [InputFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InputFragment : Fragment() {

    val binding by lazy { FragmentInputBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 입력완료 : MainActivity의 주소값을 가져와서 MainActivity의 setFragment() 메서드 호출
        binding.button.setOnClickListener { view ->
            var main_activity = activity as MainActivity // MainActivity 타입으로 형변환

            // MainActivit에서 선언된 변수에 InputFragment의 뷰 객체의 데이터 셋팅
            main_activity.value1 = binding.editText.text.toString()
            main_activity.value2 = binding.editText2.text.toString()

            // MainActivity의 setFragment() 메서드 호출햐여 ResultFragment 실행
            main_activity.setFragment("result")
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}