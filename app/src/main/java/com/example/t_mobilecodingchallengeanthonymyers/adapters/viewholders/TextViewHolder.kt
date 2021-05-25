package com.example.t_mobilecodingchallengeanthonymyers.adapters.viewholders

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO
import com.example.t_mobilecodingchallengeanthonymyers.databinding.ItemTextBinding

class TextViewHolder(private val binding: ItemTextBinding) : BaseViewHolder(binding) {

    override fun loadData(card: CardObjectDTO) = with(binding.textTv) {
        text = card.card.value
        textSize = card.card.attributes.font.size.toFloat()
        setTextColor(Color.parseColor(card.card.attributes.textColor))
    }

    companion object {
        fun inflate(parent: ViewGroup) = TextViewHolder(
            ItemTextBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}