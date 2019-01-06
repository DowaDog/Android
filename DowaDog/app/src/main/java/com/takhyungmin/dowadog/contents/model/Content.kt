package com.takhyungmin.dowadog.contents.model

data class Content(
        val auth: Boolean,
        val detail: String,
        val id: Int,
        val thumnailImg: String,
        val title: String
)