package com.yui.koalassimonsaysgame_android.rankingPage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yui.koalassimonsaysgame_android.R

class RecyclerListAdapter(private val rankingData: MutableList<String>): RecyclerView.Adapter<RecyclerListViewHolder>() {

    //ViewHolderクラスを使ってViewHolderを作成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val item = layoutInflater.inflate(R.layout.recyclerview_row, parent, false)
        return RecyclerListViewHolder(item)
    }

    //recyclerViewのコンテンツのサイズ
    override fun getItemCount(): Int {
        return rankingData.size
    }

    // ViewHolderに表示する画像とテキストを挿入
    override fun onBindViewHolder(holder: RecyclerListViewHolder, position: Int) {
    }

}