package com.yui.koalassimonsaysgame_android.resultPage

import android.content.Context
import android.content.Intent
import com.yui.koalassimonsaysgame_android.ApplicationController
import com.yui.koalassimonsaysgame_android.Model.UserRankingModel

class RegisterRankingDialogFragmentPresenter(
    private val view: RegisterRankingDialogFragment,
    intent: Intent
): RegisterRankingDialogFragmentContract.Presenter {

    private val userRankingModel = UserRankingModel()

    var context: Context = ApplicationController.applicationContext()

    // 前の画面から渡されたtotalScoreを取得する。
    private val totalScore: Int = intent.getIntExtra("totalScore", 0)

    // RegisterRankingDialogFragmentContract.Presenter

    override fun didTapRegisterButton(userText: String, worldRankingSwitch: Boolean) {

        if (userText == "") {

            // 空文字だった場合はエラーメッセージを出す
            view.showEmptyErrorMessage()

            return
        }

        if (worldRankingSwitch) {

            // worldRankingにも登録
            userRankingModel.insertDataToFirebase(userText, totalScore.toString())
            userRankingModel.insertData(userText, totalScore.toString())

        } else {

            // rankingに登録
            userRankingModel.insertData(userText, totalScore.toString())
        }

        view.backToStartPage()
    }

    override fun didTapNoRegisterButton() {
        view.backToStartPage()
    }
}