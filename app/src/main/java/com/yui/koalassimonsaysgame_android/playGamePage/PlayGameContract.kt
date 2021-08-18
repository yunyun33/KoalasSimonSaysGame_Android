package com.yui.koalassimonsaysgame_android.playGamePage

interface PlayGameContract {
    interface View {
        fun setKoalaUpImage()
        fun setKoalaDownImage()
        fun setKoalaRightImage()
        fun setKoalaLeftImage()

        fun showCountDown(text: String)
        fun transitToTotalScorePage(totalScore: Int)
        fun SetScoreForTheFragment(totalScore: Int)
        fun showInstructionText(text: String)

        fun transitToTopPage()
    }

    interface  Presenter {
        fun didCreateView()
        fun didTapBackButton()

        fun didTapUp()
        fun didTapDown()
        fun didTapRight()
        fun didTapLeft()
    }
}