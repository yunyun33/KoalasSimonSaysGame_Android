package com.example.koalassimonsaysgame_android.playGamePage

class PlayGamePresenter (
        private  val view: PlayGameContract.View
): PlayGameContract.Presenter {

    //PlayGameContract.Presenter

    override fun startTimer(millisUntilFinished: Long) {
        val time =  millisUntilFinished / 1000
        view.showCountDown("残り" + time + "秒")
    }

    override fun finishTimer() {
        view.transitToTotalScorePage()
    }

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