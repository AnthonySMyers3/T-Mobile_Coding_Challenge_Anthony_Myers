package com.example.t_mobilecodingchallengeanthonymyers.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO
import com.example.t_mobilecodingchallengeanthonymyers.data.repos.CardRepository
import com.example.t_mobilecodingchallengeanthonymyers.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardViewModel : ViewModel() {
    private var cardRepository = CardRepository

    private var _event: MutableLiveData<Event<List<CardObjectDTO>>> = MutableLiveData()
    val event: LiveData<Event<List<CardObjectDTO>>> get() = _event

    init {
        getCards()
    }

    private fun getCards() {
        _event.value = Event.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _event.postValue(Event.Success(cardRepository.getCards()))
            } catch (e: Exception) {
                _event.postValue(Event.Error())
            }
        }
    }
}