package com.kye.exercise06

import android.app.IntentService
import android.content.Intent
import android.os.SystemClock
import android.util.Log

class ServiceClass2 : IntentService("ServiceClass2") {

    // 서비스가 시작되면 제일 처음 onStartCommand메서드가 호출된다.
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    // onStartCommand메서드가 호출된 이후 onHandleIntent메서드가 호출된다.
    // 쓰레드를 별도로 만드는 것이 아니라 쓰레드에 사용할 코드를 여기에 만들면 된다.
    override fun onHandleIntent(intent: Intent?) {
        var idx = 0
        while (idx < 10){  // 10회
            SystemClock.sleep(1000)  // 1초
            var time = System.currentTimeMillis()
            Log.d("test1", "Intent Service Running : ${time}")
            idx++
        }
    }
}