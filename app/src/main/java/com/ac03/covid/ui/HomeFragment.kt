package com.ac03.covid.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.ac03.covid.R
import com.ac03.covid.data.server.CovidServiceFactory
import com.ac03.covid.databinding.FragmentHomeBinding
import com.ac03.covid.util.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            getCountries()
            globalData()
        }
    }

    private suspend fun getCountries() {
        val countries = CovidServiceFactory.service.getCountries()
        val countriesNames: ArrayList<String> = arrayListOf()
        countries.forEach { countryItem -> countriesNames.add(countryItem.country) }
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_sp_menu, countriesNames.sorted())
        (binding.spMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private suspend fun globalData() {
        val summary = CovidServiceFactory.service.getSummary()
        binding.globalConfirmed.text = "Total confirmados: ${ summary.global.totalConfirmed }"
        binding.globalDeath.text = "Total muertes: ${ summary.global.totalDeaths }"
        binding.globalRecovered.text = "Total recuperados: ${ summary.global.totalRecovered }"
    }
}