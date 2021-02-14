package com.ac03.covid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.ac03.covid.R
import com.ac03.covid.data.server.CovidServiceFactory
import com.ac03.covid.databinding.FragmentHomeBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}