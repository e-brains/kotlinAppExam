package com.kye.myapp1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("kye1", "on create")
    }

    override fun onStart() {
        super.onStart()
        Log.d("kye1", "on start")
    }

    override fun onResume() {
        super.onResume()
        Log.d("kye1","on resume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("kye1", "on restart")
    }

    override fun onPause() {
        super.onPause()
        Log.d("kye1", "on pause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("kye1", "on stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("kye1", "on destroy")

    }

}