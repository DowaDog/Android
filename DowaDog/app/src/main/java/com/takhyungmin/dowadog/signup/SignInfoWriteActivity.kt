package com.takhyungmin.dowadog.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.utils.CustomSingleResDialog
import kotlinx.android.synthetic.main.activity_mypage_setting.*
import kotlinx.android.synthetic.main.activity_sign_info_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*
import java.util.jar.Manifest

class SignInfoWriteActivity : BaseActivity(), View.OnClickListener {

    private val REQ_CODE_SELECT_IMAGE = 100
    val My_READ_STORAGE_REQUEST_CODE = 88
    lateinit var data: Uri

    private var NextBtnFlag = 0

    var imageURI : String? = null

    //edittext 다음 값 활성화
    var et_name: Boolean = false
    var et_birth: Boolean = false
    var et_phone: Boolean = false
    var et_email: Boolean = false

    //lateinit var SignInfoWrCustomSingleResDialog : CustomSingleResDialog

    val SignInfoWrCustomSingleResDialog: CustomSingleResDialog by lazy {
        CustomSingleResDialog(this@SignInfoWriteActivity, "##워딩##항목들을 입력해주세요.", reponseListener, "확인")
    }

    override fun onClick(v: View?) {
        when (v) {
            //프로필 이미지 -> 사집첩
            rl_camera_img_sign_info_wr_act -> {
                requestReadExternalStoragePermission()
            }

            rl_next_btn_sign_info_wr_act -> {
                //플래그 확인
                if (NextBtnFlag == 0) {
                    SignInfoWrCustomSingleResDialog!!.show()
                } else {
                    //다음으로 넘어가기
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
        if(requestCode == My_READ_STORAGE_REQUEST_CODE) {
            if(grantResults.size > 0 && grantResults[0] == PackageManager. PERMISSION_GRANTED){
                showAlbum()
            }else{
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
                //data.data에는 앨범에서 선택한 사진의 Uri가 들어있습니다!! 그러니까 제대로 선택됐는지 null인지 아닌지를 체크!!!
                if (data != null) {

                    val selectedImageUri: Uri = data.data
                    //Uri를 getRealPathFromURI라는 메소드를 통해 절대 경로를 알아내고, 인스턴스 변수 imageURI에 넣어줍니다!
                    //imageURI = getRealPathFromURI(selectedImageUri)

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
        //키보드 내려가게 하는 함수
        rl_sign_info_wr_act.setOnClickListener {
            val imm: InputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        rl_next_btn_sign_info_wr_act.setOnClickListener(this)

        rl_camera_img_sign_info_wr_act.setOnClickListener(this)

    }

    //이름 editText확인 name_sign_info_wr_act--> editText에 값이 들어갔는지 판별해주는 것
    private fun nameEditTextSetting() {
        et_name_sign_info_wr_act.addTextChangedListener(object : TextWatcher {
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
                                rl_next_btn_sign_info_wr_act.setOnClickListener {
                                    startActivity<SignIdSettingActivity>()
                                }
                                rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#ffc233"))
                                NextBtnFlag = 1
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
                et_birth = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = et_birth_sign_info_wr_act.text

                if (text.length >= 6) {
                    et_birth = true
                } else {
                    et_birth = false
                }

                if (et_birth) {
                    if (et_name) {
                        if (et_phone) {
                            if (et_email) {
                                rl_next_btn_sign_info_wr_act.setOnClickListener {
                                    startActivity<SignIdSettingActivity>()
                                }

                                rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#ffc233"))
                                NextBtnFlag = 1
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

    //핸드폰 번호 editText확인
    private fun phoneEditTextSetting() {
        et_phonenum_sign_info_wr_act.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                et_phone = false
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = et_phonenum_sign_info_wr_act.text
                //실제, 국제에서 사용하는 전화번호가 맞는지 체크
                val phone_form = "^\\s*(010|011|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$"

                if (text.matches(Regex(phone_form)) && text.length < 12) {
                    et_phone = true
                } else {
                    et_phone = false
                }

                if (et_phone) {
                    if (et_name) {
                        if (et_birth) {
                            if (et_email) {
                                rl_next_btn_sign_info_wr_act.setOnClickListener {
                                    startActivity<SignIdSettingActivity>()
                                }

                                rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#ffc233"))
                                NextBtnFlag = 1
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

    //이메일 editText확인
    private fun emailEditTextSetting() {
        et_email_sign_info_wr_act.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                et_email = false
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
                                rl_next_btn_sign_info_wr_act.setOnClickListener {
                                    startActivity<SignIdSettingActivity>()
                                }
                                rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#ffc233"))
                                NextBtnFlag = 1
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

}
