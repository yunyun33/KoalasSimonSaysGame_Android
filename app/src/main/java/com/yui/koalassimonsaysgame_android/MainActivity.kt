package com.yui.koalassimonsaysgame_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.yui.koalassimonsaysgame_android.playGamePage.PlayGameActivity
import com.yui.koalassimonsaysgame_android.rankingPage.RankingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClickListener()
    }

    private fun setOnClickListener() {

        //transitToPlayGamePage
        findViewById<ImageButton>(R.id.startButton).setOnClickListener() {
            val intent = Intent(this, PlayGameActivity::class.java)
            startActivity(intent)
        }

        //transitToRankingPage
        findViewById<ImageButton>(R.id.rankingButton).setOnClickListener() {
            val intent = Intent(this, RankingActivity::class.java)
            startActivity(intent)
        }
    }
}