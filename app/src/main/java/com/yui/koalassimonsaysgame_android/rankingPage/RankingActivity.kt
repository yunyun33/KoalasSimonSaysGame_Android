package com.yui.koalassimonsaysgame_android.rankingPage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yui.koalassimonsaysgame_android.R

class RankingActivity : AppCompatActivity() {

    private var page = 1
    private var recyclerListAdapter: RecyclerListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

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

    private fun createRowData(page: Int): MutableList<RowData> {
      //Rankingの処理
        val dataSet: MutableList<RowData> = ArrayList()
        var i = 1
        while (i < page * 4) {
            val data = RowData()
            data.rank = "${i}位"
            data.name = "ミニしろ"
            data.score = "9点"
            val add = dataSet.add(data)
            i += 1
        }
        return dataSet
    }

    inner class RowData {
        var rank: String? = null
        var name: String? = null
        var score: String? = null
    }
}