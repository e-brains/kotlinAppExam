package com.kye.actionview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import com.kye.actionview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // 리스트 데이터 구성
    val data1 = arrayOf("aaaaa", "bbbbb", "cccccc", "dddddd", "eeeeee")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 리스트 뷰와 리스트 데이터 연결
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data1)
        binding.list1.adapter = adapter

        // 검색어 기준으로 필터링 될 수 있도록 설정한다.
        binding.list1.isTextFilterEnabled = true
    }

    // 옵션메뉴를 화면상에 나타나도록 한다.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        // 메뉴에 나타나도록 한다.
        menuInflater.inflate(R.menu.main_menu, menu)

        // SearchView 를 가지고 있는 메뉴 아이템 객체를 추출한다.
        val item1 = menu?.findItem(R.id.item1)

        // 메뉴 아이템 객체에서 SearchView 객체를 추출한다.
        val search1 = item1?.actionView as SearchView

        // 화면상의 안내 문구를 설정한다.
        search1.queryHint = "검색어를 입력하세요"

        // 메뉴아이템에 배치된 뷰가 접히거나 펼쳐질 때 반응하는 리스너 정의
        // 메서드가 2개라서 람다식이 안되기 때문에 객체를 생성해서 사용
        val listener1 = object : MenuItem.OnActionExpandListener{

            // 접혔을 때 호출
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                binding.textView.text = "접혀졌습니다."
                return true
            }

            // 펼쳐졌을 때 호출
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                binding.textView.text = "펼쳐졌습니다."
                return true
            }

        }

        // 메뉴아이템에 배치된 뷰가 접히거나 펼쳐질 때 반응하는 리스너 가동
        item1.setOnActionExpandListener((listener1))

        // SearchView 의 리스너
        // 메서드가 2개라서 람다식이 안되기 때문에 객체를 생성해서 사용
        val listener2 = object : SearchView.OnQueryTextListener{

            //searchView 에 문자열을 입력할 때 마다 호출되는 메서드
            override fun onQueryTextChange(newText: String?): Boolean {
                binding.textView.text = "문자열 입력중"
                binding.textView2.text = "입력중 : ${newText}"

                // SearchView 에 입력한 내용을 listView 의 필터 문자열로 설정한다.
                binding.list1.setFilterText(newText) // 검색에 입력한 문자로 필터링

                // 만약 설정한 문자열의 길이가 0 이라면 필터 문자열을 제거한다.
                if (newText?.length == 0){
                    // 검색란에 문자를 모두 지우면 필터링 삭제해서 원래 리스트로 복귀
                    binding.list1.clearTextFilter()
                }

               return true
            }

            // 키보드화면의 돋보기 버튼을 눌렀을 때 호출되는 메서드
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.textView.text = "문자열 입력 완료"
                binding.textView2.text = "입력완료 : ${query}"
                search1.clearFocus()
                return true
            }
        }

        // SearchView 리스너 가동
        search1.setOnQueryTextListener(listener2)

        return true
    }
}