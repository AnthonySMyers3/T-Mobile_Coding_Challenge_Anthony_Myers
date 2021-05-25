package com.example.t_mobilecodingchallengeanthonymyers.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t_mobilecodingchallengeanthonymyers.adapters.viewholders.BaseViewHolder
import com.example.t_mobilecodingchallengeanthonymyers.adapters.viewholders.ImageTitleDescViewHolder
import com.example.t_mobilecodingchallengeanthonymyers.adapters.viewholders.TextViewHolder
import com.example.t_mobilecodingchallengeanthonymyers.adapters.viewholders.TitleDescViewHolder
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO
import com.example.t_mobilecodingchallengeanthonymyers.utils.CardType

class CardAdapter(
    private val cardsList: List<CardObjectDTO>
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        CardType.TITLE_DESCRIPTION.ordinal -> TitleDescViewHolder.inflate(parent)
        CardType.IMAGE_TITLE_DESCRIPTION.ordinal -> ImageTitleDescViewHolder.inflate(parent)
        else -> TextViewHolder.inflate(parent)
    }

    override fun getItemCount() = cardsList.size

    override fun getItemViewType(position: Int) = when (cardsList[position].cardType) {
        CardType.TEXT -> CardType.TEXT.ordinal
        CardType.TITLE_DESCRIPTION -> CardType.TITLE_DESCRIPTION.ordinal
        CardType.IMAGE_TITLE_DESCRIPTION -> CardType.IMAGE_TITLE_DESCRIPTION.ordinal
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) = with(holder) {
        loadData(cardsList[position])
    }
}