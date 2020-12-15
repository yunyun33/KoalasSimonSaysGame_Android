package com.example.koalassimonsaysgame_android.playGamePage

import android.os.CountDownTimer

class PlayGamePresenter (
        private  val view: PlayGameContract.View
): PlayGameContract.Presenter {

    //var enum調べて　textの値を持っておく→ボタン押されたのが合っているのか判別するため。

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
        view.showInstructionText("右にして！")
    }

    override fun didTapDown() {
        view.setKoalaDownImage()
        view.showInstructionText("上あげて！")
    }

    override fun didTapRight() {
        view.setKoalaRightImage()
        view.showInstructionText("左にして！")
    }

    override fun didTapLeft() {
        view.setKoalaLeftImage()
        view.showInstructionText("下さげて！")
    }

//    override fun nextInstruction() {
//        view.showInstructionText("上あげて！")
//    }
}