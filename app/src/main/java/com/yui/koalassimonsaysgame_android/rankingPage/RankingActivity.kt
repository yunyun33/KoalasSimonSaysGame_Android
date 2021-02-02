package com.yui.koalassimonsaysgame_android.rankingPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yui.koalassimonsaysgame_android.R
import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity

class RankingActivity : AppCompatActivity() {

    companion object {
        val RANKING_DATA = "ranking_data"
    }

    private var rankingDataList = mutableListOf<ResultActivity.RankingData>()
    private var page = 1
    private var recyclerListAdapter: RecyclerListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        //TotalScoreActivityからランキングに登録する値(名前とスコア)を引き受ける
        val data: ResultActivity.RankingData = intent.getSerializableExtra(RANKING_DATA) as ResultActivity.RankingData
        rankingDataList.add(data)


        val recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
        //RecyclerViewのレイアウトサイズを変更しない設定をONにする(パフォーマンス向上のため)
        recyclerView.setHasFixedSize(true)

        //RecyclerViewにlayoutManagerをセットする。
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        //Adapter生成してRecyclerViewにセットする。
        recyclerListAdapter = RecyclerListAdapter(createRowData(page))
        recyclerView.adapter = recyclerListAdapter
    }

    private fun createRowData(page: Int): MutableList<ResultActivity.RankingData> {
      //Rankingの処理
        val dataSet: MutableList<ResultActivity.RankingData> = ArrayList()
        var rowCount = 1
        while (rowCount < page * 4) {
            val data = RowData()
            data.name = "${dataSet}"
            data.score = "9点"
            rowCount += 1
        }
        return dataSet
    }

    inner class RowData {
        var name: String? = null
        var score: String? = null
    }
}