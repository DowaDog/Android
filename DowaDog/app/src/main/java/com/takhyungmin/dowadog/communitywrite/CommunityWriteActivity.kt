package com.takhyungmin.dowadog.communitywrite

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils.rotateImage
import com.takhyungmin.dowadog.BaseActivity
import com.takhyungmin.dowadog.R
import com.takhyungmin.dowadog.communitywrite.model.CommunityWriteObject
import com.takhyungmin.dowadog.communitywrite.model.post.PostCommunityPostWriteResponse
import com.takhyungmin.dowadog.presenter.activity.CommunityWriteActivityPreseneter
import com.takhyungmin.dowadog.utils.CustomDialog
import kotlinx.android.synthetic.main.activity_community_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.toast
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream


class CommunityWriteActivity : BaseActivity(), View.OnClickListener {

    var TAG = "CommunityWriteActivity_LOG"

    // val REQUEST_CODE_SELECT_IMAGE: Int = 1004
    private val REQUEST_PICK_PHOTO = 1
    private var presentImabeBoxNum = -1
    val My_READ_STORAGE_REQUEST_CODE = 7777
    lateinit var image: ArrayList<MultipartBody.Part>

    private var customDialog: CustomDialog? = null

    var firstPicAlbumFlag = 0
    var secondPicAlbumFlag = 0
    var thirdPicAlbumFlag = 0
    var fourthPicAlbumFlag = 0

    private lateinit var communityWriteActivityPresenter: CommunityWriteActivityPreseneter

    lateinit var communityWriteDataList: PostCommunityPostWriteResponse


    val imagesEncodedList: ArrayList<Uri> by lazy {
        ArrayList<Uri>()
    }


    override fun onClick(v: View?) {
        when (v) {
        // 카메라 아이콘
            btn_camera_community_write_act -> {
                // 갤러리 접근 권한 확인 기능
                requestReadExternalStoragePermission()
            }

        // 취소 버튼
            btn_cancle_community_write_act -> {
                finish()
            }

        // 확인하기 버튼
            btn_confirm_community_write_act -> {
                // 통신 코드
                selectData()
                // 어디로 가야하지 ?

            }

        // 사진 첫번째 박스
            iv_first_picture_box_community_write_act -> {

                // 이미지뷰에 담겨 있는 이미지 리소스 확인
                var temp: Drawable = iv_first_picture_box_community_write_act.getDrawable()

                // Drawable 버전의 img_plus1227의 이미지
                var temp1: Drawable = applicationContext.resources.getDrawable(R.drawable.img_plus_1227)

                /// 비교를 위한 비트맵 변환
                val tmpBitmap = (temp as BitmapDrawable).bitmap

                // 비교를 위한 비트맵 변환
                val tmpBitmap1 = (temp1 as BitmapDrawable).bitmap

                // 이미지 뷰에 담겨있는 리소스가 img_plus일 경우 앨범 보여줌
                if (tmpBitmap.equals(tmpBitmap1)) {
                    showAlbum()
                } else {
                    // 삭제하는 다이얼로그 띄우기
                    openImgDeleteDialog(0)
                }
            }
            iv_second_picture_box_community_write_act -> {

                var temp: Drawable = iv_second_picture_box_community_write_act.getDrawable()
                var temp1: Drawable = applicationContext.resources.getDrawable(R.drawable.img_plus_1227)

                val tmpBitmap = (temp as BitmapDrawable).bitmap
                val tmpBitmap1 = (temp1 as BitmapDrawable).bitmap

                if (tmpBitmap.equals(tmpBitmap1)) {
                    showAlbum()
                } else {
                    // 삭제하는 다이얼로그 띄우기
                    openImgDeleteDialog(1)
                }
            }
            iv_third_picture_box_community_write_act -> {

                var temp: Drawable = iv_third_picture_box_community_write_act.getDrawable()
                var temp1: Drawable = applicationContext.resources.getDrawable(R.drawable.img_plus_1227)

                val tmpBitmap = (temp as BitmapDrawable).bitmap
                val tmpBitmap1 = (temp1 as BitmapDrawable).bitmap

                if (tmpBitmap.equals(tmpBitmap1)) {
                    showAlbum()
                } else {
                    // 삭제하는 다이얼로그 띄우기
                    openImgDeleteDialog(2)
                }
            }
            iv_fourth_picture_box_community_write_act -> {

                var temp: Drawable = iv_fourth_picture_box_community_write_act.getDrawable()
                var temp1: Drawable = applicationContext.resources.getDrawable(R.drawable.img_plus_1227)

                val tmpBitmap = (temp as BitmapDrawable).bitmap
                val tmpBitmap1 = (temp1 as BitmapDrawable).bitmap

                if (tmpBitmap.equals(tmpBitmap1)) {
                    showAlbum()
                } else {
                    // 삭제하는 다이얼로그 띄우기
                    openImgDeleteDialog(3)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_community_write)
        // 리스너 달기
        init()
        // 프레젠터 붙이기
        initPresenter()

        // 데이터 선별


        // 이미지 박스를 정사각형 네 개로 계산
        setPictureBoxSquare()
        // setStatusBarTransparent()

    }

    // 리스너
    fun init() {
        btn_camera_community_write_act.setOnClickListener(this)
        btn_cancle_community_write_act.setOnClickListener(this)
        btn_confirm_community_write_act.setOnClickListener(this)
        iv_first_picture_box_community_write_act.setOnClickListener(this)
        iv_second_picture_box_community_write_act.setOnClickListener(this)
        iv_third_picture_box_community_write_act.setOnClickListener(this)
        iv_fourth_picture_box_community_write_act.setOnClickListener(this)
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

    // 저장소 권한 확인
    private fun requestReadExternalStoragePermission() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //사용자에게 권한을 왜 허용해야되는지에 메시지를 주기 위한 대한 로직을 추가하려면 이 블락에서 하면됩니다!! //하지만 우리는 그냥 비워놓습니다!! 딱히 줄말 없으면 비워놔도 무관해요!!! 굳이 뭐 안넣어도됩니다!
            } else {
                //아래 코드는 권한을 요청하는 메시지를 띄우는 기능을 합니다! 요청에 대한 결과는 callback으로 onRequestPermissionsResult 메소드에서 받습니다!!!
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), My_READ_STORAGE_REQUEST_CODE)
            }
        } else {
            //첫번째 if 문의 else 로써, 기존에 이미 권한 메시지를 통해 권한을 허용했다면 아래와 같은 곧바로 앨범을 여는 메소드를 호출 해주면됩니다!!
            showAlbum()
        }
    }


    // 퍼미션 팝업이 떴을 때 확인, 취소 버튼 분기
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        if (requestCode == My_READ_STORAGE_REQUEST_CODE) {

            // 퍼미션 확인 버튼을 눌렀을 때
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showAlbum()
            } // 퍼미션 취소 버튼을 눌렀을 때
            else {
                finish()
            }
        }
    }

    //앨범을 여는 메소드입니다!
    private fun showAlbum() {
        val intent = Intent()

        intent.type = "image/*"

        // 다중이미지를 위한 옵션
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        //앨범에서 사진을 선택한 결과를 받기위해 startActivityForResult를 통해 앨범 엑티비티를 열어요!
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), REQUEST_PICK_PHOTO)
    }

    // 저장소에서 Acitivity로 복귀한 후 분기
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.v("ygyg", data!!.toString())
        if (requestCode == REQUEST_PICK_PHOTO) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    // something is wrong
                }

                ll_picture_box_coummunity_write_act.visibility = View.VISIBLE
                val clipData = data!!.clipData
                //Log.v("ygyg", clipData.toString())

                // 다중 선택 로직
                if (clipData != null) {
                    // 선택한 이미지 + 선택되어있던 이미지의 합이 4 이상이면 모든 로직 취소 후 Toast 발생
                    if (clipData.itemCount + presentImabeBoxNum > 3) {
                        toast("4장 이하만 선택가능합니다.")
                    } else {
                        for (i in 0 until clipData.itemCount) {

                            // uri 가져오기
                            val uri = clipData.getItemAt(i).uri

                            // 인덱스 분기
                            when (i) {
                                0 -> {

                                    // (선택 당시, 즉, 카메라 아이콘을 눌렀을 때) 이미 선택되어있는 Img가 하나도 없었을 경우
                                    if (presentImabeBoxNum == -1) {
                                        Glide.with(applicationContext).load(uri).into(iv_first_picture_box_community_write_act)
                                        imagesEncodedList.add((uri))
                                    } // (선택 당시, 즉, 카메라 아이콘을 눌렀을 때) 이미 선택되어있는 Img가 한 개 있을 경우
                                    else if (presentImabeBoxNum == 0) {
                                        Glide.with(applicationContext).load(uri).into(iv_second_picture_box_community_write_act)
                                        imagesEncodedList.add((uri))
                                    } // (선택 당시, 즉, 카메라 아이콘을 눌렀을 때) 이미 선택되어있는 Img가 두 개 있을 경우
                                    else if (presentImabeBoxNum == 1) {
                                        Glide.with(applicationContext).load(uri).into(iv_third_picture_box_community_write_act)
                                        imagesEncodedList.add((uri))
                                    } // (선택 당시, 즉, 카메라 아이콘을 눌렀을 때) 이미 선택되어있는 Img가 세 개 있을 경우
                                    else if (presentImabeBoxNum == 2) {
                                        Glide.with(applicationContext).load(uri).into(iv_fourth_picture_box_community_write_act)
                                        imagesEncodedList.add((uri))
                                    }
                                }
                                1 -> {
                                    // 위와 동
                                    if (presentImabeBoxNum == -1) {
                                        Glide.with(applicationContext).load(uri).into(iv_second_picture_box_community_write_act)
                                        imagesEncodedList.add((uri))
                                    } else if (presentImabeBoxNum == 0) {
                                        Glide.with(applicationContext).load(uri).into(iv_third_picture_box_community_write_act)
                                        imagesEncodedList.add((uri))
                                    } else if (presentImabeBoxNum == 1) {
                                        Glide.with(applicationContext).load(uri).into(iv_fourth_picture_box_community_write_act)
                                        imagesEncodedList.add((uri))
                                    }
                                }
                                2 -> {
                                    if (presentImabeBoxNum == -1) {
                                        Glide.with(applicationContext).load(uri).into(iv_third_picture_box_community_write_act)
                                        imagesEncodedList.add((uri))
                                    } else if (presentImabeBoxNum == 0) {
                                        Glide.with(applicationContext).load(uri).into(iv_fourth_picture_box_community_write_act)
                                        imagesEncodedList.add((uri))
                                    }
                                }
                                3 -> {
                                    if (presentImabeBoxNum == -1) {
                                        Glide.with(applicationContext).load(uri).into(iv_fourth_picture_box_community_write_act)
                                        imagesEncodedList.add((uri))
                                    }
                                }

                            }

                        }

                        // 갤러리에서 선택한 이미지 수를 선택되어있는 Img 수에 더함
                        presentImabeBoxNum += clipData.itemCount
                    }
                } else {
                    // 하나만 선택했을 때 Flow
                    if (presentImabeBoxNum >= 3) {
                        toast("4장 이하만 선택가능합니다.")
                    } else {
                        val uri = data?.data

                        // (선택 당시, 즉, 카메라 아이콘을 눌렀을 때) 이미 선택되어있는 Img가 하나도 없었을 경우
                        // 위 코드와 동일
                        if (presentImabeBoxNum == -1) {
                            Glide.with(applicationContext).load(uri).into(iv_first_picture_box_community_write_act)
                        } else if (presentImabeBoxNum == 0) {
                            Glide.with(applicationContext).load(uri).into(iv_second_picture_box_community_write_act)
                        } else if (presentImabeBoxNum == 1) {
                            Glide.with(applicationContext).load(uri).into(iv_third_picture_box_community_write_act)
                        } else if (presentImabeBoxNum == 2) {
                            Glide.with(applicationContext).load(uri).into(iv_fourth_picture_box_community_write_act)
                        }

                        // 통신을 위한 uri 관리를 위한 List
                        imagesEncodedList.add((uri))

                        // 갤러리에서 선택한 하나의 이미지 수를 선택되어있는 Img 수에 더함
                        presentImabeBoxNum++
                    }
                }
            }
        }
    }

    // 정사각형으로 이미지 뽑아내기
    fun setPictureBoxSquare() {
        val lp = ll_picture_box_coummunity_write_act.layoutParams
        lp.width = resources.displayMetrics.widthPixels
        lp.height = (lp.width / 4)
    }

    // 이미지가 선택되어있는 상태에서 Click했을 경우 삭제 메소드
    fun deleteImgBox(position: Int) {
        when (position) {
            0 -> {
                // 첫번째 이미지 박스의 이미지가 있을 경우
                if (imagesEncodedList.size > 0) {
                    // 이미지 Uri 통신을 위한 Uri 삭제
                    imagesEncodedList.removeAt(0)
                    // 삭제된 이미지를 제외한 이미지가 0개일 경우
                    if (imagesEncodedList.size == 0) {
                        iv_first_picture_box_community_write_act.setImageResource(R.drawable.img_plus_1227)
                    } // 삭제된 이미지를 제외한 이미지가 1개일 경우
                    else if (imagesEncodedList.size == 1) {
                        iv_second_picture_box_community_write_act.setImageResource(R.drawable.img_plus_1227)
                        Glide.with(applicationContext).load(imagesEncodedList[0]).into(iv_first_picture_box_community_write_act)
                    } // 삭제된 이미지를 제외한 이미지가 2개일 경우
                    else if (imagesEncodedList.size == 2) {
                        iv_third_picture_box_community_write_act.setImageResource(R.drawable.img_plus_1227)
                        Glide.with(applicationContext).load(imagesEncodedList[0]).into(iv_first_picture_box_community_write_act)
                        Glide.with(applicationContext).load(imagesEncodedList[1]).into(iv_second_picture_box_community_write_act)
                    } // 삭제된 이미지를 제외한 이미지가 3개일 경우
                    else if (imagesEncodedList.size == 3) {
                        iv_fourth_picture_box_community_write_act.setImageResource(R.drawable.img_plus_1227)
                        Glide.with(applicationContext).load(imagesEncodedList[0]).into(iv_first_picture_box_community_write_act)
                        Glide.with(applicationContext).load(imagesEncodedList[1]).into(iv_second_picture_box_community_write_act)
                        Glide.with(applicationContext).load(imagesEncodedList[2]).into(iv_third_picture_box_community_write_act)
                    }
                }
            }
            1 -> {

                // 위와 동일
                if (imagesEncodedList.size > 1) {
                    imagesEncodedList.removeAt(1)
                    if (imagesEncodedList.size == 1) {
                        iv_second_picture_box_community_write_act.setImageResource(R.drawable.img_plus_1227)
                    } else if (imagesEncodedList.size == 2) {
                        iv_third_picture_box_community_write_act.setImageResource(R.drawable.img_plus_1227)
                        Glide.with(applicationContext).load(imagesEncodedList[1]).into(iv_second_picture_box_community_write_act)
                    } else if (imagesEncodedList.size == 3) {
                        iv_fourth_picture_box_community_write_act.setImageResource(R.drawable.img_plus_1227)
                        Glide.with(applicationContext).load(imagesEncodedList[1]).into(iv_second_picture_box_community_write_act)
                        Glide.with(applicationContext).load(imagesEncodedList[2]).into(iv_third_picture_box_community_write_act)
                    }
                }
            }
            2 -> {
                if (imagesEncodedList.size > 2) {
                    imagesEncodedList.removeAt(2)
                    if (imagesEncodedList.size == 2) {
                        iv_third_picture_box_community_write_act.setImageResource(R.drawable.img_plus_1227)
                    } else if (imagesEncodedList.size == 3) {
                        iv_fourth_picture_box_community_write_act.setImageResource(R.drawable.img_plus_1227)
                        Glide.with(applicationContext).load(imagesEncodedList[2]).into(iv_third_picture_box_community_write_act)
                    }
                }
            }
            3 -> {
                if (imagesEncodedList.size > 3) {
                    imagesEncodedList.removeAt(3)
                    iv_fourth_picture_box_community_write_act.setImageResource(R.drawable.img_plus_1227)
                }
            }
        }
    }

    fun openImgDeleteDialog(position: Int) {
        val content = "사진을 삭제하시겠습니까?"
        customDialog = CustomDialog(CommunityWriteActivity@ this, content, leftListener, View.OnClickListener {
            // 삭제하는 로직
            deleteImgBox(position)
            presentImabeBoxNum--
            customDialog!!.dismiss()
        }, "취소", "확인")
        customDialog!!.show()
    }

    private val leftListener = View.OnClickListener { customDialog!!.dismiss() }


    private fun initPresenter() {
        communityWriteActivityPresenter = CommunityWriteActivityPreseneter()
        // 뷰 붙여주는 작엄
        communityWriteActivityPresenter.view = this
        CommunityWriteObject.communityWriteActivityPreseneter = communityWriteActivityPresenter
    }

    fun responseData(data: PostCommunityPostWriteResponse) {
        data?.let {
            communityWriteDataList = data
        }
        finish()
    }

    fun selectData() {
        // 제목이 없을 때 분기
        Log.v("잘돼", "눌림1")
        if (et_title_community_write_act.text.toString().isNotEmpty()) {
            // 내용이 없을 때 분기
            Log.v("잘돼", "눌림2")
            if (et_content_community_write_act.text.toString().isNotEmpty()) {
                Log.v("잘돼", "눌3")
                communityWriteActivityPresenter.requestData(et_title_community_write_act.text.toString(), et_content_community_write_act.text.toString(), converToImage(imagesEncodedList))
            } else {
                Log.v("잘돼", "눌림4")
                toast("제목을 입력해주세요")
            }

        } else {
            Log.v("잘돼", "눌림5")
            toast("제목을 입력해주세요")
        }
    }

    fun converToImage(uris: ArrayList<Uri>): ArrayList<MultipartBody.Part> {


        image = ArrayList()
        uris.forEach { uri ->
            val options = BitmapFactory.Options()

            var input: InputStream? = null // here, you need to get your context.
            try {
                input = contentResolver.openInputStream(uri)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

            val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)
            // fixOrientation(bitmap, uri.toString())
            // val photo = File(uri.toString())

            val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
            image.add(MultipartBody.Part.createFormData("communityImgFiles", "photo", photoBody))
        }

        return image

    }

    fun fixOrientation(bitmap : Bitmap, url: String): Bitmap {
        var ei : ExifInterface? = null
        Log.v("TAGGGGGGG", url)
        try {
            ei = ExifInterface(url)
        } catch (e: IOException) {
            Log.v("TAGGGGGGG", e.toString())
            e.printStackTrace();
        }
        var orientation = ei!!.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)

        when(orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> {
                var selectedBitmap = rotateImage(bitmap, 90)
                return selectedBitmap
            }
            ExifInterface.ORIENTATION_ROTATE_180 -> {
                var selectedBitmap = rotateImage(bitmap, 180)
                return selectedBitmap
            }
            ExifInterface.ORIENTATION_ROTATE_270 -> {
                var selectedBitmap = rotateImage(bitmap, 270)
                return selectedBitmap
            }
            ExifInterface.ORIENTATION_NORMAL -> {
                var selectedBitmap = bitmap
                return selectedBitmap
            }
            else -> {
                var selectedBitmap = bitmap
                return selectedBitmap
            }
        }
    }

}