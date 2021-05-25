package com.example.t_mobilecodingchallengeanthonymyers.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.t_mobilecodingchallengeanthonymyers.adapters.CardAdapter
import com.example.t_mobilecodingchallengeanthonymyers.databinding.LandingPageFragmentBinding

class LandingPageFragment : Fragment() {

    private var _binding: LandingPageFragmentBinding? = null
    private val binding: LandingPageFragmentBinding get() = _binding!!

    private val cardViewModel: CardViewModel by viewModels()

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

        with(binding) {

            cardViewModel.event.observe(viewLifecycleOwner, Observer {
                when (it) {
                    is CardViewModel.Event.Loading -> {
                        loaderPb.isVisible = it.isLoading
                    }
                    is CardViewModel.Event.Success -> {
                        loaderPb.isVisible = it.isLoading
                        binding.contentRv.adapter = CardAdapter(it.data)
                    }
                    is CardViewModel.Event.Error -> {
                        loaderPb.isVisible = it.isLoading
                        binding.couldNotDownloadTv.visibility = TextView.VISIBLE
                    }

                }
            })
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private val TAG = LandingPageFragment::class.java.simpleName
    }
}