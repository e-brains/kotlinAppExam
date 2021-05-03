package com.kye.sqlite1

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class TestProvider : ContentProvider() {

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {

        var helper = DBHelper(context!!) // context객체를 상속 안 받기 때문에 this 아닌 context객체 사용
        var db = helper.writableDatabase

        return db.delete("TestTable", selection, selectionArgs) // 삭제된 수 반환
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    // insert
    override fun insert(uri: Uri, values: ContentValues?): Uri? {

        var helper = DBHelper(context!!)
        var db = helper.writableDatabase

        db.insert("TestTable", null, values)

        return uri
    }

    override fun onCreate(): Boolean {
       return false
    }

    // select
    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {

        var helper = DBHelper(context!!)
        var db = helper.writableDatabase
        var c = db.query("TestTable", projection, selection, selectionArgs, null, null, sortOrder)

        return c
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        var helper = DBHelper(context!!)
        var db = helper.writableDatabase

        return db.update("TestTable", values, selection, selectionArgs) // 수정된 갯수 반환
    }
}