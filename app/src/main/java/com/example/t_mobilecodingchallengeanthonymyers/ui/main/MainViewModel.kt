package com.example.t_mobilecodingchallengeanthonymyers.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardsDTO
import com.example.t_mobilecodingchallengeanthonymyers.data.repos.CardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainViewModel: ViewModel() {
    private val _repo: CardRepository by lazy{
        CardRepository()
    }

    lateinit var cardList: CardsDTO

    private var _loading: MutableLiveData<String> = MutableLiveData()
    val loading: LiveData<String> get() = _loading

    init{
        getCards()
    }

    /*
    * This function is called when the viewModel is initialized and will
    * populate the card collection with data retrieved from the API call.
    * The "loading" live data is observed inside of the fragment to check for
    * successful or failed API calls.
    * */
    private fun getCards() = viewModelScope.launch(Dispatchers.IO) {
        withContext(Dispatchers.Main){_loading.postValue("loading")}
        try{
            cardList = _repo.getCards().page
            _loading.postValue("success")
        } catch (e: Exception) {
            _loading.postValue("error")
            Log.d(TAG, e.message.toString())
        }
    }

    companion object{
        private val TAG = MainViewModel::class.java.simpleName
    }
}