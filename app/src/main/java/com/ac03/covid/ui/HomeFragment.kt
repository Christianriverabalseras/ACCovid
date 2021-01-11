package com.ac03.covid.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ac03.covid.R
import com.ac03.covid.api.CovidApiFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            val api = CovidApiFactory.service.getSummary()
            total_confirmed.text = "Total confirmed: ${api.global.totalConfirmed}"
            total_deaths.text = "Total deaths: ${api.global.totalDeaths}"
            total_recovered.text = "Total recovered: ${api.global.totalRecovered}"
        }
    }
}