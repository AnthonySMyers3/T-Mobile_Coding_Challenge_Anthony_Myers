package com.example.t_mobilecodingchallengeanthonymyers.utils

import com.google.gson.annotations.SerializedName

enum class CardType(val value: String) {
    @SerializedName("text")
    TEXT("text"),

    @SerializedName("title_description")
    TITLE_DESCRIPTION("title_description"),

    @SerializedName("image_title_description")
    IMAGE_TITLE_DESCRIPTION("image_title_description"),
}