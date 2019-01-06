package com.takhyungmin.dowadog.letter.model

data class getletterData(
        val complete: Boolean,
        val createdAt: String,
        val detail: String,
        val id: Int,
        val imgPath: String,
        val title: String,
        val type: String,
        val updatedAt: String
)