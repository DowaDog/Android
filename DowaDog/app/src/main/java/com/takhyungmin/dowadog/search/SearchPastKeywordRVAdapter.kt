package com.takhyungmin.dowadog.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.searchresult.SearchResultActivity
import org.jetbrains.anko.startActivity

class SearchPastKeywordRVAdapter(private var ctx: Context ,private var dataList: ArrayList<String>) : RecyclerView.Adapter<SearchPastKeywordRVAdapter.SearchPastKeywordViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchPastKeywordViewHolder {
        val pastKeywordView  : View = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_search_act_past_keyword, parent, false)
        return SearchPastKeywordViewHolder(pastKeywordView)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: SearchPastKeywordViewHolder, position: Int) {
        holder.pastKeyword.text = dataList[position]
        holder.btn.setOnClickListener{
            ctx.startActivity<SearchResultActivity>()
        }
    }

    inner class SearchPastKeywordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView!!){
        var pastKeyword : TextView = itemView!!.findViewById(R.id.tv_past_keyword_rv_item_search_act)
        var btn : RelativeLayout = itemView!!.findViewById(R.id.btn_past_keyword_rv_item_search_act)

    }
}