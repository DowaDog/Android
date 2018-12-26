package com.takhyungmin.dowadog.contents

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet





class PositionRecyclerView : RecyclerView {
    var mScrollPosition = 0
    constructor(context : Context) : super(context){

    }

    constructor(context : Context, attrs : AttributeSet) : super(context, attrs){

    }

    constructor(context : Context, attrs : AttributeSet, defStyle : Int) : super(context, attrs, defStyle){

    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val layoutManager = layoutManager
        if (layoutManager != null && layoutManager is LinearLayoutManager) {
            mScrollPosition = layoutManager.findFirstVisibleItemPosition()
        }
        val newState = PositionRecyclerView.SavedState(superState!!)
        newState.mScrollPosition = mScrollPosition
        return newState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        super.onRestoreInstanceState(state)
        if (state != null && state is PositionRecyclerView.SavedState) {
            mScrollPosition = state.mScrollPosition
            val layoutManager = layoutManager
            if (layoutManager != null) {
                val count = layoutManager.childCount
                if (mScrollPosition != RecyclerView.NO_POSITION && mScrollPosition < count) {
                    layoutManager.scrollToPosition(mScrollPosition)
                }
            }
        }
    }

    internal class SavedState : android.view.View.BaseSavedState {
        var mScrollPosition: Int = 0

        constructor(ins: Parcel) : super(ins) {
            mScrollPosition = ins.readInt()
        }

        constructor(superState: Parcelable) : super(superState) {}

        override fun writeToParcel(dest: Parcel, flags: Int) {
            super.writeToParcel(dest, flags)
            dest.writeInt(mScrollPosition)
        }

        companion object {
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(ins: Parcel): SavedState {
                    return SavedState(ins)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }
}