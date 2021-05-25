package com.example.t_mobilecodingchallengeanthonymyers.adapters.viewholders

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO
import com.example.t_mobilecodingchallengeanthonymyers.databinding.ItemTitleDescriptionBinding

class TitleDescViewHolder(
    private val binding: ItemTitleDescriptionBinding
) : BaseViewHolder(binding) {

    override fun loadData(card: CardObjectDTO) = with(binding) {
        titleTv.text = card.card.title.value
        titleTv.textSize = card.card.title.attributes.font.size.toFloat()
        titleTv.setTextColor(Color.parseColor(card.card.title.attributes.textColor))

        descriptionTv.text = card.card.description.value
        descriptionTv.textSize =
            card.card.description.attributes.font.size.toFloat()
        descriptionTv.setTextColor(Color.parseColor(card.card.description.attributes.textColor))
    }

    companion object {
        fun inflate(parent: ViewGroup) = TitleDescViewHolder(
            ItemTitleDescriptionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}