package com.kye.brapp

/*********************************************
 * Broad Cast Receiver - BrApp앱
 *********************************************/
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.brapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 다른 앱인 exercise06앱의 리시버를 동작 시키도록 요청
        binding.button.setOnClickListener { view ->

//            var intent = Intent()
//
//            // 명시적 인텐트 : 동작시킬 페키지명과 클래스명을 셋팅
//            // exercise06의 AndroidManifest.xml에 정의된 package="com.kye.exercise06" 패키지명
//            // 클래스는 패키지명이 포함된 TestReceiver로 셋팅
//            intent.setClassName("com.kye.exercise06", "com.kye.exercise06.TestReceiver")

            // 암시적 인텐트 사용
            // 호출하고자 하는 앱 (Exercise06)의 AndroidManifest.xml에 정의된 intent-filer 명 셋팅
            // intent-filer 명으로 명시된 다은 앱의 모든 리시버가 동작한다.
            var intent = Intent("com.kye.exercise06")

            // 데이터 셋팅
            intent.putExtra("data1", 200)
            intent.putExtra("data2", 22.22)
            intent.putExtra("data3", "BrApp에서 exercise06의 리시버 호출")

            sendBroadcast(intent)
        }
    }
}