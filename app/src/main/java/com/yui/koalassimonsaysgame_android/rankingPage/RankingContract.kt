package com.yui.koalassimonsaysgame_android.rankingPage

import android.widget.ImageButton
import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity

interface RankingContract {
    interface View {
        fun setRankingData(data: MutableList<ResultActivity.RankingData>)
    }

    interface Presenter {
        fun didCreate()
        fun didTapDeleteButton()
        fun disableDeleteButton(button: ImageButton)
    }
}