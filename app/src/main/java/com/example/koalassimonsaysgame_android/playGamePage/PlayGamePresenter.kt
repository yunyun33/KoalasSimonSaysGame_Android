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

    //仮に初期値としてUPを入れる
    var instructionDirection = Direction.UP
    //正解カウント
    var okCount: Int = 0
    //不正解カウント
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
        if ( instructionDirection == Direction.UP ) {
            okCount += 1
        } else {
            ngCount += 1
        }

        //判定が終わり、次のinstructionに進む
        showNextInstruction()
    }

    override fun didTapDown() {
        view.setKoalaDownImage()
        if ( instructionDirection == Direction.DOWN ) {
            okCount += 1
        } else {
            ngCount += 1
        }

        showNextInstruction()
    }

    override fun didTapRight() {
        view.setKoalaRightImage()
        if ( instructionDirection == Direction.RIGHT ) {
            okCount += 1
        } else {
            ngCount += 1
        }

        showNextInstruction()
    }

    override fun didTapLeft() {
        view.setKoalaLeftImage()
        if ( instructionDirection == Direction.LEFT ) {
            okCount += 1
        } else {
            ngCount += 1
        }

        showNextInstruction()
    }

    override fun showNextInstruction() {
        val nextInstruction = Direction.values().random()
        instructionDirection = nextInstruction

        val instructionText = when (nextInstruction) {
            Direction.UP ->  "上にあげて！"
            Direction.DOWN ->  "下にさげて！"
            Direction.RIGHT ->  "右にして！"
            Direction.LEFT ->  "左にして！"
        }

        view.showInstructionText(instructionText)
    }

    override fun getTotalScore() {
        totalScore = okCount - ngCount
        if ( totalScore < 0 ) {
            totalScore = 0
        }
    }
}