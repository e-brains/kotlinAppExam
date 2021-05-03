package com.kye.socketclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.socketclient.databinding.ActivityMainBinding
import java.io.DataInputStream
import java.io.DataOutputStream
import java.lang.Exception
import java.net.Socket

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 버튼이 클릭되면 스레드 가동
        binding.button.setOnClickListener { view ->
            var thread = NetworkThread()
            thread.start()
        }
    }

    // 네트워크 프로그램은 스레드로 만들어야 한다.
    inner class NetworkThread : Thread() {
        override fun run() {
            try {
                // 소켓 생성
                var socket = Socket("192.168.0.16", 55555)

                // input 스트림 생성
                var input = socket.getInputStream()
                var dis = DataInputStream(input)  // 서버로 부터 받은 스트림을 담는다.

                // output 스트림 생성
                var output = socket.getOutputStream()
                var dos = DataOutputStream(output) // 서버로 보낼 데이터를 스트림에 담는다

                // 서버가 먼저 보내온 데이터를 읽는다.
                var data1 = dis.readInt()
                var data2 = dis.readDouble()
                var data3 = dis.readUTF()

                // 서버로 보낼 데이터를 쓰기
                dos.writeInt(200)
                dos.writeDouble(22.22)
                dos.writeUTF("클라이언트가 보낸 문자열")

                // 소켓을 닫는다.
                socket.close()

                // 여기서는 화면처리를 할 수 없으니 main 스레드에서 화면처리를 할 수 있도록 한다.
                runOnUiThread {
                    binding.textView.text = "data1 : ${data1}\n"
                    binding.textView.append("data2 : ${data2}\n")
                    binding.textView.append("data3 : ${data3}")
                }


            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}