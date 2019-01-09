package com.takhyungmin.dowadog

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.utils.CustomPressedAdoptDialog
import kotlinx.android.synthetic.main.activity_pressed_adopt.*



class PressedAdoptActivity : BaseActivity() {

    var name = ""
    var num = ""

    lateinit var customShelterDialog : CustomPressedAdoptDialog

//    private val customShelterDialog: CustomPressedAdoptDialog by lazy {
//        CustomPressedAdoptDialog(this@PressedAdoptActivity, name, "보호소 번호가 들어가야함", cancleBtnListener, confirmBtnListener, "취소", "확인")
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_pressed_adopt)

        name = intent.getStringExtra("spotName")
        num = intent.getStringExtra("num")

        init()
    }

    private fun init(){
        btn_back_pressed_adopt_act.clicks().subscribe{
            finish()
        }
        btn_adopt_pressed_adopt_act.clicks().subscribe{

            customShelterDialog = CustomPressedAdoptDialog(this, name, num, cancleBtnListener, confirmBtnListener, "취소", "확인")

            customShelterDialog.show()
        }
    }

    private var cancleBtnListener = View.OnClickListener {
        customShelterDialog.dismiss()
    }

    private var confirmBtnListener = View.OnClickListener {
        // 전화 번호

        startActivity(Intent("android.intent.action.DIAL", Uri.parse("tel:$num")))

        customShelterDialog.dismiss()
    }

}
