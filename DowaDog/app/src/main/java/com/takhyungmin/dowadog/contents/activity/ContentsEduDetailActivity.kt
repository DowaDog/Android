package com.takhyungmin.dowadog.contents.activity

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import android.transition.Transition
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
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
    }


    private val sDecelerator = DecelerateInterpolator()
    private val sAccelerator = AccelerateInterpolator()
    //private val PACKAGE_NAME = "com.example.android.activityanim"
    private val ANIM_DURATION = 500

    private var mBitmapDrawable: BitmapDrawable? = null
    private val colorizerMatrix = ColorMatrix()
    lateinit var mBackground: ColorDrawable

    var mLeftDelta: Int = 0
    var mTopDelta: Int = 0
    var mWidthScale: Float = 0.toFloat()
    var mHeightScale: Float = 0.toFloat()
    var mOriginalOrientation: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contents_edu_detail)

        ViewCompat.setTransitionName(img_contents_edu_detail, VIEW_NAME_HEADER_IMAGE)
        ViewCompat.setTransitionName(tv_contents_edu_detail_title, VIEW_NAME_HEADER_TITLE)
        loadItem()

//        val title = intent.getStringExtra("title")
//        val thumbnailTop = intent.getIntExtra("top", 0)
//        val thumbnailLeft = intent.getIntExtra("left", 0)
//        val thumbnailWidth = intent.getIntExtra("width", 0)
//        val thumbnailHeight = intent.getIntExtra("height", 0)
//        mOriginalOrientation = intent.getIntExtra("orientation", 0)
//
//
//        //img_contents_edu_detail.setImageResource(R.drawable.drawer_btn)
//        Glide.with(this).load(R.drawable.pic1).into(img_contents_edu_detail)
//        tv_contents_edu_detail_contents.text = title
//
//        mBackground = ColorDrawable(Color.WHITE)
//        layout_edu_detail.background = mBackground
//
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

    fun loadItem(){
        tv_contents_edu_detail_title.text = "제목"

        if (addTransitionListener()) {
            // If we're running on Lollipop and we have added a listener to the shared element
            // transition, load the thumbnail. The listener will load the full-size image when
            // the transition is complete.
            loadThumbnail()
        } else {
            // If all other cases we should just load the full-size image now
            loadFullSizeImage()
        }
    }

    private fun loadThumbnail() {
        Glide.with(this)
                .load(R.drawable.pic1)
                .into(img_contents_edu_detail)
    }

    /**
     * Load the item's full-size image into our [ImageView].
     */
    private fun loadFullSizeImage() {
        Glide.with(this)
                .load(R.drawable.pic1)
                .into(img_contents_edu_detail)
    }

    private fun addTransitionListener(): Boolean {
        val transition = window.sharedElementEnterTransition

        if (transition != null) {
            // There is an entering shared element transition so add a listener to it
            transition.addListener(object : Transition.TransitionListener {
                override fun onTransitionEnd(transition: Transition) {
                    // As the transition has ended, we can now load the full-size image
                    loadFullSizeImage()

                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionStart(transition: Transition) {
                    // No-op
                }

                override fun onTransitionCancel(transition: Transition) {
                    // Make sure we remove ourselves as a listener
                    transition.removeListener(this)
                }

                override fun onTransitionPause(transition: Transition) {
                    // No-op
                }

                override fun onTransitionResume(transition: Transition) {
                    // No-op
                }
            })
            return true
        }

        // If we reach here then we have not added a listener
        return false
    }


//    fun runEnterAnimation() {
//        val duration = 1000L
//
//        // Set starting values for properties we're going to animate. These
//        // values scale and position the full size version down to the thumbnail
//        // size/location, from which we'll animate it back up
//        img_contents_edu_detail.pivotX = 0f
//        img_contents_edu_detail.pivotY = 0f
//        img_contents_edu_detail.scaleX = mWidthScale
//        img_contents_edu_detail.scaleY = mHeightScale
//        img_contents_edu_detail.translationX = mLeftDelta.toFloat()
//        img_contents_edu_detail.translationY = mTopDelta.toFloat()
//
//        // We'll fade the text in later
//        tv_contents_edu_detail_contents.alpha = 0f
//
//        // Animate scale and translation to go from thumbnail to full size
//        img_contents_edu_detail.animate().setDuration(duration).scaleX(1f).scaleY(1f).translationX(0f).translationY(0f).setInterpolator(sDecelerator).withEndAction(Runnable {
//            // Animate the description in after the image animation
//            // is done. Slide and fade the text in from underneath
//            // the picture.
//            tv_contents_edu_detail_contents.translationY = (-tv_contents_edu_detail_contents.height).toFloat()
//            tv_contents_edu_detail_contents.animate().setDuration(duration / 2).translationY(0f).alpha(1f).interpolator = sDecelerator
//        })
//
//
//        // Fade in the black background
//        val bgAnim = ObjectAnimator.ofInt(mBackground, "alpha", 0, 255)
//        bgAnim.duration = duration
//        bgAnim.start()
//
//        // Animate a color filter to take the image from grayscale to full color.
//        // This happens in parallel with the image scaling and moving into place.
//        val colorizer = ObjectAnimator.ofFloat(this@ContentsEduDetailActivity,
//                "saturation", 0f, 1f)
//        colorizer.duration = duration
//        colorizer.start()
//
//        // Animate a drop-shadow of the image
//        val shadowAnim = ObjectAnimator.ofFloat(shadow_contents_edu_detail, "shadowDepth", 0f, 1f)
//        shadowAnim.duration = duration
//        shadowAnim.start()
//    }


//    fun runExitAnimation(endAction: Runnable) {
//        val duration = 1000L
//
//        // No need to set initial values for the reverse animation; the image is at the
//        // starting size/location that we want to start from. Just animate to the
//        // thumbnail size/location that we retrieved earlier
//
//        // Caveat: configuration change invalidates thumbnail positions; just animate
//        // the scale around the center. Also, fade it out since it won't match up with
//        // whatever's actually in the center
//        val fadeOut: Boolean
//        if (resources.configuration.orientation != mOriginalOrientation) {
//            img_contents_edu_detail.pivotX = (img_contents_edu_detail.width / 2).toFloat()
//            img_contents_edu_detail.pivotY = (img_contents_edu_detail.height / 2).toFloat()
//            mLeftDelta = 0
//            mTopDelta = 0
//            fadeOut = true
//        } else {
//            fadeOut = false
//        }
//
//        // First, slide/fade text out of the way
//        tv_contents_edu_detail_contents.animate().translationY((-tv_contents_edu_detail_contents.height).toFloat()).alpha(0f).setDuration(duration / 2).setInterpolator(sAccelerator).withEndAction(Runnable {
//            // Animate image back to thumbnail size/location
//            img_contents_edu_detail.animate().setDuration(duration).scaleX(mWidthScale).scaleY(mHeightScale).translationX(mLeftDelta.toFloat()).translationY(mTopDelta.toFloat()).withEndAction(endAction)
//            if (fadeOut) {
//                img_contents_edu_detail.animate().alpha(0f)
//            }
//            // Fade out background
//            val bgAnim = ObjectAnimator.ofInt(mBackground, "alpha", 0)
//            bgAnim.duration = duration
//            bgAnim.start()
//
//             //Animate the shadow of the image
//            val shadowAnim = ObjectAnimator.ofFloat(shadow_contents_edu_detail,
//                    "shadowDepth", 1f, 0f)
//            shadowAnim.duration = duration
//            shadowAnim.start()
//
//            // Animate a color filter to take the image back to grayscale,
//            // in parallel with the image scaling and moving into place.
//            val colorizer = ObjectAnimator.ofFloat(this@ContentsEduDetailActivity,
//                    "saturation", 1f, 0f)
//            colorizer.duration = duration
//            colorizer.start()
//        })
//    }

//    override fun onBackPressed() {
//        runExitAnimation(Runnable {
//            // *Now* go ahead and exit the activity
//            finish()
//        })
//    }

    fun setSaturation(value: Float) {
        colorizerMatrix.setSaturation(value)
        val colorizerFilter = ColorMatrixColorFilter(colorizerMatrix)
        img_contents_edu_detail.colorFilter = colorizerFilter
        //mBitmapDrawable!!.setColorFilter(colorizerFilter)
    }

//    override fun finish() {
//        super.finish()
//
//        // override transitions to skip the standard window animations
//        overridePendingTransition(0, 0)
//    }
}
