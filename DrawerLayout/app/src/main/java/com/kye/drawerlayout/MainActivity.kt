package com.kye.drawerlayout

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity(){//}, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    // 메뉴를 6개 만들었기 때문에 Fragment 객체를 6개 생성ㅇ
    var sub1 = SubFragment()
    var sub2 = SubFragment()
    var sub3 = SubFragment()
    var sub4 = SubFragment()
    var sub5 = SubFragment()
    var sub6 = SubFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // activity_main.xml에서 NavigationView태그에서 android:id="@+id/nav_view" 이 id로
        // app:headerLayout="@layout/nav_header_main" 여기에서 nav_header_main.xml을 인식한다.
        // 헤더뷰를 여러개 셋팅할 수 있는데 여기서는 하나만 셋팅했기 때문에 인덱스는 0 을 입력
        var header_view = nav_view.getHeaderView(0)

        // 헤더에 이미지를 넣는다 (이미지를 drawable폴더로 복사해 놓는다)
        header_view.setBackgroundResource(com.kye.drawerlayout.R.drawable.header_backgroud)

        // header view안에 있는 이미지를 변경한다. (nav_header_main.xml에 있는 header_img1)
        header_view.header_img1.setImageResource(R.drawable.ground)

        // header view안에 있는 텍스트를 변경한다. (nav_header_main.xml에 있는 header_text1)
        header_view.header_text1.setTextColor(Color.BLACK)
        header_view.header_text1.text = "header text1"
        header_view.header_text2.setTextColor(Color.BLACK)
        header_view.header_text2.text = "header text2"

        // menu 구성
        // activity_main.xml에서 NavigationView태그에서 android:id="@+id/nav_view" 이 id로
        // app:menu="@menu/activity_main_drawer" 에서 activity_main_drawer.xml 인식




        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main2, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
/*
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        // activity_main.xml 의 <include layout="@layout/app_bar_main"에서 app_bar_main.xml을 포함
        // app_bar_main.xml의 <include layout="@layout/content_main" />에서 content_main.xml을 포함
        // content_main.xml이 메뉴선택 시 보여주는 화면 임

        var tran = supportFragmentManager.beginTransaction() // Fragment Manager를 얻어온다.

        // 메뉴 6개에 대해 Fragment 객체 할당
        when(item.itemId){
            R.id.menu1_1 -> {
                sub1.str1 = "sub1 Fragment"
                tran.replace(R.id.nav_host_fragment, sub1)
            }
            R.id.menu1_2 -> {
                sub2.str1 = "sub2 Fragment"
                tran.replace(R.id.nav_host_fragment, sub2)
            }
            R.id.menu2_1 -> {
                sub3.str1 = "sub3 Fragment"
                tran.replace(R.id.nav_host_fragment, sub3)
            }
            R.id.menu2_2 -> {
                sub4.str1 = "sub4 Fragment"
                tran.replace(R.id.nav_host_fragment, sub4)
            }
            R.id.menu3_1 -> {
                sub5.str1 = "sub5 Fragment"
                tran.replace(R.id.nav_host_fragment, sub5)
            }
            R.id.menu3_2 -> {
                sub6.str1 = "sub6 Fragment"
                tran.replace(R.id.nav_host_fragment, sub6)
            }

        }

        return true
    }

 */

}