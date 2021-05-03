package com.kye.ipc

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.os.SystemClock
import android.util.Log

class IPCService : Service() {

    var value = 0
    var thread: ThreadClass? = null
    var binder : IBinder = LocalBinder()

    override fun onBind(intent: Intent): IBinder {

        return binder // LocalBinder()의 반환값 즉, 서비스 주소값을 반환
        // 반환값은 액티비티에서 커넥션을 통해 받을 수 있다.
    }

    // 스레드 가동
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread = ThreadClass()
        thread?.start() // 스레드를 먼저 가동 시키고 서비스 접속해서 데이터를 가져오는 순서로 진행
        return super.onStartCommand(intent, flags, startId)
    }

    // 무한루프 스레드 정의
    inner class ThreadClass : Thread() {
        override fun run() {
            while (true) {
                SystemClock.sleep(1000)
                Log.d("test1", "value : ${value}")
                value++
            }
        }
    }

    // IPC서비스는 OS가 실행하기 때문에 서비스에 직접 접속이 어렵다
    // 따라서 그 서비스에 속해 있는 getNumber()함수를 이용할 수 없다. 이때에 액티비티와 서비스를
    // 이어주는 중간 매개체가 필요하며 이를 class 로 만든다.
    inner class LocalBinder : Binder() {
        fun getService() : IPCService{
            return this@IPCService // OS가 생성한 자기자신인 IPC서비스의 주소값을 반환
        }
    }


    // Activity에서 서비스에 접속하고 데이터를 받을때 사용 할 함수
    fun getNumber(): Int {
        return value
    }

}