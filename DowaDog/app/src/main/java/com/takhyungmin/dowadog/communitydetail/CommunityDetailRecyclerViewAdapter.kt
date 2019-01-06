package com.takhyungmin.dowadog.communitydetail

import android.content.Context
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.communitydetail.model.get.CommunityCommentData
import com.takhyungmin.dowadog.utils.CustomDialog
import com.takhyungmin.dowadog.utils.CustomTypeSpan
import java.text.SimpleDateFormat
import java.util.*



class CommunityDetailRecyclerViewAdapter(var ctx: Context, var dataList: ArrayList<CommunityCommentData>) : RecyclerView.Adapter<CommunityDetailRecyclerViewAdapter.CommunityDetailRecyclerViewHolder>() {

    var isModify = 0

    lateinit var dialog : CustomDialog

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommunityDetailRecyclerViewHolder {
        val view: View = LayoutInflater.from(p0.context).inflate(R.layout.rv_item_comment_community_detail_act, p0, false)
        return CommunityDetailRecyclerViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: CommunityDetailRecyclerViewHolder, position: Int) {


        Glide.with(ctx).load(dataList[position].userProfileImg).into(holder.writerIV)
        val font1 = Typeface.createFromAsset(ctx.assets, "nanum_square_bold.ttf")
        val font2 = Typeface.createFromAsset(ctx.assets, "nanum_square_round_r.ttf")

        val ssb = SpannableStringBuilder(dataList[position].userId + "  " + dataList[position].detail)

        ssb.setSpan(CustomTypeSpan("", font1), 0, dataList[position].userId.length , Spanned.SPAN_EXCLUSIVE_INCLUSIVE)
        ssb.setSpan(CustomTypeSpan("", font2), dataList[position].userId.length+1, (dataList[position].userId + dataList[position].detail).length + 2, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)

        holder.contentTV.text = ssb
        //오늘 일 경우
        if(dataList[position].today == true){
            // 들어오는 데이터
            // 2019-01-02T19:07:13.426+0000
            // 2019-01-06T13:27:00.693
            val now = System.currentTimeMillis()
            val date = Date(now)
            val sdf = SimpleDateFormat("HH:mm:ss")
            // 현재시간 ex) 02:07:49
            val getPresentTime = sdf.format(date)
            // 현재시간 ex) 14:00:00
            var time = dataList[position].createdAt.subSequence(11, 19)
            var pastTime = Integer.parseInt(getPresentTime.subSequence(0,2).toString()) - Integer.parseInt(time.subSequence(0,2).toString())
            holder.dateTV.text = pastTime.toString() + "시간 전"
        } // 작성일이 오늘이 아닐 경우
        else {
            holder.dateTV.text = dataList[position].createdAt.subSequence(0, 10).toString()
         }

        holder.rootRL.setOnLongClickListener(OnLongClickListener {

            dialog = CustomDialog(ctx, "댓글을 삭제하시겠습니까?", leftClickListener, View.OnClickListener {
                (ctx!! as CommunityDetailActivity).deleteComment(dataList[position].id)
                dialog.dismiss()
            },"취소", "확인")
            dialog.show()

            false
        })

    }

    inner class CommunityDetailRecyclerViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        var rootRL : RelativeLayout = itemView!!.findViewById(R.id.layout_community_comment_frame)
        var writerIV : ImageView = itemView!!.findViewById(R.id.iv_writter_rv_item_comment_community_detail_act)
        var contentTV : TextView = itemView!!.findViewById(R.id.tv_content_rv_item_comment_community_detail_act1)
        var dateTV: TextView = itemView!!.findViewById(R.id.tv_data_rv_item_comment_community_detail_act)

    }

    val leftClickListener = View.OnClickListener {
        dialog.dismiss()
    }
}