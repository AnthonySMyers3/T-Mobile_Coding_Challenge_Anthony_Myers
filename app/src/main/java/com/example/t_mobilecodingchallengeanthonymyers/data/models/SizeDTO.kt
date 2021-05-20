package com.example.t_mobilecodingchallengeanthonymyers.data.models

import com.google.gson.annotations.SerializedName

data class SizeDTO (
    @SerializedName("width")val width: Int,
    @SerializedName("height")val height: Int
)