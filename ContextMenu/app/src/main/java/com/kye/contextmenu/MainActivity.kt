package com.kye.contextmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.kye.contextmenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val data1 = arrayOf("항목1", "항목2", "항목3", "항목4", "항목5")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // list view컴포넌트에 넣을 리스트 항목 배열을 넘겨주기 위한 어댑터 생성
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data1)

        // 리스트에 항목 배열을 넘겨서 연결한다. 그러면 화면에 리스트 항목명이 보임
        binding.list1.adapter = adapter

        // 항목을 터치했을때 반응하는 리스너
        binding.list1.setOnItemClickListener { parent, view, position, id ->
            binding.textView.text = "리스트 뷰의 항목 선택 : ${data1[position]}"
        }

        // ContextMenu를 listView 컴포넌트를 등록한다.
        registerForContextMenu(binding.list1)

    }

    // View 를 길게 누르면 호출되는 메서드 (여기서 ContextMenu를 구성하면 됨)
    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        // 길게 누른 View 컴포넌트의 id로 분기해서 해당 메뉴를 생성한다.
        when(v?.id){

            // ListViw 컴포넌트 일때
            R.id.list1 -> {
                // 사용자가 길게 누른 리스트 항목 인덱스 번호를 파악하기 위해
                val info = menuInfo as AdapterView.AdapterContextMenuInfo

                menu?.setHeaderTitle("리스트뷰의 메뉴 :  ${info.position}")
                menuInflater.inflate(R.menu.menu2, menu)
            }

        }
    }

    //  ContextMenu를 선택했을 때 호출된다. 여기서는 View 컴포넌트를 구분할 정보가 없기 때문에 id로 구분
    override fun onContextItemSelected(item: MenuItem): Boolean {

        // 선택된 리스트 항목의 인덱스 번호를 받을 변수
        var position = 0

        // 선택된 리스트 항목의 인덱스를 추출
        when(item.itemId){
            R.id.list_item1, R.id.list_item2 ->{
                // MenuInfo 객체를 추출한다.
                val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
                position = info.position
            }
        }

        // 메뉴의 id 값만을 분기 (id 할당 시 중복 id가 없어야 한다.)
        when(item.itemId){

            R.id.list_item1 -> {
                binding.textView.text = "리스트 뷰의 ${position+1}번째 항목의 컨텍스트 메뉴항목 1를 선택했습니다."
            }
            R.id.list_item2 -> {
                binding.textView.text = "리스트 뷰의 ${position+1}번째 항목의 컨텍스트 메뉴항목 2를 선택했습니다."
            }
        }

        return super.onContextItemSelected(item)
    }
}