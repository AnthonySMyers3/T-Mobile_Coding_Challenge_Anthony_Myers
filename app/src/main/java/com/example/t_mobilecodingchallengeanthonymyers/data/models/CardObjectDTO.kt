package com.example.t_mobilecodingchallengeanthonymyers.data.models

import com.example.t_mobilecodingchallengeanthonymyers.utils.CardType
import com.google.gson.annotations.SerializedName

data class CardObjectDTO(
    @SerializedName("card_type") val cardType: CardType,
    @SerializedName("card") val card: CardDTO
)