package com.example.koalassimonsaysgame_android.TotalScorePage

interface TotalScoreContract {
    interface View {
        fun showTotalScore(scoreText: String)
        fun showKoalaMessage(messageText: String)
    }

    interface Presenter {
        fun getTotalScore()
        fun getKoalaMessage()
    }
}