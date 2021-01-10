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

        transitToPlayGamePage()
        transitToRankingPage()
    }

    //Android端末の戻るボタン無効化
    override fun onBackPressed() {
        //中身を空にすることで戻るボタンが無効化される。
    }

    private fun transitToPlayGamePage() {
        val startButton = findViewById<ImageButton>(R.id.startButton)
        startButton.setOnClickListener() {
            val intent = Intent(this, PlayGameActivity::class.java)
            startActivity(intent)
        }
    }

    private fun transitToRankingPage() {
        val rankingButton = findViewById<ImageButton>(R.id.rankingButton)
        rankingButton.setOnClickListener() {
            val intent = Intent(this, RankingActivity::class.java)
            startActivity(intent)
        }
    }
}