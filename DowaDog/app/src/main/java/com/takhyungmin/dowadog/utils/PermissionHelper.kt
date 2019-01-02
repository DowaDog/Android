package com.takhyungmin.dowadog.utils

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

object PermissionHelper {

    private const val CAMERA_PERMISSION_CODE = 1000
    private const val CAMERA_PERMISSION = Manifest.permission.CAMERA

    private const val READ_EXTERNAL_STORAGE_PERMISSION_CODE = 1001
    private const val READ_EXTERNAL_STORAGE_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE

    /**Camera Permission*/
    fun hasCameraPermission(activity : Activity) : Boolean =
            ContextCompat.checkSelfPermission(activity, CAMERA_PERMISSION) == PackageManager.PERMISSION_GRANTED

    fun requestCameraPermission(activity : Activity){
        ActivityCompat.requestPermissions(
                activity, arrayOf(CAMERA_PERMISSION), CAMERA_PERMISSION_CODE)
    }

    fun shouldShowRequestPermissionRationaleCamera(activity : Activity) : Boolean =
            ActivityCompat.shouldShowRequestPermissionRationale(activity, CAMERA_PERMISSION)


    //처음에 denied 했을 때 다시 퍼미션 걸게끔 유도하는 함수.
    fun launchPermissionSettings(activity : Activity){
        val intent = Intent()
        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        intent.data = Uri.fromParts("package", activity.packageName, null)
        activity.startActivity(intent)
    }


    /**Read External Permission*/
    fun hasReadExternalStoragePermission(activity : Activity) : Boolean =
            ContextCompat.checkSelfPermission(activity, READ_EXTERNAL_STORAGE_PERMISSION) == PackageManager.PERMISSION_GRANTED

    fun requestReadExternalStoragePermission(activity : Activity){
        ActivityCompat.requestPermissions(
                activity, arrayOf(READ_EXTERNAL_STORAGE_PERMISSION), READ_EXTERNAL_STORAGE_PERMISSION_CODE)
    }

    //처음에 denied 했을 때 다시 퍼미션 걸게끔 유도하는 함수.
    fun shouldShowRequestPermissionRationaleReadExternalStorage(activity : Activity) : Boolean =
            ActivityCompat.shouldShowRequestPermissionRationale(activity, READ_EXTERNAL_STORAGE_PERMISSION)




}