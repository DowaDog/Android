package com.takhyungmin.dowadog.apply.online

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_apply_online_fourth.*

class ApplyOnlineFourthActivity : BaseActivity() {

    var check: Boolean = false
    var all_check : Boolean = false
    var et_example : Boolean = false

    lateinit var check1_btn : RelativeLayout
    lateinit var check2_btn : RelativeLayout
    lateinit var check3_btn : RelativeLayout
    lateinit var check4_btn : RelativeLayout
    lateinit var check5_btn : RelativeLayout

    var checkOneFlag : Int = 0
    private var checkTwoFlag = 0
    private var checkThreeFlag = 0
    private var checkFourFlag = 0
    private var checkFiveFlag = 0

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
        //키보드 내려가게 함
        ll_apply_online_fourth_act.setOnClickListener {
            val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        et_apply_online_fourth_act.addTextChangedListener(object : TextWatcher{
            val text = et_apply_online_fourth_act.text.toString()
            //입력한 후
            override fun afterTextChanged(s: Editable?) {

            }
            //입력하기 전에
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            //입력하고 있을때
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (text.length >= 3) {
                    et_example = true
                } else {
                    et_example= false
                }
            }
        })

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
}
