package com.example.t_mobilecodingchallengeanthonymyers.data.models

import com.google.gson.annotations.SerializedName

data class CardObjectDTO(
    @SerializedName("card_type")val cardType: String,
    @SerializedName("card")val card: CardDTO
)