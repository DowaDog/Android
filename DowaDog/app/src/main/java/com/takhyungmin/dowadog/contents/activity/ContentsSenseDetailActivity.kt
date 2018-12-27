package com.takhyungmin.dowadog.contents.activity

import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.adapter.ContentsSenseDetailItem
import com.takhyungmin.dowadog.contents.adapter.ContentsSenseDetailRvAdapter
import com.takhyungmin.dowadog.presenter.activity.ContentsSenseDetailActivityPresenter
import kotlinx.android.synthetic.main.activity_contents_sense_detail.*

class ContentsSenseDetailActivity : AppCompatActivity() {

    private lateinit var contentsSenseDetailActivityPresenter: ContentsSenseDetailActivityPresenter
    private lateinit var contentsSenseDetailRvAdapter: ContentsSenseDetailRvAdapter
    private lateinit var requestManager : RequestManager
    override fun onCreate(savedInstanceState: Bundle?) {
        //ActivityCompat.setEnterSharedElementCallback(this, EnterTransitionCallback)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //setTheme(R.style.ContentsDetailActivityBasic)
        //window.statusBarColor = Color.TRANSPARENT
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_sense_detail)
        Glide.with(this).load(R.drawable.pic1).into(img_contents_sense_detail)
        tv_contents_sense_detail_title.text = intent.getStringExtra("title")
        rv_contents_sense_detail_content.setFocusable(false)
        layout_sense_detail.requestFocus()
        init()
    }

    private fun init(){
        contentsSenseDetailActivityPresenter = ContentsSenseDetailActivityPresenter()
        contentsSenseDetailActivityPresenter.view = this
        contentsSenseDetailActivityPresenter.initView()
        setScrollListener()
    }

    fun initView(contentsSenseDetailItems : ArrayList<ContentsSenseDetailItem>){
        requestManager = Glide.with(this)
        contentsSenseDetailRvAdapter = ContentsSenseDetailRvAdapter(contentsSenseDetailItems, requestManager)
        rv_contents_sense_detail_content.layoutManager = LinearLayoutManager(this)
        rv_contents_sense_detail_content.adapter = contentsSenseDetailRvAdapter
    }

    private fun setScrollListener(){
        sv_contents_sense_detail_scroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY + 30) {
                //scroll down
                window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                layout_contents_sense_detail_toolbar_move.visibility = View.GONE
                layout_contents_sense_detail_toolbar_basic.visibility = View.GONE
                if(scrollY > img_contents_sense_detail.height){
                    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                }

            }
            if (scrollY < oldScrollY - 30) {
                //scroll up
                window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                window.statusBarColor = this.resources.getColor(R.color.status2)
                layout_contents_sense_detail_toolbar_move.visibility = View.VISIBLE
                if(scrollY < img_contents_sense_detail.height){
                    window.decorView.systemUiVisibility = 0
                    window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                    layout_contents_sense_detail_toolbar_basic.visibility = View.VISIBLE
                    layout_contents_sense_detail_toolbar_move.visibility = View.GONE
                }
            }

            if (scrollY == 0) {
                //scroll in top
//                window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//                layout_contents_edu_detail_toolbar_basic.visibility = View.VISIBLE
//                layout_contents_edu_detail_toolbar_move.visibility = View.GONE
            }

            if (scrollY == ( v.getChildAt(0).height - v.height )) {
                //scroll in bottom
                layout_contents_sense_detail_toolbar_move.visibility = View.VISIBLE
            }
        })
    }
}
