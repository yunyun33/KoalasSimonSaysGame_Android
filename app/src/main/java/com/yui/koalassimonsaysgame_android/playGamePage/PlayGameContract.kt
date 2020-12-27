package com.yui.koalassimonsaysgame_android.playGamePage

interface PlayGameContract {
    interface View {
        fun setKoalaUpImage()
        fun setKoalaDownImage()
        fun setKoalaRightImage()
        fun setKoalaLeftImage()

        fun showCountDown(text: String)
        fun transitToTotalScorePage(totalScore: Int)
        fun showInstructionText(text: String)

    }

    interface  Presenter {
        fun didTapUp()
        fun didTapDown()
        fun didTapRight()
        fun didTapLeft()

        fun startCountDownTimer()
        fun showNextInstruction()

        fun startMediaPlayer()
    }
}