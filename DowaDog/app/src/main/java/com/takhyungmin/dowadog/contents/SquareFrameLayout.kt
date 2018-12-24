package com.takhyungmin.dowadog.contents

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class SquareFrameLayout : FrameLayout {
    constructor(context : Context) : super(context){
    }

    constructor(context : Context, attrs : AttributeSet) : super(context, attrs){
    }

    constructor(context : Context, attrs : AttributeSet, defStyle : Int) : super(context, attrs, defStyle) {
    }

    constructor(context : Context, attrs : AttributeSet, defStyle : Int, defStyleRes : Int) : super(context, attrs, defStyle, defStyleRes) {
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        if (widthSize == 0 && heightSize == 0) {
            // If there are no constraints on size, let FrameLayout measure
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)

            // Now use the smallest of the measured dimensions for both dimensions
            val minSize = Math.min(measuredWidth, measuredHeight)
            setMeasuredDimension(minSize, minSize)
            return
        }

        var size = 0

        if (widthSize == 0 || heightSize == 0) {
            // If one of the dimensions has no restriction on size, set both dimensions to be the
            // on that does
            size = Math.max(widthSize, heightSize)
        } else {
            // Both dimensions have restrictions on size, set both dimensions to be the
            // smallest of the two
            size = Math.min(widthSize, heightSize)
        }

        val newMeasureSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)

        super.onMeasure(newMeasureSpec, newMeasureSpec)

    }
}