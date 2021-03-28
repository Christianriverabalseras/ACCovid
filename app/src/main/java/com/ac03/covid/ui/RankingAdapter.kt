package com.ac03.covid.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ac03.covid.databinding.ItemCountryBinding
import com.ac03.covid.model.server.Country
import com.ac03.covid.ui.RankingAdapter.ViewHolder
import com.ac03.covid.util.viewBinding

// TODO: aquí no necesitas pasarle la list de paises en el constructor, ya que eso lo hacemos con
// el método submitList en el fragment
class RankingAdapter : ListAdapter<Country, ViewHolder>(DiffUtilCallback) {

    // TODO: esto estaba bien, lo único que te faltaba era al ViewHolder pasarle el binding, en lugar de la vista
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
