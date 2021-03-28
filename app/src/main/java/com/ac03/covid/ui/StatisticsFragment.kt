package com.ac03.covid.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ac03.covid.R
import com.ac03.covid.databinding.FragmentStatisticsBinding
import com.ac03.covid.model.server.CovidRepository
import com.ac03.covid.util.viewBinding
import androidx.lifecycle.Observer
import com.ac03.covid.model.server.Country
import com.ac03.covid.ui.StatisticsViewModel.UiModel.*
import kotlinx.coroutines.delay

class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val binding by viewBinding(FragmentStatisticsBinding::bind)
    private lateinit var viewModel: StatisticsViewModel
//    private val viewModel by viewModels<StatisticsViewModel>()

    private lateinit var adapter: RankingAdapter


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        viewModel = getViewModel { StatisticsViewModel(CovidRepository(requireActivity().app)) }
//
//        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
//        val recycler = binding.countryRecyclerView
//        val results = CovidRepository.findCountries
//
//        recycler.adapter = RankingAdapter(listOf())
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel { StatisticsViewModel(CovidRepository(requireActivity().app)) }
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
    }
    private fun updateUi(model: StatisticsViewModel.UiModel) {
        when (model) {
            is Loading -> TODO()
            is Content -> showRecycler(model)
            is Error -> Toast.makeText(context, model.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showRecycler(model: Content) {
        val results = model.data.countries

        var newResults = listOf<Country>()
        for (country in results) {
            newResults = listOf(country) + newResults
        }
//        binding.countryRecyclerView.adapter = RankingAdapter(newResults)
//        binding.countryRecyclerView.adapter.submitList(newResults)
        adapter.submitList(newResults)
    }


}