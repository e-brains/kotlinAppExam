package com.kye.xmlclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.xmlclient.databinding.ActivityMainBinding
import java.lang.Exception
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.textView.text = ""

        // http 통신을 이용하여 xml문서 가져오기
        binding.button.setOnClickListener { view ->
            var thread = NetworkThread()
            thread.start()
        }

    }

    // 인터넷을 사용하기 때문에 스레드로 만들어 준다.
    // xml문서 읽어 오기
    inner class NetworkThread : Thread(){
        override fun run() {
            try {
                // 접속 준비 및 접속
                var site = "http://192.168.0.16:8080/xml"
                var url = URL(site)
                var conn = url.openConnection()

                // 여러줄을 라인단위로 읽을 수 있는 스트림으로 만든다.
                var input = conn.getInputStream()

                // DOM방식으로 읽기 위한 객체를 만들어 줘야 한다.
                var factory = DocumentBuilderFactory.newInstance()
                var builder = factory.newDocumentBuilder()
                var doc = builder.parse(input)

                // 파싱이 끝난 문서전체를 객체화 한다.
                var root = doc.documentElement

                // xml문서는 기본적으로 각 태그에 대해 리스트로 간주한다.
                // 그래서 원하는 태그명으로 리스를 가져온다.
                var item_node_list = root.getElementsByTagName("item")

                // 루프를 돌면 item 태그 안에 데이터를 추출한다.
                for (i in 0 until item_node_list.length){

                    // item은 여러줄 그 아래 data는 각 한줄씩 들어 있다.
                    var item_element = item_node_list.item(i) as org.w3c.dom.Element

                    var data1_node_list = item_element.getElementsByTagName("data1")
                    var data2_node_list = item_element.getElementsByTagName("data2")
                    var data3_node_list = item_element.getElementsByTagName("data3")

                    // 각각의 item 안에는 data1 data2 data3 노드는 한줄만 있으므로 첫째줄 0번째만 읽는다.
                    var data1_node = data1_node_list.item(0) as org.w3c.dom.Element
                    var data2_node = data2_node_list.item(0) as org.w3c.dom.Element
                    var data3_node = data3_node_list.item(0) as org.w3c.dom.Element

                    // data1 data2 data3 노드에서 Text를 읽는다.
                    var data1 = data1_node.textContent
                    var data2 = data2_node.textContent
                    var data3 = data3_node.textContent

                    // 화면 처리를 메인 스레드에 맡긴다.
                    runOnUiThread {
                        binding.textView.append("data1 : ${data1}\n")
                        binding.textView.append("data2 : ${data2}\n")
                        binding.textView.append("data3 : ${data3}\n")
                    }
                }

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}