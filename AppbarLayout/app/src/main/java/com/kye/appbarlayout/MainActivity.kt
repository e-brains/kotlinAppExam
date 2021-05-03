package com.kye.appbarlayout

import android.content.DialogInterface
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.kye.appbarlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // 리스트에서 사용할 데이터 배열 객체 생성
    var data_list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 기본 액션바를 없앤다
        supportActionBar?.hide()

        binding.toolbar.title = "AppbarLayout"

        // 이미지를 선택해서 백그라운드의 이미지를 바꾼다.
        binding.appBarImage.setImageResource(R.drawable.img1x)

        // 글자 색상 설정
        binding.clpsTblrLayout.setCollapsedTitleTextColor(Color.YELLOW)
        binding.clpsTblrLayout.setExpandedTitleColor(Color.YELLOW)

        // 리스트 뷰  구성
        binding.list1.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_list)

        // Floating Action Button이 눌러지면 AlertDialog 창이 팝업 되도록 한다.
        binding.btn1.setOnClickListener { view ->
            var builder = AlertDialog.Builder(this)
            builder.setTitle("문자열 입력")

            // 다이얼로그에 input.xml을 사용하기 위한 객체 생성
            var v1 = layoutInflater.inflate(R.layout.input, null)
            builder.setView(v1)

            builder.setNegativeButton("취소", null)
            builder.setPositiveButton("확인", DialogListener())
            builder.show()
        }
    }

    // 다이얼로그의 이벤트르 캐치할 리스너 클래스 정의
    inner class DialogListener : DialogInterface.OnClickListener{
        override fun onClick(dialog: DialogInterface?, which: Int) {
            var alert = dialog as AlertDialog // 파라미터로 넘어오는 dialog를 AlertDialog로 형변환

            // 파라미터로 넘어오는 dialog 에서 사용하는 input.xml의 editText에 문자열을 가져온다.
            var editText = alert.findViewById<EditText>(R.id.editText)
            var str1 = editText?.text.toString()
            data_list.add(str1)  // data_list에 저장

            // 팝업 창에서 데이터가 추가될 때 만다 메인화면의 리스트 뷰를 갱신해준다.
            var adapter = binding.list1.adapter as ArrayAdapter<String> // 리스트의 어댑터를 배열로 형변환
            adapter.notifyDataSetChanged() // 이 배열을 이용해서 리스트 데이터를 갱신한다.
        }
    }
}