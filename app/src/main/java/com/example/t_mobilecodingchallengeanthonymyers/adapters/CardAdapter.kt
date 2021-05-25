package com.example.t_mobilecodingchallengeanthonymyers.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO
import com.example.t_mobilecodingchallengeanthonymyers.databinding.CardItemBinding
import com.example.t_mobilecodingchallengeanthonymyers.utils.CardTypes
import com.example.t_mobilecodingchallengeanthonymyers.utils.loadImage

class CardAdapter(
    private val cardsList: List<CardObjectDTO>
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CardViewHolder(
            CardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int = cardsList.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) =
        with(holder) {
            loadData(cardsList[position])
        }

    class CardViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /*
        * This function binds the data to the views in the item layout. It determines view
        * visibility based on the cardType retrieved from the API call. A shortcoming with this
        * structure is that it relies on consistency of the data received from the API call.
        * Given more time I would include placeholders that would cover unexpected data or null
        * values received from the API call.
        * */
        fun loadData(card: CardObjectDTO) = with(binding) {
            when (card.cardType) {
                CardTypes.TEXT -> {
                    with(binding) {
                        textTv.text = card.card.value
                        textTv.textSize = card.card.attributes.font.size.toFloat()
                        textTv.setTextColor(Color.parseColor(card.card.attributes.textColor))
                        textCard.isVisible = true
                    }
                }
                CardTypes.TITLE_DESCRIPTION -> {
                    with(binding) {
                        titleTv.text = card.card.title.value
                        titleTv.textSize = card.card.title.attributes.font.size.toFloat()
                        titleTv.setTextColor(Color.parseColor(card.card.title.attributes.textColor))

                        descriptionTv.text = card.card.description.value
                        descriptionTv.textSize =
                            card.card.description.attributes.font.size.toFloat()
                        descriptionTv.setTextColor(Color.parseColor(card.card.description.attributes.textColor))
                        titleDescriptionCard.isVisible = true
                    }
                }
                CardTypes.IMAGE_TITLE_DESCRIPTION -> {
                    with(binding) {
                        imageTitleTv.text = card.card.title.value
                        imageTitleTv.textSize =
                            card.card.title.attributes.font.size.toFloat()
                        imageTitleTv.setTextColor(Color.parseColor(card.card.title.attributes.textColor))

                        imageDescriptionTv.text = card.card.description.value
                        imageDescriptionTv.textSize =
                            card.card.description.attributes.font.size.toFloat()
                        imageDescriptionTv.setTextColor(Color.parseColor(card.card.description.attributes.textColor))

                        cardImageIv.layoutParams.height = card.card.image.size.height
                        cardImageIv.layoutParams.width = card.card.image.size.width
                        cardImageIv.requestLayout()
                        cardImageIv.loadImage(card.card.image.url)

                        imageTitleDescriptionCard.visibility = ConstraintLayout.VISIBLE
                    }
                }
            }
        }
    }

}