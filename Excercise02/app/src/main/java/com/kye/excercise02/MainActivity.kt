package com.kye.excercise02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import com.kye.excercise02.databinding.ActivityMainBinding

//10강 체크박스
class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener { view ->
            binding.textView.text = ""

            if (binding.checkBox.isChecked) {
                binding.textView.append("체크박스1이 체크되었습니다.\n")
            }

            if (binding.checkBox2.isChecked){
                binding.textView.append("체크박스2가 체크되었습니다.\n")
            }

            if (binding.checkBox3.isChecked){
                binding.textView.append("체크박스3이 체크되었습니다.")
            }
        }

        binding.button2.setOnClickListener { view ->
            binding.checkBox.isChecked = true
            binding.checkBox2.isChecked = true
            binding.checkBox3.isChecked = true
        }

        binding.button4.setOnClickListener { view ->
            binding.checkBox.isChecked = false
            binding.checkBox2.isChecked = false
            binding.checkBox3.isChecked = false
        }

        binding.button5.setOnClickListener { view ->
            binding.checkBox.toggle()
            binding.checkBox2.toggle()
            binding.checkBox3.toggle()
        }
        //클래스를 만들어서 리스너 상태 확인
        var listener1 = CheckBoxListener()
        binding.checkBox.setOnCheckedChangeListener(listener1)

        //람다식으로 리스너 상태 확인
        binding.checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.textView.text = "이벤트: 체크박스2 가 체크되었습니다."
            }else{
                binding.textView.text = "이벤트: 체크박스2 가 체크 해제되었습니다."
            }
        }
        binding.checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                binding.textView.text = "이벤트: 체크박스3 가 체크되었습니다."
            }else{
                binding.textView.text = "이벤트: 체크박스3 가 체크 해제되었습니다."
            }
        }

    }

    //클래스를 만들어서 체크박스의 리스너 상태 학인하기
    inner class CheckBoxListener : CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            if(isChecked){
                binding.textView.text = "이벤트 : 체크박스1 이 체크되었습니다."
            }else{
                binding.textView.text = "이벤트 : 체크박스1 이 체크 해제되었습니다."
            }
        }
    }
}