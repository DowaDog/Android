package com.takhyungmin.dowadog.dogdetail

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.PressedAdoptActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.dogdetail.model.DogDetailObject
import com.takhyungmin.dowadog.dogdetail.model.get.GetDogDetailData
import com.takhyungmin.dowadog.dogdetail.model.get.GetDogDetailResponse
import com.takhyungmin.dowadog.login.LoginActivity
import com.takhyungmin.dowadog.presenter.activity.DogDetailActivityPresenter
import com.takhyungmin.dowadog.utils.*
import kotlinx.android.synthetic.main.activity_dog_detail.*
import kotlinx.android.synthetic.main.custom_dialog_share.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class DogDetailActivity : AppCompatActivity(), View.OnClickListener {

    private var isLike: Int = 0
    private var isKakaoShare = 0
    private var islinkShare = 0


    lateinit var dogDetailActivityPresenter : DogDetailActivityPresenter

    var animalId : Int = 0

    var educationState = true

    var careTel = ""

    var careName = ""

    val completeDogDialog: CustomCompleteDogDialog by lazy {
        CustomCompleteDogDialog(this@DogDetailActivity, "잠깐! 아직 입양 할 수 없어요!", completeConfirmListener, "확인")
    }

    private val shareDogDialog: CustomShareDogDialog by lazy {
        CustomShareDogDialog(this@DogDetailActivity, shareKaKaoBtnListener, shareLinkBtnListener, shareCancleListener, shareConfirmListener, "취소", "확인")
    }

    val shareKaKaoDialog: CustomDialog by lazy {
        CustomDialog(this@DogDetailActivity, "'기다릴개'에서 '카카오톡'을(를)\n" +
                "열려고 합니다.", View.OnClickListener { shareKaKaoDialog.dismiss() }, View.OnClickListener { shareThanksDialog }, "취소", "확인")
    }

    val shareThanksDialog: CustomThanksDogDialog by lazy {
        CustomThanksDogDialog(this@DogDetailActivity, "공유가 완료되었습니다.", View.OnClickListener { shareThanksDialog.dismiss() }, "확인")
    }

    override fun onClick(v: View?) {
        when (v) {

        // 백버튼
            btn_back_dog_detail_act -> {
                finish()
            }

        // 공유하기 버튼
            btn_share_dog_detail_act -> {
                shareDogDialog.show()
            }

        // 좋아요 버튼
            btn_heart_dog_detail_act -> {
                if (isLike == 0) {
                    iv_heart_dog_detail_act.setImageResource(R.drawable.hearts_full_icon)
                    isLike = 1
                    // 좋아요 통신
                } else {
                    iv_heart_dog_detail_act.setImageResource(R.drawable.hearts_line_icon)
                    isLike = 0
                    // 좋아요 취소 통신
                }
            }


        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_detail)

        setStatusBarTransparent()
        init()
        initPresenter()

        animalId = intent.getIntExtra("animalId", 0)
        dogDetailActivityPresenter.requestData(animalId)


    }

    fun init() {
        btn_back_dog_detail_act.setOnClickListener(this)
        btn_share_dog_detail_act.setOnClickListener(this)
        btn_heart_dog_detail_act.setOnClickListener(this)
        btn_adopt_dog_detail_act.clicks().subscribe {

            if(ApplicationData.auth == "")
                logoutCustomDialog.show()
            else{
                // TODO : 왜 Intent가 안넘아가는지 확인

                if(educationState){
                    startActivity<PressedAdoptActivity>("num" to careTel, "spotName" to careName)
                }else{
                    completeDogDialog.show()
                }

                // startActivity(Intent(this, PressedAdoptActivity::class.java))
            }
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

    // 상태바 투명하게 하는 함수
    private fun setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    // 상태바 투명하게 하는 함수
    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    private val completeConfirmListener = View.OnClickListener { completeDogDialog!!.dismiss() }
    private val shareKaKaoBtnListener = View.OnClickListener {
        if (isKakaoShare == 0) {
            shareDogDialog.tv_kakao_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#ffc233"))
            shareDogDialog.tv_link_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#434343"))
            isKakaoShare = 1
            islinkShare = 0
        } else {
            shareDogDialog.tv_kakao_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#434343"))
            isKakaoShare = 0
            islinkShare = 1
        }

    }
    private val shareLinkBtnListener = View.OnClickListener {
        if (islinkShare == 0) {
            shareDogDialog.tv_link_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#ffc233"))
            shareDogDialog.tv_kakao_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#434343"))
            islinkShare = 1
            isKakaoShare = 0
        } else {
            shareDogDialog.tv_link_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#434343"))
            islinkShare = 0
            isKakaoShare = 1
        }

    }
    private val shareCancleListener = View.OnClickListener {
        islinkShare = 0
        isKakaoShare = 0
        shareDogDialog.tv_kakao_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#434343"))
        shareDogDialog.tv_link_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#434343"))
        shareDogDialog!!.dismiss()
    }
    private val shareConfirmListener = View.OnClickListener {

        // ## 취소하기전 관련 로직 수행
        if (islinkShare == 1) {

            shareDogDialog.tv_kakao_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#434343"))
            shareDogDialog.tv_link_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#434343"))
            islinkShare = 0
            isKakaoShare = 0




            shareThanksDialog.show()
        } else {
            shareDogDialog.tv_kakao_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#434343"))
            shareDogDialog.tv_link_share_custom_dialog_community_detail.setTextColor(Color.parseColor("#434343"))
            islinkShare = 0
            isKakaoShare = 0

            toast("준비 중인 기능입니다.")
        }

        shareDogDialog!!.dismiss()
    }

    private fun initPresenter() {
        dogDetailActivityPresenter = DogDetailActivityPresenter()
        // 뷰 붙여주는 작엄
        dogDetailActivityPresenter.view = this
        DogDetailObject.dogDetailActivityPresenter = dogDetailActivityPresenter
    }

    fun responseData(data : GetDogDetailResponse){
        data?.data?.also {
            initView(it)

        }?.let {
            educationState = it.educationState
            careName = it.careName
            careTel = it.careTel
        }
    }

    fun initView(data: GetDogDetailData){
        Glide.with(this@DogDetailActivity).load(data.thumbnailImg).into(iv_top_dog_dog_detail_act)

        data.region?.let {
            tv_area_dog_detail_act.text = it
        }

        data.kindCd?.let { tv_kind_dog_detail_act.text = it }

        data.specialMark?.let {
            tv_description_dog_detail_act.text = it
        }

        data.sexCd?.let{
            if(it == "M") {
                iv_sex_dog_detail_act.setImageResource(R.drawable.findingdetail_woman_icon)
                tv_sex_dog_detail_act.text = "남아"
            }else {
                iv_sex_dog_detail_act.setImageResource(R.drawable.findingdetail_man_icon)
                tv_sex_dog_detail_act.text = "여아"
            }
        }


        data.type?.let {
            if(it == "개"){
                iv_dog_or_cat_dog_detail_act.setImageResource(R.drawable.findingdetail_dog_icon)
            }else {
                iv_dog_or_cat_dog_detail_act.setImageResource(R.drawable.findingdetail_cat_icon)
            }
        }

        data.age?.let {
            tv_age_dog_detail_act.text = it
        }

        data.weight?.let {
            tv_weight_dog_detail_act.text = it
        }

        data.noticeNo?.let{
            tv_announcement_number_dog_detail_act.text = it
        }


        if (data.noticeEddt != null && data.noticeStdt != null){
            tv_term_dog_detail_act.text = data.noticeStdt.replace("-", ".") + " - " + data.noticeEddt.replace("-", ".")
            val now = System.currentTimeMillis()
            val date = Date(now)
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
            val beginDate = sdf.format(date)
            val today = sdf.parse(beginDate)

            val endDate = sdf.parse(data.noticeEddt)
            val diff = endDate.time - today.time
            val dDay = diff / (24 * 60 * 60 * 1000)

            tv_dday_dog_detail_act.text = "D-" + dDay.toString()
        }

        data.happenPlace?.let {
            tv_discovery_spot_dog_detail_act.text = it
        }

        data.careName?.let {
            tv_protect_spot_dog_detail_act.text = it
        }

        data.careTel?.let{
            tv_protect_spot_telephone_num_dog_detail_act.text = it
        }
    }
}

