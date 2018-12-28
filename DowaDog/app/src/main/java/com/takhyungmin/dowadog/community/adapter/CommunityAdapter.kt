package com.takhyungmin.dowadog.community.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.community.model.CommunityItem
import com.takhyungmin.dowadog.presenter.fragment.CommunityFragmentPresenter
import com.takhyungmin.dowadog.utils.BaseDialog

class CommunityAdapter(var communityItems : ArrayList<CommunityItem>, var requestManager: RequestManager, var communityFragmentPresenter: CommunityFragmentPresenter, var context : Context) : RecyclerView.Adapter<CommunityViewHolder>() {

    lateinit var baseDialog : BaseDialog

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CommunityViewHolder {
        val mainView = LayoutInflater.from(parent.context).inflate(R.layout.fragment_community_item, parent,false)
        //mainView.setOnClickListener(onItemClick)
        return CommunityViewHolder(mainView)
    }

    override fun getItemCount(): Int = communityItems.size

    override fun onBindViewHolder(holder: CommunityViewHolder, position: Int) {
        holder.communityName.text = communityItems[position].communityName
        requestManager.load(communityItems[position].communityProfile).into(holder.communityProfile)
        holder.communityTime.text = communityItems[position].communityTime
        holder.communityMore.setOnClickListener {
            loadDialog()
        }
        when(communityItems[position].communityImage.size){
            1->{
                holder.communityMain1.visibility = View.VISIBLE
                holder.communityMain2.visibility = View.GONE
                holder.communityMain3.visibility = View.GONE
                holder.communityMain4.visibility = View.GONE

                requestManager.load(communityItems[position].communityImage[0])
                        .into(holder.communityMain1)
            }
            2->{
                holder.communityMain1.visibility = View.GONE
                holder.communityMain2.visibility = View.VISIBLE
                holder.communityMain3.visibility = View.GONE
                holder.communityMain4.visibility = View.GONE

                requestManager.load(communityItems[position].communityImage[0])
                        .into(holder.communityMain21)
                requestManager.load(communityItems[position].communityImage[1])
                        .into(holder.communityMain22)
            }
            3->{
                holder.communityMain1.visibility = View.GONE
                holder.communityMain2.visibility = View.GONE
                holder.communityMain3.visibility = View.VISIBLE
                holder.communityMain4.visibility = View.GONE

                requestManager.load(communityItems[position].communityImage[0])
                        .into(holder.communityMain31)
                requestManager.load(communityItems[position].communityImage[1])
                        .into(holder.communityMain32)
                requestManager.load(communityItems[position].communityImage[2])
                        .into(holder.communityMain33)
            }
            4->{
                holder.communityMain1.visibility = View.GONE
                holder.communityMain2.visibility = View.GONE
                holder.communityMain3.visibility = View.VISIBLE
                holder.communityMain4.visibility = View.VISIBLE

                requestManager.load(communityItems[position].communityImage[0])
                        .into(holder.communityMain31)
                requestManager.load(communityItems[position].communityImage[1])
                        .into(holder.communityMain32)
                requestManager.load(communityItems[position].communityImage[2])
                        .into(holder.communityMain33)
            }
        }
    }

    fun loadDialog() {
        val content = "신고"
        baseDialog = BaseDialog(context, content, leftListener, rightListener)
        baseDialog.show()
    }

    private val leftListener = View.OnClickListener {
        baseDialog.dismiss()
    }

    private val rightListener = View.OnClickListener {
        baseDialog.dismiss()
    }
}