package com.example.koalassimonsaysgame_android.playGamePage

interface PlayGameContract {
    interface View {
        fun setKoalaUpImage()
        fun setKoalaDownImage()
        fun setKoalaRightImage()
        fun setKoalaLeftImage()
    }

    interface  Presenter {
        fun didTapUp()
        fun didTapDown()
        fun didTapRight()
        fun didTapLeft()
    }
}