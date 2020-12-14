package com.example.koalassimonsaysgame_android.playGamePage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.example.koalassimonsaysgame_android.R

class PlayGameActivity : AppCompatActivity(), PlayGameContract.View {

    private lateinit var presenter: PlayGameContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)

        setOnClickListener()

        presenter = PlayGamePresenter(this)
    }

    private fun setOnClickListener() {
        findViewById<ImageButton>(R.id.flag_up).setOnClickListener() {
            presenter.didTapUp()
        }

        findViewById<ImageButton>(R.id.flag_down).setOnClickListener() {
            presenter.didTapDown()
        }

        findViewById<ImageButton>(R.id.flag_right).setOnClickListener() {
            presenter.didTapRight()
        }

        findViewById<ImageButton>(R.id.flag_left).setOnClickListener() {
            presenter.didTapLeft()
        }
    }

    override fun setKoalaUpImage() {
        findViewById<ImageView>(R.id.mrKoala).setImageResource(R.drawable.koala_up)
    }

    override fun setKoalaDownImage() {
        findViewById<ImageView>(R.id.mrKoala).setImageResource(R.drawable.koala_down)
    }

    override fun setKoalaRightImage() {
        findViewById<ImageView>(R.id.mrKoala).setImageResource(R.drawable.koala_right)
    }

    override fun setKoalaLeftImage() {
        findViewById<ImageView>(R.id.mrKoala).setImageResource(R.drawable.koala_left)
    }
}