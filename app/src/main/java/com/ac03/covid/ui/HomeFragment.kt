package com.ac03.covid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.ac03.covid.R
import com.ac03.covid.databinding.FragmentHomeBinding
import com.ac03.covid.api.CovidApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            getCountries()
            globalData()
        }
    }

    private suspend fun getCountries() {
        val countries = CovidApiFactory.service.getCountries()
        val countriesNames: ArrayList<String> = arrayListOf()
        countries.forEach { countryItem -> countriesNames.add(countryItem.country) }
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_sp_menu, countriesNames.sorted())
        (binding.spMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private suspend fun globalData() {
        val summary = CovidApiFactory.service.getSummary()
        binding.globalConfirmed.text = "Total confirmados: ${ summary.global.totalConfirmed }"
        binding.globalDeath.text = "Total muertes: ${ summary.global.totalDeaths }"
        binding.globalRecovered.text = "Total recuperados: ${ summary.global.totalRecovered }"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}