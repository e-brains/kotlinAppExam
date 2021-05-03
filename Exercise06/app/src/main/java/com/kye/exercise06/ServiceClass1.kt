package com.kye.exercise06

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.util.Log

class ServiceClass1 : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    // 서비스 테스트용 쓰레드를 처리하는 코드를 여기에 추가
    // 서비스가 시작되면 이 메서드(onStartCommand)가 최초에 자동으로 호출된다.
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        //쓰레드 서비스 가동
        var thread = ThreadClass()
        thread.start()  // 이 메서드가 호출되면 OS는 서비스 객체를 만들고 백그라운드에서 실행

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("test1", "서비스 실행 종료")
    }

    // 서비스 테스트용 쓰레드 만들기
    inner class ThreadClass : Thread(){
        override fun run() {
            var idx = 0
            while (idx < 10){  // (총10회)
                SystemClock.sleep(1000) // 1초 마다 출력
                var time = System.currentTimeMillis()
                Log.d("test1", "Service Running : ${time}")
                idx++
            }
        }
    }
}