package com.development.georgemcl.scdgapsapp.objects

data class Food(
    val name: String,
    val allowed: FoodAllowed,
    val foodGroup: FoodGroup = FoodGroup.Miscellaneous,
    val description: String = ""
)