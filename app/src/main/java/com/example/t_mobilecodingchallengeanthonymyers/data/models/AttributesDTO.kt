package com.example.t_mobilecodingchallengeanthonymyers.data.models

import com.google.gson.annotations.SerializedName

data class AttributesDTO (
    @SerializedName("text_color")val textColor: String,
    @SerializedName("font")val font: FontDTO
)