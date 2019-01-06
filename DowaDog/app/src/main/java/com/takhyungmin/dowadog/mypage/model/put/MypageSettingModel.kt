package com.takhyungmin.dowadog.mypage.model.put

import android.util.Log
import com.takhyungmin.dowadog.mypage.model.MypageSettingNetworkService
import com.takhyungmin.dowadog.mypage.model.MypageSettingObject
import com.takhyungmin.dowadog.utils.ApplicationData
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MypageSettingModel {
    private var mypageSettingNetworkService: MypageSettingNetworkService

    init {
        val builder = Retrofit.Builder()
        val retrofit = builder
                .baseUrl(ApplicationData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        mypageSettingNetworkService = retrofit.create(MypageSettingNetworkService::class.java)
    }

    fun getMypageSetData(img : MultipartBody.Part?) {

        Log.v("mypagesetting통신이인","아아아아")

        var name = RequestBody.create(MediaType.parse("text/plain"), "유가희")
        var phone = RequestBody.create(MediaType.parse("text/plain"), "010-2222-2222")
        var birth = RequestBody.create(MediaType.parse("text/plain"), "1997-04-02")
        var email = RequestBody.create(MediaType.parse("text/plain"), "asdlkfj@gmail.com")

        mypageSettingNetworkService.putMypageList("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoidGFla3l1bmcwNDAyIiwiaXNzIjoiZG93YWRvZyIsImV4cCI6MTU3ODIyNjg0MX0.abzE4hLsRbVe5Xj-PigEC1SlUNwbcaYZfNRu0V4nsU0",
                name, phone, birth, email, img)
                .enqueue(object : Callback<PUTMypageSettingResponse> {

                    override fun onFailure(call: Call<PUTMypageSettingResponse>?, t: Throwable?) {
                        Log.e("mypageset통신 실패", t.toString())
                    }

                    override fun onResponse(call: Call<PUTMypageSettingResponse>?, response: Response<PUTMypageSettingResponse>?) {

                        Log.v("TAGG",response.toString())
                        Log.v("TAGG",response!!.body().toString())

                        response?.takeIf { it.isSuccessful }
                                ?.body()
                                ?.let {
                                    MypageSettingObject.mypageSettingActivityPresenter.responseData(it)
                                    Log.v("TAGG","모델 리스폰스")

                                }
                    }
                })
    }
}