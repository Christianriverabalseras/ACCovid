package com.ac03.covid.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.ac03.covid.R
import com.ac03.covid.data.server.CovidServiceFactory
import com.ac03.covid.data.server.SummaryData
import com.ac03.covid.databinding.FragmentHomeBinding
import com.ac03.covid.util.viewBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private var summary: SummaryData? = null
    private var isFirstTime: Boolean = true
    private var selectedCountry: String = ""

    private val nFormat = DecimalFormat("#,###,###")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.spMenu.editText?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isFirstTime = false
                selectedCountry = s.toString()
                getDataForCountrySelected()
            }
        })
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
        val data = CovidServiceFactory.service.getSummary()
        this.summary = data
        getDataForCountrySelected()
        binding.globalConfirmed.text = changeFormat(nFormat.format(data.global.totalConfirmed))
        binding.globalDeath.text = changeFormat(nFormat.format(data.global.totalDeaths))
        binding.globalRecovered.text = changeFormat(nFormat.format(data.global.totalRecovered))
    }

    private fun getDataForCountrySelected() {
        val countries = this.summary?.countries
        if(isFirstTime) {
            for (country in countries!!) {
                if(country.country == "Spain") {
                    binding.tvCountryNameCard.setText(R.string.spainCountry)
                    binding.tvConfirmedCases.text = (changeFormat(nFormat.format(country.totalConfirmed).toString()))
                    binding.tvDeathCases.text = (changeFormat(nFormat.format(country.totalDeaths).toString()))
                    binding.tvRecoveredCases.text = (changeFormat(nFormat.format(country.totalRecovered).toString()))
                }
            }
        } else {
            for (country in countries!!) {
                if(country.country == selectedCountry) {
                    binding.tvCountryNameCard.text = selectedCountry
                    binding.tvConfirmedCases.text = (changeFormat(nFormat.format(country.totalConfirmed).toString()))
                    binding.tvDeathCases.text = (changeFormat(nFormat.format(country.totalDeaths).toString()))
                    binding.tvRecoveredCases.text = (changeFormat(nFormat.format(country.totalRecovered).toString()))
                }
            }
        }

    }

    private fun changeFormat(cadena: String?): String {
        return cadena!!.replace(",", ".")
    }

}