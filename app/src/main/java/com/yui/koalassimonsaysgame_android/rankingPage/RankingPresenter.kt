package com.yui.koalassimonsaysgame_android.rankingPage

import com.yui.koalassimonsaysgame_android.Model.UserRankingModel
import com.yui.koalassimonsaysgame_android.Model.UserRankingModelContract
import com.yui.koalassimonsaysgame_android.Model.UserRankingModelMock

class RankingPresenter(
    private val view: RankingContract.View
): RankingContract.Presenter {

    private val userRankingModel: UserRankingModelContract  = UserRankingModelMock()

    override fun didCreate() {
        val rankingData = userRankingModel.selectData()
        view.setRankingData(rankingData)
    }
}