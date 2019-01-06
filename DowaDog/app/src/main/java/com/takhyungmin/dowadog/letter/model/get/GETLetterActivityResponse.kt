package com.takhyungmin.dowadog.letter.model.get

import com.takhyungmin.dowadog.letter.model.getletterData

data class GETLetterActivityResponse(
        val data: ArrayList<getletterData>,
        val message: String,
        val status: Int
)