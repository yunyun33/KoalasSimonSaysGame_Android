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
//        val rankingData = userRankingModel.selectData()

//        if (rankingData == mutableListOf<ResultActivity.RankingData>()) {
//            // ランキングデータがなかったら、ボタンを無効にする。
//            button.isClickable = false
//
//        } else {
//            button.setOnClickListener() {
//        }
    }

    override fun didTapPositiveButton() {
        userRankingModel.deleteData()
    }
}