package com.example.t_mobilecodingchallengeanthonymyers.data.models

import com.google.gson.annotations.SerializedName

data class DescriptionDTO (
    @SerializedName("value")val value: String,
    @SerializedName("attributes")val attributes: AttributesDTO
)