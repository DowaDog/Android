package com.takhyungmin.dowadog.contents

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout

class ShadowLayout : RelativeLayout {

    var mShadowPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var mShadowDepth: Float = 0.toFloat()
    lateinit var mShadowBitmap: Bitmap
    val BLUR_RADIUS = 6
    val sShadowRectF = RectF(0f, 0f, 0f, 0f)
    val sShadowRect = Rect(0, 0, 0 + 2 * BLUR_RADIUS, 0 + 2 * BLUR_RADIUS)
    var tempShadowRectF = RectF(0f, 0f, 0f, 0f)


    constructor(context : Context) : super(context){
        init()
    }

    constructor(context : Context, attrs : AttributeSet) : super(context, attrs){
        init()
    }

    constructor(context : Context, attrs : AttributeSet, defStyle : Int) : super(context, attrs, defStyle){
        init()
    }

    fun init(){
        mShadowPaint.color = Color.BLACK
        mShadowPaint.style = Paint.Style.FILL
        setWillNotDraw(false)
        mShadowBitmap = Bitmap.createBitmap(sShadowRect.width(),
                sShadowRect.height(), Bitmap.Config.ARGB_8888)
        val c = Canvas(mShadowBitmap)
        mShadowPaint.maskFilter = BlurMaskFilter(BLUR_RADIUS.toFloat(), BlurMaskFilter.Blur.NORMAL)
        c.translate(BLUR_RADIUS.toFloat(), BLUR_RADIUS.toFloat())
        c.drawRoundRect(sShadowRectF, sShadowRectF.width() / 40,
                sShadowRectF.height() / 40, mShadowPaint)
    }

    fun setShadowDepth(depth: Float) {
        if (depth != mShadowDepth) {
            mShadowDepth = depth
            mShadowPaint.alpha = (100 + 150 * (1 - mShadowDepth)).toInt()
            invalidate() // We need to redraw when the shadow attributes change
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            if (child.visibility != View.VISIBLE || child.alpha == 0f) {
                continue
            }
            val depthFactor = (80 * mShadowDepth).toInt()
            canvas.save()
            canvas.translate((child.left + depthFactor).toFloat(),
                    (child.top + depthFactor).toFloat())
            canvas.concat(child.matrix)
            tempShadowRectF.right = child.width.toFloat()
            tempShadowRectF.bottom = child.height.toFloat()
            canvas.drawBitmap(mShadowBitmap, sShadowRect, tempShadowRectF, mShadowPaint)
            canvas.restore()
        }

    }
}