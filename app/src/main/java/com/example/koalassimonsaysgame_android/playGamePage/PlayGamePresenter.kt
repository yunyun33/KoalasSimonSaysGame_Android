package com.example.koalassimonsaysgame_android.playGamePage

class PlayGamePresenter (
        private  val view: PlayGameContract.View
): PlayGameContract.Presenter {

    override fun didTapUp() {
        view.setKoalaUpImage()
    }

    override fun didTapDown() {
        view.setKoalaDownImage()
    }

    override fun didTapRight() {
        view.setKoalaRightImage()
    }

    override fun didTapLeft() {
        view.setKoalaLeftImage()
    }
}