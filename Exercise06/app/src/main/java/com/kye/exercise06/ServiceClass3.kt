package com.kye.exercise06

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.os.SystemClock
import android.util.Log
import androidx.core.app.NotificationCompat

class ServiceClass3 : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // Foreground Service는 서비스 시작 후 5초 이내에 notification을 띄워 주지 않으면 강제 종료
        // 되므로 여기서 notification 관련 코드를 추가한다.
        var builder : NotificationCompat.Builder? = null

        // 버전별 분기 (오레오 버전 이상이면 channel 셋팅이 필요함)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var channel = NotificationChannel("test1", "Service", NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(this, "test1")
        }else{
            builder = NotificationCompat.Builder(this)
        }

        //알림 메시지를 만들어서 등록
        builder?.setSmallIcon(android.R.drawable.ic_menu_search)
        builder?.setContentTitle("Foreground 서비스 가동 ")
        builder?.setContentText("Foreground 서비스가 가동 중입니다.")
        var notification = builder.build()

        startForeground(10, notification)

        // 서비스가 실행되면 스레드가 시작된다.
        var thread = ThreadClass()
        thread.start()
        return super.onStartCommand(intent, flags, startId)
    }

    // 테스트 용 스레드 만들기
    inner class ThreadClass : Thread(){
        override fun run() {
            var idx = 0
            while (idx < 10){
                SystemClock.sleep(1000)
                var time = System.currentTimeMillis()
                Log.d("test1", "Foreground Service Running : ${time}")
                idx++
            }
        }
    }
}