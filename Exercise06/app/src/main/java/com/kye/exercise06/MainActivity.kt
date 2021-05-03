package com.kye.exercise06

/*********************************************
 * IPC
 *********************************************/
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.exercise06.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}


/*********************************************
 * Foreground Service
 *********************************************/
//import android.content.Intent
//import android.content.IntentFilter
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.exercise06.databinding.ActivityMainBinding
//import java.util.jar.Manifest
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    // 여기서 미리 선언하는 이유는 종료와 같이 사용하기 위해서이다.
//    var service_intent: Intent? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        // 서비스 시작 버튼
//        binding.button.setOnClickListener { view ->
//            service_intent = Intent(this, ServiceClass1::class.java)
//            startService(service_intent)
//            finish() // 바로 Activity를 종료해도 서비스는 수행된다.
//        }
//
//        // 서비스 종료 버튼 (서비스는 종료되지만 쓰레드는 계속 수행되므로 쓰레드 사용 시 고려)
//        binding.button2.setOnClickListener { view ->
//            stopService(service_intent)
//        }
//
//        // Intent Service Start
//        binding.button3.setOnClickListener { view ->
//            service_intent = Intent(this, ServiceClass2::class.java)
//            startService(service_intent)
//            finish()
//        }
//
//        // Forground Service
//        binding.button4.setOnClickListener { view ->
//            service_intent = Intent(this, ServiceClass3::class.java)
//
//            // 8.0을 기준으로 버전별로 호출되는 메서드가 틀림
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //오레오 버전 이상이면
//
//                // 서비스 시작 후 5초 안에 notification을 띄워 주지 않으면 서비스가 강제 종료됨
//                // 단, 서비스가 강제 종료되어도 스레드는 그대로 수행됨
//                startForegroundService(service_intent)
//            }else {
//                startService(service_intent)
//            }
//
//            finish()
//        }
//    }
//}

/*********************************************
 * System Message
 *********************************************/
//import android.content.Intent
//import android.content.IntentFilter
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.exercise06.databinding.ActivityMainBinding
//import java.util.jar.Manifest
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //Manifest파일에 등록된 권한 목록
//    var permission_list = arrayOf(
//        android.Manifest.permission.READ_PHONE_STATE,
//        android.Manifest.permission.RECEIVE_SMS
//    )
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //권한 승인을 얻는 팝업 함수
//        checkPermission()
//
//    }
//
//    //권한체크를 위한 함수
//    fun checkPermission(){
//        //마쉬멜로우 버전 보다 작으면 권한체크를 물어보지 않고 자동 승인이됨
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
//            return
//        }
//        //권한 체크
//        for (permission:String in permission_list){
//            var chk = checkCallingOrSelfPermission(permission)
//            //허용되지 않은 권한을 발견했다면
//            if (chk == PackageManager.PERMISSION_DENIED){
//                requestPermissions(permission_list, 0)
//                break
//            }
//        }
//    }
//}


/*********************************************
 * Broad Cast Receiver - Exercise06앱
 *********************************************/
//import android.content.Intent
//import android.content.IntentFilter
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.exercise06.databinding.ActivityMainBinding
//import java.util.jar.Manifest
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //암시적 인텐트 사용을 위해 코드 추가
//    var exe06:TestReceiver? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        // 암시적 인텐트 사용을 위해 함수 호출
//        addReceiver()
//
//        // 같은 앱내에서 리시버를 호출
//        binding.button.setOnClickListener { view ->
//
//            // 명시적 인텐트 : 동작시키고자 하는 클래스명 직접 기술
//            var intent = Intent(this, TestReceiver::class.java)
//
//            //데이터 셋팅
//            intent.putExtra("data1", 100)
//            intent.putExtra("data2", 11.11)
//            intent.putExtra("data3", "exercise06 동일 앱 내에서 리시버 호출")
//
//            sendBroadcast(intent)
//        }
//
//    }
//
//    // 안드로이드 8.0에서 암시적 인텐트를 사용하기 위한 함수
//    // 코드를 통해 암시적 인텐트를 사용했다가 이후 제거를 하는 형태로 사용해야 함
//    // 해당 리시버를 가지고 있는 앱이 실행되고 있는 상태여야 한다.
//    fun addReceiver(){
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O){ //오레오 버전보다 작으면 함수를 강제종료
//            return
//        }
//        // AndroidManifest.xml에 등록된 intent-filter명을 이용하여 코드상에서 암시적 인텐트 생성
//        exe06 = TestReceiver()
//        var filter = IntentFilter("com.kye.exercise06")
//        registerReceiver(exe06, filter)
//    }
//
//    //앱이 종료되면 암시적 인텐트 제거
//    override fun onDestroy() {
//        super.onDestroy()
//        if (exe06 != null){
//            unregisterReceiver(exe06)
//            exe06 = null
//        }
//    }
//
//}

/*********************************************
 * Activity Action (안드로이드의 공개된 Activity 호출)
 *********************************************/
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.exercise06.databinding.ActivityMainBinding
//import java.util.jar.Manifest
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //전화걸기 권한 목록
//    var permission_list = arrayOf(
//        android.Manifest.permission.CALL_PHONE
//    )
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //권한 체크를 위한 메서드 호출
//        checkPermission()
//
//        //구글맵 띄우기
//        binding.button.setOnClickListener { view ->
//            //url을 넣으면 웹브라우저가 뜨고 위도과 경도를 넣으면 맵이 뜬다.
//            var uri = Uri.parse("geo:37.243243, 131.861601") //위도 , 경도
//            var intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
//        }
//
//        //웹사이트 띄우기
//        binding.button2.setOnClickListener { view ->
//            var uri = Uri.parse("http://naver.com")
//            var intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
//        }
//
//        //전화 다이얼 띄우기
//        binding.button3.setOnClickListener { view ->
//            var uri = Uri.parse("tel:000000000000")
//            var intent = Intent(Intent.ACTION_DIAL, uri)
//            startActivity(intent)
//        }
//
//        //전화걸기 (자동) 요금과 관련 있어서 필히 사용자의 권한 승인이 필요함
//        binding.button4.setOnClickListener { view ->
//            var uri = Uri.parse("tel:000000000000")
//            var intent = Intent(Intent.ACTION_CALL, uri)
//            startActivity(intent)
//        }
//    }
//
//    //권한체크를 위한 메서드
//    fun checkPermission(){
//        //마쉬멜로우 버전 보다 작으면 권한체크를 물어보지 않고 자동 승인이됨
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
//            return
//        }
//        //권한 체크
//        for (permission:String in permission_list){
//            var chk = checkCallingOrSelfPermission(permission)
//            //허용되지 않은 권한을 발견했다면
//            if (chk == PackageManager.PERMISSION_DENIED){
//                requestPermissions(permission_list, 0)
//                break
//            }
//        }
//    }
//}