package com.takhyungmin.dowadog.utils

import android.content.Context

object SharedPreferenceController {
    val USER = "user"
    private val ACCESS_TOKEN = "access_token"
    private val REFRESH_TOKEN = "refresh_token"
    private val REFRESH_EXPIRED = "refresh_expired"
    private val ACCESS_EXPIRED = "access_expired"
    private val ID = "id"
    private val PASSWORD = "password"
    private val FIRSTPOPFLAG = "first_pop_flag"



    fun setId(context : Context, id : String){
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(ID, id)
        editor.apply()
    }

    fun getId(context : Context) : String{
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return pref.getString(ID, "")!!
    }

    fun setPwd(context : Context, pwd : String){
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(PASSWORD, pwd)
        editor.apply()
    }

    fun getPwd(context : Context) : String{
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return pref.getString(PASSWORD, "")!!
    }



    fun setAccessToken(context : Context, accessToken : String){
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(ACCESS_TOKEN, accessToken)
        editor.apply()
    }

    fun getAccessToken(context : Context) : String{
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return pref.getString(ACCESS_TOKEN, "")!!
    }

    fun setRefreshToken(context : Context, refreshToken : String){
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(REFRESH_TOKEN, refreshToken)
        editor.apply()
    }

    fun getRefreshToken(context : Context) : String{
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return pref.getString(REFRESH_TOKEN, "")!!
    }

    fun setRefreshTokenExpired(context : Context, expired : Long){
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putLong(REFRESH_EXPIRED, expired)
        editor.apply()
    }

    fun getRefreshTokenExpired(context : Context) : Long{
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return pref.getLong(REFRESH_EXPIRED, 0L)
    }

    fun setAccessTokenExpired(context : Context, expired : Long){
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putLong(ACCESS_EXPIRED, expired)
        editor.apply()
    }

    fun getAccessTokenExpired(context : Context) : Long{
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return pref.getLong(ACCESS_EXPIRED, 0L)
    }

    fun setFirstPopUpFlag(context : Context, firstPopFlag : Int){
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt(FIRSTPOPFLAG, firstPopFlag)
        editor.apply()
    }

    fun getFirstPopUpFlag(context : Context) : Int{
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return pref.getInt(FIRSTPOPFLAG, 0)
    }

}