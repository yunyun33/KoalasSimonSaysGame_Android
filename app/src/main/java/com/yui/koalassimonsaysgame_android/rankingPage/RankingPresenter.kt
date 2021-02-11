package com.yui.koalassimonsaysgame_android.rankingPage

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.ImageButton
import com.yui.koalassimonsaysgame_android.ApplicationController
import com.yui.koalassimonsaysgame_android.Model.UserRankingModel
import com.yui.koalassimonsaysgame_android.Model.UserRankingModelContract

class RankingPresenter(
    private val view: RankingContract.View
): RankingContract.Presenter {

    private val userRankingModel: UserRankingModelContract  = UserRankingModel()

    var context: Context = ApplicationController.applicationContext()

    override fun didCreate() {
        view.setRankingData(userRankingModel.selectData())
    }

    override fun checkRankingData(button: ImageButton) {
        val rankingData = userRankingModel.selectData()

        if (rankingData.isEmpty()) {
            // ランキングデータがなかったら、ボタンを無効にする。
            button.isClickable = false

        } else {
            button.setOnClickListener() {

                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("ランキングデータを\n削除します。")
                dialog.setMessage("データは全て削除されます。")

                dialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->

                    // OKボタン押したときの処理(ランキングデータを削除する)
                    userRankingModel.deleteData()
                })

                dialog.setNegativeButton("キャンセル", null)
                dialog.show()
            }
        }
    }
}