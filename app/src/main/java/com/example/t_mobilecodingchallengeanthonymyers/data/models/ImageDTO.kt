package com.example.t_mobilecodingchallengeanthonymyers.data.models

import com.google.gson.annotations.SerializedName

data class ImageDTO (
    @SerializedName("url")val url: String,
    @SerializedName("size")val size: SizeDTO
)