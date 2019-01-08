package com.takhyungmin.dowadog.utils

import android.content.Context

object ApplicationData {

    var baseUrl = "http://13.209.185.163:8080/"

    var auth = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoidGFla3l1bmcwNDAyIiwiaXNzIjoiZG93YWRvZyIsImV4cCI6MTU3ODI4NDQzOH0.MTN9ke4pknmiqwu29Je24mUWn56GVM8OEuCca4HEPqI"

    var loginState = false

    lateinit var applicationContext : Context

    var firstLoginFlag = false

    var userName = ""

    var userBirth = ""

    var userPhone = ""
}