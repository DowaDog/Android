package com.takhyungmin.dowadog.scrap

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.takhyungmin.dowadog.R

class ScrapRecyclerViewAdapter(var ctx: Context, var dataList: ArrayList<ScrapRVData>, var isScrapAct : Int) : RecyclerView.Adapter<ScrapRecyclerViewAdapter.ScrapRecyclerViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ScrapRecyclerViewHolder {
        var v : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_scrap_act, p0, false)

        return ScrapRecyclerViewHolder(v)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ScrapRecyclerViewHolder, position: Int) {

        // Scrap일때
        if(isScrapAct == 1){

            holder.btn.setOnClickListener {
                // 컨텐츠디테일 액티비티로 넘어가기
            }

        }else {
            holder.btn.setOnClickListener {
                // 커뮤니티 디테일 액티비티로 넘어가기
            }
        }

        holder.title.text = dataList[position].title

        holder.date.text = dataList[position].date


    }

    inner class ScrapRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btn: RelativeLayout = itemView.findViewById(R.id.rl_all_box_scrap_act)
        var title: TextView = itemView.findViewById(R.id.tv_title_scrap_act)
        var date: TextView = itemView.findViewById(R.id.tv_date_scrap_act)
    }
}

data class ScrapRVData(
        var postId: Int,
        var title : String,
        var date : String
)