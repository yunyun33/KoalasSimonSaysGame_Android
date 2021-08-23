package com.yui.koalassimonsaysgame_android.resultPage

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yui.koalassimonsaysgame_android.MainActivity
import com.yui.koalassimonsaysgame_android.R
import java.io.Serializable

class ResultActivity : AppCompatActivity(), ResultContract.View {

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
            val dialog = RegisterRankingDialogFragment.newInstance(presenter.getTotalScore())
            dialog.show(supportFragmentManager, null)
        }
    }

    // Android端末の戻るボタン無効化
    override fun onBackPressed() {
        // 中身を空にすることで戻るボタンが無効化される。
    }

    // PlayGameContract.View

    override fun showTotalScore(scoreText: String) {
        findViewById<TextView>(R.id.totalScoreText).text = scoreText
    }

    override fun showKoalaMessage(messageText: String) {
        findViewById<TextView>(R.id.koalaMessageText).text = messageText
    }

    override fun backToStartPage() {

        val intent = Intent(this, MainActivity::class.java)
        // 今までのページのインスタンスは破棄される。(Activityのスタックを消すようにする。)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
    }

    data class RankingData (
        val rankingName: String,
        val rankingScore: Int
    ): Serializable
}