package com.takhyungmin.dowadog.contents

import com.takhyungmin.dowadog.presenter.fragment.ContentsEduFragmentPresenter
import com.takhyungmin.dowadog.presenter.fragment.ContentsSenseFragmentPresenter

object ContentsObject {
    var isCreated = false
    var position = 0
    lateinit var contentsEduFragmentPresenter: ContentsEduFragmentPresenter
    lateinit var contentsSenseFragmentPresenter: ContentsSenseFragmentPresenter
}