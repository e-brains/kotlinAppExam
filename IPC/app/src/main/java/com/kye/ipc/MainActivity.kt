package com.kye.ipc

/*********************************************
 * IPC
 *********************************************/
import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import com.kye.ipc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // ipc서비스의 주소값을 담을 수 있는 변수 선언
    var ipc_service: IPCService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 이 앱을 실행할 때 마다 자동으로 서비스에 접속, 서비스가 시작되지 않았으면 시작을 시킨 다음 접속
        // 서비스가 시작된 상태인 경우는 서비스를 가동시키지 않고 접속

        // 우선 intent를 만든다.
        var intent = Intent(this, IPCService::class.java)

        // 만약 서비스가 실행 중이지 않으면 실행 시킨다. (패키지명.서비스명)
        if (isServiceRunning("com.kye.ipc.IPCService") == false) {
            startService(intent)
        }

        // 서비스에 접속하기
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE)

        // IPC서비스에서 값 가져오는 버튼
        binding.button.setOnClickListener { view ->
            var value = ipc_service?.getNumber()
            binding.textView.text = "value : ${value}"
        }

    }

    // 액티비티 닫을때 서비스 접속 해제
    override fun onDestroy() {
        super.onDestroy()
        unbindService(mConnection)
    }

    // 서비스가 동작 상태인지 여부 체크하는 함수
    fun isServiceRunning(name: String): Boolean {

        var manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        // 실행중인 서비스 목록을 루프를 돌면서 name으로 넘어온 서비스와 비교
        for (srvc: ActivityManager.RunningServiceInfo in manager.getRunningServices(Int.MAX_VALUE)) {

            // name과 일치하는 서비스가 있으면
            if (srvc.service.className.equals(name)) {
                return true //실행중인 서비스 임
            }
        }

        return false // 실행중이 아님
    }

    // ipc 서비스의 주소값을 읽기 위한 서비스 커넥션 객체 생성
    private val mConnection = object : ServiceConnection {

        // 접속할 때 자동으로 호출되는 메서드
        // 여기서 service: IBinder 파라미터는 IPCService의 onBind메서드의 리턴값 (주소값)
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as IPCService.LocalBinder
            ipc_service = binder.getService() // 주소 가져오기
        }

        // 해제할 때 호출되는 메서드
        override fun onServiceDisconnected(name: ComponentName?) {
            ipc_service = null
        }
    }

}