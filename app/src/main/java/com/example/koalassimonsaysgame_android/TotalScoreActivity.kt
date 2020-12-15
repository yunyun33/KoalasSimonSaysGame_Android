package com.example.koalassimonsaysgame_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TotalScoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_score)

        val score = intent.getIntExtra("totalScore", 0)
        findViewById<TextView>(R.id.totalScoreText).text = "あなたの得点は\n${score}点です。"
    }
}