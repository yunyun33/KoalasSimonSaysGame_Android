package com.example.koalassimonsaysgame_android.playGamePage

import android.os.CountDownTimer

class PlayGamePresenter (
        private  val view: PlayGameContract.View
): PlayGameContract.Presenter {

    //textの値を持っておく→ボタン押されたのが合っているのか判別するため。
    enum class instructionTexts(val id:String) {
        up ("上にあげて！"),
        down ("下にさげて！"),
        right ("右にして！"),
        left ("左にして！")
    }

    //PlayGameContract.Presenter

    override fun startCountDownTimer() {
        object : CountDownTimer(16000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val time =  millisUntilFinished / 1000
                view.showCountDown("残り" + time + "秒")
            }

            override fun onFinish() {
                view.transitToTotalScorePage()
            }
        }.start()
    }

    override fun didTapUp() {
        view.setKoalaUpImage()
        showNextInstruction()
    }

    override fun didTapDown() {
        view.setKoalaDownImage()
        showNextInstruction()
    }

    override fun didTapRight() {
        view.setKoalaRightImage()
        showNextInstruction()
    }

    override fun didTapLeft() {
        view.setKoalaLeftImage()
        showNextInstruction()
    }

    override fun showNextInstruction() {
        var setText = instructionTexts.values().random()
        view.showInstructionText("${setText.id}")
    }
}