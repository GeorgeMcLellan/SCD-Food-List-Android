package com.development.georgemcl.scdgapsapp.utils

import com.development.georgemcl.scdgapsapp.objects.FoodGroup

fun FoodGroup.friendlyName(): String {
    return when(this) {
        FoodGroup.Dairy -> "Dairy"
        FoodGroup.Legumes -> "Legumes"
        FoodGroup.AnimalProteins -> "Animal Proteins"
        FoodGroup.FatsAndOils -> "Fats & Oils"
        FoodGroup.Grains -> "Grains"
        FoodGroup.Starch -> "Starches"
        FoodGroup.NutsAndSeeds -> "Nuts & Seeds"
        FoodGroup.Vegetables -> "Vegetables"
        FoodGroup.NightshadeVegetables -> "Nightshade Vegetables"
        FoodGroup.Fruit -> "Fruits"
        FoodGroup.Spices -> "Spices"
        FoodGroup.Sweeteners -> "Sweeteners"
        FoodGroup.Herbs -> "Herbs"
        FoodGroup.Miscellaneous -> "Miscellaneous"
        FoodGroup.Drinks -> "Drinks"
        FoodGroup.Additives -> "Additives"
        FoodGroup.Sauces -> "Sauces"
    }
}

fun foodGroupFriendlyName(foodGroup: FoodGroup): String {
    return foodGroup.friendlyName()
}