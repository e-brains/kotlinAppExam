package com.kye.exercise05

/*********************************************
 * 다른 애플리케이션의 액티비티 실행하기
 *********************************************/
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.exercise05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}

/*********************************************
 * Parcelable를 이용한 객체 전달 하기
 *********************************************/
//import android.app.Activity
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.exercise05.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //Activity를 구분하기 위한 구분 상수값 정의
//    val SECOND_ACTIVITY = 1
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //객체를 전달하기 위한 코드
//        binding.button.setOnClickListener { view ->
//
//            // SecondActivity로 넘길 객체 정보 셋팅
//            var t1 = TestClass()
//            t1.data10 = 100
//            t1.data20 = "문자열 1111"
//
//            // Intent 생성
//            var intent = Intent(this, SecondActivity::class.java)
//
//            // 여기서는 TestClass라는 객체를 넘기는 것처럼 보이지만
//            // 실제는 속성이 parcelable을 통해 넘어간다.
//            intent.putExtra("test1", t1)
//
//            startActivityForResult(intent, SECOND_ACTIVITY)
//        }
//    }
//
//    // SecondActivity에서 반환한 데이터를 읽기 위해 생성
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        // SecondActivity에서 TestClass라는 객체를 반환해 오는 경우 parcelable 읽기
//        if (resultCode == Activity.RESULT_OK){
//            var t2 = data?.getParcelableExtra<TestClass>("test2")
//
//            binding.textView.text = "t2.data10 : ${t2?.data10}\n"
//            binding.textView.append("t2.data20 : ${t2?.data20}")
//        }
//    }
//}

/*********************************************
 * Intent 객체를 통한 데이터 전달하고 받기
 *********************************************/
//import android.app.Activity
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.exercise05.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //Activity를 구분하기 위한 구분 상수값 정의
//    val SECOND_ACTIVITY = 1
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //SecondActivity호출 (Intent객체를 생성해야 함)
//        binding.button.setOnClickListener { view ->
//            //SecondActivity::class.java => SecondActivity를 클래스로 만들라는 의미
//            var intent = Intent(this, SecondActivity::class.java)
//
//            //데이터 셋팅 (모든 자료형 지원)
//            intent.putExtra("data1", 100)
//            intent.putExtra("data2", 11.11)
//            intent.putExtra("data3", true)
//            intent.putExtra("data4", "문자열")
//
//            //startActivity(intent) //반환값이 없다.
//            startActivityForResult(intent, SECOND_ACTIVITY) //반환값을 확인할 수 있다.
//        }
//
//    }
//
//    //SecondActivity에서 반환한 데이터를 읽기 위해 생성
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        //SecondActivity에서 RESULT_OK 코드로 반환한 intent에 셋팅한 데이터를 읽는다.
//        if (resultCode == Activity.RESULT_OK){
//            var value1 = data?.getIntExtra("value1", 0)
//            var value2 = data?.getDoubleExtra("value2", 0.0)
//            var value3 = data?.getBooleanExtra("value3", false)
//            var value4 = data?.getStringExtra("value4")
//
//            binding.textView.text = "value1 : ${value1}\n"
//            binding.textView.append("value2 : ${value2}\n")
//            binding.textView.append("value3 : ${value3}\n")
//            binding.textView.append("value4 : ${value4}")
//        }
//
//    }
//
//}

/*********************************************
 * OnResultActivity
 *********************************************/
//import android.app.Activity
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.exercise05.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //Activity를 구분하기 위한 구분 상수값 정의
//    val SECOND_ACTIVITY = 1
//    val THIRD_ACTIVITY = 2
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //SecondActivity호출 (Intent객체를 생성해야 함)
//        binding.button.setOnClickListener { view ->
//            //SecondActivity::class.java => SecondActivity를 클래스로 만들라는 의미
//            var intent = Intent(this, SecondActivity::class.java)
//            startActivityForResult(intent, SECOND_ACTIVITY) //SECOND_ACTIVITY은 Activity 구분 값
//        }
//
//        //ThirdActivity를 호출
//        binding.button5.setOnClickListener { view ->
//            var intent = Intent(this, ThirdActivity::class.java)
//            startActivityForResult(intent, THIRD_ACTIVITY)
//        }
//
//        //종료 버튼 처리
//        binding.button3.setOnClickListener { view ->
//            finish() //현재 Activity 종료 , Back Stack에 아무것도 없으면 완전 종료
//        }
//
//    }
//
//    //startActivityForResult()호출해서 다른 Activity를 실행 후 돌아오면 수행되는 메서드
//    override fun onActivityResult(requestCode: Int, //Activity 구분 값
//                                  resultCode: Int,  //해당 Activity에서의 작업 결과
//                                  data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        when(requestCode){
//            SECOND_ACTIVITY -> {
//                binding.textView.text = "Second Activity에서 돌아옴\n"
//
//                //Second Activity에서 수행된 결과 값 체크
//                when(resultCode){
//                    Activity.RESULT_OK -> {
//                        binding.textView.append("결과값 : RESULT_OK")
//                    }
//                    Activity.RESULT_CANCELED -> {
//                        binding.textView.append("결과값 : RESULT_CANCELED")
//                        //Second Activity화면에서 뒤로가기를 하면 자동으로 RESULT_CANCELED값으로 온다.
//                    }
//                    Activity.RESULT_FIRST_USER -> {
//                        binding.textView.append("결과값 : RESULT_FIRST_USER")
//                    }
//                }
//            }
//            THIRD_ACTIVITY -> {
//                binding.textView.text = "Third Activity에서 돌아옴"
//            }
//        }
//
//    }
//}

/*********************************************
 * Activity 실행하기
 *********************************************/
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.exercise05.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //SecondActivity호출 (Intent객체를 생성해야 함)
//        binding.button.setOnClickListener { view ->
//            //SecondActivity::class.java => SecondActivity를 클래스로 만들라는 의미
//            var intent = Intent(this, SecondActivity::class.java)
//            startActivity(intent)
//        }
//
//        //종료 버튼 처리
//        binding.button3.setOnClickListener { view ->
//            finish() //현재 Activity 종료 , Back Stack에 아무것도 없으면 완전 종료
//        }
//
//    }
//
//}

/*********************************************
 * RunOnUiThread
 *********************************************/
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.SystemClock
//import android.util.Log
//import com.kye.exercise05.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    var isRunning = false
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //버튼 클릭시 textView에 문자열 넣기
//        binding.button.setOnClickListener { view ->
//            var time = System.currentTimeMillis()
//            binding.textView.text = "현재시간 버튼 클릭 : ${time}"
//        }
//
//        //쓰레드 객체 생성 및 가동
//        isRunning = true
//        var thread = ThreadClass()
//        thread.start()
//
//
//    }
//
//    //뒤로가기 클릭 시 쓰레드 정지
//    override fun onDestroy() {
//        super.onDestroy()
//        isRunning = false
//    }
//
//
//    //thread class 정의 (일반 쓰레드)
//    inner class ThreadClass : Thread(){
//        override fun run() {
//            while (isRunning){
//                SystemClock.sleep(500)
//                var time = System.currentTimeMillis()
//                Log.d("test1", "쓰레드 : ${time}")
//
//                //오류 없는 화면처리를 위해서 RunOnUiThread사용 (람다식 가능)
//                //일반 쓰레드 내부이지만 이 부분은 main Thread가 가져가서 실행시키기 때문에 오류 없이 작동함
//                runOnUiThread {
//                    binding.textView2.text = "쓰레드 : ${time}"
//                }
//            }
//        }
//    }
//
//}



/*********************************************
 * AsyncTask
 *********************************************/
//import android.os.AsyncTask
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.SystemClock
//import android.util.Log
//import com.kye.exercise05.databinding.ActivityMainBinding

//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        binding.button.setOnClickListener { view ->
//            var time = System.currentTimeMillis()
//            binding.textView.text = "현재시간 버튼 틀릭 : ${time}"
//        }
//
//        var sync = AsyncTaskClass()
//        //doInBackground메서드의 파라미터 타입과 일치, doInBackground메서드에서는 배열로 받게 됨
//        sync.execute(10, 20)
//
//
//    }
//
//    //비동기처리 클래스 정의
//    inner class AsyncTaskClass : AsyncTask<Int, Long, String>() {
//        override fun onPreExecute() {
//            super.onPreExecute()
//            binding.textView2.text = "AsyncTask 가동"
//        }
//
//        //여기 있는 코드는 일반 쓰레드로 동작한다.
//        //네트워크 작업이나 5초 이상 걸리는 작업은 여기서 작업하도록 한다.
//        //단, 8.0이하 버전에서 화면 작업은 불가하다
//        //AsyncTaskClass의 첫번째 제네릭 String 타입과 일치
//        override fun doInBackground(vararg params: Int?): String {
//            //sync.execute(10, 20) 에서 호출되면 여기서는 Int의 배열 형태로 받게 됨
//            var a1 = params[0]!! //null을 허용하는 변수를 null을 허용하지 않는 변수에 할당하려면 !! 두개
//            var a2 = params[1]!! //params[0]은 10, params[1]은 20
//
//            for (idx in 0..9){
//                SystemClock.sleep(1000)
//                a1++
//                a2++
//                Log.d("test1","idx : ${idx}, a1 : ${a1}, a2 : ${a2}")
//
//                //여기서 화면처리 하면 8.0이하 버전에서는 오류 발생
//                //binding.textView.text = "idx : ${idx}, a1 : ${a1}, a2 : ${a2}"
//
//                //오류를 막기 위해 publishProcess호출하여 onProgressUpdate 메서드가 동작하도록 한다.
//                var time = System.currentTimeMillis()
//
//                //time은 Long타입, AsyncTaskClass()의 두번째 제네릭과도 일치
//                //파라미터 타입이 vararg이기 때문에 쉼표를 이용해 나열하면 배열로 넘어감
//                //main thread가 한가 할때 수행한다.
//                publishProgress(time)
//
//            }
//
//            return "수행이 완료되었습니다."
//        }
//
//        //publishProcess 메서드를 호출하면 onProgressUpdate 메서드가 동작하며 이는 Main Thread가 처리
//        //doInBackground 메서드에서 화면처리가 필요할 때 호출
//        //vararg타입의 파라미터이기 땜에 values가 배열로 넘어온다.
//        //AsyncTaskClass의 두번째 제네릭 Long 타입과 일치
//        //main thread가 한가 할때 수행한다.
//        override fun onProgressUpdate(vararg values: Long?) {
//            super.onProgressUpdate(*values)
//            binding.textView2.text = "Async : ${values[0]}"
//        }
//
//        //doInBackground 메서드가 수행 완료된 후 호출된다
//        //AsyncTaskClass의 세번째 제네릭 String 타입과 일치
//        //doInBackground의 return값을 받는다
//        override fun onPostExecute(result: String?) {
//            super.onPostExecute(result)
//            binding.textView2.text = "result : ${result}"
//        }
//
//    }
//
//}
/*********************************************
 * Handler를 통한 화면 처리
// *********************************************/
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.Handler
//import android.os.Message
//import android.os.SystemClock
//import android.util.Log
//import com.kye.exercise05.databinding.ActivityMainBinding
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //Thread의 무한루프를 제어하기 위한 플래그 변수
//    var isRunning = true
//    var handler : DisplayHandler? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //현재시간 버튼 클릭 시 textView에 보여주기
//        binding.button.setOnClickListener { view ->
//            var time = System.currentTimeMillis()
//            binding.textView.text = "현재시간 버튼 클릭 : ${time}"
//        }
//
//        //Handler 객체 생성해주면 TheradClass1()안에  handler?.sendEmptyMessage(0)가 동작
//        handler = DisplayHandler()
//
//        //내가 만든 쓰레드를 start시킨다.
//        isRunning = true
//        var thread = TheradClass1()
//        thread.start()
//
//    }
//
//    //화면에서 뒤로가기 버튼을 클릭 시 발생하는 이벤트에 무한루프 제어를 위한 flag값 셋팅
//    override fun onDestroy() {
//        super.onDestroy()
//        isRunning = false
//    }
//
//    //네트워크나 5초이상 걸리는 작업일 경우 개발자가 직접 쓰레드 클래스를 만들어 써야 한다.
//    //버전 8.0에서는 동시에 화면처리가 가능하지만 그 이하 버전에서는 Handler를 이용해야 한다.
//    inner class TheradClass1 : Thread() {
//        override fun run() {
//
//            var a1 = 10
//            var a2 = 20
//
//            while (isRunning) {
//                SystemClock.sleep(100) //너무 빠르면 알 수 없으니 속도를 좀 줄이자
//                var time = System.currentTimeMillis()
//                Log.d("test1", "쓰레드 : ${time}")
//                //binding.textView2.text = "쓰레드 : ${time}"
//
//                //main thread가 한가 할때 아래 sendEmptyMessage()가
//                //DisplayHandler()클래스의 handleMessage()를 호출하여 수행한다.
//                //handler?.sendEmptyMessage(0) //화면처리만 가능하기 때문에 수정이 필요
//
//                //둘 중에 원하는 방법 사용 가능 (편의를 위해 분기)
//                if (false){
//                    //Message()를 이용한 첫번째 방식 데이터 처리 방식
//                    var msg = Message()
//                    //what은 Handler 클래스에서 작업을 분기하여 나누고 싶을때 구분자로 사용
//                    msg.what = 0
//                    //여기서 구한 시간 값을 Handler에서 사용 , long타입의 값을 object타입으로 넣음
//                    msg.obj = time
//                    handler?.sendMessage(msg)
//                }else{
//                    ////Message()를 이용한 두번째 방식 데이터 처리 방식
//                    var msg2 = Message()
//                    msg2.what = 1
//                    msg2.arg1 = ++a1 //본 클래스가 호출될 때 마다 a1값을 하나 증가 시켜서 할당
//                    msg2.arg2 = ++a2 //본 클래스가 호출될 때 마다 a2값을 하나 증가 시켜서 할당
//                    msg2.obj = "안녕하세요" //문자열 객체를 할당
//                    handler?.sendMessage(msg2)
//                }
//            }
//        }
//    }
//
//    //버전 8.0이하 버전에서 동작하는 Handler를 만든다.
//    //화면에 관련된 작업을 여기서 한다. (데이터 작업은 여기서 하지 않음)
//    inner class DisplayHandler : Handler(){
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//
//            /*
//            //화면처리만 가능하기 때문에 데이터 처리를 위해서는 수정이 필요
//            var time = System.currentTimeMillis()
//            binding.textView2.text = "Handler : ${time}"
//            */
//
//            //데이터 처리를 위해 아래와 같이 수정
//            //binding.textView2.text = "Handler : ${msg?.obj}"
//
//            //핸들어 내부에서 분기할 작업이 있으면 아래와 같이 분기하고 what을 이용하여 구분
//            if (msg?.what == 0){
//                binding.textView2.text = "Handler : ${msg?.obj}"
//            }else if (msg?.what == 1){
//                binding.textView2.text = "arg1 : ${msg?.arg1}, arg2 : ${msg?.arg2}, obj : ${msg?.obj}"
//            }
//
//        }
//    }
//
//}

/*********************************************
 * Handler를 활용한 반복 작업
 *********************************************/
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.Handler
//import android.os.SystemClock
//import com.kye.exercise05.databinding.ActivityMainBinding
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //Handler객체를 담을 변수 생성
//    var handler:Handler? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //현재 시간 버튼 클릭 시 textView에 현재 시간을 보여 준다.
//        binding.button.setOnClickListener { view ->
//            var time = System.currentTimeMillis()
//            binding.textView.text = "현재 시간 버튼 클릭 : ${time}"
//        }
//
//        //Handler객체 생성
//        handler = Handler()
//
//        //100밀리초 마다 현재 시간을 textView2에 출력하기
//        //100밀리초를 sleep하는 것도 main thread 입장에서는 계속 일하는 것이이 때문에 ANR이 발생한다.
////        while (true){
////            SystemClock.sleep(100)
////            var time = System.currentTimeMillis()
////            binding.textView2.text = "현재 시간 무한루프 출력 : ${time}"
////        }
//
//        //핸들러를 이용해서 호출할 thread 클래스 객체를 생성하여 핸들러에게 넘겨준다.
//        //여기서 부터는 안드로이드 OS가 관리하는 Main Thread에 종속되어 관리되기 때문에
//        //5초이상 걸리거나 무한반복 되게 되면 안된다.
//        //무한반복하고 싶다면 내가 만든 ThreadClass1()에서도 handler 처리를 해준다.
//        //그러면 OS가 한가할 때 계속 처리해 준다
//        var thread = ThreadClass1()
//        handler?.post(thread)
//        //handler?.postDelayed(thread, 100) //100밀리 초 단위로 실행
//    }
//
//    //Thread상속 받아서 내가 사용할 Thread클래스 만들기
//    inner class ThreadClass1 : Thread(){
//        override fun run() {
//            var time = System.currentTimeMillis()
//            binding.textView2.text = "Handler : ${time}"
//
//            //핸들러를 이용한 무한반복을 위한 처리 (OS가 한가할 때 계속 처리해 준다)
//            handler?.post(this) //핸들러에게 자기 자신을 넘겨준다.
//            //handler?.postDelayed(this, 100) //100밀리 초 단위로 실행
//
//        }
//    }
//
//}

/*********************************************
 * Thread
 *********************************************/
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.SystemClock
//import android.util.Log
//import com.kye.exercise05.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //Thread의 무한루프를 제어하기 위한 플래그 변수
//    var isRunning = false
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //현재 시각 버튼 리스너
//        binding.button.setOnClickListener { view ->
//            var now = System.currentTimeMillis()
//            binding.textView.text = "현재 시각 버튼 클릭 : ${now}"
//        }
//
//        //현재시각 무한 루프를 걸면 MainActivity가 바빠서 화면을 찍을 수 없게 된다.(ANR발생)
////        while (true){
////            var now = System.currentTimeMillis()
////            binding.textView2.text = "현재 시각 무한 루프 : ${now}"
////        }
//
//        //아래 쓰레드 클래스를 이용하여 Thread로 처리하여  ANR을 막는다.
//        isRunning = true
//        var thread = ThreadClass1()
//        thread.start()
//
//    }
//
//    //Thread를 만들어서 MainActivity가 무한루프 걸리지 않도록 처리하는 클래스
//    inner class ThreadClass1:Thread(){
//        override fun run() {
//            while (isRunning){
//                SystemClock.sleep(100)
//                var now = System.currentTimeMillis()
//                Log.d("test1", "쓰레드 : ${now}")
//
//                binding.textView2.text = "쓰레드 : ${now}"
//            }
//        }
//    }
//
//    //화면에서 아래에 뒤로가기 버튼을 누르면 안드로이드 내부 이벤트인 onDestroy()가 호출되는데
//    //여기서 쓰레드 제어 변수에 false 할당하여 루프를 끝낼 수 있다.
//    override fun onDestroy() {
//        super.onDestroy()
//        isRunning = false
//    }
//
//}

/*********************************************
 * Permission
 *********************************************/
//import android.content.pm.PackageManager
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.exercise05.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //권한 등록을 사용자에게 확인 받기 위한 권한 목록 (manifests에 등록한 권한들)
//    var permission_list = arrayOf(
//        android.Manifest.permission.ACCESS_FINE_LOCATION,
//        android.Manifest.permission.ACCESS_COARSE_LOCATION,
//        android.Manifest.permission.READ_CONTACTS,
//        android.Manifest.permission.WRITE_CONTACTS,
//        android.Manifest.permission.SEND_SMS,
//        android.Manifest.permission.READ_SMS
//    )
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //권할 체크를 해서 비활성되어 있는 것이 있다면 앱이 실행될때 권한승인 여부를 묻는 창이 뜬다.
//        checkPermission()
//
//    }
//
//    //권한 체크 함수 (마시멜로 이상에서 동작, 이하에서는 무동작)
//    fun checkPermission(){
//        //마시멜로 이상에서 동작, 이하에서는 그냥 리턴
//        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
//            return
//        }
//
//        //권한 목록에서 하나씩 꺼내서 활성화 여부를 확인 후 활성화가 안되어 있으면 물어본다.
//        for (permission:String in permission_list){
//            var chk = checkCallingOrSelfPermission(permission)
//            //하나라도 거부 즉 비활성화 되어 있으면
//            if (chk == PackageManager.PERMISSION_DENIED){
//                //requestPermissions()에 권한목록과 0을 넘겨주고 루프를 종료한다.
//                requestPermissions(permission_list, 0)
//                break
//            }
//        }
//    }
//
//    //권한 설정 후 결과를 확인할 수 있다.
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>, //체크한 권한 목록
//        grantResults: IntArray //권한 허용 여부 값
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        var idx = 0
//        for (idx in grantResults.indices){  //indices 인덱스 번호
//            if (grantResults[idx] == PackageManager.PERMISSION_GRANTED){
//                binding.textView.append("${permission_list[idx]} : 혀용함\n")
//            }else{
//                binding.textView.append("${permission_list[idx]} : 허용하지 않음\n")
//            }
//        }
//    }
//}