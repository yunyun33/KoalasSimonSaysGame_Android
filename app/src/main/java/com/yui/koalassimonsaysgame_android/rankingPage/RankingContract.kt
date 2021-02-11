package com.yui.koalassimonsaysgame_android.rankingPage

import android.widget.ImageButton
import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity

interface RankingContract {
    interface View {
        fun setRankingData(data: MutableList<ResultActivity.RankingData>)
        fun showAlertDialog()
    }

    interface Presenter {
        fun didCreate()
        fun didTapPositiveButton()
        fun didTapDeleteButton()
    }
}