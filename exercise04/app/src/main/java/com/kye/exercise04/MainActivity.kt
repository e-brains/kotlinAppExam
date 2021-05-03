package com.kye.exercise04


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.kye.exercise04.R.*
import com.kye.exercise04.databinding.ActivityMainBinding

/*********************************************
 * Style Notivication
 *********************************************/
class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //Big Picture Style (내가 만든 이미지 넣기)
        binding.button.setOnClickListener { view ->
            var builder = getNotificationBuilder("style", "style Notification")
            builder.setContentTitle("Big Picture")
            builder.setContentText("Big Picture Notivication")
            builder.setSmallIcon(android.R.drawable.ic_menu_search)

            //Notification에 사용할 이미지를 bitmap 객체로 만들어서 넣기
            var big = NotificationCompat.BigPictureStyle(builder)
            var bitmap = BitmapFactory.decodeResource(resources, drawable.ground)
            big.bigPicture(bitmap)
            big.setBigContentTitle("Big Content Title")
            big.setSummaryText("Summary Text")

            //Notification 생성
            var notification = builder.build()
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(10, notification)
        }

        //Big Text Style (장문의 글을 입력)
        binding.button2.setOnClickListener { view ->
            var builder = getNotificationBuilder("style", "style Notification")
            builder.setContentTitle("Big Text")
            builder.setContentText("Big Text Notivication")
            builder.setSmallIcon(android.R.drawable.ic_menu_search)

            //Notification에 사용할 이미지를 bitmap 객체로 만들어서 넣기
            var big = NotificationCompat.BigTextStyle(builder)
            big.setSummaryText("Summary Text")
            big.setBigContentTitle("Big Content Title")
            big.bigText("동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세 ")

            //Notification 생성
            var notification = builder.build()
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(20, notification)
        }

        //InBox Style
        binding.button3.setOnClickListener { view ->
            var builder = getNotificationBuilder("style", "style Notification")
            builder.setContentTitle("InBox")
            builder.setContentText("InBox Notivication")
            builder.setSmallIcon(android.R.drawable.ic_menu_search)

            //Notification에 사용할 이미지를 bitmap 객체로 만들어서 넣기
            var inbox = NotificationCompat.InboxStyle(builder)
            inbox.setSummaryText("Summary Text")
            inbox.addLine("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            inbox.addLine("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb")
            inbox.addLine("cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc")
            inbox.addLine("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd")

            //Notification 생성
            var notification = builder.build()
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.notify(20, notification)
        }

    }

    //Notification builder 객체를 가져오는 함수
    fun getNotificationBuilder(id:String, name:String):NotificationCompat.Builder{

        var builder : NotificationCompat.Builder? = null

        //버전이 오레오 8.0 이상이면
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(this, id)
        }else{
            builder = NotificationCompat.Builder(this)
        }

        return builder
    }

}