package com.takhyungmin.dowadog.dogdetail.model.get

data class GetDogDetailData(
        val age: String,
        val careName: String,
        val careTel: String,
        val happenPlace: String,
        val id: Int,
        val kindCd: String,
        val noticeEddt: String,
        val noticeNo: String,
        val noticeStdt: String,
        val processState: String,
        val region: String,
        val remainDateState: Boolean,
        val sexCd: String,
        val specialMark: String,
        val thumbnailImg: String,
        val type: String,
        val weight: String
)