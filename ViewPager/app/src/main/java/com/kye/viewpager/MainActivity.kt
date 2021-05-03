package com.kye.viewpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    // 여러가지 뷰를 넣을 수 있는 배열리스트를 만든다
    var viewList = ArrayList<View>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰 리스트에 fragment화면들을 추가한다.
        viewList.add(layoutInflater.inflate(R.layout.fragment_home, null))
        viewList.add(layoutInflater.inflate(R.layout.fragment_favorite, null))
        viewList.add(layoutInflater.inflate(R.layout.fragment_profile, null))

        // viewPager 어댑터는 커스텀으로 만들어야함
        viewPager.adapter = ViewPagerAcapter()

        /********************************
        * 상단 메뉴 추가 및 동기화 작업
        ********************************/
        // tabLayout과 viewPager를 연결시킨다.
        tabLayout.setupWithViewPager(viewPager)

        // tabLayout에 Text를 동적으로 할당한다.
        tabLayout.getTabAt(0)?.setText("Home")
        tabLayout.getTabAt(1)?.setText("Favorite")
        tabLayout.getTabAt(2)?.setText("Profile")

        // 아이콘 넣기
        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_baseline_home_24)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_baseline_favorite_24)
        tabLayout.getTabAt(2)?.setIcon(R.drawable.ic_baseline_person_24)

        /********************************
         * 하단 메뉴 추가 및 동기화 작업
         ********************************/
        // 선택된 페이지뷰의 인덱스로 해당 메뉴를 동기화 시켜준다.
        viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener(){
            // 선택된 페이지 뷰의 인덱스가 넘어온다.
            override fun onPageSelected(position: Int) {

                // 선택된 페이지뷰의 인덱스로 해당 메뉴를 선택해 준다.
                when(position){
                    0 -> bottomNavigationView.selectedItemId = R.id.home
                    1 -> bottomNavigationView.selectedItemId = R.id.favorite
                    2 -> bottomNavigationView.selectedItemId = R.id.profile
                }
            }
        })

        // 선택된 메뉴의 인덱스로 페이지뷰를 동기화 시켜준다.
        bottomNavigationView.setOnNavigationItemSelectedListener {
            // 기본 파라미터 it으로 메뉴의 id를 가져올 수 있으므로
            when(it.itemId){
                R.id.home -> viewPager.setCurrentItem(0)
                R.id.favorite -> viewPager.setCurrentItem(1)
                R.id.profile -> viewPager.setCurrentItem(2)
            }

            return@setOnNavigationItemSelectedListener true
        }

    }

    // viewList 전역 변수를 쉽게 사용하기 위해서 inner class로 선언해서 사용한다.
    inner class ViewPagerAcapter : PagerAdapter(){
        // viewList에 들어 있는 뷰의 갯수를 반환한다.
        override fun getCount() = viewList.size

        // view와 object가 같으냐에 따라서 불린 값 리턴
        override fun isViewFromObject(view: View, `object`: Any) = view == `object`

        // 페이지를 넘길때 마다 생성하는 메서드
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            // 현재 뷰를 가져온다.
            var curView = viewList[position] // 처음에는 0번째 뷰인 Home 넘길때 마다 1, 2번째 뷰
            viewPager.addView(curView) // 뷰페이저에 현재 뷰를 계속 추가
            return curView
        }

        // instantiateItem메서드의 viewPager.addView(curView)에 의해 뷰가 무수히 늘어나면 안되기 때문에
        // 없애 주는 메서드도 필요하다. `object`-> 사라질 오브젝트
        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            // `object`를 View타입으로 바꾼다음 제거
            viewPager.removeView(`object` as View)
        }
    }
}