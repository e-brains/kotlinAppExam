package com.kye.sqlite1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

// SQLiteOpenHelper클래스는 DB가 이미 생성되어 있는지 체크해서 없으면 onCreatea메서드 호출
// 기존에 생성한 Test.db 가 있으면 onCreate메서드를 호출하지 않고 기존 Test.db를 오픈한다.
class DBHelper (context: Context) : SQLiteOpenHelper(context, "Test.db", null, 1){

    // 기존 DB가 없으면 호출되니 DB생성코드를 여기에 작성하면 됨
    override fun onCreate(db: SQLiteDatabase?) {

        Log.d("msg", "on create")

        var sql = "create table TestTable (" +
                "idx integer primary key autoincrement, " +
                "textData text not null, " +  //text타입은 가변형 문자열
                "intData integer not null, " +
                "floatData real not null, " +  // 실수는  real
                "dateData date not null )"

        db?.execSQL(sql)
    }

    // 유지보수 시 테이블의 구조가 변경되었을때 여기에 코드를 작성하면 됨
    // SQLiteOpenHelper의 마지막 파라미터는 DB버전을 나타내는데 이를 하나 올리면
    // (현재는 2로 고치면 됨) onUpgrade 메서드가 호출된다.
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Log.d("msg", "oldVersion : ${oldVersion} newVersion : ${newVersion}")
    }
}