package com.kye.activitycontroller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kye.activitycontroller.databinding.FragmentResultBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {

    val binding by lazy { FragmentResultBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // MainActivity 참조값 가져오기
        var main_activity = activity as MainActivity

        // MainActivity의 변수값 읽기
        binding.textView.text = main_activity.value1
        binding.textView2.text = main_activity.value2

        // 이전 버튼 : back stack에 있는 직전 화면 실행
        binding.button2.setOnClickListener { vier ->
            main_activity.supportFragmentManager.popBackStack() // 자기 자신을 제거 후 이전 가기
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}