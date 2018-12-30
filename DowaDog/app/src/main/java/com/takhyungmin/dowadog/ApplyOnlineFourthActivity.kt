package com.takhyungmin.dowadog

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_apply_online_fourth.*

class ApplyOnlineFourthActivity : BaseActivity() {

    var check: Boolean = false
    var all_check : Boolean = false

    lateinit var check1_btn : RelativeLayout
    lateinit var check2_btn : RelativeLayout
    lateinit var check3_btn : RelativeLayout
    lateinit var check4_btn : RelativeLayout
    lateinit var check5_btn : RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_online_fourth)

        check1_btn = findViewById(R.id.rl_check1_apply_online_fourth_act) as RelativeLayout
        check2_btn = findViewById(R.id.rl_check2_apply_online_fourth_act) as RelativeLayout
        check3_btn = findViewById(R.id.rl_check3_apply_online_fourth_act) as RelativeLayout
        check4_btn = findViewById(R.id.rl_check4_apply_online_fourth_act) as RelativeLayout
        check5_btn = findViewById(R.id.rl_check5_apply_online_fourth_act) as RelativeLayout

        //키보드 내려가게 하는 함수
        rl_full_apply_online_fourth_act.setOnClickListener {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
       }

        ll_apply_online_fourth_act.setOnClickListener {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        Check_btn(check1_btn)
        Check_btn(check2_btn)
        Check_btn(check3_btn)
        Check_btn(check4_btn)
        Check_btn(check5_btn)

        //모두 동의 버튼을 누르면!
        rl_all_agree_apply_online_fourth_act.setOnClickListener {
            if (all_check == false) {
                rl_all_agree_apply_online_fourth_act.isSelected = true
                check1_btn.isSelected = true
                check2_btn.isSelected = true
                check3_btn.isSelected = true
                check4_btn.isSelected = true
                check5_btn.isSelected = true
                all_check = true
            } else {
                rl_all_agree_apply_online_fourth_act.isSelected = false

                check1_btn.isSelected = false
                check2_btn.isSelected = false
                check3_btn.isSelected = false
                check4_btn.isSelected = false
                check5_btn.isSelected = false
                all_check = false
            }
        }
    }

    //체크버튼 누르면 색깔이 바뀌는 함수
    fun Check_btn(check_btn: RelativeLayout) {

        check_btn.setOnClickListener {
            if (check == false) {
                check_btn.isSelected = true
                check = true
            } else {
                check_btn.isSelected = false
                check = false
            }
        }
    }
}
