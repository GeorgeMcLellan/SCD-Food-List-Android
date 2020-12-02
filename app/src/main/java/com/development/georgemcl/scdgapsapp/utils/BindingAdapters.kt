package com.development.georgemcl.scdgapsapp.utils

import android.content.res.ColorStateList
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.development.georgemcl.scdgapsapp.R
import com.development.georgemcl.scdgapsapp.objects.FoodAllowed

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("foodBackgroundColor")
    fun loadItemImage(view: View, allowed: FoodAllowed) {
        view.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(view.context, when(allowed) {
            FoodAllowed.ALLOWED -> R.color.greenTransparent
            FoodAllowed.Warning -> R.color.yellowTransparent
            FoodAllowed.NOT_ALLOWED -> R.color.redTransparent
        }))
    }

    @JvmStatic
    @BindingAdapter("allowedTextColor")
    fun setAllowedTextColor(view: TextView, allowed: FoodAllowed) {
        view.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(view.context, when(allowed) {
            FoodAllowed.ALLOWED -> R.color.greenTextColor
            FoodAllowed.Warning -> R.color.yellowTextColor
            FoodAllowed.NOT_ALLOWED -> R.color.redTextColor
        })))
    }
}