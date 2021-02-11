package com.yui.koalassimonsaysgame_android.rankingPage

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
    }

    override fun didTapDeleteButton() {
        val deleteButton = findViewById<ImageButton>(R.id.deleteButton)
        presenter.checkRankingData(deleteButton)
    }

    //Adapter生成してRecyclerViewにセットする。
    override fun setRankingData(data: MutableList<ResultActivity.RankingData>) {
        recyclerView.adapter = RecyclerListAdapter(data)
    }
}