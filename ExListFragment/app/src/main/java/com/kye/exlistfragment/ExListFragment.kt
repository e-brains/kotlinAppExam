package com.kye.exlistfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.kye.exlistfragment.databinding.FragmentExListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ExListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ExListFragment : androidx.fragment.app.ListFragment() {

    val binding by lazy { FragmentExListBinding.inflate(layoutInflater) }
    var list = arrayOf("항목1", "항복2", "항복3", "항목4", "항목5")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 어댑터 만들기
        var adapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_1, list)
        listAdapter = adapter

        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_ex_list, container, false)
        return binding.root
    }

    // 선택한 항목을 textView에 display한다.
    override fun onListItemClick(l: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        var str = list[position]
        binding.textView.text = str
    }
}