package com.example.t_mobilecodingchallengeanthonymyers.adapters.viewholders

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO
import com.example.t_mobilecodingchallengeanthonymyers.databinding.ItemImageTitleDescriptionBinding
import com.example.t_mobilecodingchallengeanthonymyers.utils.loadImage

class ImageTitleDescViewHolder(
    private val binding: ItemImageTitleDescriptionBinding
) : BaseViewHolder(binding) {

    override fun loadData(card: CardObjectDTO) = with(binding) {
        imageTitleTv.text = card.card.title.value
        imageTitleTv.textSize = card.card.title.attributes.font.size.toFloat()
        imageTitleTv.setTextColor(Color.parseColor(card.card.title.attributes.textColor))

        imageDescriptionTv.text = card.card.description.value
        imageDescriptionTv.textSize = card.card.description.attributes.font.size.toFloat()
        imageDescriptionTv.setTextColor(Color.parseColor(card.card.description.attributes.textColor))

        cardImageIv.layoutParams.height = card.card.image.size.height
        cardImageIv.layoutParams.width = card.card.image.size.width
        cardImageIv.requestLayout()
        cardImageIv.loadImage(card.card.image.url)
    }

    companion object {
        fun inflate(parent: ViewGroup) = ImageTitleDescViewHolder(
            ItemImageTitleDescriptionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
}