package com.example.koalassimonsaysgame_android.TotalScorePage

interface TotalScoreContract {
    interface View {

        fun showKoalaMessage(messageText: String)
    }

    interface Presenter {

        fun getKoalaMessage()
    }
}