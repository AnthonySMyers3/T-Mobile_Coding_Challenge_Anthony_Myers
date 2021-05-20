package com.example.t_mobilecodingchallengeanthonymyers.data.models

import com.google.gson.annotations.SerializedName

data class CardsDTO (
    @SerializedName("cards")val cards: List<CardObjectDTO>
)