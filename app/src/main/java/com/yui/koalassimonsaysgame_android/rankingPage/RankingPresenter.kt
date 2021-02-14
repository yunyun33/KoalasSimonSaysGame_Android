package com.yui.koalassimonsaysgame_android.rankingPage

import com.yui.koalassimonsaysgame_android.Model.UserRankingModel
import com.yui.koalassimonsaysgame_android.Model.UserRankingModelContract
import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity

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

        //deleteButton押されたらリストに表示されているデータをすぐに消す(表示を空にする)。
        val data: MutableList<ResultActivity.RankingData> = mutableListOf()
        view.resetRankingData(data)
        view.disableDeleteButton()
    }
}