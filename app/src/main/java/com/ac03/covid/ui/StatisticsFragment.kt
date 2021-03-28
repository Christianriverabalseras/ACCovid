package com.ac03.covid.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ac03.covid.R
import com.ac03.covid.databinding.FragmentStatisticsBinding
import com.ac03.covid.model.server.CovidRepository
import com.ac03.covid.ui.StatisticsViewModel.UiModel.*
import com.ac03.covid.util.viewBinding

class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val binding by viewBinding(FragmentStatisticsBinding::bind)
    private lateinit var viewModel: StatisticsViewModel

    private val rankingAdapter by lazy { RankingAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = getViewModel { StatisticsViewModel(CovidRepository(requireActivity().app)) }
        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
        setupList()
    }

    private fun setupList() = with(binding.countryRecyclerView) {
        // TODO: realmente, solo te faltaba el asignar el layoutManager al recycler view,
        // y sobraba el pasarle una lista vacía al inicializar el adapter.
        adapter = rankingAdapter
        layoutManager = LinearLayoutManager(context)
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

        // TODO: Cuál es la finalidad de esto?
//        var newResults = listOf<Country>()
//        for (country in results) {
//            newResults = listOf(country) + newResults
//        }

        rankingAdapter.submitList(results)
    }
}