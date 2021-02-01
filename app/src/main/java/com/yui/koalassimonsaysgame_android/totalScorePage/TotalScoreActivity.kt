package com.yui.koalassimonsaysgame_android.totalScorePage

import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.yui.koalassimonsaysgame_android.MainActivity
import com.yui.koalassimonsaysgame_android.R
import com.yui.koalassimonsaysgame_android.rankingPage.RankingActivity
import java.io.Serializable

class TotalScoreActivity : AppCompatActivity(), TotalScoreContract.View {

    private lateinit var presenter: TotalScoreContract.Presenter

    val dbName: String = "ranking.db"
    val tableName: String = "LocalRankingTable"
    val dbVersion: Int = 1

    var totalScore:Int = 10

    var rankingDataList = mutableListOf<rankingData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_score)

        setOnClickListener()

        presenter = TotalScorePresenter(this, intent)

        presenter.didCreateView()
    }

    private fun setOnClickListener() {
        findViewById<Button>(R.id.transitToTopPageButton).setOnClickListener() {
            presenter.didTapTransitToTopPage()
        }

        findViewById<Button>(R.id.displayTheRegistrationDialogButton).setOnClickListener() {
            val nameText = EditText(this)
            nameText.setHint("What's your name?")

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("ランキングに登録しますか？")
            dialog.setView(nameText)

            dialog.setPositiveButton("登録する", DialogInterface.OnClickListener { dialog, which ->
                // OKボタン押したときの処理
                val userText = nameText.getText().toString()

                //rankingに登録
                insertData(userText, totalScore.toString())

                //確認用に表示
                Toast.makeText(applicationContext, "${rankingDataList}", Toast.LENGTH_SHORT).show()

//                val registerRanking = rankingData(0, userText, 10)
//                rankingDataList.add(registerRanking)
                val intent = Intent(this, RankingActivity::class.java)
                val data = rankingData(0, userText, totalScore.toString())
                intent.putExtra("RANKING_DATA", data)

                presenter.didTapTransitToTopPage()
            })

            dialog.setNegativeButton("登録しない", DialogInterface.OnClickListener { dialog, which ->
                presenter.didTapTransitToTopPage()
            })
            dialog.show()
        }
    }

    //Android端末の戻るボタン無効化
    override fun onBackPressed() {
        //中身を空にすることで戻るボタンが無効化される。
    }

    //PlayGameContract.View

    override fun showTotalScore(scoreText: String) {
        findViewById<TextView>(R.id.totalScoreText).text = scoreText
    }

    override fun showKoalaMessage(messageText: String) {
        findViewById<TextView>(R.id.koalaMessageText).text = messageText
    }

    override fun transitToTopPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    data class rankingData (
        val rank: Int,
        val rankingName: String,
        val rankingScore: String
    ): Serializable

    private fun insertData(userName: String, score: String) {
        try {
            //SQLiteOpenHelperを継承したクラスを呼び出す。
            val dbHelper = DataBaseHelper(applicationContext, dbName, null, dbVersion)
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

    private fun selectData() {
        try {
            val dbHelper = DataBaseHelper(applicationContext, dbName, null, dbVersion)
            val database = dbHelper.readableDatabase

            val sql = "select userName, score from LocalRankingTable"

            val cursor = database.rawQuery(sql, null)
            Log.i("selectData","cursor.count" + cursor.count)
            if (cursor.count > 0) {
                cursor.moveToFirst()
                while (!cursor.isAfterLast) {
                    val userName = cursor.getString(0)
                    val score = cursor.getString(1)

                    val localRankingData = rankingData(1, userName, score)
                    rankingDataList.add(localRankingData)
                    
                    cursor.moveToNext()
                }
            }

        } catch (exception: Exception) {
            Log.e("せselectData", exception.toString())
        }
//        adapter.notifyDataSetChanged()
    }


}