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
    lateinit var adapter: RecyclerListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        title = "ランキング"

        presenter = RankingPresenter(this)

        recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)

        //RecyclerViewにlayoutManagerをセットする。
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        //RecyclerViewのレイアウトサイズを変更しない設定をONにする(パフォーマンス向上のため)
        recyclerView.setHasFixedSize(true)

        presenter.didCreate()

        setOnClickListener()
    }

    private fun setOnClickListener() {
        findViewById<ImageButton>(R.id.deleteButton).setOnClickListener() {

            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("ランキングデータを\n削除します。")
            dialog.setMessage("データは全て削除されます。")

            dialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->

                // OKボタン押したときの処理(ランキングデータを削除する)
                presenter.didTapDeleteButton()
                adapter.notifyDataSetChanged()
            })

            dialog.setNegativeButton("キャンセル", null)
            dialog.show()
        }
    }

    //Adapter生成してRecyclerViewにセットする。
    override fun setRankingData(data: MutableList<ResultActivity.RankingData>) {
        adapter = RecyclerListAdapter(data)
        recyclerView.adapter = adapter
    }
}