package com.kye.sqlite1

/***************************************************
 * Content Provider
 ***************************************************/
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kye.sqlite1.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Insert 버튼
        binding.button.setOnClickListener { view ->

            // DB 오픈하기
            var helper : DBHelper = DBHelper(this)
            var db : SQLiteDatabase = helper.writableDatabase  // 데이터베이스 객체를 얻는다

            // 날짜 포맷 지정
            var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            var date = sdf.format(Date())

            // 저장할 데이터 셋팅
            var cv1 = ContentValues()
            cv1.put("textData", "문자열 1")
            cv1.put("intData", 100)
            cv1.put("floatData", 11.11)
            cv1.put("dateData", date)

            db.insert("TestTable", null, cv1)

            var cv2 = ContentValues()
            cv2.put("textData", "문자열 2")
            cv2.put("intData", 200)
            cv2.put("floatData", 22.22)
            cv2.put("dateData", date)

            // insert 클래스
            db.insert("TestTable", null, cv2)

            db.close()  // DB 닫기
            binding.textView.text = " SQLite에 Insert 완료 !!"
        }

        // Select 버튼
        binding.button2.setOnClickListener { view ->
            var helper : DBHelper = DBHelper(this)
            var db : SQLiteDatabase = helper.writableDatabase

            // select 클래스
            // 파라미터 1 : 테이블의 이름
            // 파라미터 2 : 가져올 컬럼이름의 문자열 배열, 없으면 null
            // 파라미터 3 : 조건절 (a=? and b=?) , 없으면 null
            // 파라미터 4 : 조건절의 ?에 해당하는 값의 문자열 배열
            // 파라미터 5 : Group by  (동일명의 컬럼으로 그룹핑하여 합계, 평균 등등)
            // 파라미터 6 : Having (그룹화를 한 이후 결과에 조건식을 넣고 싶을때 Having 조건식이 적용된다)
            // 파라미터 7 : 정렬 기준
            var c = db.query("TestTable", null, null, null, null, null, null)

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

            // Update 버튼
            binding.button3.setOnClickListener { view ->

                var helper : DBHelper = DBHelper(this)
                var db : SQLiteDatabase = helper.writableDatabase

                // 업데이트 데이터 및 조건 셋팅
                var cv = ContentValues()
                cv.put("textData", "문자열 3")
                var where = "idx=?"
                var args = arrayOf("1")

                // update 클래스
                db.update("TestTable", cv, where, args)

                db.close()
                binding.textView.text = " SQLite에 Update 완료 !!"
            }

            // Delete 버튼
            binding.button4.setOnClickListener { view ->
                var helper : DBHelper = DBHelper(this)
                var db : SQLiteDatabase = helper.writableDatabase

                // 조건 데이터 셋팅
                var where = "idx=?"
                var args = arrayOf("1")

                // delete 클래스 실행
                db.delete("TestTable", where, args)

                db.close()
                binding.textView.text = " SQLite에 Delete 완료 !!"
            }
        }

    }
}

/***************************************************
 * SQLite 데이터베이스 - 쿼리문 대신 클래스 사용하기
 ***************************************************/
//import android.content.ContentValues
//import android.database.Cursor
//import android.database.sqlite.SQLiteDatabase
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.sqlite1.databinding.ActivityMainBinding
//import java.text.SimpleDateFormat
//import java.util.*
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        // Insert 버튼
//        binding.button.setOnClickListener { view ->
//
//            // DB 오픈하기
//            var helper : DBHelper = DBHelper(this)
//            var db : SQLiteDatabase = helper.writableDatabase  // 데이터베이스 객체를 얻는다
//
//            // 날짜 포맷 지정
//            var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//            var date = sdf.format(Date())
//
//            // 저장할 데이터 셋팅
//            var cv1 = ContentValues()
//            cv1.put("textData", "문자열 1")
//            cv1.put("intData", 100)
//            cv1.put("floatData", 11.11)
//            cv1.put("dateData", date)
//
//            db.insert("TestTable", null, cv1)
//
//            var cv2 = ContentValues()
//            cv2.put("textData", "문자열 2")
//            cv2.put("intData", 200)
//            cv2.put("floatData", 22.22)
//            cv2.put("dateData", date)
//
//            // insert 클래스
//            db.insert("TestTable", null, cv2)
//
//            db.close()  // DB 닫기
//            binding.textView.text = " SQLite에 Insert 완료 !!"
//        }
//
//        // Select 버튼
//        binding.button2.setOnClickListener { view ->
//            var helper : DBHelper = DBHelper(this)
//            var db : SQLiteDatabase = helper.writableDatabase
//
//            // select 클래스
//            // 파라미터 1 : 테이블의 이름
//            // 파라미터 2 : 가져올 컬럼이름의 문자열 배열, 없으면 null
//            // 파라미터 3 : 조건절 (a=? and b=?) , 없으면 null
//            // 파라미터 4 : 조건절의 ?에 해당하는 값의 문자열 배열
//            // 파라미터 5 : Group by  (동일명의 컬럼으로 그룹핑하여 합계, 평균 등등)
//            // 파라미터 6 : Having (그룹화를 한 이후 결과에 조건식을 넣고 싶을때 Having 조건식이 적용된다)
//            // 파라미터 7 : 정렬 기준
//            var c = db.query("TestTable", null, null, null, null, null, null)
//
//            binding.textView.text = ""
//            // 커서를 raw단위로 이동시키며 데이터를 읽는다
//            while (c.moveToNext()){
//                // 먼저 컬럼명으로 인덱스를 구한다. (컬럼명으로 데이터를 조회하는 기능이 없음)
//                var idx_pos = c.getColumnIndex("idx")
//                var text_pos = c.getColumnIndex("textData")
//                var int_pos = c.getColumnIndex("intData")
//                var float_pos = c.getColumnIndex("floatData")
//                var date_pos = c.getColumnIndex("dateData")
//
//                // 인덱스 포지션으로 데이터를 읽는다.
//                var idx = c.getInt(idx_pos)
//                var textData = c.getString(text_pos)
//                var intData = c.getInt(int_pos)
//                var floatData = c.getDouble(float_pos)
//                var dateData = c.getString(date_pos)
//
//                binding.textView.append("idx : ${idx}\n")
//                binding.textView.append("idx : ${textData}\n")
//                binding.textView.append("idx : ${intData}\n")
//                binding.textView.append("idx : ${floatData}\n")
//                binding.textView.append("idx : ${dateData}\n\n")
//            }
//
//            // Update 버튼
//            binding.button3.setOnClickListener { view ->
//
//                var helper : DBHelper = DBHelper(this)
//                var db : SQLiteDatabase = helper.writableDatabase
//
//                // 업데이트 데이터 및 조건 셋팅
//                var cv = ContentValues()
//                cv.put("textData", "문자열 3")
//                var where = "idx=?"
//                var args = arrayOf("1")
//
//                // update 클래스
//                db.update("TestTable", cv, where, args)
//
//                db.close()
//                binding.textView.text = " SQLite에 Update 완료 !!"
//            }
//
//            // Delete 버튼
//            binding.button4.setOnClickListener { view ->
//                var helper : DBHelper = DBHelper(this)
//                var db : SQLiteDatabase = helper.writableDatabase
//
//                // 조건 데이터 셋팅
//                var where = "idx=?"
//                var args = arrayOf("1")
//
//                // delete 클래스 실행
//                db.delete("TestTable", where, args)
//
//                db.close()
//                binding.textView.text = " SQLite에 Delete 완료 !!"
//            }
//        }
//
//    }
//}

/***************************************************
 * SQLite 데이터베이스 - 쿼리문 사용하기
 ***************************************************/
//import android.database.Cursor
//import android.database.sqlite.SQLiteDatabase
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.kye.sqlite1.databinding.ActivityMainBinding
//import java.text.SimpleDateFormat
//import java.util.*
//
//class MainActivity : AppCompatActivity() {
//
//    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(binding.root)
//
//        // Insert 버튼
//        binding.button.setOnClickListener { view ->
//
//            // DB 오픈하기
//            var helper : DBHelper = DBHelper(this)
//            var db : SQLiteDatabase = helper.writableDatabase  // 데이터베이스 객체를 얻는다
//
//            // 저장할 데이터 생성 (autoincrement인 idx는 생략)
//            var sql = "insert into TestTable (" +
//                    "textData, " +
//                    "intData, " +
//                    "floatData, " +
//                    "dateData ) values (?, ?, ?, ?)"
//
//            // 날짜 포맷 지정
//            var sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//            var date = sdf.format(Date())
//
//            // 자료형이 모두 틀리더라도 모두 문자 배열에 넣는다.
//            var arg1 = arrayOf("문자열1111", "100", "11.11", date) // 쿼리의 ? 순서에 맞게 입력한다.
//            var arg2 = arrayOf("문자열2222", "200", "22.22", date)
//
//            // 쿼리문 실행 Insert
//            db.execSQL(sql, arg1)
//            db.execSQL(sql, arg2)
//
//            db.close()  // DB 닫기
//            binding.textView.text = " SQLite에 Insert 완료 !!"
//        }
//
//        // Select 버튼
//        binding.button2.setOnClickListener { view ->
//            var helper : DBHelper = DBHelper(this)
//            var db : SQLiteDatabase = helper.writableDatabase
//            var sql = "select * from TestTable"
//
//            // 조회시에는 execSQL이 아닌 rawQuery를 사용한다.
//            var c : Cursor = db.rawQuery(sql, null) //두번째 파라미터는 조건
//            binding.textView.text = ""
//
//            // 커서를 raw단위로 이동시키며 데이터를 읽는다
//            while (c.moveToNext()){
//                // 먼저 컬럼명으로 인덱스를 구한다. (컬럼명으로 데이터를 조회하는 기능이 없음)
//                var idx_pos = c.getColumnIndex("idx")
//                var text_pos = c.getColumnIndex("textData")
//                var int_pos = c.getColumnIndex("intData")
//                var float_pos = c.getColumnIndex("floatData")
//                var date_pos = c.getColumnIndex("dateData")
//
//                // 인덱스 포지션으로 데이터를 읽는다.
//                var idx = c.getInt(idx_pos)
//                var textData = c.getString(text_pos)
//                var intData = c.getInt(int_pos)
//                var floatData = c.getDouble(float_pos)
//                var dateData = c.getString(date_pos)
//
//                binding.textView.append("idx : ${idx}\n")
//                binding.textView.append("idx : ${textData}\n")
//                binding.textView.append("idx : ${intData}\n")
//                binding.textView.append("idx : ${floatData}\n")
//                binding.textView.append("idx : ${dateData}\n\n")
//            }
//
//            // Update 버튼
//            binding.button3.setOnClickListener { view ->
//
//                var helper : DBHelper = DBHelper(this)
//                var db : SQLiteDatabase = helper.writableDatabase
//                var sql = "update TestTable set textData=? where idx=?"
//                var args = arrayOf("문자열3", "1") // 물음표 순서대로 데이터를 입력
//
//                // 업데이트 쿼리 실행
//                db.execSQL(sql, args)
//
//                db.close()
//                binding.textView.text = " SQLite에 Update 완료 !!"
//            }
//
//            // Delete 버튼
//            binding.button4.setOnClickListener { view ->
//                var helper : DBHelper = DBHelper(this)
//                var db : SQLiteDatabase = helper.writableDatabase
//                var sql = "delete from TestTable where idx=?"
//                var args = arrayOf("1")  // 조건이 하나 이더라도 배열로 물음표 순서대로 넣는다.
//
//                // delete 쿼리 실행
//                db.execSQL(sql, args)
//
//                db.close()
//                binding.textView.text = " SQLite에 Delete 완료 !!"
//            }
//        }
//
//    }
//}