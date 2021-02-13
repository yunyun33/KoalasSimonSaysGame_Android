package com.yui.koalassimonsaysgame_android.rankingPage

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yui.koalassimonsaysgame_android.R
import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity

class RankingActivity : AppCompatActivity(), RankingContract.View {

    private lateinit var presenter: RankingContract.Presenter

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        title = "ランキング"

        presenter = RankingPresenter(this)

        recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)

        //RecyclerViewにlayoutManagerをセットする。
        recyclerView.layoutManager = LinearLayoutManager(this)

        //RecyclerViewのレイアウトサイズを変更しない設定をONにする(パフォーマンス向上のため)
        recyclerView.setHasFixedSize(true)

        presenter.didCreate()

        setOnClickListener()
    }

    private fun setOnClickListener() {
        findViewById<ImageButton>(R.id.deleteButton).setOnClickListener() {
            presenter.didTapDeleteButton()
        }
    }

    //Adapter生成してRecyclerViewにセットする。
    override fun setRankingData(data: MutableList<ResultActivity.RankingData>) {
        recyclerView.adapter = RecyclerListAdapter(data)
    }

    override fun showAlertDialog() {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("ランキングデータを\n削除します。")
        dialog.setMessage("データは全て削除されます。")

        dialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->

            // OKボタン押したときの処理(ランキングデータを削除する)
            presenter.didTapPositiveButtonOnDeletePopup()

            //deleteButton押されたらリストに表示されているデータをすぐに消す(表示を空にする)。
            val data: MutableList<ResultActivity.RankingData> = mutableListOf()
            recyclerView.adapter = RecyclerListAdapter(data)
            (recyclerView.adapter as RecyclerListAdapter).notifyDataSetChanged()
        })

        dialog.setNegativeButton("キャンセル", null)
        dialog.show()
    }

    override fun disableDeleteButton() {
        //ゴミ箱ボタンを無効にする。
        findViewById<ImageButton>(R.id.deleteButton).isEnabled = false
    }
}