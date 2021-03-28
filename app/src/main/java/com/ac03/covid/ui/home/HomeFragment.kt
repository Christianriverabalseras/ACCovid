package com.ac03.covid.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ac03.covid.CovidApplication
import com.ac03.covid.R
import com.ac03.covid.data.database.RoomDataSource
import com.ac03.covid.data.repository.CovidRepository
import com.ac03.covid.data.server.CovidRemoteDataSource
import com.ac03.covid.databinding.FragmentHomeBinding
import com.ac03.covid.domain.Country
import com.ac03.covid.domain.SummaryData
import com.ac03.covid.ui.getViewModel
import com.ac03.covid.ui.home.HomeViewModel.UiModel
import com.ac03.covid.ui.home.HomeViewModel.UiModel.*
import com.ac03.covid.usecases.GetSummaryData
import com.ac03.covid.ui.util.changeFormat
import com.ac03.covid.ui.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val viewModel by viewModels<HomeViewModel>()

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
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }

    private fun updateUi(model: UiModel) {
        when (model) {
            is Loading -> TODO()
            is Content -> showGlobalData(model)
            is Error -> Toast.makeText(context, model.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showGlobalData(model: Content) = with(binding) {
        val countriesNames = mutableListOf<String>()
        model.data.countries.forEach { countryItem -> countriesNames.add(countryItem.country) }
        val adapter =
            ArrayAdapter(requireContext(), R.layout.list_item_sp_menu, countriesNames.sorted())
        (spMenu.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        summary = model.data
        getDataForCountrySelected()
        globalConfirmed.text = nFormat.format(model.data.global.totalConfirmed).changeFormat()
        globalDeath.text = nFormat.format(model.data.global.totalDeaths).changeFormat()
        globalRecovered.text = nFormat.format(model.data.global.totalRecovered).changeFormat()
    }

    private fun getDataForCountrySelected() {
        val countrySelected = when {
            isFirstTime -> summary?.countries?.find { it.country == resources.getString(R.string.spain_country_name) }
            else -> summary?.countries?.first { it.country == selectedCountry }
        }
        countrySelected?.let { showCountryData(it) }
    }

    private fun showCountryData(country: Country) = with(binding) {
        tvCountryNameCard.text = country.country
        tvConfirmedCases.text = nFormat.format(country.totalConfirmed).changeFormat()
        tvDeathCases.text = nFormat.format(country.totalDeaths).changeFormat()
        tvRecoveredCases.text = nFormat.format(country.totalRecovered).changeFormat()
    }
}
