package com.yui.koalassimonsaysgame_android.rankingPage

import android.app.AlertDialog
import android.content.DialogInterface
import android.nfc.NfcAdapter.EXTRA_DATA
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.r0adkll.slidr.Slidr
import com.r0adkll.slidr.model.SlidrInterface
import com.yui.koalassimonsaysgame_android.MainActivity
import com.yui.koalassimonsaysgame_android.R
import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity


class RankingActivity : AppCompatActivity(), RankingContract.View {

    private lateinit var presenter: RankingContract.Presenter

    lateinit var recyclerView: RecyclerView

    //スワイプで前画面に戻れるようにする
    private var slidr: SlidrInterface? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        title = this.getString(R.string.rankingActivity_title)

        slidr = Slidr.attach(this)

        presenter = RankingPresenter(this)

        recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)

        //RecyclerViewにlayoutManagerをセットする。
        recyclerView.layoutManager = LinearLayoutManager(this)

        //RecyclerViewのレイアウトサイズを変更しない設定をONにする(パフォーマンス向上のため)
        recyclerView.setHasFixedSize(true)

        showRankingData()

        setOnClickListener()
    }

    private fun setOnClickListener() {
        findViewById<ImageButton>(R.id.deleteButton).setOnClickListener() {
            presenter.didTapDeleteButton()
        }
    }

    //RankingContract.View

    //Adapter生成してRecyclerViewにセットする。
    override fun setRankingData(data: MutableList<ResultActivity.RankingData>) {
        recyclerView.adapter = RecyclerListAdapter(data)

    }

    override fun showRankingData() {
        val isWorldRanking = intent.getBooleanExtra("WORLDRANKING_KEY", false)

        if (isWorldRanking == false) {
            //local ranking表示
            presenter.didCreate()
        } else if (isWorldRanking == true) {
            //Firebase ranking表示
            presenter.didCreateWorldRanking()
        }
    }

    override fun showAlertDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(this.getString(R.string.deleteRankingDataDialog_title))
        dialog.setMessage(this.getString(R.string.deleteRankingDataDialog_message))

        dialog.setPositiveButton(this.getString(R.string.deleteRankingDataDialog_positiveText), DialogInterface.OnClickListener { dialog, which ->

            // OKボタン押したときの処理(ランキングデータを削除する)
            presenter.didTapPositiveButtonOnDeletePopup()
        })

        dialog.setNegativeButton(this.getString(R.string.deleteRankingDataDialog_negativeText), null)
        dialog.show()
    }

    override fun disableDeleteButton() {
        //ゴミ箱ボタンを無効にする。
        findViewById<ImageButton>(R.id.deleteButton).isEnabled = false
    }

    override fun resetRankingData(data: MutableList<ResultActivity.RankingData>) {
        recyclerView.adapter = RecyclerListAdapter(data)
        (recyclerView.adapter as RecyclerListAdapter).notifyDataSetChanged()
    }
}