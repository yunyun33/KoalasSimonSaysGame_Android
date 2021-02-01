package com.yui.koalassimonsaysgame_android.totalScorePage

import android.widget.EditText

interface TotalScoreContract {
    interface View {
        fun showTotalScore(scoreText: String)
        fun showKoalaMessage(messageText: String)

        fun transitToTopPage()
    }

    interface Presenter {
        fun didCreateView()
        fun didTapPositiveButton()
        fun didTapTransitToTopPage()
    }
}