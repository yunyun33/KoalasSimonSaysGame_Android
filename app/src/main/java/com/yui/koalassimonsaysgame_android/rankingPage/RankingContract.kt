package com.yui.koalassimonsaysgame_android.rankingPage

import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity

interface RankingContract {
    interface View {
        fun setRankingData(data: MutableList<ResultActivity.RankingData>)
        fun showRankingData()
        fun showAlertDialog()
        fun disableDeleteButton()
        fun resetRankingData(data: MutableList<ResultActivity.RankingData>)
    }

    interface Presenter {
        fun didCreate()
        fun didCreateWorldRanking()
        fun didTapPositiveButtonOnDeletePopup()
        fun didTapDeleteButton()
    }
}