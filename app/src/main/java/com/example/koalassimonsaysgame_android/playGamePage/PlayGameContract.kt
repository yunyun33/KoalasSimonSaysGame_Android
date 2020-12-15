package com.example.koalassimonsaysgame_android.playGamePage

interface PlayGameContract {
    interface View {
        fun setKoalaUpImage()
        fun setKoalaDownImage()
        fun setKoalaRightImage()
        fun setKoalaLeftImage()

        fun showCountDown(text: String)
        fun transitToTotalScorePage()
    }

    interface  Presenter {
        fun didTapUp()
        fun didTapDown()
        fun didTapRight()
        fun didTapLeft()

        fun startTimer(millisUntilFinished: Long)
        fun finishTimer()
    }
}