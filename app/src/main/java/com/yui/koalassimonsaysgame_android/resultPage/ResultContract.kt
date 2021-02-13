package com.yui.koalassimonsaysgame_android.resultPage

import android.app.AlertDialog

interface ResultContract {
    interface View {
        fun showTotalScore(scoreText: String)
        fun showKoalaMessage(messageText: String)
        fun closeDialog(dialog: AlertDialog.Builder)
        fun showErrorMessage()

        fun transitToTopPage()
    }

    interface Presenter {
        fun didCreateView()
        fun didTapResultButton(userText: String)
        fun didTapTransitToTopPage()
    }
}