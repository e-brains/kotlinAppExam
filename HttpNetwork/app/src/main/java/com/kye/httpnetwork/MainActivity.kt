package com.kye.httpnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.httpnetwork.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // http 통신을 위한 스레드 가동
        binding.button.setOnClickListener { view ->
            var thread = NetworkThread() // 스레드 객체 생성
            thread.start()  // 스레드 스타트
        }
    }

    // http 통신을 위한 스레드
    inner class NetworkThread : Thread(){
        override fun run() {

            // 접속을 준비
            var site = "http://192.168.0.16:8080/string"
            var url = URL(site)
            var conn = url.openConnection()

            // 여러줄을 라인단위로 읽을 수 있는 스트림으로 만든다.
            var input = conn.getInputStream()
            var isr = InputStreamReader(input, "UTF-8")
            var br = BufferedReader(isr)

            var str:String? = null
            var buf = StringBuffer()

            // 라인단위로 읽은 스트림을 스트링 버퍼에 누적 시킨다.
            do{
                str = br.readLine()
                if (str != null){
                    buf.append(str)
                }
            }while (str != null)

            var data = buf.toString()

            // 화면 처리를 메인 스레드에 맡긴다.
            runOnUiThread {
                binding.textView.text = data
            }
        }
    }
}