package com.yui.koalassimonsaysgame_android.resultPage

interface RegisterRankingDialogFragmentContract {

    interface View {
        fun backToStartPage()
        fun showEmptyErrorMessage()
    }

    interface Presenter {
        fun didTapRegisterButton(userText: String, worldRankingSwitch: Boolean)
        fun didTapNoRegisterButton()
    }
}