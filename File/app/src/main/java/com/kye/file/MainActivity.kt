package com.kye.file

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.os.PatternMatcher
import com.kye.file.databinding.ActivityMainBinding
import java.io.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    // 외부저장소 읽기 쓰기 권한 목록 정의
    var permission_list = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    // 외부저장소에 저장할 경로 설정
    var path:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 시스템의 default 외부저장소 경로룰 구해서 내가 원하는 폴더 경로를 추가한다.
        path = Environment.getExternalStorageDirectory().absolutePath + "/android/data/" + packageName

        // 외부저장소 읽기 쓰기 권한 체크
        checkPermission()

        // 파일 객체를 만든다.
        var file = File(path)

        // 해당 경로에 파일이 없으면 만들어라
        if (file.exists() == false){
            file.mkdir()
        }

        // 내부 저장소 저장 버튼
        binding.button.setOnClickListener { view ->
            try {
                var output = openFileOutput("myFile.dat", Context.MODE_PRIVATE)
                var dos = DataOutputStream(output)
                dos.writeInt(100)
                dos.writeDouble(11.11)
                dos.writeUTF("안녕하세요 내부저장소")
                dos.flush()
                dos.close()
                binding.textView.text = "내부저장소 저장 완료 !!"
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        // 내부 저장소 읽기 버튼
        binding.button2.setOnClickListener { view ->
            try {
                var input = openFileInput("myFile.dat")
                var dis = DataInputStream(input)
                var v1 = dis.readInt()
                var v2 = dis.readDouble()
                var v3 = dis.readUTF()
                dis.close()
                binding.textView.text = "v1 : ${v1}\n"
                binding.textView.append("v2 : ${v2}\n")
                binding.textView.append("v3 : ${v3}")
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        // 외부 저장소 저장 버튼
        binding.button3.setOnClickListener { view ->
            try {
                var output = FileOutputStream(path + "/sdFile.dat") // 저장경로 + 파일명
                var dos = DataOutputStream(output)
                dos.writeInt(200)
                dos.writeDouble(22.22)
                dos.writeUTF("반갑습니다. 외부저장소")
                dos.flush()
                dos.close()
                binding.textView.text = "외부저장소 저장완료 !!"
            }catch (e:Exception){
                e.printStackTrace()
            }
        }

        // 외부 저장소 읽기 버튼
        binding.button4.setOnClickListener { view ->
            try {
                var input = FileInputStream(path + "/sdFile.dat")
                var dis = DataInputStream(input)
                var v1 = dis.readInt()
                var v2 = dis.readDouble()
                var v3 = dis.readUTF()
                dis.close()
                binding.textView.text = "[v1 = ${v1}]\n[v2 = ${v2}]\n[v3 = ${v3}]"

            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    // 외부저장소 읽기 쓰기 권한 체크 (승인 받는 창)
    fun checkPermission(){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return
        }

        for (permission:String in permission_list){
            var chk = checkCallingOrSelfPermission(permission)
            if (chk == PackageManager.PERMISSION_DENIED){   // 거부가 있으면
                requestPermissions(permission_list, 0) // 승인 창 오픈
                break
            }
        }
    }

}