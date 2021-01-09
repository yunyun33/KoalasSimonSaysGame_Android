package com.yui.koalassimonsaysgame_android.rankingPage

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yui.koalassimonsaysgame_android.R

class RecyclerListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    //リスト1行分に表示する部品
    val rank: TextView = itemView.findViewById(R.id.rank)
    val rankingImageView: ImageView = itemView.findViewById(R.id.ranking_imageView)
    val name: TextView = itemView.findViewById(R.id.name)
    val score: TextView = itemView.findViewById(R.id.score)

}