package com.yui.koalassimonsaysgame_android.rankingPage

import android.widget.ImageButton
import com.yui.koalassimonsaysgame_android.Model.UserRankingModel
import com.yui.koalassimonsaysgame_android.Model.UserRankingModelContract
import com.yui.koalassimonsaysgame_android.Model.UserRankingModelMock
import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity

class RankingPresenter(
    private val view: RankingContract.View
): RankingContract.Presenter {

    private val userRankingModel: UserRankingModelContract  = UserRankingModel()

    override fun didCreate() {
        val rankingData = userRankingModel.selectData()
        view.setRankingData(rankingData)
    }

    override fun didTapDeleteButton() {
        userRankingModel.deleteData()
    }

    override fun disableDeleteButton(button: ImageButton) {
        val rankingData = userRankingModel.selectData()

        if ( rankingData == null) {
            button.isClickable = false
        }
    }
}