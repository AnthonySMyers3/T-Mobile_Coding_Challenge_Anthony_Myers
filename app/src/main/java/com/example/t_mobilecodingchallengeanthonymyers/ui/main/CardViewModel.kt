package com.example.t_mobilecodingchallengeanthonymyers.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO
import com.example.t_mobilecodingchallengeanthonymyers.data.repos.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class CardViewModel : ViewModel() {
    private var cardRepository = CardRepository

    private var _cardsList: MutableLiveData<List<CardObjectDTO>> = MutableLiveData()
    val cardsList: LiveData<List<CardObjectDTO>> get() = _cardsList


    private var _event: MutableLiveData<Event> = MutableLiveData()
    val event: LiveData<Event> get() = _event

    init {
        getCards()
    }

    /*
    * This function is called when the viewModel is initialized and will
    * populate the card collection with data retrieved from the API call.x
    * The "loading" live data is observed inside of the fragment to check for
    * successful or failed API calls.
    * */
    private fun getCards() {
        _event.value = Event.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _cardsList.postValue(cardRepository.getCards())
                _event.postValue(Event.Success(data = cardRepository.getCards()))
            } catch (e: Exception) {
                _event.postValue(Event.Error())
                Log.d(TAG, e.message.toString())
            }
        }
    }

    companion object {
        private val TAG = CardViewModel::class.java.simpleName
    }

    sealed class Event {
        data class Loading(val isLoading: Boolean = true) : Event()
        data class Error(val isLoading: Boolean = false) : Event()
        data class Success(val isLoading: Boolean = false, val data: List<CardObjectDTO>) : Event()
    }
}