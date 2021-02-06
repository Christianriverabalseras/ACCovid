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
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            getCountries()
            globalData()
        }
    }

    private suspend fun getCountries() {
        val api = CovidApiFactory.service.getCountries()

        val countries: ArrayList<String> = arrayListOf<String>()
        for(country in api) {
            countries.add(country.Country)
        }

        countries.sort()

        val adapter = ArrayAdapter(requireContext(), R.layout.list_item_sp_menu, countries)
        (binding.spMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

    }

    private suspend fun globalData() {
        val api = CovidApiFactory.service.getSummary()
        binding.globalConfirmed.text = "Total confirmados: ${ api.global.TotalConfirmed }"
        binding.globalDeath.text = "Total muertes: ${ api.global.TotalDeaths }"
        binding.globalRecovered.text = "Total recuperados: ${ api.global.TotalRecovered }"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}