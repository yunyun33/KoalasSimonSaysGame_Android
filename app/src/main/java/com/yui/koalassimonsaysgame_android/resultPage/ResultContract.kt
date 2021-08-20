package com.yui.koalassimonsaysgame_android.resultPage

interface ResultContract {
    interface View {
        fun showTotalScore(scoreText: String)
        fun showKoalaMessage(messageText: String)
        fun backToStartPage()
    }

    interface Presenter {
        fun didCreateView()
        fun didTapTransitToTopPage()
        fun getTotalScore(): Int
    }
}