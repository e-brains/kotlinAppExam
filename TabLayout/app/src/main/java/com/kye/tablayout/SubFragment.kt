package com.kye.tablayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kye.tablayout.databinding.FragmentSubBinding

/**
 * A simple [Fragment] subclass.
 * Use the [SubFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubFragment : Fragment() {

    val binding by lazy { FragmentSubBinding.inflate(layoutInflater) }
    var str1 : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.textView.text = str1

        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_sub, container, false)
        return binding.root
    }


}