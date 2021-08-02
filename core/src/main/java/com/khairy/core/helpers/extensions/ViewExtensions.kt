package com.khairy.core.helpers.extensions

import android.view.View

fun View.shouldShow(it: Boolean?) {
    visibility = if(it == true)
        View.VISIBLE
    else
        View.GONE
}