package com.example.koalassimonsaysgame_android.TotalScorePage

class TotalScorePresenter (
        private val view: TotalScoreContract.View
): TotalScoreContract.Presenter {


    private val koalaMessage = getKoalaMessage(totalScore)

    override fun getKoalaMessage() {
        view.showKoalaMessage(koalaMessage)
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