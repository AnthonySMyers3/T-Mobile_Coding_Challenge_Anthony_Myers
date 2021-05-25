package com.example.t_mobilecodingchallengeanthonymyers

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation

class CardActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Navigation.findNavController(this, R.id.nav_host_fragment)
            .setGraph(R.navigation.nav_graph)
    }
}