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
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_mypage_setting.*
import kotlinx.android.synthetic.main.activity_sign_id_setting.*
import kotlinx.android.synthetic.main.activity_sign_info_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*

class SignIdSettingActivity : AppCompatActivity() {

    private val REQ_CODE_SELECT_IMAGE = 100
    lateinit var data: Uri
    private var image: MultipartBody.Part? = null

    var et_id: Boolean = false
    var et_pw: Boolean = false
    var et_pw_check: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_id_setting)

        //갤러리 접근
        rl_camera_img_sign_id_set_act.setOnClickListener {
            changeImage()
        }

        img_back_btn_sign_id_set_act.setOnClickListener {
            startActivity<SignInfoWriteActivity>()
        }

        //키보드 내려가게 하는 함수
        rl_sign_id_set_act.setOnClickListener {
            val imm: InputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }

        //et_id_sign_id_set_act--> editText에 값이 들어갔는지 판별해주는 것
        et_id_sign_id_set_act.addTextChangedListener(object : TextWatcher {
            //입력하기 전에
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                et_id = false
            }

            // 입력되는 텍스트에 변화가 있을 때
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = et_id_sign_id_set_act.text.toString()

                if (text.length >= 6) {
                    et_id = true
                } else {
                    et_id = false
                }

                if (et_id) {
                    if (et_pw) {
                        if (et_pw_check) {
                            rl_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        }
                    }
                } else {
                    rl_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                }
            }
            //입력이 끝났을 때
            override fun afterTextChanged(s: Editable?) {
            }
        })

        //et_pw_sign_id_set_act--> editText에 값이 들어갔는지 판별해주는 것
        et_pw_sign_id_set_act.addTextChangedListener(object : TextWatcher {
            //입력하기 전에
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //et_pw = false
            }
            // 입력되는 텍스트에 변화가 있을 때
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text = et_pw_sign_id_set_act.text.toString()
                val pw_form = "((?=.*\\d)(?=.*[a-zA-Z])(?=.*[@#\$%!&]).{8,20})"

                if (text.length >= 8 && text.matches(Regex(pw_form))) {
                    et_pw = true
                } else {
                    et_pw = false
                }

                if (et_pw) {
                    if (et_id) {
                        if (et_pw_check) {
                            rl_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        }
                    }
                } else {
                    rl_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                }
            }
            //입력이 끝났을 때
            override fun afterTextChanged(s: Editable?) {
            }
        })

        //et_pw_check_sign_id_set_act --> editText에 값이 들어갔는지 판별해주는 것
        et_pw_check_sign_id_set_act.addTextChangedListener(object : TextWatcher {
            //입력하기 전에
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                et_pw_check = false
            }

            // 입력되는 텍스트에 변화가 있을 때
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val text_pw = et_pw_sign_id_set_act.text.toString()
                val text_check = et_pw_check_sign_id_set_act.text.toString()

                if (text_check == text_pw) {
                    et_pw_check = true
                } else {
                    et_pw_check = false
                }

                if (et_pw_check) {
                    if (et_id) {
                        if (et_pw) {
                            rl_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        }
                    }
                } else {
                    rl_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                }
            }
            //입력이 끝났을 때
            override fun afterTextChanged(s: Editable?) {
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
                            .into(img_profile_sign_id_set_act)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}
