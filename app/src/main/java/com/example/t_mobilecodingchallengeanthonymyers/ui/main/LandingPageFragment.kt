package com.example.t_mobilecodingchallengeanthonymyers.ui.main

import android.content.Context
import android.content.SharedPreferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.t_mobilecodingchallengeanthonymyers.R
import com.example.t_mobilecodingchallengeanthonymyers.adapters.CardAdapter
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardsDTO
import com.example.t_mobilecodingchallengeanthonymyers.databinding.LandingPageFragmentBinding
import com.google.gson.Gson


class LandingPageFragment : Fragment() {
    private var _binding: LandingPageFragmentBinding? = null
    private val binding: LandingPageFragmentBinding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LandingPageFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            binding.loaderPb.visibility = ProgressBar.INVISIBLE
            if (it == "success") {
                onSuccess(sharedPref)
            } else if (it == "error") {
                onError(sharedPref)
            }
        })
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }


    /*
    * The data received by the API call gets stored into shared preferences
    * to be accessible for a session that is not connected to the internet.
    * I would consider restructuring this to either cache this information or
    * store inside of a RoomDb.
    * */
    private fun onSuccess(sharedPref: SharedPreferences){
        binding.contentRv.adapter = CardAdapter(viewModel.cardList!!)

        with(sharedPref.edit()) {
            putString(
                getString(R.string.previous_card_instance),
                Gson().toJson(viewModel.cardList)
            )
            apply()
        }
    }

    /*
    * One shortcoming of having the data stored in shared preferences is that the
    * images are still fetched remotely. Converting the images into a byte array and
    * storing in cache or RoomDb could be a possible solution.
    * */
    private fun onError(sharedPref: SharedPreferences){
        val prevSession =
            sharedPref.getString(getString(R.string.previous_card_instance), null)
        if (prevSession != null) {
            binding.contentRv.adapter =
                CardAdapter(Gson().fromJson(prevSession, CardsDTO::class.java))
        }
    }
}