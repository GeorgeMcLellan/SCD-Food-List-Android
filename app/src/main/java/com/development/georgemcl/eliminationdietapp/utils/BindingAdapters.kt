package com.development.georgemcl.eliminationdietapp.utils

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.development.georgemcl.eliminationdietapp.R
import com.development.georgemcl.eliminationdietapp.objects.FoodAllowed

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("foodBackgroundColor")
    fun loadItemImage(view: View, allowed: FoodAllowed) {
        view.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, when(allowed) {
            FoodAllowed.Allowed -> R.color.greenTransparent
            FoodAllowed.Warning -> R.color.yellowTransparent
            FoodAllowed.Avoid -> R.color.redTransparent
        }))
    }
}