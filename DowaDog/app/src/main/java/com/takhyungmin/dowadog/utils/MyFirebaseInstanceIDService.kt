package com.takhyungmin.dowadog.utils

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.FirebaseInstanceId



class MyFirebaseInstanceIDService : FirebaseInstanceIdService() {

    // 토큰 재생성
    override fun onTokenRefresh() {
        // Get updated InstanceID token.
        val token = FirebaseInstanceId.getInstance().token
    }
}