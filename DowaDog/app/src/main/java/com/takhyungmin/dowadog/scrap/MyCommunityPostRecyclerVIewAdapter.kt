package com.takhyungmin.dowadog.scrap

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.communitydetail.CommunityDetailActivity
import com.takhyungmin.dowadog.scrap.model.get.GetMyCommunityPostData
import org.jetbrains.anko.startActivity

class MyCommunityPostRecyclerVIewAdapter(var ctx: Context, var dataList: ArrayList<GetMyCommunityPostData>) : RecyclerView.Adapter<MyCommunityPostRecyclerVIewAdapter.MyCommunityPostRecyclerVIewAdapter>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyCommunityPostRecyclerVIewAdapter {
        var v : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_scrap_act, p0, false)

        return MyCommunityPostRecyclerVIewAdapter(v)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: MyCommunityPostRecyclerVIewAdapter, position: Int) {

        holder.btn.setOnClickListener {
            // 커뮤니티 디테일 액티비티로 넘어가기
            ctx.startActivity<CommunityDetailActivity>("communityId" to dataList[position].id)
        }

        if(dataList[position].createAt != dataList[position].updateAt){
            holder.date.text = dataList[position].updateAt.substring(0, 10)
        }else {
            holder.date.text = dataList[position].createAt.substring(0, 10)
        }

        holder.title.text = dataList[position].title




    }

    inner class MyCommunityPostRecyclerVIewAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var btn: RelativeLayout = itemView.findViewById(R.id.rl_all_box_scrap_act)
        var title: TextView = itemView.findViewById(R.id.tv_title_scrap_act)
        var date: TextView = itemView.findViewById(R.id.tv_date_scrap_act)
    }
}