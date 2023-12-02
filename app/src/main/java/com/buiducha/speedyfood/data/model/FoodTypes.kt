package com.buiducha.speedyfood.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.buiducha.speedyfood.R

enum class FoodTypes(
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    All(
        label = R.string.all,
        icon = R.drawable.all
    ),
    Rice(
        label = R.string.rice,
        icon = R.drawable.rice
    ),
    Soup(
        label = R.string.soup,
        icon = R.drawable.soup
    ),
    Drink(
        label = R.string.drink,
        icon = R.drawable.drink
    ),
    FastFood(
        label = R.string.fast_food,
        icon = R.drawable.fast_food
    ),
    Snack(
        label = R.string.snack,
        icon = R.drawable.snack
    ),
    Meat(
        label = R.string.meat,
        icon = R.drawable.meat
    )
}