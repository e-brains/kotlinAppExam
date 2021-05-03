package com.kye.toolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.kye.toolbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 액션바 대신 툴바를 사용하도록 설정한다.
        setSupportActionBar(binding.toolbar)
    }

    // 옵션메뉴 생성
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return true
    }

    // 메뉴를 선택하면 호출되는 메서드
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // 메뉴의 id값으로 분기
        when(item.itemId){
            R.id.item1 -> {
                binding.textView.text = "메뉴1 을 눌렀습니다."
            }
            R.id.item2 -> {
                binding.textView.text = "메뉴2 을 눌렀습니다."
            }
            R.id.item3 -> {
                binding.textView.text = "메뉴3 을 눌렀습니다."
            }
        }

        return super.onOptionsItemSelected(item)
    }
}