package com.takhyungmin.dowadog.signup

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.presenter.activity.SignIdSettingActivityPresenter
import com.takhyungmin.dowadog.presenter.activity.SignInfoWriteActivityPresenter
import com.takhyungmin.dowadog.signup.model.get.GetSignInfoEmailResponse
import com.takhyungmin.dowadog.signup.model.post.PostSignIdSettingResponse
import com.takhyungmin.dowadog.utils.CustomDialog
import com.takhyungmin.dowadog.utils.CustomSingleResDialog
import kotlinx.android.synthetic.main.activity_mypage_setting.*
import kotlinx.android.synthetic.main.activity_sign_id_setting.*
import kotlinx.android.synthetic.main.activity_sign_info_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.*

class SignIdSettingActivity : BaseActivity(), View.OnClickListener {

    override fun onClick(v: View?) {

        when (v) {
            //중복확인 버튼
            btn_id_check_sign_id_set_act -> {
                idCheckDialog!!.show()
            }

            //갤러리 접근
            rl_camera_img_sign_id_set_act -> {
                requestReadExternalStoragePermission()
            }

            //back버튼을 눌렀을때, ##생각해야하는 건 그 전에 입력해논 데이터가 그대로 존재해야한다는 점
            img_back_btn_sign_id_set_act -> {
                startActivity<SignInfoWriteActivity>()
            }
            rl_sign_id_set_act -> {
                keyboardDown(rl_sign_id_set_act)
            }

            btn_agree_sign_id_set_act -> {
                signIdSettingPresenter.requestData(mimage)

            }

        }
    }

    val idCheckDialog: CustomSingleResDialog by lazy {
        CustomSingleResDialog(this@SignIdSettingActivity, "사용가능한 아이디입니다.", mResponseClickListener, "확인")
    }


    private lateinit var signIdSettingPresenter: SignIdSettingActivityPresenter

    val My_READ_STORAGE_REQUEST_CODE = 88
    private val REQ_CODE_SELECT_IMAGE = 100
    lateinit var data: Uri
    private var mimage: MultipartBody.Part? = null

    var et_id: Boolean = false
    var et_pw: Boolean = false
    var et_pw_check: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_id_setting)

        init()
        initPresenter()

        signIdSettingPresenter.initView()

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
                            btn_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        }
                    }
                } else {
                    btn_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
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
                            btn_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        }
                    }
                } else {
                    btn_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
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
                            btn_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#ffc233"))
                        }
                    }
                } else {
                    btn_agree_sign_id_set_act.setBackgroundColor(Color.parseColor("#e2e2e2"))
                }
            }

            //입력이 끝났을 때
            override fun afterTextChanged(s: Editable?) {
            }
        })

//        //signInfoWriteAct에서 EditText에 넣은 값들을 intent로 가져옴
//        var name: String = intent.getStringExtra("username")
//        var birth: String = intent.getStringExtra("birth")
//        var phone: String = intent.getStringExtra("phone")
//        var email: String = intent.getStringExtra("email")

    }

    private fun keyboardDown(view: View) {

        val imm: InputMethodManager = applicationContext!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun init() {
        rl_camera_img_sign_id_set_act.setOnClickListener(this)
        img_back_btn_sign_id_set_act.setOnClickListener(this)

        rl_sign_id_set_act.setOnClickListener(this)

        btn_id_check_sign_id_set_act.setOnClickListener(this)
    }

    //저장소 권한 확인
    private fun requestReadExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //사용자에게 권한을 왜 허용해야되는지에 메세지를 주기 위한 대한 로직을 추가하면 이 블락에서 하면됩니다!
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), My_READ_STORAGE_REQUEST_CODE)
            }
        } else {
            showAlbum()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == My_READ_STORAGE_REQUEST_CODE) {
            //결과에 대해 허용을 눌렀는지 체크하는 조건문이구요!
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //이곳은 외부저장소 접근을 허용했을 때에 대한 로직을 쓰시면됩니다. 우리는 앨범을 여는 메소드를 호출해주면되겠죠?
                showAlbum()
            } else {
                //이곳은 외부저장소 접근 거부를 했을때에 대한 로직을 넣어주시면 됩니다.
                finish()
            }
        }
    }

    fun showAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                data?.let {

                    this.data = data!!.data

                    var selectedPictureUri = it.data
                    val options = BitmapFactory.Options()
                    var inputstream: InputStream? = contentResolver.openInputStream(selectedPictureUri)  // here, you need to get your context.
                    val bitmap = BitmapFactory.decodeStream(inputstream, null, options)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())

                    mimage = MultipartBody.Part.createFormData("profileImgFile", File(selectedPictureUri.toString()).name, photoBody)

                    Glide.with(this@SignIdSettingActivity)
                            .load(data.data)
                            //썸네일
                            .thumbnail(0.1f)
                            .into(img_profile_sign_id_set_act)
                }
            }
        }
    }

    // 이미지 파일을 확장자까지 표시해주는 메소드
    fun getRealPathFromURI(context: Context, contentUri: Uri): String {
        var cursor: Cursor? = null
        try {
            val proj = arrayOf(MediaStore.Images.Media.DATA)
            cursor = context.contentResolver.query(contentUri, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            return cursor.getString(column_index)
        } finally {
            cursor?.close()
        }
    }

    //view에 presenter 붙여주기
    private fun initPresenter() {

        signIdSettingPresenter = SignIdSettingActivityPresenter()
        // 뷰 붙여주는 작업
        signIdSettingPresenter.view = this
        SignIdSettingObject.signIdSettingActivityPresenter = signIdSettingPresenter
    }

    fun responseData(data: PostSignIdSettingResponse) {

        data?.let {

            Log.v("바보", data!!.message)

        }
    }

    private val mResponseClickListener = View.OnClickListener { idCheckDialog!!.dismiss() }

}
