package com.example.t_mobilecodingchallengeanthonymyers.data.models

import com.google.gson.annotations.SerializedName

data class TitleDTO (
    @SerializedName("value")val value: String,
    @SerializedName("attributes")val attributes: AttributesDTO
)