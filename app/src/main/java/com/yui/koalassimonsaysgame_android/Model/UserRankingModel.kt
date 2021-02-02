package com.yui.koalassimonsaysgame_android.Model

import android.content.ContentValues
import android.content.Context
import android.util.Log
import com.yui.koalassimonsaysgame_android.ApplicationController
import com.yui.koalassimonsaysgame_android.totalScorePage.TotalScoreActivity

class UserRankingModel: TotalScoreActivity() {

    var context: Context = ApplicationController.applicationContext()

    val dbName: String = "ranking.db"
    val tableName: String = "LocalRankingTable"
    val dbVersion: Int = 1

    fun insertData(userName: String, score: String) {
        try {
            //SQLiteOpenHelperを継承したクラスを呼び出す。
            val dbHelper = DataBaseHelper(context, dbName, null, dbVersion)
            //writableDatabaseメソッドを実行し、書き込み可能なSQLiteDatabaseを取得する。
            val database = dbHelper.writableDatabase

            val values = ContentValues().apply {
                put("userName", userName)
                put("score", score)

            }
            Log.i("insertDataの中身", "${values}")

            database.insertOrThrow(tableName, null, values)

        } catch(exception: Exception) {
            Log.e("insertData", exception.toString())
        }
    }

    fun selectData() {
        try {
            val dbHelper = DataBaseHelper(context, dbName, null, dbVersion)
            val database = dbHelper.readableDatabase

            val sql = "select userName, score from LocalRankingTable"

            val cursor = database.rawQuery(sql, null)
            Log.i("selectData","cursor.count" + cursor.count)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    val userName = cursor.getString(0)
                    val score = cursor.getString(1)

                    val localRankingData = TotalScoreActivity.rankingData(userName, score)
                    TotalScoreActivity().rankingDataList.add(localRankingData)

                    cursor.moveToNext()
                }
            }

        } catch (exception: Exception) {
            Log.e("selectData", exception.toString())
        }
//        adapter.notifyDataSetChanged()
    }
}