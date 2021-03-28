package com.ac03.covid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ac03.covid.R
import com.ac03.covid.databinding.ItemCountryBinding
import com.ac03.covid.model.server.Country
import com.ac03.covid.ui.RankingAdapter.ViewHolder
import com.ac03.covid.util.viewBinding

class RankingAdapter(private val countries: (Country) -> Unit) :
    ListAdapter<Country, RankingAdapter.ViewHolder>(DiffUtilCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
//        ViewHolder(viewBinding(parent, ItemCountryBinding::inflate))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = getItem(position)
        holder.bind(country)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCountryBinding.bind(view)

        fun bind(country: Country) = with(binding) {
            this.countryItem.text = country.country
            this.confirmedItem.text = country.totalConfirmed.toString()
            this.deathItem.text = country.totalDeaths.toString()
            this.recoveredItem.text = country.totalRecovered.toString()
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
