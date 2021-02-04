package com.yui.koalassimonsaysgame_android.rankingPage

import com.yui.koalassimonsaysgame_android.Model.UserRankingModel
import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity

class RankingPresenter(
    private val view: RankingContract.View
): RankingContract.Presenter {

    private val userRankingModel = UserRankingModel()
    private var rankingDataList = mutableListOf<ResultActivity.RankingData>()
    private var page = 1

    override fun didGetRankingData() {
        userRankingModel.selectData()
    }

}