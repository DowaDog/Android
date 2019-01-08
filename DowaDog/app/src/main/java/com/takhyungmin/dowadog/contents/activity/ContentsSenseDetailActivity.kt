package com.takhyungmin.dowadog.contents.activity

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.contents.adapter.ContentsSenseDetailRvAdapter
import com.takhyungmin.dowadog.contents.model.ContentsSenseDetailObject
import com.takhyungmin.dowadog.contents.model.get.ContentSenseDetailResponse
import com.takhyungmin.dowadog.login.LoginActivity
import com.takhyungmin.dowadog.presenter.activity.ContentsSenseDetailActivityPresenter
import com.takhyungmin.dowadog.utils.ApplicationData
import com.takhyungmin.dowadog.utils.CustomDialog
import kotlinx.android.synthetic.main.activity_contents_edu_detail.*
import kotlinx.android.synthetic.main.activity_contents_sense_detail.*

class ContentsSenseDetailActivity : AppCompatActivity() {

    private lateinit var contentsSenseDetailActivityPresenter: ContentsSenseDetailActivityPresenter
    private lateinit var contentsSenseDetailRvAdapter: ContentsSenseDetailRvAdapter
    private lateinit var requestManager : RequestManager
    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        //ActivityCompat.setEnterSharedElementCallback(this, EnterTransitionCallback)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
        //setTheme(R.style.ContentsDetailActivityBasic)
        //window.statusBarColor = Color.TRANSPARENT
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_sense_detail)
        Glide.with(this).load(intent.getStringExtra("image")).into(img_contents_sense_detail)
        tv_contents_sense_detail_title.text = intent.getStringExtra("title")
        rv_contents_sense_detail_content.setFocusable(false)
        layout_sense_detail.requestFocus()
        setOnBinding()
        init()
    }

    private fun init(){
        contentsSenseDetailActivityPresenter = ContentsSenseDetailActivityPresenter()
        contentsSenseDetailActivityPresenter.view = this


        id = intent.getIntExtra("id", 27)
        contentsSenseDetailActivityPresenter.requestData(id)
        //contentsSenseDetailActivityPresenter.initView()
        setScrollListener()
        initPresenter()
    }

    fun initView(contentsSenseDetailItems : ArrayList<ContentSenseDetailResponse>){
        requestManager = Glide.with(this)
       // contentsSenseDetailRvAdapter = ContentsSenseDetailRvAdapter(contentsSenseDetailItems, requestManager)
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
                window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
                window.statusBarColor = this.resources.getColor(R.color.status2)
                layout_contents_sense_detail_toolbar_move.visibility = View.VISIBLE
            }
        })
    }


    fun responseSenseData(data: ContentSenseDetailResponse) {

        data?.let {

            requestManager = Glide.with(this)

            var setContentsSenseDetailAdapter = ContentsSenseDetailRvAdapter(it.data.content, requestManager)
            //여기에 받아온 데이터들을 가져와서 보여주는 것을 해야함 (함수로 만들던 여기에 구현하던)
            Log.v("ygyg", it.data.content.toString())


            rv_contents_sense_detail_content.adapter = setContentsSenseDetailAdapter
            rv_contents_sense_detail_content.layoutManager = LinearLayoutManager(this)

            rv_contents_sense_detail_content.setNestedScrollingEnabled(false)

        }
    }

    //view에 presenter 붙여주기
    private fun initPresenter() {

        contentsSenseDetailActivityPresenter= ContentsSenseDetailActivityPresenter()
        // 뷰 붙여주는 작업
        contentsSenseDetailActivityPresenter.view = this
        ContentsSenseDetailObject.contentsSenseDetailActivityPresenter = contentsSenseDetailActivityPresenter

        Log.v("TAGG", "SenseDetail 엑티비티 이닛프레젠터")

    }

    fun setOnBinding(){
        btn_contents_sense_detail_back1.clicks().subscribe {
            finish()
        }

        btn_contents_sense_detail_back2.clicks().subscribe {
            finish()
        }

        btn_contents_sense_detail_scrap1.clicks().subscribe {
            if(ApplicationData.auth == "")
                logoutCustomDialog.show()
            else
                contentsSenseDetailActivityPresenter.requestScrap(id)
        }

        btn_contents_sense_detail_scrap2.clicks().subscribe {
            if(ApplicationData.auth == "")
                logoutCustomDialog.show()
            else
                contentsSenseDetailActivityPresenter.requestScrap(id)
        }
    }

    val logoutCustomDialog : CustomDialog  by lazy {
        CustomDialog(this, "로그인이 필요한 서비스입니다.\n로그인 하시겠습니까?", responseRight, responseLeft,"취소", "확인")
    }

    private val responseRight = View.OnClickListener {

        logoutCustomDialog!!.dismiss()
    }
    private val responseLeft = View.OnClickListener {
        startActivity(Intent(this, LoginActivity::class.java))
        logoutCustomDialog!!.dismiss()
        //##로그아웃
    }

    fun responseScrap(clear : Boolean){
        if(clear){
            btn_contents_sense_detail_scrap1_1.setImageResource(R.drawable.contents_scrap_btn)
            btn_contents_edu_detail_scrap2_1.setImageResource(R.drawable.contents_scrap_btn)
        }else{
            btn_contents_edu_detail_scrap1_1.setImageResource(R.drawable.contents_unscrap_btn)
            btn_contents_edu_detail_scrap2_1.setImageResource(R.drawable.contents_unscrap_btn)
        }
    }


}
