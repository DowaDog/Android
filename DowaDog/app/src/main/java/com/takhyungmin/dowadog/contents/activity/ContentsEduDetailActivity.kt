package com.takhyungmin.dowadog.contents.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.SharedElementCallback
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.takhyungmin.dowadog.R
import kotlinx.android.synthetic.main.activity_contents_edu_detail.*


class ContentsEduDetailActivity : AppCompatActivity() {

    companion object {
        // Extra name for the ID parameter
        val EXTRA_PARAM_ID = "detail:_id"

        // View name of the header image. Used for activity scene transitions
        val VIEW_NAME_HEADER_IMAGE = "detail:header:image"

        // View name of the header title. Used for activity scene transitions
        val VIEW_NAME_HEADER_TITLE = "detail:header:title"

        val EXTRA_POSITION = "position"

        var SelectedIndex = 0
    }

    var title = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        ActivityCompat.setEnterSharedElementCallback(this, EnterTransitionCallback)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_edu_detail)
        SelectedIndex = intent.getIntExtra(EXTRA_POSITION, -1)
        //ViewCompat.setTransitionName(img_contents_edu_detail, VIEW_NAME_HEADER_IMAGE)
        //ViewCompat.setTransitionName(tv_contents_edu_detail_title, VIEW_NAME_HEADER_TITLE)
        //title = intent.getStringExtra("title")
        //loadItem()

//        val title = intent.getStringExtra("title")
//        val thumbnailTop = intent.getIntExtra("top", 0)
//        val thumbnailLeft = intent.getIntExtra("left", 0)
//        val thumbnailWidth = intent.getIntExtra("width", 0)
//        val thumbnailHeight = intent.getIntExtra("height", 0)
//        mOriginalOrientation = intent.getIntExtra("orientation", 0)


        //img_contents_edu_detail.setImageResource(R.drawable.drawer_btn)
        Glide.with(this).load(R.drawable.pic1).into(img_contents_edu_detail)
        //tv_contents_edu_detail_title.text = "일단 임시 제목"

//        mBackground = ColorDrawable(Color.WHITE)
//        layout_edu_detail.background = mBackground

//        if (savedInstanceState == null) {
//            val observer = img_contents_edu_detail.viewTreeObserver
//            observer.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
//
//                override fun onPreDraw(): Boolean {
//                    img_contents_edu_detail.viewTreeObserver.removeOnPreDrawListener(this)
//
//                    // Figure out where the thumbnail and full size versions are, relative
//                    // to the screen and each other
//                    val screenLocation = IntArray(2)
//                    img_contents_edu_detail.getLocationOnScreen(screenLocation)
//                    Log.v("data", "left : " + screenLocation[0])
//                    Log.v("data", "top : " + screenLocation[1])
//
//
//                    mLeftDelta = thumbnailLeft - screenLocation[0]
//                    mTopDelta = thumbnailTop - screenLocation[1]
//
//                    // Scale factors to make the large version the same size as the thumbnail
//                    mWidthScale = thumbnailWidth.toFloat() / img_contents_edu_detail.width
//                    mHeightScale = thumbnailHeight.toFloat() / img_contents_edu_detail.height
//
//                    runEnterAnimation()
//
//                    return true
//                }
//            })
//        }
    }


    private val EnterTransitionCallback = object : SharedElementCallback() {
        @SuppressLint("NewApi")
        override fun onMapSharedElements(names: List<String>, sharedElements: MutableMap<String, View>) {
            sharedElements.put(names[0], img_contents_edu_detail)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()

    }

    override fun finish() {
        super.finish()

        // override transitions to skip the standard window animations
        overridePendingTransition(0, 0)
    }
}
