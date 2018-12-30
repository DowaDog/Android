package com.takhyungmin.dowadog.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.searchresult.SearchResultActivity
import org.jetbrains.anko.startActivity

class SearchRecyclerViewAdapter(var ctx: Context, var recommendKeyword: ArrayList<String>) : RecyclerView.Adapter<SearchRecyclerViewAdapter.SearchRecyclerViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SearchRecyclerViewHolder {
        val searchView : View = LayoutInflater.from(p0.context).inflate(R.layout.rv_item_search_act_keyword, p0, false)
        return SearchRecyclerViewHolder(searchView)
    }

    override fun getItemCount(): Int = recommendKeyword.size

    override fun onBindViewHolder(holder: SearchRecyclerViewHolder, position: Int) {
        holder.recommendKeyword.text = recommendKeyword[position]

        holder.btnRootView.setOnClickListener {
            Log.v("TAGG", holder.recommendKeyword.text.toString())
            ctx.startActivity<SearchResultActivity>("keyword" to holder.recommendKeyword.text.toString())
        }
    }

    inner class SearchRecyclerViewHolder(itemView: View?): RecyclerView.ViewHolder(itemView!!) {
        var recommendKeyword : TextView = itemView!!.findViewById(R.id.tv_keyword_rv_item_search_act) as TextView
        var btnRootView : RelativeLayout = itemView!!.findViewById(R.id.rl_root_view_rv_item_search_act_keyword)

    }
}