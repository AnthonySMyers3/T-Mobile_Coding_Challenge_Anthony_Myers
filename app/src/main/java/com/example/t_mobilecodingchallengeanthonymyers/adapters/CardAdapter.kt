package com.example.t_mobilecodingchallengeanthonymyers.adapters

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardsDTO
import com.example.t_mobilecodingchallengeanthonymyers.databinding.CardItemBinding

class CardAdapter (
    private val cardCollection: CardsDTO
): RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardAdapter.CardViewHolder =
        CardViewHolder(
            CardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun getItemCount(): Int = cardCollection.cards.size

    override fun onBindViewHolder(holder: CardAdapter.CardViewHolder, position: Int): Unit =
        with(holder){
            val card = cardCollection.cards[position]
            loadData(card)
        }

    class CardViewHolder(val binding: CardItemBinding):
            RecyclerView.ViewHolder(binding.root){
        private val TAG = CardViewHolder::class.java.canonicalName
        private val foreignCardType = "Card type received from API call was unknown."

        /*
        * This function binds the data to the views in the item layout. It determines view
        * visibility based on the cardType retrieved from the API call. A shortcoming with this
        * structure is that it relies on consistency of the data received from the API call.
        * Given more time I would include placeholders that would cover unexpected data or null
        * values received from the API call.
        * */
        fun loadData(card: CardObjectDTO) = with(binding){
            when(card.cardType){
                "text" -> {
                    binding.textTv.text = card.card.value
                    binding.textTv.textSize = card.card.attributes.font.size.toFloat()
                    binding.textTv.setTextColor(Color.parseColor(card.card.attributes.textColor))

                    binding.textCard.visibility = ConstraintLayout.VISIBLE
                }
                "title_description" -> {
                    binding.titleTv.text = card.card.title.value
                    binding.titleTv.textSize = card.card.title.attributes.font.size.toFloat()
                    binding.titleTv.setTextColor(Color.parseColor(card.card.title.attributes.textColor))

                    binding.descriptionTv.text = card.card.description.value
                    binding.descriptionTv.textSize = card.card.description.attributes.font.size.toFloat()
                    binding.descriptionTv.setTextColor(Color.parseColor(card.card.description.attributes.textColor))

                    binding.titleDescriptionCard.visibility = ConstraintLayout.VISIBLE
                }
                "image_title_description" -> {
                    binding.imageTitleTv.text = card.card.title.value
                    binding.imageTitleTv.textSize = card.card.title.attributes.font.size.toFloat()
                    binding.imageTitleTv.setTextColor(Color.parseColor(card.card.title.attributes.textColor))

                    binding.imageDescriptionTv.text = card.card.description.value
                    binding.imageDescriptionTv.textSize = card.card.description.attributes.font.size.toFloat()
                    binding.imageDescriptionTv.setTextColor(Color.parseColor(card.card.description.attributes.textColor))

                    binding.cardImageIv.layoutParams.height = card.card.image.size.height
                    binding.cardImageIv.layoutParams.width = card.card.image.size.width
                    binding.cardImageIv.requestLayout()

                    Glide.with(binding.cardImageIv)
                        .load(card.card.image.url)
                        .listener(object: RequestListener<Drawable> {
                            override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
                                return false
                            }
                            override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                                binding.cardImageIv.visibility = ImageView.VISIBLE
                                binding.loadingImagePb.visibility = ProgressBar.INVISIBLE
                                return false
                            }
                        })
                        .into(binding.cardImageIv)

                    binding.imageTitleDescriptionCard.visibility = ConstraintLayout.VISIBLE
                }
                else -> {
                    Log.d(TAG, foreignCardType)}
            }
        }
    }

}