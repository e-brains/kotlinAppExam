package com.kye.contentprovider

import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.contentprovider.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // select
        binding.button.setOnClickListener { view ->
            // 내가 query하고자 하는 앱(SQLite1)의 AndroidManifest.xml에 있는 provider Authorities를 OS에게
            // 알려주기 위해 uri를 만든다.
            var uri = Uri.parse("content://com.kye.dbprovider")

            // 내가 query하고자 하는 앱(SQLite1)의 ContentProvider의 query메서드를 OS에게 호출 요청
            var c:Cursor = contentResolver.query(uri, null, null, null, null)!!

            binding.textView.text = ""

            // 커서를 raw단위로 이동시키며 데이터를 읽는다
            while (c.moveToNext()){
                // 먼저 컬럼명으로 인덱스를 구한다. (컬럼명으로 데이터를 조회하는 기능이 없음)
                var idx_pos = c.getColumnIndex("idx")
                var text_pos = c.getColumnIndex("textData")
                var int_pos = c.getColumnIndex("intData")
                var float_pos = c.getColumnIndex("floatData")
                var date_pos = c.getColumnIndex("dateData")

                // 인덱스 포지션으로 데이터를 읽는다.
                var idx = c.getInt(idx_pos)
                var textData = c.getString(text_pos)
                var intData = c.getInt(int_pos)
                var floatData = c.getDouble(float_pos)
                var dateData = c.getString(date_pos)

                binding.textView.append("idx : ${idx}\n")
                binding.textView.append("idx : ${textData}\n")
                binding.textView.append("idx : ${intData}\n")
                binding.textView.append("idx : ${floatData}\n")
                binding.textView.append("idx : ${dateData}\n\n")
            }
        }

        // insert
        binding.button2.setOnClickListener { view ->

            // 날짜 포맷 지정
            var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            var date = sdf.format(Date())

            // 저장할 데이터 셋팅
            var cv1 = ContentValues()
            cv1.put("textData", "문자열 3")
            cv1.put("intData", 300)
            cv1.put("floatData", 33.33)
            cv1.put("dateData", date)

            var cv2 = ContentValues()
            cv2.put("textData", "문자열 4")
            cv2.put("intData", 400)
            cv2.put("floatData", 44.44)
            cv2.put("dateData", date)

            // 내가 insert하고자 하는 앱(SQLite1)의 AndroidManifest.xml에 있는 provider Authorities를 OS에게
            // 알려주기 위해 uri를 만든다.
            var uri = Uri.parse("content://com.kye.dbprovider")

            // 내가 insert하고자 하는 앱(SQLite1)의 ContentProvider의 insert메서드를 OS에게 호출 요청
            contentResolver.insert(uri, cv1)
            contentResolver.insert(uri, cv2)

            binding.textView.text = "Content Provider 저장 완료"
        }

        // update
        binding.button3.setOnClickListener { view ->

            // 업데이트 데이터 및 조건 셋팅
            var cv = ContentValues()
            cv.put("textData", "문자열 10000")

            var where = "idx=?"
            var args = arrayOf("3")

            // 내가 update하고자 하는 앱(SQLite1)의 AndroidManifest.xml에 있는 provider Authorities를 OS에게
            // 알려주기 위해 uri를 만든다.
            var uri = Uri.parse("content://com.kye.dbprovider")

            // 내가 update하고자 하는 앱(SQLite1)의 ContentProvider의 update메서드를 OS에게 호출 요청
            contentResolver.update(uri, cv, where, args)

            binding.textView.text = "Content Provider 수정 완료"
        }

        // delete
        binding.button4.setOnClickListener { view ->

            // 조건 데이터 셋팅
            var where = "idx=?"
            var args = arrayOf("3")

            // 내가 delete하고자 하는 앱(SQLite1)의 AndroidManifest.xml에 있는 provider Authorities를 OS에게
            // 알려주기 위해 uri를 만든다.
            var uri = Uri.parse("content://com.kye.dbprovider")

            // 내가 delete하고자 하는 앱(SQLite1)의 ContentProvider의 delete메서드를 OS에게 호출 요청
            contentResolver.delete(uri, where, args)

            binding.textView.text = "Content Provider 삭제완료"
        }
    }
}