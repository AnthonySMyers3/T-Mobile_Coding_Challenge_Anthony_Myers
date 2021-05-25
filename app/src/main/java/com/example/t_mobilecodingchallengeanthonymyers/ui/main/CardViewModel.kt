package com.example.t_mobilecodingchallengeanthonymyers.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO
import com.example.t_mobilecodingchallengeanthonymyers.data.repos.CardRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.*
import java.lang.Exception

class CardViewModel(application: Application) : AndroidViewModel(application) {
    private var cardRepository = CardRepository
    private var appContext = application.applicationContext

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
                writeCachedSession()
            } catch (e: Exception) {
                readCachedSession()
                Log.d(TAG, e.message.toString())
            }
        }
    }

    private fun writeCachedSession() {
        val cacheFile = File(appContext.cacheDir, CACHED_FILE)
        val fw = FileWriter(cacheFile.absoluteFile)
        val bw = BufferedWriter(fw)
        bw.write(Gson().toJson(cardsList.value))
        bw.close()
    }

    private fun readCachedSession() {
        try {
            val cacheFile = File(appContext.cacheDir, CACHED_FILE)
            val fr = FileReader(cacheFile.absoluteFile)
            val br = BufferedReader(fr)
            val cachedData = Gson().fromJson(br.readText(), Array<CardObjectDTO>::class.java).toList()
            _cardsList.postValue(cachedData)
            _event.postValue(Event.Success(data = cachedData))
            br.close()
        } catch (e: Exception) {
            _event.postValue(Event.Error())
            Log.d(TAG, e.message.toString())
        }
    }

    companion object {
        private val TAG = CardViewModel::class.java.simpleName
        private const val CACHED_FILE = "CachedSession"
    }

    sealed class Event {
        data class Loading(val isLoading: Boolean = true) : Event()
        data class Error(val isLoading: Boolean = false) : Event()
        data class Success(val isLoading: Boolean = false, val data: List<CardObjectDTO>) : Event()
    }
}