package com.takhyungmin.dowadog.utils

import android.content.Context

object ApplicationData {

    var baseUrl = "http://13.209.185.163:8080/"

    var auth = ""

    var loginState = false

    lateinit var applicationContext : Context

    var firstLoginFlag = false

    var userName = ""

    var userBirth = ""

    var userPhone = ""

    var userImage = ""
}