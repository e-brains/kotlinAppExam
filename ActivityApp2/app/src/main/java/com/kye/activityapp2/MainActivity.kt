package com.kye.activityapp2

/*********************************************
 * 다른 애플리케이션의 액티비티 실행하기 App2
 *********************************************/
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.activityapp2.databinding.ActivityMainBinding
import com.kye.activityapp2.databinding.ActivitySecondBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}