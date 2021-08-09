package com.yui.koalassimonsaysgame_android.rankingPage

import android.util.Log
import com.yui.koalassimonsaysgame_android.Model.UserRankingModel
import com.yui.koalassimonsaysgame_android.Model.UserRankingModelContract
import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity
import kotlinx.coroutines.*

class RankingPresenter(
    private val view: RankingContract.View
): RankingContract.Presenter {

    private val userRankingModel: UserRankingModelContract = UserRankingModel()

    override fun didCreate() {
        val rankingData = userRankingModel.selectData()

        if (rankingData.isEmpty()) {
            view.disableDeleteButton()
        } else {
            view.setRankingData(rankingData)
        }
    }

    override fun didCreateWorldRanking() {

        userRankingModel.selectDataToFirebase {
            Log.i("ランキングデータ：", "${it.count()}")

            if (it.isEmpty()) {
                view.disableDeleteButton()
            } else {
                view.setRankingData(it)
            }
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