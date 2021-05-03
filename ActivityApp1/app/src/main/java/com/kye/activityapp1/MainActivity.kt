package com.kye.activityapp1

/*********************************************
 * 다른 애플리케이션의 액티비티 실행하기 App1
 *********************************************/
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.activityapp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val SECOND_ACTIVITY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.button.setOnClickListener { view ->
            // 다른 앱의 AndroidManifest.xml의 intent-filter에 정의된 이름으로 셋팅
            // com.kye.second는 ActivityApp1 앱에 있는 SecondActivity이다.
            // 만약 다른 앱에 com.kye.second 라는 동일한 이름이 여러개 존재하면 앱을 선택하는 창이 뜨며
            // 여기서 선택하면 해당 앱의 Activity가 동작한다.
            var intent = Intent("com.kye.second")

            //서로 다른앱의 액티비티 간의 데이터 넘기기 (앱 내에서 액티비티간 데이터 이동과 같은 방법)
            intent.putExtra("data1", 100)
            intent.putExtra("data2", 11.11)
            intent.putExtra("data3", "문자열1111")

            startActivityForResult(intent, SECOND_ACTIVITY)
        }
    }

    //돌아올때 반환 값 일기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            var value1 = data?.getIntExtra("value1", 0)
            var value2 = data?.getDoubleExtra("value2", 0.0)
            var value3 = data?.getStringExtra("value3")
            binding.textView.text = "value1 : ${value1}\n"
            binding.textView.append("value2 : ${value2}\n")
            binding.textView.append("value3 : ${value3}")
        }
    }
}