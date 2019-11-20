package com.koa.tvmaze.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by ANTHONY KOUEIK on 7/17/2019.
 * Email: anthony.koueik@gmail.com
 *
 * RecyclerView Item Spacing for Gird Layout
 */
class SpacesItemDecoration(val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {


        // Top Item I dont want margin for it, only the bottom to match the design i Need
        if (parent.getChildLayoutPosition(view) == 0
        ) {
            outRect.top = 0
            outRect.left = 0
            outRect.right = 0
            outRect.bottom = space

        } else {
            // adding margin to everything but the top
            outRect.top = 0
            outRect.left = space
            outRect.right = space
            outRect.bottom = space
        }
    }
}