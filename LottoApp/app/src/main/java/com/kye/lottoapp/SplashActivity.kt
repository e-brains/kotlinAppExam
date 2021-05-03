package com.kye.lottoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // 화면 클릭으로 넘어간 경우에는 자동화면 넘기기가 중복 실행되지 않도록 하기 위한 설정
        val handler = Handler(Looper.getMainLooper())  // 핸들러 객체 생성
        val runnable = Runnable {                      // 런너블 객체 생성
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // 3초 뒤에 runnable 스레드 실행
        handler.postDelayed(runnable, 3000)

        // 3초이내에 애니메이션 화면을 클릭하면 위에 Runnable 스레드를 종료한 후 메인 액티비티를 실행
        animationView.setOnClickListener { view ->
            handler.removeCallbacks(runnable)  // Runnable 스레드를 종료
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // 스플래쉬는 첫화면으로서 이동 후에는 필요없기 때문에 종료처리 해준다. (이전 키를 눌러도 나오지 않음)
            finish()
        }
    }
}