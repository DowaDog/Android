package com.takhyungmin.dowadog.mypage.model.put

import android.util.Log
import com.takhyungmin.dowadog.mypage.model.MypageSettingGetObject
import com.takhyungmin.dowadog.mypage.model.MypageSettingNetworkService
import com.takhyungmin.dowadog.mypage.model.MypageSettingObject
import com.takhyungmin.dowadog.mypage.model.get.GETMypageSettingResponse
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

    //회원정보 가져오기 get
    fun getMypageSetData(img : MultipartBody.Part?) {

        mypageSettingNetworkService.getMypagesetting("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoidGFla3l1bmcwNDAyIiwiaXNzIjoiZG93YWRvZyIsImV4cCI6MTU3ODI5MjY0MX0.vYv4lH8y-Q_DAinNkJrg3-t3MY5c1qROmBADn-MIMs4")
                .enqueue(object : Callback<GETMypageSettingResponse> {

                    override fun onFailure(call: Call<GETMypageSettingResponse>?, t: Throwable?) {
                        Log.e("mypageset 회원정보 get통신 실패", t.toString())
                    }

                    override fun onResponse(call: Call<GETMypageSettingResponse>?, response: Response<GETMypageSettingResponse>?) {

                        Log.v("TAGG",response.toString())
                        Log.v("TAGG",response!!.body().toString())

                        response?.takeIf { it.isSuccessful }
                                ?.body()
                                ?.let {
                                    MypageSettingGetObject.mypageSettingGETActivityPresenter.responseGetData(it)
                                    Log.v("TAGG","모델 get 리스폰스")

                                }
                    }
                })
    }

    //회원정보 put
    fun putMypageSetData(name: String, phone : String, birth : String, img : MultipartBody.Part?) {

        var name = RequestBody.create(MediaType.parse("text/plain"), name)
        var phone = RequestBody.create(MediaType.parse("text/plain"), phone)
        var birth = RequestBody.create(MediaType.parse("text/plain"), birth)
        var email = RequestBody.create(MediaType.parse("text/plain"), "askfjsdl@alkd.co")

        mypageSettingNetworkService.putMypageList("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoidGFla3l1bmcwNDAyIiwiaXNzIjoiZG93YWRvZyIsImV4cCI6MTU3ODIyNjg0MX0.abzE4hLsRbVe5Xj-PigEC1SlUNwbcaYZfNRu0V4nsU0",
                name, phone, email, birth, img)
                .enqueue(object : Callback<PUTMypageSettingResponse> {

                    override fun onFailure(call: Call<PUTMypageSettingResponse>?, t: Throwable?) {
                        Log.e("회원정보 put 통신 실패", t.toString())
                    }

                    override fun onResponse(call: Call<PUTMypageSettingResponse>?, response: Response<PUTMypageSettingResponse>?) {

                        Log.v("TAGG",response.toString())
                        Log.v("TAGG",response!!.body().toString())

                        response?.takeIf { it.isSuccessful }
                                ?.body()
                                ?.let {
                                    MypageSettingObject.mypageSettinActivityPresenter.responseData(it)
                                    Log.v("TAGG","모델 리스폰스")

                                }
                    }
                })
    }
}