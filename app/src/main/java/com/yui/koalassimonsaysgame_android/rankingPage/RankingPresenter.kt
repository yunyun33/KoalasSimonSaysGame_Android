package com.yui.koalassimonsaysgame_android.rankingPage

import com.yui.koalassimonsaysgame_android.Model.UserRankingModel
import com.yui.koalassimonsaysgame_android.Model.UserRankingModelContract

class RankingPresenter(
    private val view: RankingContract.View
): RankingContract.Presenter {

    private val userRankingModel: UserRankingModelContract  = UserRankingModel()

    override fun didCreate() {
        val rankigData = userRankingModel.selectData()

        if (rankigData.isEmpty()) {
            view.disableDeleteButton()
        } else {
            view.setRankingData(rankigData)
        }
    }

    override fun didTapDeleteButton() {
        view.showAlertDialog()
    }

    override fun didTapPositiveButtonOnDeletePopup() {
        userRankingModel.deleteData()
    }
}