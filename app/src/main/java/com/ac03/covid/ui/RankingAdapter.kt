package com.ac03.covid.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac03.covid.databinding.ItemCountryBinding
import com.ac03.covid.domain.Country
import com.ac03.covid.ui.RankingAdapter.ViewHolder
import com.ac03.covid.ui.util.viewBinding

class RankingAdapter : ListAdapter<Country, ViewHolder>(DiffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(viewBinding(parent, ItemCountryBinding::inflate))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = getItem(position)
        holder.bind(country)
    }

    class ViewHolder(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) = with(binding) {
            countryItem.text = country.country
            confirmedItem.text = country.totalConfirmed.toString()
            deathItem.text = country.totalDeaths.toString()
            recoveredItem.text = country.totalRecovered.toString()
        }
    }
}

private object DiffUtilCallback : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.countryCode == newItem.countryCode
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }
}
