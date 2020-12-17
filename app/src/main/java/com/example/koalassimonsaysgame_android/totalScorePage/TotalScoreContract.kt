package com.example.koalassimonsaysgame_android.totalScorePage

interface TotalScoreContract {
    interface View {
        fun showTotalScore(scoreText: String)
        fun showKoalaMessage(messageText: String)

        fun transitToTopPage()
    }

    interface Presenter {
        fun didCreateView()
        fun didTapTransitToTopPage()
    }
}