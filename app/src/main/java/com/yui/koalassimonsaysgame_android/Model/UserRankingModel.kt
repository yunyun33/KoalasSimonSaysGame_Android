package com.yui.koalassimonsaysgame_android.Model

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.yui.koalassimonsaysgame_android.ApplicationController
import com.yui.koalassimonsaysgame_android.R
import com.yui.koalassimonsaysgame_android.rankingPage.*
import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface UserRankingModelContract {
    fun insertData(userName: String, score: String)
    fun insertDataToFirebase(userName: String, score: String)
    fun selectData() : MutableList<ResultActivity.RankingData>
    fun selectDataToFirebase(success: (data: MutableList<ResultActivity.RankingData>) -> Unit)
    fun deleteData()
}

class UserRankingModel: UserRankingModelContract {

    var context: Context = ApplicationController.applicationContext()

    val dbName: String = "ranking.db"
    val tableName: String = "LocalRankingTable"
    val dbVersion: Int = 1

    var options: FirestoreRecyclerOptions<User>? = null

    override fun insertData(userName: String, score: String) {
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

    override fun insertDataToFirebase(userName: String, score: String) {
        val db = Firebase.firestore

        val user = hashMapOf(
            "rankingName" to userName,
            "totalScore" to score.toInt()
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }

    override fun selectData(): MutableList<ResultActivity.RankingData> {
        val rankingDataList = mutableListOf<ResultActivity.RankingData>()

        try {
            val dbHelper = DataBaseHelper(context, dbName, null, dbVersion)
            val database = dbHelper.readableDatabase

            //scoreの降順でデータを取得する。
            val sql = "select userName, score from LocalRankingTable order by score desc"

            val cursor = database.rawQuery(sql, null)
            Log.i("selectData","cursor.count" + cursor.count)

            if (cursor.count > 0) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    val userName = cursor.getString(0)
                    val score = cursor.getInt(1)

                    val localRankingData = ResultActivity.RankingData(userName, score)

                    rankingDataList.add(localRankingData)

                    cursor.moveToNext()
                }
            }

        } catch (exception: Exception) {
            Log.e("selectData", exception.toString())
        }

        Log.i("中身","${rankingDataList}")
        return rankingDataList
    }

    override fun selectDataToFirebase(success: (data: MutableList<ResultActivity.RankingData>) -> Unit) {
        val rankingDataList = mutableListOf<ResultActivity.RankingData>()
        val db = Firebase.firestore

        try {
            db.collection("users")
                    // 降順に並べ替え
                    .orderBy("totalScore", Query.Direction.DESCENDING)
                    .get()
                    .addOnSuccessListener { result ->
                        for (document in result) {
                            Log.d(TAG, "DocumentSnapshot data: " + document.data?.get("rankingName"))
                            Log.d(TAG, "DocumentSnapshot data: " + document.data?.get("totalScore"))

                            val userName: String = document.data?.get("rankingName").toString()
                            val score: Int = document.data?.get("totalScore").toString().toInt()

                            val worldRankingData = ResultActivity.RankingData(userName, score)

                            rankingDataList.add(worldRankingData)
                            Log.d(TAG, "rankingDataList: " + rankingDataList)

                        }
                        Log.d(TAG, "rankingDataListのカウント: " + rankingDataList.count())
                        success(rankingDataList)
                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents.", exception)
                    }

        } catch (exception: Exception) {
            Log.e("selectDataToFirebase", exception.toString())
        }
    }

    override fun deleteData() {
        try {
            val dbHelper = DataBaseHelper(context, dbName, null, dbVersion)
            val database = dbHelper.writableDatabase

            //全行を削除する場合はnullを指定する。
            val whereClauses = null
            //WHERE句に「?」が存在しない場合はnullを指定する。
            val whereArgs = null

            database.delete(tableName, whereClauses, whereArgs)
        } catch (exception: Exception) {
            Log.e("deleteData", exception.toString())
        }
    }
}

class User (
        val rankingName: String = "",
        val rankingScore: String = ""
)

class UserRankingModelMock: UserRankingModelContract {

    override fun insertData(userName: String, score: String) {

    }

    override fun insertDataToFirebase(userName: String, score: String) {

    }

    override fun selectData() : MutableList<ResultActivity.RankingData> {
        //サンプルの値を作ってリターンする。
        var rankingData: MutableList<ResultActivity.RankingData> = mutableListOf()
        rankingData.add(ResultActivity.RankingData("mini", 1))
        rankingData.add(ResultActivity.RankingData("ちゅー", 3))
        rankingData.add(ResultActivity.RankingData("でか", 5))

        return rankingData
    }

    override fun selectDataToFirebase(success: (data: MutableList<ResultActivity.RankingData>) -> Unit) {

    }

    override fun deleteData() {

    }
}