package com.example.koalassimonsaysgame_android.playGamePage

import android.os.CountDownTimer

class PlayGamePresenter (
        private  val view: PlayGameContract.View
): PlayGameContract.Presenter {

    //方向を示すenum classを定義
    enum class Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    var okCount: Int = 0
    var ngCount: Int = 0
    var totalScore: Int = 0

    //PlayGameContract.Presenter

    override fun startCountDownTimer() {
        object : CountDownTimer(16000, 100) {
            override fun onTick(millisUntilFinished: Long) {
                val time =  millisUntilFinished / 1000
                view.showCountDown("残り" + time + "秒")
            }

            override fun onFinish() {
                view.transitToTotalScorePage(totalScore)
            }
        }.start()
    }

    override fun didTapUp() {
        view.setKoalaUpImage()
        showNextInstruction()
        if ( InstructionTexts.values() == InstructionTexts.up ) {
            okCount += 1
        } else {
            ngCount += 1
        }
    }

    override fun didTapDown() {
        view.setKoalaDownImage()
        showNextInstruction()
        if ( InstructionTexts.values() == InstructionTexts.down ) {
            okCount += 1
        } else {
            ngCount += 1
        }
    }

    override fun didTapRight() {
        view.setKoalaRightImage()
        showNextInstruction()
        if ( InstructionTexts.values() == InstructionTexts.right ) {
            okCount += 1
        } else {
            ngCount += 1
        }
    }

    override fun didTapLeft() {
        view.setKoalaLeftImage()
        showNextInstruction()
        if ( InstructionTexts.values() == InstructionTexts.left ) {
            okCount += 1
        } else {
            ngCount += 1
        }
    }

    override fun showNextInstruction() {
        var setText = InstructionTexts.values().random()
        view.showInstructionText("${setText.id}")
    }

    override fun getTotalScore() {
        totalScore = okCount - ngCount
        if ( totalScore < 0 ) {
            totalScore = 0
        }
    }
}