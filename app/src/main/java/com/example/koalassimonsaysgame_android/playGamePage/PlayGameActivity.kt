package com.example.koalassimonsaysgame_android.playGamePage

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.koalassimonsaysgame_android.R
import com.example.koalassimonsaysgame_android.TotalScoreActivity

class PlayGameActivity : AppCompatActivity(), PlayGameContract.View {

    private lateinit var presenter: PlayGameContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_game)

        startCountDownTimer()
        setOnClickListener()

        presenter = PlayGamePresenter(this)
    }

    private fun startCountDownTimer() {
        object : CountDownTimer(15000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                presenter.startTimer(millisUntilFinished)
            }

            override fun onFinish() {
                presenter.finishTimer()
            }
        }.start()
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

    //PlayGameContract.View

    override fun showCountDown(text: String) {
        findViewById<TextView>(R.id.countDownText).setText(text)
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

    override fun transitToTotalScorePage() {
        val intent = Intent(this, TotalScoreActivity::class.java)
        startActivity(intent)
    }
}