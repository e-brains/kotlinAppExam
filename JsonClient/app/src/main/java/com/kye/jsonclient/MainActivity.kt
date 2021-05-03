package com.kye.jsonclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.jsonclient.databinding.ActivityMainBinding
import org.json.JSONArray
import java.io.*
import java.lang.Exception
import java.net.URL

class MainActivity : AppCompatActivity() {

   val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView.text = ""

        binding.button.setOnClickListener { view ->
            var thread = NetworkThread()
            thread.start()
        }

    }

    // 인터넷을 사용하기 때문에 스레드로 만들어 준다.
    // Json 읽어 오기
    inner class NetworkThread : Thread(){
        override fun run() {
            try {
                // 접속 준비 및 접속
                var site = "http://192.168.0.16:8080/json"
                var url = URL(site)
                var conn = url.openConnection()

                // 여러줄을 라인단위로 읽을 수 있는 스트림으로 만든다.
                var input = conn.getInputStream()

                // 스트림에서 읽는다.
                var istr = InputStreamReader(input)

                // 라인단위로 읽어야 하기 때문에 버퍼에 넣는다.
                var buf_r = BufferedReader(istr)

                var str : String? = null  // 라인단위로 읽은 문자열을 담을 변수
                var buf = StringBuffer()  // 라인단위로 읽은 데이터를 저장할 버터

                // 루플를 돌면서 라인단위로 읽는다.
                do {
                    str = buf_r.readLine()
                    if (str != null){
                        buf.append(str)  // 버퍼에 추가
                    }
                }while (str != null)

                // 버퍼의 스트림을 스트링으로 만들어 JSON array에 넣는다.
                var root = JSONArray(buf.toString())

                // 각 item 객체 수 만큼 루프 (중괄호 부분을 객체로 봄)
                for (i in 0 until root.length()){

                    var obj = root.getJSONObject(i)  // 중괄호 부분을 하나의 객체로 봄

                    var data1 = obj.getString("data1")
                    var data2 = obj.getInt("data2")
                    var data3 = obj.getDouble("data3")

                    // 화면에 출력
                    runOnUiThread {
                        binding.textView.append("data1 : ${data1}\n")
                        binding.textView.append("data2 : ${data2}\n")
                        binding.textView.append("data3 : ${data3}\n\n")
                    }
                }

            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

}