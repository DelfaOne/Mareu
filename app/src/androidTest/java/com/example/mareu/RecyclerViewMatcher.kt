package com.example.mareu

import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

class RecyclerViewMatcher private constructor(@IdRes private val recyclerViewId: Int) : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {

    override fun describeTo(description: Description?) {
        description?.appendText("with Recyclerview with id: $recyclerViewId ")
    }

    override fun matchesSafely(item: RecyclerView): Boolean {
        return item.id == recyclerViewId
    }

    companion object {
        @JvmStatic fun withRecyclerView(@IdRes recyclerViewId: Int): RecyclerViewMatcher {
            return RecyclerViewMatcher(recyclerViewId)
        }
    }
}