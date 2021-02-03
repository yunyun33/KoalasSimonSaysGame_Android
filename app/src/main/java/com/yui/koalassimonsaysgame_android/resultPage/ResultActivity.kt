package com.yui.koalassimonsaysgame_android.resultPage

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
import java.io.Serializable

open class ResultActivity : AppCompatActivity(), ResultContract.View {

    private lateinit var presenter: ResultContract.Presenter

    lateinit var userRankingModel: UserRankingModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_score)

        setOnClickListener()

        presenter = ResultPresenter(this, intent)

        presenter.didCreateView()

        userRankingModel = UserRankingModel()
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
                // OKボタン押したときの処理(rankingに登録する)
                val userText = nameText.getText().toString()

                presenter.didTapResultButton(userText)


                userRankingModel.selectData()

                //確認用に表示
                Toast.makeText(applicationContext, "${userRankingModel.selectData()}", Toast.LENGTH_SHORT).show()

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

    data class RankingData (
        val rankingName: String,
        val rankingScore: Int
    ): Serializable
}