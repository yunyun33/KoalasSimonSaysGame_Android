package com.example.koalassimonsaysgame_android.TotalScorePage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.koalassimonsaysgame_android.R
import com.example.koalassimonsaysgame_android.playGamePage.PlayGameContract
import com.example.koalassimonsaysgame_android.playGamePage.PlayGamePresenter

class TotalScoreActivity : AppCompatActivity(), TotalScoreContract.View {

    private lateinit var presenter: TotalScoreContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_score)

        val score = intent.getIntExtra("totalScore", 0)
        findViewById<TextView>(R.id.totalScoreText).text = "あなたの得点は\n${score}点です。"

        presenter = TotalScorePresenter(this)
    override fun showKoalaMessage(messageText: String) {
        findViewById<TextView>(R.id.koalaMessageText).text = messageText
    }
}