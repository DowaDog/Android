package com.takhyungmin.dowadog.signup.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.telephony.PhoneNumberFormattingTextWatcher
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.view.clicks
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.presenter.activity.SignInfoWriteActivityPresenter
import com.takhyungmin.dowadog.signup.SignObject
import com.takhyungmin.dowadog.signup.model.get.GetSignInfoEmailResponse
import com.takhyungmin.dowadog.utils.CustomSingleResDialog
import kotlinx.android.synthetic.main.activity_sign_info_write.*
import java.util.*



class SignInfoWriteActivity : BaseActivity(), View.OnClickListener {

    private lateinit var signInfoWriteActivityPresenter: SignInfoWriteActivityPresenter

    var signInfoEmaildataBoolean: Boolean = true

    private val REQ_CODE_SELECT_IMAGE = 100
    val My_READ_STORAGE_REQUEST_CODE = 88
    lateinit var data: Uri

    private var NextBtnFlag = 0

    var imageURI: String? = null

    //edittext 다음 값 활성화
    var et_name: Boolean = false
    var et_birth: Boolean = false
    var et_phone: Boolean = false
    var et_email: Boolean = false
    var checkEmail = false
    lateinit var selectedImageUri : Uri
    //lateinit var SignInfoWrCustomSingleResDialog : CustomSingleResDialog

    // 필수항목들을 입력해주세요.
    val SignInfoWrCustomSingleResDialog: CustomSingleResDialog by lazy {
        CustomSingleResDialog(this@SignInfoWriteActivity, "정보를 올바르게 입력해주세요.", reponseListener, "확인")
    }
    //이메일 형식에 맞지 않을 때???????이거 왜 만든거지?
    val emailCheckDialog: CustomSingleResDialog by lazy {
        CustomSingleResDialog(this@SignInfoWriteActivity, "이메일 형식에 맞지 않습니다.", mResponseClickListener, "확인")
    }
    //사용 가능한 이메일
    val enableEmailDialog: CustomSingleResDialog by lazy {
        CustomSingleResDialog(this@SignInfoWriteActivity, "사용가능한 이메일입니다.", enableResponseClickListener, "확인")
    }
    //중복된 이메일
    val duplicateEmailCheckDialog: CustomSingleResDialog by lazy {
        CustomSingleResDialog(this@SignInfoWriteActivity, "중복된 이메일입니다.", duplicateResponseClickListener, "확인")
    }

    override fun onClick(v: View?) {

        when (v) {
            //프로필 이미지 -> 사집첩
            rl_camera_img_sign_info_wr_act -> {
                requestReadExternalStoragePermission()
            }

            //다음 회원가입창으로 넘어가기
            rl_next_btn_sign_info_wr_act -> {
                //플래그 확인
                if (NextBtnFlag == 0) {
                    SignInfoWrCustomSingleResDialog!!.show()
                } else {
                    Log.v("TAG", "intent1")
                    //startActivity<SignIdSettingActivity>("username" to et_name_sign_info_wr_act.text.toString(), "birth" to et_birth_sign_info_wr_act.text.toString(), "phone" to et_phonenum_sign_info_wr_act.text.toString(), "email" to et_email_sign_info_wr_act.text.toString())
                    val intent = Intent(this, SignIdSettingActivity::class.java)
                    intent.putExtra("username", et_name_sign_info_wr_act.text.toString())
                    intent.putExtra("birth", et_birth_sign_info_wr_act.text.toString())
                    intent.putExtra("phone", et_phonenum_sign_info_wr_act.text.toString())
                    intent.putExtra("email", et_email_sign_info_wr_act.text.toString())
                    intent.putExtra("image", selectedImageUri.toString())
                    Log.v("uri", selectedImageUri.toString())

                    startActivity(intent)
                }
            }

            //이메일 중복확인
            btn_email_check_sign_info_wr_act -> {
                //이메일 형식이 맞으면
                if (et_email == true) {
                    //통신
                    initPresenter()
                    signInfoWriteActivityPresenter.initView()
                    signInfoWriteActivityPresenter.requestData()


                } else {
                    emailCheckDialog!!.show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_info_write)

        init()
        nameEditTextSetting()
        birthEditTextSetting()
        phoneEditTextSetting()
        emailEditTextSetting()

    }

    var phoneComplete = false
    var birthComplete = false
    var nameComplete = false
    var emailComplete = false
    //각 항목별 조건 충족 여부.

    fun setOnBinding(){
        //전화번호를 입력하다가 다른 곳으로 갔을 때 1. 형식에 맞았다. 2. 형식에 맞지 않았다.
        //2일 때 토스트.
    }

    private fun requestReadExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //사용자에게 권한을 왜 허용해야되는지에 메시지를 주기 위한 대한 로직을 추가하려면 이 블락에서 하면됩니다!! //하지만 우리는 그냥 비워놓습니다!! 딱히 줄말 없으면 비워놔도 무관해요!!! 굳이 뭐 안넣어도됩니다!
            } else {
                //아래 코드는 권한을 요청하는 메시지를 띄우는 기능을 합니다! 요청에 대한 결과는 callback으로 onRequestPermissionsResult 메소드에서 받습니다!!!
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), My_READ_STORAGE_REQUEST_CODE)
            }
        } else {
            //첫번째 if 문의 else 로써, 기존에 이미 권한 메시지를 통해 권한을 허용했다면 아래와 같은 곧바로 앨범을 여는 메소드를 호출 해주면됩니다!!
            showAlbum()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == My_READ_STORAGE_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showAlbum()
            } else {
                finish()
            }
        }
    }

    //사진첩으로 가는 함수
    private fun showAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    //사진첩으로 간 후 다음 동작을 설정
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {

                    selectedImageUri = data.data!!
                    //Uri를 getRealPathFromURI라는 메소드를 통해 절대 경로를 알아내고, 인스턴스 변수 imageURI에 넣어줍니다!

                    Glide.with(this@SignInfoWriteActivity)
                            .load(selectedImageUri)
                            .thumbnail(0.1f)
                            .into(img_profile_sign_info_wr_act)

                }
            }
        }
    }

    private val reponseListener = View.OnClickListener { SignInfoWrCustomSingleResDialog!!.dismiss() }

    private fun init() {
        selectedImageUri = Uri.parse("")
        //키보드 내려가게 하는 함수
        rl_sign_info_wr_act.setOnClickListener {
            val imm: InputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        rl_next_btn_sign_info_wr_act.setOnClickListener(this)

        rl_camera_img_sign_info_wr_act.setOnClickListener(this)

        //이메일 중복 확인 버튼
        btn_email_check_sign_info_wr_act.setOnClickListener(this)

        et_phonenum_sign_info_wr_act.addTextChangedListener(PhoneNumberFormattingTextWatcher())

        btn_write_back.clicks().subscribe {
            finish()
        }
    }


    //이름 editText확인 name_sign_info_wr_act--> editText에 값이 들어갔는지 판별해주는 것
    private fun nameEditTextSetting() {
        et_name_sign_info_wr_act.addTextChangedListener(object : TextWatcher{
            //입력하기 전에
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                et_name = false
            }

            // 입력되는 텍스트에 변화가 있을 때
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = et_name_sign_info_wr_act.text.toString().toLowerCase(Locale.getDefault())

                if (text.length > 0) {
                    et_name = true
                } else {
                    et_name = false
                }

                if (et_name) {
                    if (et_birth) {
                        if (et_phone) {
                            if (et_email) {
                                if(!signInfoEmaildataBoolean){
                                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#ffc233"))
                                    NextBtnFlag = 1
                                }
                            }
                        }
                    }
                } else {
                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                    NextBtnFlag = 0
                }

            }

            //입력이 끝났을 때
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    //생년월일 editText확인
    private fun birthEditTextSetting() {
        et_birth_sign_info_wr_act.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //et_birth = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = et_birth_sign_info_wr_act.text
                //val birth_form = "/^[0-9]{0,8}\$/"
                val birth_form = "^\\s*(\\d{4})(-|\\)|\\s)*(\\d{2})(-|\\s)*(\\d{2})\\s*$"

                et_birth = text.matches(Regex(birth_form))
                //et_birth = (text.length >= 10)

                if (et_birth) {
                    img_sign_info_write_unfit_birth.visibility = View.INVISIBLE
                    if (et_name) {
                        if (et_phone) {
                            if (et_email) {
                                if(!signInfoEmaildataBoolean) {
                                    Log.v("TAG", "intent3")
                                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#ffc233"))
                                    NextBtnFlag = 1
                                }
                            }
                        }
                    }
                } else {
                    img_sign_info_write_unfit_birth.visibility = View.VISIBLE
                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                    NextBtnFlag = 0
                }
            }
        })
    }

    //핸드폰 번호 editText확인
    private fun phoneEditTextSetting() {
        et_phonenum_sign_info_wr_act.addTextChangedListener(object : PhoneNumberFormattingTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                Log.v("after", "after")

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //et_phone = false
                Log.v("before", "before")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.v("change", "change")

                val text = et_phonenum_sign_info_wr_act.text
                //실제, 국제에서 사용하는 전화번호가 맞는지 체크
                //val phone_form = "^\\s*(010|011|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$"

                et_phone = ((text.length == 12) or (text.length == 13))


                if (et_phone) {
                    img_sign_info_write_unfit_phone.visibility = View.INVISIBLE
                    if (et_name) {
                        if (et_birth) {
                            if (et_email) {
                                if(!signInfoEmaildataBoolean) {
                                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#ffc233"))
                                    NextBtnFlag = 1
                                }
                            }
                        }
                    }
                } else {
                    img_sign_info_write_unfit_phone.visibility = View.VISIBLE
                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                    NextBtnFlag = 0
                }
            }
        })
    }

    //이메일 editText확인
    private fun emailEditTextSetting() {
        et_email_sign_info_wr_act.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //et_email = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = et_email_sign_info_wr_act.text
                //e-mail form
                val email_form = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"

                if (text.matches(Regex(email_form))) {
                    et_email = true
                } else {
                    et_email = false
                }

                if (et_email) {
                    if (et_name) {
                        if (et_phone) {
                            if (et_birth) {
                                if(!signInfoEmaildataBoolean) {
                                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#ffc233"))
                                    NextBtnFlag = 1
                                }
                            }
                        }
                    }
                } else {
                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                    NextBtnFlag = 0
                }
            }
        })
    }

    //email 중복시
    private val mResponseClickListener = View.OnClickListener {
        //이메일 EditText 초기화
        et_email_sign_info_wr_act.setText(null)
        emailCheckDialog!!.dismiss()
    }

    //사용가능한 이메일
    private val enableResponseClickListener = View.OnClickListener {
        enableEmailDialog!!.dismiss()
    }

    //중복된 이메일
    private val duplicateResponseClickListener = View.OnClickListener {
        //이메일 EditText 초기화
        et_email_sign_info_wr_act.setText(null)
        duplicateEmailCheckDialog!!.dismiss()
    }

    //view에 presenter 붙여주기
    private fun initPresenter() {

        signInfoWriteActivityPresenter = SignInfoWriteActivityPresenter()
        // 뷰 붙여주는 작업
        signInfoWriteActivityPresenter.view = this
        SignObject.SignInfoWriteActivityPresenter = signInfoWriteActivityPresenter
    }

    fun responseData(data: GetSignInfoEmailResponse) {

        data?.let {

            signInfoEmaildataBoolean = data.data
            Log.v("TAGG", signInfoEmaildataBoolean.toString())
            //여기서 확인을 해야하나?
            if (signInfoEmaildataBoolean) {
                //editText초기화
                et_email_sign_info_wr_act.setText(null)
                //중복된 이메일입니다
                duplicateEmailCheckDialog.show()
            } else {
                //사용가능한 이메일
                enableEmailDialog.show()
                Log.v("name", et_name.toString())
                Log.v("birth", et_birth.toString())
                Log.v("email", et_email.toString())
                Log.v("phone", et_phone.toString())
                Log.v("duplicate", signInfoEmaildataBoolean.toString())
                if (!signInfoEmaildataBoolean) {
                    if (et_name) {
                    if (et_birth) {
                        if (et_phone) {
                            if (et_email) {

                                    Log.v("들어옴", "들어옴")
                                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#ffc233"))
                                    NextBtnFlag = 1
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
