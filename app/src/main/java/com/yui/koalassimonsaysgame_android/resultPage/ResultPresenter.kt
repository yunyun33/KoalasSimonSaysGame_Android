package com.yui.koalassimonsaysgame_android.resultPage

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import com.yui.koalassimonsaysgame_android.ApplicationController
import com.yui.koalassimonsaysgame_android.Model.UserRankingModel

class ResultPresenter (
    private val view: ResultContract.View,
    intent: Intent
): ResultContract.Presenter {

    private val userRankingModel = UserRankingModel()

    var context: Context = ApplicationController.applicationContext()

    // 前の画面から渡されたtotalScoreを取得する。
    private val totalScore: Int = intent.getIntExtra("totalScore", 0)

    private val koalaMessage = getKoalaMessage(totalScore)

    //PlayGameContract.Presenter

    override fun didCreateView() {
        view.showTotalScore("あなたの得点は\n${totalScore}点です。")
        view.showKoalaMessage(koalaMessage)
    }

    override fun didTapResultButton(userText: String) {

        val dialog = AlertDialog.Builder(context)

        //EditTextが空ならイベント何もなくダイアログを閉じる。
        if (userText.equals("")) {
            view.closeDialog(dialog)
            view.showErrorMessage()
        } else {
            //rankingに登録
            userRankingModel.insertData(userText, totalScore.toString())
            didTapTransitToTopPage()
        }
    }

    override fun didTapTransitToTopPage() {
        view.transitToTopPage()
    }

    //コアラさんのコメント
    private fun getKoalaMessage(totalScore: Int) : String {
        return when (totalScore) {
            in 25..50 -> "す、すごい！\nあなたの弟子にしてください！"
            in 21..24 -> "すごいですね\nあなたは旗振り名人です！"
            in 16..20 -> "パチパチパチ\nお上手ですね"
            in 11..15 -> "もう少しでしたね\nもう一度やってみましょう！"
            in 2..10 -> "もう少し頑張りましょう"
            in 0..1 -> "もしかして寝ていましたか？\n私も眠くなってきました..."
            else -> ""
        }
    }
}