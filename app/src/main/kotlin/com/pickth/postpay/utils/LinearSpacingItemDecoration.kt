/*
 * Copyright 2017 Yonghoon Kim
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pickth.postpay.utils

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.pickth.postpay.extension.convertDpToPixel

/**
 * Created by yonghoon on 2018-04-05
 * Blog   : http://blog.pickth.com
 */
class LinearSpacingItemDecoration(context: Context, var spacing: Int, val includeEdge: Boolean): RecyclerView.ItemDecoration() {
    init {
        spacing = context.convertDpToPixel(spacing)
    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {

        val position = parent!!.getChildAdapterPosition(view) // item position
        val column = position % 1 // item column

        if (includeEdge) {
            outRect!!.left = spacing - column * spacing // spacing - column * ((1f / spanCount) * spacing)
            outRect.right = (column + 1) * spacing // (column + 1) * ((1f / spanCount) * spacing)
            if (position < 1) {
                outRect.top = spacing * 2
            }
            outRect.bottom = spacing // item bottom
        } else {
            outRect!!.left = column * spacing // column * ((1f / spanCount) * spacing)
            outRect.right = spacing - (column + 1) * spacing // spacing - (column + 1) * ((1f /    spanCount) * spacing)
            if (position >= 1) {
                outRect.top = spacing // item top
            }
        }
    }
}