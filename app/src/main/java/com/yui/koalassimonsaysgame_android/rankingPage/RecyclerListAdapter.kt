package com.yui.koalassimonsaysgame_android.rankingPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yui.koalassimonsaysgame_android.R
import com.yui.koalassimonsaysgame_android.resultPage.ResultActivity

class RecyclerListAdapter(private var rankingDataList: MutableList<ResultActivity.RankingData>): RecyclerView.Adapter<RecyclerListViewHolder>() {

    //ViewHolderクラスを使ってViewHolderを作成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.recyclerview_row, parent, false)

        return RecyclerListViewHolder(item)
    }

    // 一行のViewに対して共通でやりたい処理をここで書く。
    override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
        val rowData = rankingDataList[position]

        for (i in rankingDataList) {

            holder.rank.text =  (position + 1).toString() + "位"
            holder.name.text = rowData.rankingName
            holder.score.text = rowData.rankingScore.toString()
        }
    }

    //recyclerViewのコンテンツのサイズ
    override fun getItemCount(): Int {
        return rankingDataList.size
    }
}