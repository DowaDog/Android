package com.takhyungmin.dowadog.communitydetail

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.R

class CommunityDetailViewPagerAdapter(val ctx: Context, var ImageData: ArrayList<String>)  : PagerAdapter(){

    // isViewFromObject() : instantiateItem메소드에서 생성한 객체를 이용할 것인지 여부를 반환 한다.
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as View
    }

    // getCount() : 현재 PagerAdapter에서 관리할 갯수를 반환 한다.
    override fun getCount(): Int = ImageData.size

    // instantiateItem() : ViewPager에서 사용할 뷰객체 생성 및 등록 한다.
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_community_detail, container, false)
        val imageView : ImageView = itemView.findViewById(R.id.iv_community_detail_item_frag) as ImageView
        Glide.with(ctx).load(ImageData[position]).into(imageView)
        container.addView(itemView)
        return itemView
    }

    // destroyItem() : View 객체를 삭제 한다.
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.invalidate()
    }
}