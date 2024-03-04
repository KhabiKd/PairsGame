package com.example.pairsgame.data

import androidx.annotation.DrawableRes

data class Item (
    val id: Int,
    @DrawableRes val imgSrc: Int,
    var checked: Boolean = false
)