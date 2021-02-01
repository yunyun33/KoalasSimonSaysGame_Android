package com.yui.koalassimonsaysgame_android.totalScorePage

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.yui.koalassimonsaysgame_android.MainActivity
import com.yui.koalassimonsaysgame_android.Model.UserRankingModel
import com.yui.koalassimonsaysgame_android.R
import com.yui.koalassimonsaysgame_android.rankingPage.RankingActivity
import java.io.Serializable

open class TotalScoreActivity : AppCompatActivity(), TotalScoreContract.View {

    private lateinit var presenter: TotalScoreContract.Presenter

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
                UserRankingModel().insertData(userText, totalScore.toString())

                UserRankingModel().selectData()

                //確認用に表示
                Toast.makeText(applicationContext, "${rankingDataList}", Toast.LENGTH_SHORT).show()

//                val registerRanking = rankingData(0, userText, 10)
//                rankingDataList.add(registerRanking)
                val intent = Intent(this, RankingActivity::class.java)
                val data = rankingData(userText, totalScore.toString())
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
        val rankingName: String,
        val rankingScore: String
    ): Serializable

}