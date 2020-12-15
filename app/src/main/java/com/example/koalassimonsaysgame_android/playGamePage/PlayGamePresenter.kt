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