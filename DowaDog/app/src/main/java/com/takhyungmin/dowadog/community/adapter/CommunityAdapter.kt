package com.takhyungmin.dowadog.community.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.community.model.get.GetCommunityContents
import com.takhyungmin.dowadog.presenter.fragment.CommunityFragmentPresenter
import com.takhyungmin.dowadog.utils.CustomDialog
import java.text.SimpleDateFormat
import java.util.*

class CommunityAdapter(var communityItems : ArrayList<GetCommunityContents>, var requestManager: RequestManager, var communityFragmentPresenter: CommunityFragmentPresenter, var context : Context) : RecyclerView.Adapter<CommunityViewHolder>() {

    lateinit var customDialog : CustomDialog

    val now : Long
    val date : Date
    var sdf  : SimpleDateFormat
    // 현재시간 ex) 02:07:49
    val getPresentTime : String

    init {
        now = System.currentTimeMillis()
        date = Date(now)
        sdf = SimpleDateFormat("HH:mm:ss", Locale.KOREA)
        getPresentTime = sdf.format(date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CommunityViewHolder {
        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_community_item, parent,false)
        //mainView.setOnClickListener(onItemClick)
        return CommunityViewHolder(mainView)
    }

    override fun getItemCount(): Int = communityItems.size

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.communityName.text = communityItems[position].userId
        requestManager.load(communityItems[position].userProfileImg).into(holder.communityProfile)
        //holder.communityTime.text = communityItems[position].createdAt
         holder.communityTitle.text = communityItems[position].title


        if(communityItems[position].today){
            // 현재시간 ex) 14:00:00
            var time = communityItems[position].updatedAt.subSequence(11, 19)
            var pastTime = Integer.parseInt(getPresentTime.subSequence(0,2).toString()) - Integer.parseInt(time.subSequence(0,2).toString())
            holder.communityTime.text = pastTime.toString() + "시간 전"
            // 0시간에서 분으로 바꾸기.
            if(pastTime == 0){
                pastTime = Integer.parseInt(getPresentTime.subSequence(3,5).toString()) - Integer.parseInt(time.subSequence(3,5).toString())
                holder.communityTime.text = pastTime.toString() + "분 전"
            }

            if(pastTime == 0){
                holder.communityTime.text = "방금 전"
            }
        }else
            holder.communityTime.text = communityItems[position].updatedAt.subSequence(0, 10).toString()



            holder.communityFrame.clicks().subscribe {
            communityFragmentPresenter.toDetail(communityItems[position].id)
            }

        when(communityItems[position].communityImgList.size){
            1->{
                holder.communityMain1.visibility = View.VISIBLE
                holder.communityMain2.visibility = View.GONE
                holder.communityMain3.visibility = View.GONE
                holder.communityMain4.visibility = View.GONE

                requestManager.load(communityItems[position].communityImgList[0].filePath)
                        .into(holder.communityMain1)
            }
            2->{
                holder.communityMain1.visibility = View.GONE
                holder.communityMain2.visibility = View.VISIBLE
                holder.communityMain3.visibility = View.GONE
                holder.communityMain4.visibility = View.GONE

                requestManager.load(communityItems[position].communityImgList[0].filePath)
                        .into(holder.communityMain21)
                requestManager.load(communityItems[position].communityImgList[1].filePath)
                        .into(holder.communityMain22)
            }
            3->{
                holder.communityMain1.visibility = View.GONE
                holder.communityMain2.visibility = View.GONE
                holder.communityMain3.visibility = View.VISIBLE
                holder.communityMain4.visibility = View.GONE

                requestManager.load(communityItems[position].communityImgList[0].filePath)
                        .into(holder.communityMain31)
                requestManager.load(communityItems[position].communityImgList[1].filePath)
                        .into(holder.communityMain32)
                requestManager.load(communityItems[position].communityImgList[2].filePath)
                        .into(holder.communityMain33)
            }
            4->{
                holder.communityMain1.visibility = View.GONE
                holder.communityMain2.visibility = View.GONE
                holder.communityMain3.visibility = View.VISIBLE
                holder.communityMain4.visibility = View.VISIBLE

                requestManager.load(communityItems[position].communityImgList[0].filePath)
                        .into(holder.communityMain31)
                requestManager.load(communityItems[position].communityImgList[1].filePath)
                        .into(holder.communityMain32)
                requestManager.load(communityItems[position].communityImgList[2].filePath)
                        .into(holder.communityMain33)
            }
            else->{
                holder.communityMain1.visibility = View.VISIBLE
                holder.communityMain2.visibility = View.GONE
                holder.communityMain3.visibility = View.GONE
                holder.communityMain4.visibility = View.GONE
            }
        }
    }

    fun loadDialog() {
        val content = "신고"
        customDialog = CustomDialog(context, content, leftListener, rightListener, "취소", "확인")
        customDialog.show()
    }

    private val leftListener = View.OnClickListener {

        customDialog.dismiss()
    }

    private val rightListener = View.OnClickListener {
        //데이터 저장, 통신, 뷰 변경..
        customDialog.dismiss()
    }

//    fun add(r: CommunityItem) {
//        communityItems.add(r)
//        notifyItemInserted(communityItems.size - 1)
//    }
//
//    fun addAll(communityItems: ArrayList<CommunityItem>) {
//        for (item in communityItems) {
//            add(item)
//        }
//    }

    fun addAll(result : ArrayList<GetCommunityContents>){
        communityItems.addAll(result)
        notifyItemRangeInserted(communityItems.size,
                result.size)

        Log.v("position1", communityItems.size.toString())
        Log.v("position2", result.size.toString())

    }
}