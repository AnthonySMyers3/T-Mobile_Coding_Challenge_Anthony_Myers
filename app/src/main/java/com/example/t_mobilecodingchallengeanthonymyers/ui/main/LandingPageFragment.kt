package com.example.t_mobilecodingchallengeanthonymyers.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.t_mobilecodingchallengeanthonymyers.adapters.CardAdapter
import com.example.t_mobilecodingchallengeanthonymyers.data.models.CardObjectDTO
import com.example.t_mobilecodingchallengeanthonymyers.databinding.LandingPageFragmentBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*
import java.lang.Exception


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
                        writeCachedSession()
                    }
                    is CardViewModel.Event.Error -> {
                        loaderPb.isVisible = it.isLoading
                        readCachedSession()
                    }

                }
            })
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun writeCachedSession() {
        val cacheFile = File(context?.cacheDir, CACHED_FILE)
        val fw = FileWriter(cacheFile.absoluteFile)
        val bw = BufferedWriter(fw)
        bw.write(Gson().toJson(cardViewModel.cardsList.value))
        bw.close()
    }

    private fun readCachedSession() {
        try {
            val cacheFile = File(context?.cacheDir, CACHED_FILE)
            val fr = FileReader(cacheFile.absoluteFile)
            val br = BufferedReader(fr)
            val listType = object : TypeToken<List<CardObjectDTO>>() {}.type
            binding.contentRv.adapter = CardAdapter(Gson().fromJson(br.readText(), listType))
            br.close()
        } catch (e: Exception) {
            binding.couldNotDownloadTv.visibility = TextView.VISIBLE
            Log.d(TAG, e.message.toString())
        }
    }

    companion object {
        private val TAG = LandingPageFragment::class.java.simpleName
        private const val CACHED_FILE = "CachedSession"
    }
}