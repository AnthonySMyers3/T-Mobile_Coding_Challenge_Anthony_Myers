package com.example.t_mobilecodingchallengeanthonymyers.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO

abstract class BaseViewHolder(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    /*
    * This function binds the data to the views in the item layout. It determines view
    * visibility based on the cardType retrieved from the API call. A shortcoming with this
    * structure is that it relies on consistency of the data received from the API call.
    * Given more time I would include placeholders that would cover unexpected data or null
    * values received from the API call.
    * */
    abstract fun loadData(card: CardObjectDTO)
}