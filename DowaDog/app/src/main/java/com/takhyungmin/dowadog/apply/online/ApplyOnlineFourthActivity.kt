package com.takhyungmin.dowadog.apply.online

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.utils.CustomSingleResDialog
import kotlinx.android.synthetic.main.activity_apply_online_fourth.*

class ApplyOnlineFourthActivity : BaseActivity(), View.OnClickListener {


    var check: Boolean = false
    var allCheck : Boolean = false
    var etFlag : Boolean = false
    var completeBtnFlag :  Boolean = false

    private var checkOneFlag : Int = 0
    private var checkTwoFlag = 0
    private var checkThreeFlag = 0
    private var checkFourFlag = 0
    private var checkFiveFlag = 0

    // 1단계 다이얼로그 사용을 위한 객체만들기
    val applyFourthCustomSingleResDialog : CustomSingleResDialog by lazy{
        CustomSingleResDialog(this@ApplyOnlineFourthActivity, "필수 항목을 입력해주세요.", reponseListener, "확인")
    }

    override fun onClick(v: View?) {
        when(v) {

            // 전체뷰
            rl_full_apply_online_fourth_act -> {
                downKeyboard(rl_full_apply_online_fourth_act)
            }

            // 리니어뷰
            ll_apply_online_fourth_act -> {
                downKeyboard(ll_apply_online_fourth_act)
            }

            // 모두 동의 버튼
            rl_all_agree_apply_online_fourth_act -> {
                if (allCheck == false){
                    rl_check1_apply_online_fourth_act.isSelected = true
                    checkOneFlag = 1
                    rl_check2_apply_online_fourth_act.isSelected = true
                    checkTwoFlag = 1
                    rl_check3_apply_online_fourth_act.isSelected = true
                    checkThreeFlag = 1
                    rl_check4_apply_online_fourth_act.isSelected = true
                    checkFourFlag = 1
                    rl_check5_apply_online_fourth_act.isSelected = true
                    checkFiveFlag = 1
                    allCheck = true
                    rl_all_agree_apply_online_fourth_act.isSelected = true

                    if(etFlag == true){
                        rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        completeBtnFlag = true
                    }

                }else {
                    rl_check1_apply_online_fourth_act.isSelected = false
                    checkOneFlag = 0
                    rl_check2_apply_online_fourth_act.isSelected = false
                    checkTwoFlag = 0
                    rl_check3_apply_online_fourth_act.isSelected = false
                    checkThreeFlag = 0
                    rl_check4_apply_online_fourth_act.isSelected = false
                    checkFourFlag = 0
                    rl_check5_apply_online_fourth_act.isSelected = false
                    checkFiveFlag = 0
                    allCheck = false
                    rl_all_agree_apply_online_fourth_act.isSelected = false


                    rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#c7c7c7"))
                    completeBtnFlag = false
                }

            }

            // 첫 체크박스
            rl_check1_apply_online_fourth_act -> {

                // 회색일 때
                if(checkOneFlag == 0){
                    checkOneFlag = 1
                    rl_check1_apply_online_fourth_act.isSelected = true
                    if(checkTwoFlag == 1 && checkThreeFlag == 1 && checkFourFlag == 1 && checkFiveFlag ==1) {
                        rl_all_agree_apply_online_fourth_act.isSelected = true
                        allCheck = true

                        if(etFlag == true){
                            completeBtnFlag = true
                            rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        }
                    }
                } // 선택이 되어있는 상태일 때 클릭
                else {
                    checkOneFlag = 0
                    rl_check1_apply_online_fourth_act.isSelected = false

                    rl_all_agree_apply_online_fourth_act.isSelected = false

                    rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#c7c7c7"))
                    completeBtnFlag = false

                }

            }

            rl_check2_apply_online_fourth_act -> {
                // 회색일 때
                if(checkTwoFlag== 0){
                    checkTwoFlag = 1
                    rl_check2_apply_online_fourth_act.isSelected = true
                    if(checkOneFlag == 1 && checkThreeFlag == 1 && checkFourFlag == 1 && checkFiveFlag ==1) {
                        rl_all_agree_apply_online_fourth_act.isSelected = true
                        allCheck = true

                        if(etFlag == true){
                            completeBtnFlag = true
                            rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        }
                    }
                } // 선택이 되어있는 상태일 때 클릭
                else {
                    checkTwoFlag = 0
                    rl_check2_apply_online_fourth_act.isSelected = false

                    rl_all_agree_apply_online_fourth_act.isSelected = false

                    rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#c7c7c7"))
                    completeBtnFlag = false
                }
            }

            rl_check3_apply_online_fourth_act -> {
                // 회색일 때
                if(checkThreeFlag == 0){
                    checkThreeFlag = 1
                    rl_check3_apply_online_fourth_act.isSelected = true
                    if(checkTwoFlag == 1 && checkOneFlag == 1 && checkFourFlag == 1 && checkFiveFlag ==1) {
                        rl_all_agree_apply_online_fourth_act.isSelected = true
                        allCheck = true

                        if(etFlag == true){
                            completeBtnFlag = true
                            rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        }
                    }
                } // 선택이 되어있는 상태일 때 클릭
                else {
                    checkThreeFlag = 0
                    rl_check3_apply_online_fourth_act.isSelected = false

                    rl_all_agree_apply_online_fourth_act.isSelected = false

                    rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#c7c7c7"))
                    completeBtnFlag = false

                }
            }

            rl_check4_apply_online_fourth_act -> {
                // 회색일 때
                if(checkFourFlag == 0){
                    checkFourFlag = 1
                    rl_check4_apply_online_fourth_act.isSelected = true
                    if(checkTwoFlag == 1 && checkThreeFlag == 1 && checkOneFlag == 1 && checkFiveFlag ==1) {
                        rl_all_agree_apply_online_fourth_act.isSelected = true
                        allCheck = true

                        if(etFlag == true){
                            completeBtnFlag = true
                            rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        }
                    }
                } // 선택이 되어있는 상태일 때 클릭
                else {
                    checkFourFlag = 0
                    rl_check4_apply_online_fourth_act.isSelected = false

                    rl_all_agree_apply_online_fourth_act.isSelected = false

                    rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#c7c7c7"))
                    completeBtnFlag = false

                }
            }

            rl_check5_apply_online_fourth_act -> {
                // 회색일 때
                if(checkFiveFlag == 0){
                    checkFiveFlag = 1
                    rl_check5_apply_online_fourth_act.isSelected = true
                    if(checkTwoFlag == 1 && checkThreeFlag == 1 && checkFourFlag == 1 && checkOneFlag ==1) {
                        rl_all_agree_apply_online_fourth_act.isSelected = true
                        allCheck = true

                        if(etFlag == true){
                            completeBtnFlag = true
                            rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        }
                    }
                } // 선택이 되어있는 상태일 때 클릭
                else {
                    checkFiveFlag = 0
                    rl_check5_apply_online_fourth_act.isSelected = false

                    rl_all_agree_apply_online_fourth_act.isSelected = false

                    rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#c7c7c7"))
                    completeBtnFlag = false

                }
            }

            rl_complete_apply_online_fourth_act -> {

                if(completeBtnFlag == false){
                    // 다이얼로그 띄우기
                    applyFourthCustomSingleResDialog.show()
                }else {
                    // ## 다음뷰로 넘어가기 위한 통신하기
                }


            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply_online_fourth)


        // 클릭리스너 달아주기
        init()

        setEditTextChangedListener()
    }

    private fun downKeyboard(view: View){
        val imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun init(){

        //키보드 내려가게 하는 함수
        rl_full_apply_online_fourth_act.setOnClickListener(this)

        //키보드 내려가게 함
        ll_apply_online_fourth_act.setOnClickListener(this)

        //모두 동의 버튼을 누르면!
        rl_all_agree_apply_online_fourth_act.setOnClickListener (this)

        rl_check1_apply_online_fourth_act.setOnClickListener(this)
        rl_check2_apply_online_fourth_act.setOnClickListener(this)
        rl_check3_apply_online_fourth_act.setOnClickListener(this)
        rl_check4_apply_online_fourth_act.setOnClickListener(this)
        rl_check5_apply_online_fourth_act.setOnClickListener(this)
        rl_complete_apply_online_fourth_act.setOnClickListener(this)

    }

    private fun setEditTextChangedListener(){
        et_apply_online_fourth_act.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_apply_online_fourth_act.text.toString().length <= 0){
                    etFlag = false
                    rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#c7c7c7"))
                    completeBtnFlag = false
                }
                else {
                    etFlag = true
                    if(allCheck == true){
                        rl_complete_apply_online_fourth_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        completeBtnFlag = true
                    }
                }
            }
        })
    }

    private val reponseListener = View.OnClickListener { applyFourthCustomSingleResDialog!!.dismiss() }


}
