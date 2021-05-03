package com.kye.tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kye.tablayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // Fragment를 담아 둘 ArrayList객체 생성
    var frag_list = ArrayList<SubFragment>()

    // 각 Fragment의 타이틀을 담아둘 배열 객체 생성
    var title_list = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 액션바 없애기
        supportActionBar?.hide()

        //
        for (i in 0..9){
            var sub = SubFragment()
            sub.str1 = "Sub ${i+1}" // Fragment의 textView 뷰에 보여줄 문자열을 str1에 담는다.

            frag_list.add(sub)  // textView 뷰에 보여줄 문자열을 배열에도 담는다.
            title_list.add("tab ${i+1}")

        }

        supportFragmentManager
        // ViewPager 의 어댑터를 셋팅
        //binding.pager.adapter = PagerAdapter(supportFragmentManager)
       // binding.tabs.setupWithViewPager()
    }


    // pagerView에 셋팅할 어댑터를 만든다.
    // 파라미터 frgm을 부모클래스의 생성자에 넘겨준다.
     inner class PagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm){

        // Fragmentm의 갯수를 반환한다. (나중에 탭의 갯수가 이것으로 결정된다.)
        override fun getCount(): Int {
            return frag_list.size
        }

        // pager를 통해 보여줄 Fragment를 반환
        // tab 이눌러지면 몇번째인지 값이 넘어온다 이때 해당 인덱스의 Fragment를 반환한다.
        override fun getItem(position: Int): Fragment {
            return frag_list.get(position)
        }

        // 탭 버튼에 나타낼 문자열
        override fun getPageTitle(position: Int): CharSequence? {
            return title_list.get(position)
        }

    }
 }