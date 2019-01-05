package com.takhyungmin.dowadog.signup.model.post

import android.util.Log
import com.takhyungmin.dowadog.signup.model.SignIdSettingNetworkService
import com.takhyungmin.dowadog.utils.ApplicationData
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignIdSettingModel {

    private var singIdSettingNetworkService: SignIdSettingNetworkService

    init {

        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        singIdSettingNetworkService = retrofit.create(SignIdSettingNetworkService::class.java)
    }

    fun postSignIdSettingData(mimg: MultipartBody.Part?) {
        var id = RequestBody.create(MediaType.parse("text/plain"), "1234")
        var password = RequestBody.create(MediaType.parse("text/plain"), "1234")
        var name = RequestBody.create(MediaType.parse("text/plain"), "유가희")
        var birth = RequestBody.create(MediaType.parse("text/plain"), "19970402")
        var phone = RequestBody.create(MediaType.parse("text/plain"), "01051721920")
        var email = RequestBody.create(MediaType.parse("text/plain"), "asdlkfj@gmail.com")
        var gender = RequestBody.create(MediaType.parse("text/plain"), "F")
        var deviceToken = RequestBody.create(MediaType.parse("text/plain"), "alsdkfjlsjlsk&123")
        var type = RequestBody.create(MediaType.parse("text/plain"), "NORMAL")
        //와 이미지 이거 어떻게 하지?
       // var profileImgFile = RequestBody.create(MediaType.parse("text.plain"), "1234")
        var pushAllow = RequestBody.create(MediaType.parse("text/plain"), "true")
        singIdSettingNetworkService.PostSignIdSettingResponse( id, password, name, birth, phone, email, gender, deviceToken, type, mimg, pushAllow)
                .enqueue(object : Callback<PostSignIdSettingResponse> {
                    override fun onFailure(call: Call<PostSignIdSettingResponse>?, t: Throwable?) {
                        Log.e("회원가입 통신실패", t.toString())
                    }
                    override fun onResponse(call: Call<PostSignIdSettingResponse>?, response: Response<PostSignIdSettingResponse>?) {
                        response?.takeIf { it.isSuccessful }
                                ?.body()
                                ?.let {
                                    //SignIdSettingObject.signIdSettingActivityPresenter.responseData(id, password)
                                    Log.v("ad", "회원가입 통신 성공, 메시지 = " + response.body()!!.message)
                                }
                    }
                })
    }
}
