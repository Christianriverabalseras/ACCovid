package com.ac03.covid.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ac03.covid.R
import com.ac03.covid.databinding.FragmentStatisticsBinding
import com.ac03.covid.model.server.CovidRepository
import com.ac03.covid.util.viewBinding
import androidx.lifecycle.Observer
import com.ac03.covid.model.server.Country
import kotlinx.coroutines.delay

class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val binding by viewBinding(FragmentStatisticsBinding::bind)
    private lateinit var viewModel: StatisticsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel { StatisticsViewModel(CovidRepository(requireActivity().app)) }

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
//        val recycler = binding.countryRecyclerView
//        val results = CovidRepository.findCountries

//        recycler.adapter = RankingAdapter(listOf())
    }
    private fun updateUi(model: StatisticsViewModel.UiModel) {
        when (model) {
            is StatisticsViewModel.UiModel.Loading -> TODO()
            is StatisticsViewModel.UiModel.Content -> showRecycler(model)
            is StatisticsViewModel.UiModel.Error -> Toast.makeText(context, model.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showRecycler(model: StatisticsViewModel.UiModel.Content) {
        val results = model.data.countries

        var newResults = listOf<Country>()
        for (country in results) {
            newResults = listOf(country) + newResults
            binding.countryRecyclerView.adapter = RankingAdapter(newResults)
        }
    }


}