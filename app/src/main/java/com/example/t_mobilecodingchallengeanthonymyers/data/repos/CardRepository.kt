package com.example.t_mobilecodingchallengeanthonymyers.data.repos

import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO
import com.example.t_mobilecodingchallengeanthonymyers.data.remote.network.CardManager

object CardRepository {
    private val cardManager: CardManager by lazy{
        CardManager()
    }

    suspend fun getCards(): List<CardObjectDTO> = cardManager.getCards().page.cards
}