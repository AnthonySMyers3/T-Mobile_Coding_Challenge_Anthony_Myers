package com.example.t_mobilecodingchallengeanthonymyers.data.repos

import com.example.t_mobilecodingchallengeanthonymyers.data.models.PageDTO
import com.example.t_mobilecodingchallengeanthonymyers.data.remote.network.CardManager

class CardRepository {
    private val cardManager: CardManager by lazy{
        CardManager()
    }

    suspend fun getCards(): PageDTO = cardManager.getCards()
}