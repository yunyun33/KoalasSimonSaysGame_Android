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
import com.yui.koalassimonsaysgame_android.R
import java.io.Serializable

open class ResultActivity : AppCompatActivity(), ResultContract.View {

    private lateinit var presenter: ResultContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_score)

        setOnClickListener()

        presenter = ResultPresenter(this, intent)

        presenter.didCreateView()
    }

    private fun setOnClickListener() {
        findViewById<Button>(R.id.transitToTopPageButton).setOnClickListener() {
            presenter.didTapTransitToTopPage()
        }

        findViewById<Button>(R.id.displayTheRegistrationDialogButton).setOnClickListener() {
            val nameText = EditText(this)
            nameText.setHint(this.getString(R.string.registerForRankingDialog_editText_hint))

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle(this.getString(R.string.registerForRankingDialog_title))
            dialog.setView(nameText)

            dialog.setPositiveButton(this.getString(R.string.registerForRankingDialog_positiveText), DialogInterface.OnClickListener { dialog, which ->
                // OKボタン押したときの処理(rankingに登録する)
                val userText = nameText.getText().toString().trim()

                presenter.didTapResultButton(userText)
            })

            dialog.setNegativeButton(this.getString(R.string.registerForRankingDialog_negativeText), DialogInterface.OnClickListener { dialog, which ->
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

    override fun backToStartPage() {
        val intent = Intent(this, MainActivity::class.java)
        //今までのページのインスタンスは破棄される。
        //トップページに戻ったら、端末の戻るボタン押した場合、アプリが終了するようになる。
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    override fun showEmptyErrorMessage() {
        Toast.makeText(applicationContext, this.getString(R.string.emptyErrorMessage), Toast.LENGTH_LONG).show()
    }

    data class RankingData (
        val rankingName: String,
        val rankingScore: Int
    ): Serializable
}