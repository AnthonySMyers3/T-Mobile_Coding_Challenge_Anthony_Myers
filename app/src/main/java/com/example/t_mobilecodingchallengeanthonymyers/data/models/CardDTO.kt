package com.example.t_mobilecodingchallengeanthonymyers.data.models

import com.google.gson.annotations.SerializedName

data class CardDTO (
    @SerializedName("value")val value: String,
    @SerializedName("attributes")val attributes: AttributesDTO,
    @SerializedName("image")val image: ImageDTO,
    @SerializedName("description")val description: DescriptionDTO,
    @SerializedName("title")val title: TitleDTO
)