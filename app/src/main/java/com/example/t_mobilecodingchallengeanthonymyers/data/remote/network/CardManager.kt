package com.example.t_mobilecodingchallengeanthonymyers.data.remote.network

import com.example.t_mobilecodingchallengeanthonymyers.data.models.PageDTO
import retrofit2.http.GET

class CardManager {
    private val service: CardService
    private val retrofit = RetrofitService.providesRetrofitService()

    init{
        service = retrofit.create(CardService::class.java)
    }

    suspend fun getCards() = service.getCards()

    /*
    * This interface can be expanded if further functionality is needed.
    * It would include different queries to fetch data from the endpoint. For
    * the purpose of this application, there is only one request that is needed.
    * */
    interface CardService{
        @GET("/test/home")
        suspend fun getCards(): PageDTO
    }
}