package com.development.georgemcl.eliminationdietapp.objects

data class Food(
    val name: String,
    val allowed: FoodAllowed,
    val foodGroup: FoodGroup,
    val description: String = ""
)