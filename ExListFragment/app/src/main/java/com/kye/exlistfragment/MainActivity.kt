package com.kye.exlistfragment

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.kye.exlistfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @RequiresApi(Build.VERSION_CODES.O)
    var list_fragment = ExListFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var tran = supportFragmentManager.beginTransaction()
        tran.replace(com.kye.exlistfragment.R.id.container, list_fragment)
        tran.commit()


    }
}