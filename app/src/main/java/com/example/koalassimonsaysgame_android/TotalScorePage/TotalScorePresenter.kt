package com.example.koalassimonsaysgame_android.TotalScorePage

import android.content.Intent

class TotalScorePresenter (
        private val view: TotalScoreContract.View,
        intent: Intent
): TotalScoreContract.Presenter {

    // 前の画面から渡されたtotalScoreを取得する。
    private val totalScore: Int = intent.getIntExtra("totalScore", 0)

    private val koalaMessage = getKoalaMessage(totalScore)

    //PlayGameContract.Presenter

    override fun getTotalScore() {
        view.showTotalScore("あなたの得点は\n${totalScore}点です。")
    }

    override fun getKoalaMessage() {
        view.showKoalaMessage(koalaMessage)
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