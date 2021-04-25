package com.ac03.covid.ui.statistics

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ac03.covid.R
import com.ac03.covid.databinding.FragmentStatisticsBinding
import com.ac03.covid.ui.RankingAdapter
import com.ac03.covid.ui.statistics.StatisticsViewModel.UiModel.*
import com.ac03.covid.ui.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    private val binding by viewBinding(FragmentStatisticsBinding::bind)
    private val viewModel by viewModels<StatisticsViewModel>()

    private val rankingAdapter by lazy { RankingAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.model.observe(viewLifecycleOwner, Observer(::updateUi))
        setupList()
    }

    private fun setupList() = with(binding.countryRecyclerView) {
        adapter = rankingAdapter
        layoutManager = LinearLayoutManager(context)
    }

    private fun updateUi(model: StatisticsViewModel.UiModel) {

        binding.progress.visibility = if (model is Loading) View.VISIBLE else View.GONE

        when (model) {
            is Content -> showRecycler(model)
            is Error -> Toast.makeText(context, model.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun showRecycler(model: Content) {
        val results = model.data
        rankingAdapter.submitList(results)
    }
}