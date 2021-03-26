package com.joesamyn.envelope.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @BindingAdapter("android:src")
    @JvmStatic
    fun bindIcon(view: ImageView, id: Int){
        view.setImageResource(id)
    }
}