package com.kye.optionmenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.kye.optionmenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

    // Activity객체가 만들어 질 때 자동으로 호출되는 메서드이며 여기서 메뉴 코드 입력
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // xml 로 메뉴 구성
        //menuInflater.inflate(R.menu.main_menu, menu)

        // 코드로 메뉴 구성
        menu?.add(Menu.NONE, Menu.FIRST, Menu.NONE, "코드메뉴 1")
        //menu?.add(Menu.NONE, Menu.FIRST+1, Menu.NONE, "코드메뉴 2")
        // 하위 메뉴 구성하는 경우
        val sub = menu?.addSubMenu("코드메뉴 2")
        sub?.add(Menu.NONE, Menu.FIRST+10, Menu.NONE, "코드메뉴 2-1")
        sub?.add(Menu.NONE, Menu.FIRST+20, Menu.NONE, "코드메뉴 2-2")
        menu?.add(Menu.NONE, Menu.FIRST+2, Menu.NONE, "코드메뉴 3")

        return true
    }

    // 사용자가 메뉴를 선택했을 때 자동으로 호출되는 메서드이다
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            Menu.FIRST -> {
                binding.textView.text = "코드 메뉴 1을 선택했습니다."
            }
            Menu.FIRST+10 -> {
                binding.textView.text = "코드 메뉴 2-1을 선택했습니다."
            }
            Menu.FIRST+20 -> {
                binding.textView.text = "코드 메뉴 2-2를 선택했습니다."
            }
            Menu.FIRST+2 -> {
                binding.textView.text = "코드 메뉴 3을 선택했습니다."
            }
        }


        // 리턴값은 별 상관없으므로 그래도 놔둔다.
        return super.onOptionsItemSelected(item)
    }
}