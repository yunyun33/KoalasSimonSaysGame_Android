package com.yui.koalassimonsaysgame_android.rankingPage

import com.yui.koalassimonsaysgame_android.Model.UserRankingModel
import com.yui.koalassimonsaysgame_android.Model.UserRankingModelContract

class RankingPresenter(
    private val view: RankingContract.View
): RankingContract.Presenter {

    private val userRankingModel: UserRankingModelContract  = UserRankingModel()

    override fun didCreate() {
        view.setRankingData(userRankingModel.selectData())
    }

    override fun didTapDeleteButton() {
        view.showAlertDialog()
    }

    override fun didTapPositiveButtonOnDeletePopup() {
        userRankingModel.deleteData()
    }
}