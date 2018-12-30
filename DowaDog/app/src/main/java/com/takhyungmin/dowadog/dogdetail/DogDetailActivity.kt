package com.takhyungmin.dowadog.dogdetail

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_dog_detail.*

class DogDetailActivity : AppCompatActivity(), View.OnClickListener {

    private var isLike: Int = 0

    override fun onClick(v: View?) {
        when(v){

            // 백버튼
            btn_back_dog_detail_act -> {
                finish()
            }

            // 공유하기 버튼
            btn_share_dog_detail_act -> {

            }

            // 좋아요 버튼
            btn_heart_dog_detail_act -> {
                if (isLike == 0){
                    iv_heart_dog_detail_act.setImageResource(R.drawable.hearts_full_icon)
                    // 좋아요 통신
                }else {
                    iv_heart_dog_detail_act.setImageResource(R.drawable.hearts_line_icon)
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

    }

    fun init(){
        btn_back_dog_detail_act.setOnClickListener(this)
        btn_share_dog_detail_act.setOnClickListener(this)
        btn_heart_dog_detail_act.setOnClickListener(this)
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
}
