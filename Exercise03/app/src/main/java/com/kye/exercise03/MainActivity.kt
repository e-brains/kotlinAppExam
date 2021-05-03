package com.kye.exercise03


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.kye.exercise03.databinding.ActivityMainBinding

/***************************************
 * Notification
 ***************************************/
class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //버튼 리스너를 통해 notification 띄우기
        binding.button.setOnClickListener { view ->

            //builder를 통해 Notification을 띄우기 위한 설정 작업
            //var builder = NotificationCompat.Builder(this)

            //Notification Channel 적용을 위한 코드 수정
            var builder = getNotificationBuilder1("channel01", "첫번째 채널")

            builder.setTicker("Ticker")
            //작은 아이콘
            builder.setSmallIcon(android.R.drawable.ic_menu_search)

            //큰 아이콘 셋팅 (bitmap으로 변환)
            var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder.setLargeIcon(bitmap)
            builder.setNumber(100)
            builder.setAutoCancel(true) //true이면 사용자가 메시지를 터치하면 메시지가 자동으로 제거
            builder.setContentTitle("Content Title")
            builder.setContentText("Content Text")

            //Notification 띄우기
            var notification = builder.build()
            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            //첫번째 파라미터 10은 알림메시지를 구분하기 위한 id 값이다.
            //id 값을 동일한 값으로 주고 띄우면 메시지가 같은 위치에 뜬다.
            mng.notify(10, notification)

            //최상단 메시지 바에 돋보기 모양 작은 아이콘이 뜨고 이를 내려서 보면 큰 아이콘으로
            //메시지가 있는 것이 보인다.
            //8.0이상 버전에서는 Notification Channel을 이용해야 동작한다. 이때 8.0 미만 버전에서는
            //Notification Channel을 이용하면 오류가 발생하므로 주의해야 한다.

            //실행 후에 앱 이름이 exercise03이라는 앱을 찾아서 설정에 들어가면 app notification항목에
            //첫번째 채널 , 두번째 채널 이라는 명칭으로 메시지를 보일 지 말지 설정하는 창이 나오는데
            //여기서 채널별로 on/off 할 수 있다

        }

        //버튼 리스너를 통해 notification 띄우기
        binding.button2.setOnClickListener { view ->

            //builder를 통해 Notification을 띄우기 위한 설정 작업
            //var builder = NotificationCompat.Builder(this)

            //Notification Channel 적용을 위한 코드 수정
            var builder = getNotificationBuilder1("channel01", "첫번째 채널")

            builder.setTicker("Ticker")
            //작은 아이콘
            builder.setSmallIcon(android.R.drawable.ic_menu_search)

            //큰 아이콘 셋팅 (bitmap으로 변환)
            var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder.setLargeIcon(bitmap)
            builder.setNumber(100)
            builder.setAutoCancel(true) //true이면 사용자가 메시지를 터치하면 메시지가 자동으로 제거
            builder.setContentTitle("Content Title 2222")
            builder.setContentText("Content Text 2222")

            //Notification 띄우기
            var notification = builder.build()
            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            //첫번째 파라미터 20은 알림메시지를 구분하기 위한 id 값이다.
            //id 값을 틀린 값으로 주어야 메시지가 정상적으로 뜬다.
            mng.notify(20, notification)
      }

        //버튼 리스너를 통해 notification 띄우기
        binding.button3.setOnClickListener { view ->

            //builder를 통해 Notification을 띄우기 위한 설정 작업
            //var builder = NotificationCompat.Builder(this)

            //Notification Channel 적용을 위한 코드 수정
            var builder = getNotificationBuilder1("channel02", "두번째 채널")

            builder.setTicker("Ticker")
            //작은 아이콘
            builder.setSmallIcon(android.R.drawable.ic_menu_search)

            //큰 아이콘 셋팅 (bitmap으로 변환)
            var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder.setLargeIcon(bitmap)
            builder.setNumber(100)
            builder.setAutoCancel(true) //true이면 사용자가 메시지를 터치하면 메시지가 자동으로 제거
            builder.setContentTitle("Content Title 3333")
            builder.setContentText("Content Text 3333")

            //Notification 띄우기
            var notification = builder.build()
            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            //첫번째 파라미터 30은 알림메시지를 구분하기 위한 id 값이다.
            //id 값을 동일한 값으로 주고 띄우면 메시지가 같은 위치에 뜬다.
            mng.notify(30, notification)

        }

    }

    //8.0이상에서도 동작하도록 Notification Channel을 이용하기 위한 함수
    fun getNotificationBuilder1(id:String, name:String):NotificationCompat.Builder{
        var builder:NotificationCompat.Builder? = null

        //OS 버전별로 분기
        //Build.VERSION.SDK_INT 현재 디바이스의 OS 버전을 가져온다
        //8.0 오레오 버전 이상이면
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ //영문 대문자 O는 8.0 오레오 버전

            //Notification Channel을 만들어야 하는데 이때는 NotificationManager가 필요함
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            //Notification Channel을 만든다. (마지막 파라미터는 중요도임)
            var channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)

            //메시지가 오면 단말기의 led를 보이게 할 것인지 정의
            channel.enableLights(true)

            //led 색상 정의
            channel.lightColor = Color.RED

            //진동 설정
            channel.enableVibration(true)

            //NotificationChannel 객체 생성 메서드에 내가 설정한 채널 객체를 넘긴다.
            manager.createNotificationChannel(channel)

            //id는 본 함수를 호출할 때 넘어오는 채널 id 이다
            builder = NotificationCompat.Builder(this, id)

        }else{
            //오레오 버전 이하 이면 기존대로 Notification 객체 생성
            builder = NotificationCompat.Builder(this)
        }

        return builder
    }

}

/***************************************
 * Notification
 ***************************************/
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.Context
//import android.graphics.BitmapFactory
//import android.graphics.Color
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import androidx.core.app.NotificationCompat
//import com.kye.exercise03.databinding.ActivityMainBinding
//

//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //버튼 리스너를 통해 notification 띄우기
//        binding.button.setOnClickListener { view ->
//
//            //builder를 통해 Notification을 띄우기 위한 설정 작업
//            //var builder = NotificationCompat.Builder(this)
//
//            //Notification Channel 적용을 위한 코드 수정
//            var builder = getNotificationBuilder1("channel01", "첫번째 채널")
//
//            builder.setTicker("Ticker")
//            //작은 아이콘
//            builder.setSmallIcon(android.R.drawable.ic_menu_search)
//
//            //큰 아이콘 셋팅 (bitmap으로 변환)
//            var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
//            builder.setLargeIcon(bitmap)
//            builder.setNumber(100)
//            builder.setAutoCancel(true) //true이면 사용자가 메시지를 터치하면 메시지가 자동으로 제거
//            builder.setContentTitle("Content Title")
//            builder.setContentText("Content Text")
//
//            //Notification 띄우기
//            var notification = builder.build()
//            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//            //첫번째 파라미터 10은 알림메시지를 구분하기 위한 id 값이다.
//            //id 값을 동일한 값으로 주고 띄우면 메시지가 같은 위치에 뜬다.
//            mng.notify(10, notification)
//
//            //최상단 메시지 바에 돋보기 모양 작은 아이콘이 뜨고 이를 내려서 보면 큰 아이콘으로
//            //메시지가 있는 것이 보인다.
//            //8.0이상 버전에서는 Notification Channel을 이용해야 동작한다. 이때 8.0 미만 버전에서는
//            //Notification Channel을 이용하면 오류가 발생하므로 주의해야 한다.
//
//            //실행 후에 앱 이름이 exercise03이라는 앱을 찾아서 설정에 들어가면 app notification항목에
//            //첫번째 채널 , 두번째 채널 이라는 명칭으로 메시지를 보일 지 말지 설정하는 창이 나오는데
//            //여기서 채널별로 on/off 할 수 있다
//
//        }
//
//        //버튼 리스너를 통해 notification 띄우기
//        binding.button2.setOnClickListener { view ->
//
//            //builder를 통해 Notification을 띄우기 위한 설정 작업
//            //var builder = NotificationCompat.Builder(this)
//
//            //Notification Channel 적용을 위한 코드 수정
//            var builder = getNotificationBuilder1("channel01", "첫번째 채널")
//
//            builder.setTicker("Ticker")
//            //작은 아이콘
//            builder.setSmallIcon(android.R.drawable.ic_menu_search)
//
//            //큰 아이콘 셋팅 (bitmap으로 변환)
//            var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
//            builder.setLargeIcon(bitmap)
//            builder.setNumber(100)
//            builder.setAutoCancel(true) //true이면 사용자가 메시지를 터치하면 메시지가 자동으로 제거
//            builder.setContentTitle("Content Title 2222")
//            builder.setContentText("Content Text 2222")
//
//            //Notification 띄우기
//            var notification = builder.build()
//            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//            //첫번째 파라미터 20은 알림메시지를 구분하기 위한 id 값이다.
//            //id 값을 틀린 값으로 주어야 메시지가 정상적으로 뜬다.
//            mng.notify(20, notification)
//        }
//
//        //버튼 리스너를 통해 notification 띄우기
//        binding.button3.setOnClickListener { view ->
//
//            //builder를 통해 Notification을 띄우기 위한 설정 작업
//            //var builder = NotificationCompat.Builder(this)
//
//            //Notification Channel 적용을 위한 코드 수정
//            var builder = getNotificationBuilder1("channel02", "두번째 채널")
//
//            builder.setTicker("Ticker")
//            //작은 아이콘
//            builder.setSmallIcon(android.R.drawable.ic_menu_search)
//
//            //큰 아이콘 셋팅 (bitmap으로 변환)
//            var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
//            builder.setLargeIcon(bitmap)
//            builder.setNumber(100)
//            builder.setAutoCancel(true) //true이면 사용자가 메시지를 터치하면 메시지가 자동으로 제거
//            builder.setContentTitle("Content Title 3333")
//            builder.setContentText("Content Text 3333")
//
//            //Notification 띄우기
//            var notification = builder.build()
//            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//            //첫번째 파라미터 30은 알림메시지를 구분하기 위한 id 값이다.
//            //id 값을 동일한 값으로 주고 띄우면 메시지가 같은 위치에 뜬다.
//            mng.notify(30, notification)
//
//        }
//
//    }
//
//    //8.0이상에서도 동작하도록 Notification Channel을 이용하기 위한 함수
//    fun getNotificationBuilder1(id:String, name:String):NotificationCompat.Builder{
//        var builder:NotificationCompat.Builder? = null
//
//        //OS 버전별로 분기
//        //Build.VERSION.SDK_INT 현재 디바이스의 OS 버전을 가져온다
//        //8.0 오레오 버전 이상이면
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ //영문 대문자 O는 8.0 오레오 버전
//
//            //Notification Channel을 만들어야 하는데 이때는 NotificationManager가 필요함
//            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//            //Notification Channel을 만든다. (마지막 파라미터는 중요도임)
//            var channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
//
//            //메시지가 오면 단말기의 led를 보이게 할 것인지 정의
//            channel.enableLights(true)
//
//            //led 색상 정의
//            channel.lightColor = Color.RED
//
//            //진동 설정
//            channel.enableVibration(true)
//
//            //NotificationChannel 객체 생성 메서드에 내가 설정한 채널 객체를 넘긴다.
//            manager.createNotificationChannel(channel)
//
//            //id는 본 함수를 호출할 때 넘어오는 채널 id 이다
//            builder = NotificationCompat.Builder(this, id)
//
//        }else{
//            //오레오 버전 이하 이면 기존대로 Notification 객체 생성
//            builder = NotificationCompat.Builder(this)
//        }
//
//        return builder
//    }
//
//}

///***************************************
// * List Dialogue
// ***************************************/
//import android.content.DialogInterface
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.SimpleAdapter
//import androidx.appcompat.app.AlertDialog
//import com.kye.exercise03.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //기본 리스트 다이얼로그 문자열 배열 (리스트 다이얼로그는 항목 하나에 문자열 하나를 표시한다)
//    var data1 = arrayOf("항목1", "항목2", "항목3", "항목4", "항목5", "항목6")
//
//    //커스텀 리스트 다이얼로그 데이터
//    var data2 = arrayOf("김씨", "최씨","이씨")
//    var data3 = intArrayOf(R.drawable.ground, R.drawable.ground2, R.drawable.ground3)
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //기본 리스트 다이얼로그 오픈하기
//        binding.button.setOnClickListener { view ->
//            var builder = AlertDialog.Builder(this)
//            builder.setTitle("기본 리스트 다이얼로그")
//            builder.setNegativeButton("취소", null)
//
//            //리스너 만들기
//            var listener = object :DialogInterface.OnClickListener{
//                override fun onClick(dialog: DialogInterface?, which: Int) {
//                    //dialog:클릭된 객체정보(다이얼로그에 셋팅한 리스트 개체정보도 있음)
//                    //which:클릭된 항목의 index
//                    //다이얼로그에서 클릭이 일어나면 해당 항목의 인덱스로 데이터 출력
//                    binding.textView.text = "기본 리스트 다이얼로그 : ${data1[which]}"
//                }
//            }
//
//            //데이터 셋팅 (위에서 만든 리스너를 넘겨주면 된다)
//            builder.setItems(data1, listener)
//            builder.show()
//        }
//
//        //커스텀 리스트 다이얼로그 오픈하기
//        binding.button2.setOnClickListener { view ->
//            var builder = AlertDialog.Builder(this)
//            builder.setTitle("커스텀 리스트 다이얼로그")
//
//            //SimpleAdapter구성 (항목 한칸을 구성하기 위해서 필요한 데이터를 해시맵에 담아주고 그 맵을
//            //ArrayList에 담은 다음에 이 ArrayList를 SimpleAdapter에 셋한다)
//            var list = ArrayList<HashMap<String, Any>>()
//            var idx = 0
//            while (idx < data2.size){
//                var map = HashMap<String, Any>()
//                map.put("data2", data2[idx])
//                map.put("data3", data3[idx])
//
//                list.add(map)
//                idx++
//            }
//
//            //SimpleAdapter 만들기 위한 매개 변수 생성
//            var keys = arrayOf("data2", "data3") //SimpleAdapter에 넘겨줄 키 배열
//            var ids = intArrayOf(R.id.textView5, R.id.imageView3) //내가 만든 레이아웃의 뷰 id 배열
//
//            //리스너 만들기
//            var listener = object :DialogInterface.OnClickListener{
//                override fun onClick(dialog: DialogInterface?, which: Int) {
//                    binding.textView.text = "커스텀 리스트 다이얼로그 : ${data2[which]}"
//                }
//            }
//
//            //SimpleAdapter 만들기
//            var adapter = SimpleAdapter(this, list, R.layout.custom_dialog, keys, ids)
//
//            //다이얼로그 빌더에 어댑터와 리스너를 넘겨준다.
//            builder.setAdapter(adapter, listener)
//            builder.setNegativeButton("취소", null)
//            builder.show()
//
//        }
//
//    }
//
//}

/*********************************************************
 * Dialogue - DatePicker , TimePicker , ProgressDialog 추가
 *********************************************************/
//import android.app.DatePickerDialog
//import android.app.ProgressDialog
//import android.app.TimePickerDialog
//import android.content.DialogInterface
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.os.Handler
//import android.widget.DatePicker
//import android.widget.EditText
//import android.widget.TimePicker
//import androidx.appcompat.app.AlertDialog
//import com.kye.exercise03.R.*
//import com.kye.exercise03.databinding.ActivityMainBinding
//import java.util.*
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //ProgressDialog 미리 선언하는 이유는 나중에 제거처리를 위해서임
//    var pro:ProgressDialog? = null //deprecate된 기능임 (사용은 가능함)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //기본 다이얼로그 띄우기
//        binding.button.setOnClickListener { view ->
//            var builder = AlertDialog.Builder(this)
//            builder.setTitle("기본 다이얼로그")
//            builder.setMessage("다이얼로그의 본문입니다.")
//            builder.setIcon(android.R.mipmap.sym_def_app_icon)
//
//            //리스너 만들기 (익명 중첩 클래스)
//            var listener = object :DialogInterface.OnClickListener{
//                override fun onClick(dialog: DialogInterface?, which: Int) {
//                    when(which){
//                        DialogInterface.BUTTON_POSITIVE ->
//                            binding.textView.text = "기본 다이얼로그 : POSITIVE"
//                        DialogInterface.BUTTON_NEUTRAL ->
//                            binding.textView.text = "기본 다이얼로그 : NEUTRAL"
//                        DialogInterface.BUTTON_NEGATIVE ->
//                            binding.textView.text = "기본 다이얼로그 : NEGATIVE"
//                    }
//                }
//            }
//
//            //버튼 배치 (리스너를 할당하지 않으면 취소 버튼과 동일)
//            builder.setPositiveButton("Positive",listener)
//            builder.setNeutralButton("Neutral",listener)
//            builder.setNegativeButton("Negative",null)
//
//            builder.show()
//        }
//
//        //커스텀 다이얼로그 띄우기
//        binding.button2.setOnClickListener { view ->
//            var builder = AlertDialog.Builder(this)
//            builder.setTitle("커스텀 다이얼로그")
//            builder.setIcon(mipmap.ic_launcher)
//
//            //내가 만든 view를 설정한다.
//            var v1 = layoutInflater.inflate(layout.dialog, null)
//
//            //다이얼로그 객체에 뷰를 넘겨준다
//            builder.setView(v1)
//
//            //리스너 만들기 (익명 중첩 클래스)
//            var listener = object :DialogInterface.OnClickListener{
//                override fun onClick(dialog: DialogInterface?, which: Int) {
//                    //PositiveButton만 처리하기 때문에 when을 통한 분기는 생략
//                    //첫번째 매개변수 dialog를 AlertDialog로 형 변환하여 사용한다.
//                    var alert = dialog as AlertDialog
//
//                    //내가 만든 dialog.xml 레이아웃에서 editText객체를 가져온다
//                    var edit1:EditText? = alert.findViewById<EditText>(id.editText)
//                    var edit2:EditText? = alert.findViewById<EditText>(id.editText2)
//
//                    //textView에 출력
//                    binding.textView.text = "edit1 : ${edit1?.text}\n"
//                    binding.textView.append("edit2 : ${edit2?.text}")
//                }
//            }
//
//            //버튼 배치 (취소는 리스너를 할당하지 않으면 된다)
//            builder.setPositiveButton("확인",listener)
//            builder.setNegativeButton("취소",null)
//
//            builder.show()
//        }
//
//        //DatePicker 띄우기 추가
//        binding.button3.setOnClickListener { view ->
//            var calendar = Calendar.getInstance()
//            var year = calendar.get(Calendar.YEAR)
//            var month = calendar.get(Calendar.MONTH)
//            var day = calendar.get(Calendar.DAY_OF_MONTH)
//
//            //리스너 만들기
//            var listener = object :DatePickerDialog.OnDateSetListener{
//                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//                    //월은 0월 부터 시작하기 때문에 +1 해준다.
//                    binding.textView.text = "${year}년 ${month+1}월 ${dayOfMonth}일"
//                }
//            }
//
//            //리스너 할당하면서 datePicker 생성
//            var picker = DatePickerDialog(this, listener, year, month, day)
//            picker.show()
//        }
//
//        //TimePicker 띄우기 추가
//        binding.button4.setOnClickListener { view ->
//            var calendar = Calendar.getInstance()
//            var hour = calendar.get(Calendar.HOUR)
//            var minute = calendar.get(Calendar.MINUTE)
//
//            //리스너 만들기
//            var listener = object :TimePickerDialog.OnTimeSetListener{
//                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
//                    binding.textView.text = "${hourOfDay}시 ${minute}분"
//                }
//            }
//
//            //리스너 할당하면서 TimePicker 생성 (마지막 매개변수 true로 놓으면 24시간제로 표시)
//            var picker = TimePickerDialog(this, listener, hour, minute, false)
//            picker.show()
//        }
//
//        //ProgressDialog 띄우기 추가 (코딩으로만 종료할 수 있다)
//        //특정 작업을 하는 동안 손댈 수 없도록 할때 쓰이는 기능인것 같음
//        binding.button5.setOnClickListener { view ->
//            pro = ProgressDialog.show(this, "타이틀입니다","메시지입니다")
//
//            var handler = Handler()
//            var thread = Runnable { pro?.cancel() } //람다식으로 처리 가능
//
//            //handler에 thread를 넘겨서 5초 후에 thread를 종료 시킴
//            handler.postDelayed(thread, 5000)
//        }
//    }
//}

/**************************************
 * Toast
 ***************************************/
//@Suppress("DEPRECATION")
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //메시지를 표시하고 끝나기 때문에 이벤트 같은 처리는 없다.
//        binding.button.setOnClickListener { view ->
//            //토스트 생성 메서드는 makeText()
//            var t1 = Toast.makeText(this, "토스트 메시지 입니다.", Toast.LENGTH_SHORT)
//            t1.show()
//        }
//
//        //레이아웃을 직접 만들어서 메시지로 사용
//        binding.button3.setOnClickListener { view ->
//            //토스트를 통해 보여줄 레이아웃 설정
//            var v1 = layoutInflater.inflate(com.kye.exercise03.R.layout.custom_toast, null)
//
//            //처음 셋팅된 이미지를 여기서 코딩으로 변경해 줄 수 있다.
//            //내가 만든 레이아웃의 이미지객체를 변수에 할당하고 변경하고자 하는 이미지를 넘겨준다.
//            var image_view:ImageView? = v1.findViewById<ImageView>(com.kye.exercise03.R.id.imageView)
//            image_view?.setImageResource(com.kye.exercise03.R.drawable.ground2)
//
//            //내가 만든 xml파일의 뷰 객체에는 binding으로 접근이 안되서 아래와 같이 연동 작업이 필요함
//            //TextView에 문자열 셋팅
//            //내가 만든 텍스트 뷰 id를 가져와서 변수에 할당한 후 변경하고자 하는 글자를 넘겨준다.
//            var text_view:TextView? = v1.findViewById<TextView>(com.kye.exercise03.R.id.textView)
//            text_view?.text = "토스트 이미지 메시지 입니다."
//
//            //글자색 변경
//            text_view?.setTextColor(Color.WHITE)
//
//            //배경 설정 (안드로이드에서 기본 제공하는 배경 이미지를 사용할 수 있다)
//            v1.setBackgroundResource(android.R.drawable.toast_frame)
//
//            //배경 컬러도 설정
//            v1.setBackgroundColor(Color.BLUE)
//
//            //토스트 객체를 만든다.
//            var t2 = Toast(this)
//
//            //토스트 객체에 뷰를 할당한다.
//            t2.view = v1
//
//            //토스트 메시지의 위치 변경 (센터를 중심으로 0,0이면 움직이지 않겠다는 의미)
//            //센터를 기준으로 x축 값이 크면 오른쪽으로 y축이 크면 아래로 이동
//            t2.setGravity(Gravity.CENTER, 0,0)
//            t2.show()
//        }
//
//    }
//
//}

/**************************************
 * Action Bar
 ***************************************/
//import android.R
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.*
//import androidx.appcompat.widget.SearchView
//import com.kye.exercise03.R.id.*
//import com.kye.exercise03.databinding.ActivityMainBinding

//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//    }
//    //옵션 메뉴 생성
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(com.kye.exercise03.R.menu.main_menu, menu)
//
//        //접었다 펼쳤다하는 collapseActionView 사용하기
//        //적용할 메뉴의 아이템 설정
//        var search_item:MenuItem? = menu?.findItem(item5)
//        //설정한 메뉴 아이템에서 사용할 뷰 선택 및 형변환 해서 변수에 저장
//        var search_view: SearchView = search_item?.actionView as SearchView
//        //검색란에 출력할 리딩 메시지 셋팅
//        search_view.queryHint = "검색어를 입력해 주세요"
//
//        //리스너 (메서드가 2개라서 람다식 불가, 클래스나 익명클래스로 만들어야 함
//        //익명 클래스 타입의 리스너 만들기
//        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            //입력할 때 마다 호출되는 메서드
//            override fun onQueryTextChange(newText: String?): Boolean {
//                binding.textView5.text = newText //검색란에 입력되는 문자열
//                return true
//            }
//            //입력이 완료될 때 호출되는 메서드
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                binding.textView6.text = query
//                return false  //true로 하면 키보드의 검색 아이콘을 눌러 완료해도 키보드가 안내려감
//            }
//        })
//
//        return true
//    }
//    //옴션 메뉴 선택 시 처리
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            item1 -> binding.textView5.text = "메뉴1 선택"
//            item2 -> binding.textView5.text = "메뉴2 선택"
//            item3 -> binding.textView5.text = "메뉴3 선택"
//            item4 -> binding.textView5.text = "메뉴4 선택"
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//}
/**************************************
 * Popup Menu
 ***************************************/
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //버튼이 누르면 textView에 팝업창을 띄운다.
//        binding.button.setOnClickListener { view ->
//            var pop = PopupMenu(this, binding.textView)
//
//            //내가 만든 팝업 레이아웃 xml파일을 텍스트뷰와 연결된 메뉴 관리 객체와 연동한다.
//            menuInflater.inflate(com.kye.exercise03.R.menu.popup_menu, pop.menu)
//
//            //팝업 리스너 호출 (클래스 방식)
////            var listener = PopupListener()
////            pop.setOnMenuItemClickListener(listener)
//
//            //리스너의 오버라이딩 메서드가 하나라서 람다식으로 만들 수 있다.
//            pop.setOnMenuItemClickListener { item ->
//                when(item.itemId){
//                    com.kye.exercise03.R.id.item1 -> binding.textView.text = "메뉴1 을 눌렀다"
//                    com.kye.exercise03.R.id.item2 -> binding.textView.text = "메뉴2 를 눌렸다"
//                    com.kye.exercise03.R.id.item3 -> binding.textView.text = "메뉴3 을 눌렀다"
//                }
//                //람다식은 리턴할 때 리턴 값만 적어주면 된다.
//                false
//            }
//
//            //팝업창을 보여준다
//            pop.show()
//        }
//
//    }
//
//    //클래스 방식 리스너 클래스 생성
//    inner class PopupListener:PopupMenu.OnMenuItemClickListener{
//        override fun onMenuItemClick(item: MenuItem?): Boolean {
//            when(item?.itemId){
//                com.kye.exercise03.R.id.item1 -> binding.textView.text = "메뉴1 을 눌렀습니다."
//                com.kye.exercise03.R.id.item2 -> binding.textView.text = "메뉴2 를 눌렀습니다."
//                com.kye.exercise03.R.id.item3 -> binding.textView.text = "메뉴3 를 눌렀습니다."
//            }
//            return false
//        }
//    }
//}
///**************************************
// * Context Menu
// ***************************************/
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    //리스트 뷰를 위한 배열
//    var data = arrayOf("리스트1","리스트2","리스트3","리스트4","리스트5")
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //리스트 뷰와 데이터 연동을 위한 어댑터 설정
//        var adapter = ArrayAdapter(this, R.layout.simple_list_item_1, data)
//        binding.listview.adapter = adapter
//
//        //리스너 셋팅
//        binding.listview.setOnItemClickListener { parent, view, position, id ->
//            binding.textView.text = "${position} 번째 항목을 터치했습니다."
//        }
//
//        //컨텍스트 메뉴를 리스트 뷰와 연동해야 함 (컨텍스트 메뉴에 뷰를 셋팅)
//        registerForContextMenu(binding.listview)
//
//    }
//
//    //컨텍스트 메뉴가 연동된 뷰를 길게 누르면 아래 메서드가 호출됨
//    override fun onCreateContextMenu(
//        menu: ContextMenu?,
//        v: View?, //사용자가 길게 누른 뷰 객체
//        menuInfo: ContextMenu.ContextMenuInfo? //사용자가 길게 누른 항목이 몇번째인지 알수 있는 정보
//    ) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//
//        when(v?.id) {
//            id.listview -> {
//                menu?.setHeaderTitle("리스트뷰의 메뉴")
//                menuInflater.inflate(com.kye.exercise03.R.menu.listview_menu, menu)
//
//                //사용자가 길게 누른 항목이 몇번째인지 알기 위해 형변환 필요
//                var info = menuInfo as AdapterView.AdapterContextMenuInfo
//                //코드를 통해 메뉴 추가
//                if (info.position %2 == 0){ //인덱스 번호 0,2,4,6 에 해당되는 메뉴만 추가
//                    menu?.add(Menu.NONE, Menu.FIRST+100, Menu.NONE, "리스트뷰 메뉴3")
//                }
//            }
//            id.textView -> {
//                menu?.setHeaderTitle("텍스트뷰의 메뉴")
//                menuInflater.inflate(com.kye.exercise03.R.menu.textview_menu, menu)
//            }
//        }
//    }
//
//    //사용자가 메뉴를 선택했을 때 호출됨
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        //선택된 view정보가 안 넘어 오기 때문에 id로 처리
//        binding.textView.text = "item.itemId = ${item.itemId}"
//        when(item?.itemId){
//            com.kye.exercise03.R.id.textview_item1 -> binding.textView.text = "텍스트뷰의 컨텍스트 메뉴1"
//            com.kye.exercise03.R.id.textview_item2 -> binding.textView.text = "텍스트뷰의 컨텍스트 메뉴2"
//            com.kye.exercise03.R.id.listview_item1 -> {
//                binding.textView.text = "리스트뷰의 컨텍스트 메뉴1 \n"
//                //몇번째가 선택되었는지 인덱스 정보를 가져오기 위해 형변환
//                var info = item?.menuInfo as AdapterView.AdapterContextMenuInfo
//                binding.textView.append(" [ ${info.position} 번째 항목 ]")
//            }
//            com.kye.exercise03.R.id.listview_item2 -> {
//                binding.textView.text = "리스트뷰의 컨테스트 메뉴2 \n"
//                var info = item.menuInfo as AdapterView.AdapterContextMenuInfo
//                binding.textView.append(" [ ${info.position} 번째 항목 ]")
//            }
//            101 -> { //코드로  추가한 메뉴의 id값 FIRST+100
//                binding.textView.text = "리스트뷰의 컨테스트 메뉴3 \n"
//                var info = item.menuInfo as AdapterView.AdapterContextMenuInfo
//                binding.textView.append(" [ ${info.position} 번째 항목 ]")
//            }
//        }
//        return super.onContextItemSelected(item)
//    }
//
//}
///**************************************
// * Option Menu
// ***************************************/
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//
//    }
//
//    //xml로 만들때 menuInflater 이용
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//
//        //주 메뉴 생성
//        menu?.add(Menu.NONE, Menu.FIRST+1, Menu.NONE, "메뉴1(코드)")
//
//        //주 메뉴3에 서브메뉴 생성
//        var sub:Menu? = menu?.addSubMenu("메뉴2(코드)")
//        sub?.add(Menu.NONE, Menu.FIRST+2, Menu.NONE, "메뉴2-1(코드)")
//        sub?.add(Menu.NONE, Menu.FIRST+3, Menu.NONE, "메뉴2-2(코드)")
//
//        //다시 주 메뉴
//        menu?.add(Menu.NONE, Menu.FIRST+4, Menu.NONE, "메뉴3(코드)")
//
//        return true //false를 리턴하면 메뉴가 화면에 나타나지 않는다.
//    }
//
//    //메뉴 리스너 만들기
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item?.itemId){
//            Menu.FIRST+1 -> binding.textView.text = "메뉴1(코드)을 선택했습니다."
//            Menu.FIRST+2 -> binding.textView.text = "메뉴2-1(코드)을 선택했습니다."
//            Menu.FIRST+3 -> binding.textView.text = "메뉴2-2(코드)를 선택했습니다."
//            Menu.FIRST+4 -> binding.textView.text = "메뉴3(코드)을 선택했습니다."
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//}
/**************************************
 * ViewPager - 현재는 deprecated 됨
 ***************************************/
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    var view_list = ArrayList<View>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        view_list.add(layoutInflater.inflate(com.kye.exercise03.R.layout.row1, null))
//        view_list.add(layoutInflater.inflate(com.kye.exercise03.R.layout.row2, null))
//
//        //어댑터를 화면과 연결
//        binding.pager.adapter = CustomAdapter()
//
//    }
//
//    inner class  CustomAdapter : PagerAdapter(){
//        override fun getCount(): Int {
//            return view_list.size
//        }
//
//        //현재 객체가 보여줄 객체와 일치하는지 구분 (보여주고자하는 뷰, 안드로이드가 필요에 의해서 만드는 객체)
//        //두개의 파라미터를 비교해서 일치하면 true 반환함
//        //`object` 키워드인데 ''을 통해 변수로 사용가능 (이 변수명은 편한 이름으로 변경 가능)
//        override fun isViewFromObject(view: View, `object`: Any): Boolean {
//            return view == `object` //두 파라미터의 값이 일치하면 하면에 보여준다.
//        }
//
//        override fun instantiateItem(container: ViewGroup, position: Int): Any {
//            binding.pager.addView(view_list[position]) //isViewFromObject()함수의 view 파라미터에 매칭
//            return view_list[position] //isViewFromObject()함수의 `object` 파라미터에 매칭
//        }
//
//        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
//            binding.pager.removeView(`object` as View) //View로 형변환 후 제거
//        }
//    }
//
//}

/**************************************
 * Spinner
 ***************************************/
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    var data1 = arrayOf("스피너1-1", "스피너1-2", "스피너1-3", "스피너1-4", "스피너1-5")
//    var data2 = arrayOf("스피너2-1", "스피너2-2", "스피너2-3", "스피너2-4", "스피너2-5")
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        //일단 레이아웃은 안드로이드 기본 레이아웃을 선택해서 사용한다.
//        var adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, data1)
//        var adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, data2)
//
//        //팝업창 레이아웃 셋팅 (디자인창에서 스피너모드 설정에 따라 모양이 바뀔 수 있다.)
//        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        //스피너 뷰와 어댑터 연결하여 화면에 보이도록 한다.
//        binding.spinner.adapter = adapter1
//        binding.spinner2.adapter = adapter2
//
//        //함수가 2개라서 클래스로 선언해서 사용해야 함
//        var listener = SpinnerListener()
//        binding.spinner.onItemSelectedListener = listener
//
//        //함수가 2개라서 람다식 대신 익명 클래스를 이용함
//        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                binding.textView.text = data2[position]
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//
//            }
//        }
//
//        //버튼 클릭 시 스피너값 일괄 가져오기
//        binding.button.setOnClickListener { view ->
//            binding.textView.text = data1[binding.spinner.selectedItemPosition] + "\n"
//            binding.textView.append(data2[binding.spinner2.selectedItemPosition])
//        }
//
//    }
//
//    //클래스 타입의 리스너 작성
//    inner class SpinnerListener : AdapterView.OnItemSelectedListener{
//        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//            binding.textView.text = data1[position]
//        }
//
//        override fun onNothingSelected(parent: AdapterView<*>?) {
//            TODO("Not yet implemented")
//        }
//    }
//
//}

/**************************************
 * Custom Adapter
 ***************************************/
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    var data = arrayOf("문자열1", "문자열2", "문자열3", "문자열4", "문자열5")
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//
//        //레이아웃과 데이터 연결 어댑터 정보 생성
//        var adapter = ArrayAdapter<String>(this, layout.row2,
//                id.textView4, data)
//
//        //어댑터로 리스트뷰와 연결해서 하면에 뿌려주기
//        binding.listView.adapter = adapter
//
//        //내가 직접 만든 adapter 로 화면에 뿌려주기
//        var adapter2 = ListAdapter()
//        binding.listView.adapter = adapter2
//
//
//
//
//
//    }
//
//    //사용자 레이아웃에 있는 뷰에 접근하기 위한 사용자 정의 어댑터를 만든다.
//    inner class ListAdapter : BaseAdapter() {
//
//        //리스너 객체 생성
//        var listener = BtnListener()
//
//        override fun getCount(): Int {
//            return data.size
//        }
//
//        override fun getItem(position: Int): Any? {
//            return null
//        }
//
//        override fun getItemId(position: Int): Long {
//            return 0
//        }
//
//        //화면에 보이지 않는 아이템을 재사용할 수 있도록 해준다.
//        //convertView에는 위 아래로 스크롤 시 보이지 않는 뷰가 들어오게 되며 이 뷰정보를 가지고
//        //재사용하게 된다.
//        //getView()는 항목 하나를 처리한다. ( 한개 row 처리 )
//        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//            var convertView: View? = convertView  //재사용 가능한 뷰
//
//            //convertView에 재사용 가능한 뷰가 없다면 뷰를 만든다.
//            if (convertView == null) {
//                convertView = layoutInflater.inflate(layout.row2, null)
//            }
//
//            //com.kye.exercise03.R.id.textView4 => import하면 id.textView4로 줄일 수 있음
//            var text:TextView? = convertView?.findViewById(id.textView4)
//            text?.text = data[position]
//
//            //버튼 id를 가져와서 버튼 객체를 생성
//            var button1:Button? = convertView?.findViewById<Button>(id.button1)
//            var button2:Button? = convertView?.findViewById<Button>(id.button2)
//
//            //리스너를 버튼 이벤트와 연결
//            button1?.setOnClickListener(listener)
//            button2?.setOnClickListener(listener)
//
//            //리스너는 몇번째 항목 즉 몇 row인지 정보가 없다 그래서 getView에 파리머터로 들어오는 row정보를
//            //버튼 객체에 임시로 저장해서 사용해야 한다.
//            //몇번째 항목 즉 row 정보가 position으로 들어오는데 이를 tag에 저장
//            button1?.tag = position
//            button2?.tag = position
//
//            return convertView
//        }
//    }
//
//    //1. 클래스 타입 리스너
//    inner class BtnListener:View.OnClickListener{
//        override fun onClick(v: View?) {
//
//            //getView()에서 tag에 담아 뒀던 row값 가져오기
//            var idx = v?.tag as Int //Int로 형변환
//
//            when(v?.id){
//                com.kye.exercise03.R.id.button1 ->
//                    binding.textView.text = "${idx} 번째 항목 : 버튼1이 눌렸습니다."
//                com.kye.exercise03.R.id.button2 ->
//                    binding.textView.text = "${idx} 번째 항목 : 버튼2가 눌렸습니다."
//
//
//            }
//        }
//    }
//
//}
/**************************************
 * two line listView - SimpleAdapter 사용
 ***************************************/
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//    var data1 = arrayOf("문자열1", "문자열2", "문자열3", "문자열4", "문자열5")
//    var data2 = arrayOf("String1", "String2", "String3", "String4", "String5")
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        /**************************************
//         * two line listView - SimpleAdapter 사용
//         ***************************************/
//        var list = ArrayList<HashMap<String, String>>()
//        var idx = 0
//        while (idx < data1.size){
//            var map = HashMap<String, String>()
//            map.put("str1", data1[idx])
//            map.put("str2", data2[idx])
//
//            list.add(map)
//            idx++
//        }
//        //SimpleAdapter를 사용하여 listView와 연결
//        var key = arrayOf("str1", "str2")
//
//        //안드로이드의 내장된 R.layout.simple_list_item_2 레이아웃을 사용
//        //여기에 textView의 id는 각각 text1, text2이다.
//        var ids = intArrayOf(R.id.text1, R.id.text2)
//        var adapter = SimpleAdapter(this, list, R.layout.simple_list_item_2, key, ids)
//        binding.listView.adapter = adapter
//
//        //람다로 리스너 만들기
//        binding.listView.setOnItemClickListener { parent, view, position, id ->
//            binding.textView4.text = data1[position] + " : " + data2[position]
//        }
//
//    }

/**************************************
 * custom listView - SimpleAdapter 사용
 ***************************************/
//    /****  listView는 배열을 만들어서 사용한다.
//     * 여기서는 이미지 배열을 위해 정수값 배열 사용 ****/
//    var imgRes = intArrayOf(com.kye.exercise03.R.drawable.ground, com.kye.exercise03.R.drawable.ground2,
//            com.kye.exercise03.R.drawable.ground3, com.kye.exercise03.R.drawable.ground4)
//    var data1 = arrayOf("엽전1","엽전2", "엽전3", "엽전4" )
//    var data2 = arrayOf("img1", "img2", "img3", "img4")
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        /**************************************
//         * custom listView - SimpleAdapter 사용
//         * 이미지 1개의 항목을 구성하기 위해 2개의 데이타를 사용
//         * 그래서 SimpleAdatper 사용함
//         * 우선 hash맵을 만들고 이를 array에 넘겨준다.
//         ***************************************/
//        //array구성, 해쉬는 제네릭으로 타입은 이미지와 스트링 사용하기 Any타입으로 설정
//        var list = ArrayList<HashMap<String, Any>>()
//
//        //루프를 통해 hash맵을 구성하여 배열에 담는다.
//        var idx = 0
//        while (idx < data1.size){
//            var map = HashMap<String, Any>()
//            map.put("flag", imgRes[idx])
//            map.put("data1", data1[idx])
//            map.put("data2", data2[idx])
//            list.add(map)
//            idx++
//        }
//        //생성된 맵을 어디에 사용할지 asign이 필요하고 이를 이해 key배열을 생성해 둠
//        var keys = arrayOf("flag", "data1", "data2")
//        //내가 만든  row1.xml 레이아웃의 view들을 배열에 넣는다.
//        var ids = intArrayOf(com.kye.exercise03.R.id.imageView2, com.kye.exercise03.R.id.textView2,
//                com.kye.exercise03.R.id.textView3)
//        //adapter를 이용하여 연결작업
//        var adapter = SimpleAdapter(this, list, com.kye.exercise03.R.layout.row1, keys, ids)
//        binding.listView.adapter = adapter
//
//        //람다식으로 리스너 생성
//        binding.listView.setOnItemClickListener { parent, view, position, id ->
//            binding.textView.text = data1[position] + " : " + data2[position]
//        }
//    }


/**************************************
 * custom listView - ArrayAdapter 사용
 ***************************************/
//    //내장되어 있는 layout 사용 시
//    var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
//
//    //내가 만든 layout 사용 시 (layout폴더에 row1.xml이 만들어져 있다)
////        var adapter = ArrayAdapter(this, R.layout.row1, R.id.textView3, data)
//    binding.listView.adapter = adapter
//
//    //람다를 이용한 리스너 사용하기
//    binding.listView.setOnItemClickListener { parent, view, position, id ->
//        binding.textView2.text = data[position]
//    }


/**************************************
 *  listView
 ***************************************/
//    //배열 데이터의 모양을 잡아서 어댑터 변수에 담고 이를 다시 리스트뷰로 넘겨준다.
//    var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
//    binding.listView.adapter = adapter
//
//    //1. 클래스를 이용한 리스너 사용
//    var listener = ListListener()
//    binding.listView.setOnItemClickListener(listener)
//
//    //2. 메서드가 하나 이므로 람다식으로 처리할 수 있음
//    binding.listView.setOnItemClickListener { parent, view, position, id ->
//        binding.textView.text = data[position]
//    }
//
//
//    //1. 클래스를 이용한 리스너를 위한 중첩 클래스 만들기
//    inner class ListListener : AdapterView.OnItemClickListener{
//        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//            binding.textView.text = data[position]
//        }
//    }


/**************************************
 * imageView
 ***************************************/
//drawable에 있는 이미지로 바꾸기
//    binding.imageView6.setImageResource(R.drawable.ground)


/**************************************
 * seek bar
 ***************************************/
//
//        //현재 위치값 가져오기
//        binding.button.setOnClickListener { view ->
//            binding.textView.text = "seek1 : " + binding.seekBar.progress
//            binding.textView2.text = "seek2 : " + binding.seekBar2.progress
//        }
//
//        //위치값 증가하기
//        binding.button5.setOnClickListener { view ->
//            binding.seekBar.incrementProgressBy(1)
//            binding.seekBar2.incrementProgressBy(1)
//        }
//
//        //위치값 감소하기
//        binding.button6.setOnClickListener { view ->
//            binding.seekBar.incrementProgressBy(-1)
//            binding.seekBar2.incrementProgressBy(-1)
//        }
//
//        //seek bar의 값이 변경되었을때 반응하는 리스너
//        //1. 클래스를 이용한 리스너 사용
//        var listener = SeekListener()
//        binding.seekBar.setOnSeekBarChangeListener(listener)
//
//        //override 함수가 2개 이상이 되면 람다식을 이용할 수 없다. 이럴때는
//        //2. 익명중첩클래스를 이용하면 된다.
//        binding.seekBar2.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
//
//            //seek bar의 값이 변경되었을때
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                binding.textView2.text = "seek2 : " + progress
//            }
//
//            //값을 변경하기 위해 사용자가 터치했을 때
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//            }
//
//            //값을 변경한 후 터치를 떼었을때
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//            }
//
//        })
//
//    }
//
//    //1. 클래스를 이용한 리스너 사용
//    inner class SeekListener:SeekBar.OnSeekBarChangeListener{
//        //seek bar의 값이 변경되었을때
//        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//            binding.textView.text = "seek1 : " + progress
//        }
//
//        //값을 변경하기 위해 사용자가 터치했을 때
//        override fun onStartTrackingTouch(seekBar: SeekBar?) {
//
//        }
//
//        //값을 변경한 후 터치를 떼었을때
//        override fun onStopTrackingTouch(seekBar: SeekBar?) {
//
//        }
//    }

/**************************************
 * progress bar
 ***************************************/
//
//        //증가 단위 설정
//        binding.button.setOnClickListener { view ->
//            binding.progressBar4.incrementProgressBy(5)
//        }
//
//        //감소 단위 설정
//        binding.button2.setOnClickListener { view ->
//            binding.progressBar4.incrementProgressBy(-5)
//        }
//
//        //현재 위치 값 설정
//        binding.button3.setOnClickListener { view ->
//            binding.progressBar4.progress = 50
//        }

/**************************************
 * 라디오 버튼
 ***************************************/
//        //버튼 클릭하면 현재 체크된 라디오버튼 가져오기
//        binding.button.setOnClickListener { view ->
//            when(binding.group1.checkedRadioButtonId){
//                R.id.radioButton -> binding.textView.text = "라디오 1-1가 선택됨"
//                R.id.radioButton2 -> binding.textView.text = "라디오 1-2가 선택됨"
//                R.id.radioButton3 -> binding.textView.text = "라디오 1-3이 선택됨"
//            }
//            when(binding.group2.checkedRadioButtonId){
//                R.id.radioButton4 -> binding.textView2.text = "라디오 2-1이 선택됨"
//                R.id.radioButton5 -> binding.textView2.text = "라디오 2-2가 선택됨"
//                R.id.radioButton6 -> binding.textView2.text = "라디오 2-3이 선택됨"
//            }
//        }
//
//        //클래스 리스너를 이용한 상태값 체크
////        var listener = RadioListener()
////        binding.group1.setOnCheckedChangeListener(listener)
////        binding.group2.setOnCheckedChangeListener(listener)
//
//        //람다식을 이용한 상태값 체크
//        binding.group1.setOnCheckedChangeListener { group, checkedId ->
//            when(checkedId){
//                R.id.radioButton -> binding.textView.text = "이벤트: 라디오 1-1 체크"
//                R.id.radioButton2 -> binding.textView.text = "이벤트: 라디오 1-2 체크"
//                R.id.radioButton3 -> binding.textView.text = "이벤트: 라디오 1-3 체크"
//            }
//        }
//        binding.group2.setOnCheckedChangeListener { group, checkedId ->
//            when(checkedId){
//                R.id.radioButton4 -> binding.textView2.text = "이벤트: 라디오 2-1 체크"
//                R.id.radioButton5 -> binding.textView2.text = "이베트: 라디오 2-2 체크"
//                R.id.radioButton6 -> binding.textView2.text = "이벤트: 라디오 2-3 체크"
//            }
//        }
//
//        //코드로 체크 상태 강제 할당하기
//        binding.button2.setOnClickListener { view ->
//            binding.radioButton2.isChecked = true
//            binding.radioButton5.isChecked = true
//        }


//    //클래스 리스너를 이용한 상태값 체크
//    inner class RadioListener : RadioGroup.OnCheckedChangeListener{
//        override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
//            when(group?.id){
//                R.id.group1 ->
//                    when(checkedId){
//                        R.id.radioButton -> binding.textView.text = "이벤트: 라디오 1-1 체크"
//                        R.id.radioButton2 -> binding.textView.text = "이벤트: 라디오 1-2 체크"
//                        R.id.radioButton3 -> binding.textView.text = "이벤트: 라디오 1-3 체크"
//                    }
//                R.id.group2 ->
//                    when(checkedId){
//                        R.id.radioButton4 -> binding.textView2.text = "이벤트: 라디오 2-1 체크"
//                        R.id.radioButton5 -> binding.textView2.text = "이베트: 라디오 2-2 체크"
//                        R.id.radioButton6 -> binding.textView2.text = "이벤트: 라디오 2-3 체크"
//                    }
//            }
//        }
//    }

