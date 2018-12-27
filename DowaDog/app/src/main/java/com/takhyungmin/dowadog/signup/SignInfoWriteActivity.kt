package com.takhyungmin.dowadog.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_sign_info_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.email
import org.jetbrains.anko.startActivity
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*

class SignInfoWriteActivity : AppCompatActivity() {

    private val REQ_CODE_SELECT_IMAGE = 100
    lateinit var data: Uri
    private var image: MultipartBody.Part? = null

    //edittext 다음 값 활성화
    var et_name: Boolean = false
    var et_birth: Boolean = false
    var et_phone: Boolean = false
    var et_email: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_info_write)

        rl_camera_img_sign_info_wr_act.setOnClickListener {
            changeImage()
        }

        //키보드 내려가게 하는 함수
        rl_sign_info_wr_act.setOnClickListener {
            val imm: InputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        //name_sign_info_wr_act--> editText에 값이 들어갔는지 판별해주는 것
        et_name_sign_info_wr_act.addTextChangedListener(object : TextWatcher {
            //입력하기 전에
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                et_name = false
            }

            // 입력되는 텍스트에 변화가 있을 때
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = et_name_sign_info_wr_act.text.toString().toLowerCase(Locale.getDefault())

                if(text.length > 0){
                    et_name = true
                }
                else{
                    et_name = false
                }

                if (et_name) {
                    if (et_birth) {
                        if (et_phone) {
                            if (et_email) {
                                rl_next_btn_sign_info_wr_act.setOnClickListener {
                                    startActivity<SignIdSettingActivity>()
                                }

                                rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#00dada"))
                            }
                        }
                    }
                } else {
                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                }

            }
            //입력이 끝났을 때
            override fun afterTextChanged(s: Editable?) {
            }
        })
        //생년월일 editText확인
        et_birth_sign_info_wr_act.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                et_birth = false
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = et_birth_sign_info_wr_act.text

                if(text.length >= 6){
                    et_birth = true
                }
                else{
                    et_birth = false
                }

                if (et_birth) {
                    if (et_name) {
                        if (et_phone) {
                            if (et_email) {
                                rl_next_btn_sign_info_wr_act.setOnClickListener {
                                    startActivity<SignIdSettingActivity>()
                                }

                                rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#00dada"))
                            }
                        }
                    }
                } else {
                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                }
            }
        })

        //핸드폰 번호 editText확인
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

                if(text.matches(Regex(phone_form) ) && text.length < 12 ){
                    et_phone = true
                }
                else{
                    et_phone = false
                }

                if (et_phone) {
                    if (et_name) {
                        if (et_birth) {
                            if (et_email) {
                                rl_next_btn_sign_info_wr_act.setOnClickListener {
                                    startActivity<SignIdSettingActivity>()
                                }

                                rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#00dada"))
                            }
                        }
                    }
                } else {
                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                }
            }
        })

        //이메일 editText확인
        et_email_sign_info_wr_act.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                et_email = false
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = et_email_sign_info_wr_act.text
                //e-mail form
                val email_form ="^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$"

                if(text.matches(Regex(email_form))){
                    et_email = true
                }
                else{
                    et_email = false
                }

                if (et_email) {
                    if (et_name) {
                        if (et_phone) {
                            if (et_birth) {
                                rl_next_btn_sign_info_wr_act.setOnClickListener {
                                    startActivity<SignIdSettingActivity>()
                                }
                                rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#00dada"))
                            }
                        }
                    }
                } else {
                    rl_next_btn_sign_info_wr_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                }
            }
        })
    }

    fun changeImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //if(ApplicationController.getInstance().is)
                    this.data = data!!.data
                    Log.v("이미지", this.data.toString())

                    val options = BitmapFactory.Options()

                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = contentResolver.openInputStream(this.data)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                    //val img = File(getRealPathFromURI(this!!, this.data).toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

                    // Log.v("TAG", "이미지 이름 = " + img)
                    Log.v("TAG", "이미지 바디 = " + photoBody.toString())


                    image = MultipartBody.Part.createFormData("image", "image", photoBody)

                    //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);

                    Glide.with(this)
                            .load(this.data.toString())
                            .into(img_profile_sign_info_wr_act)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
