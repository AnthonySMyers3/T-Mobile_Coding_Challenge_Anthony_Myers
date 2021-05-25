package com.example.t_mobilecodingchallengeanthonymyers.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.example.t_mobilecodingchallengeanthonymyers.R
import com.example.t_mobilecodingchallengeanthonymyers.adapters.CardAdapter
import com.example.t_mobilecodingchallengeanthonymyers.databinding.FragmentLandingPageBinding
import com.example.t_mobilecodingchallengeanthonymyers.utils.Event

class LandingPageFragment : Fragment(R.layout.fragment_landing_page) {

    private val cardViewModel: CardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FragmentLandingPageBinding.bind(view).apply {
            cardViewModel.event.observe(viewLifecycleOwner) { event ->
                loaderPb.isVisible = event is Event.Loading
                couldNotDownloadTv.isVisible = event is Event.Error
                if (event is Event.Success) contentRv.adapter = CardAdapter(event.data)
            }
        }
    }
}