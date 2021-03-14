package com.ac03.covid.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ac03.covid.R
import com.ac03.covid.model.server.Country
import com.ac03.covid.ui.RankingAdapter.ViewHolder

class RankingAdapter(private val countries: List<Country>) :
    RecyclerView.Adapter<RankingAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_country, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.bind(country)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val countryName = view.findViewById<TextView>(R.id.country_item)
        private val confirmed = view.findViewById<TextView>(R.id.confirmed_item)
        private val death = view.findViewById<TextView>(R.id.death_item)
        private val recovered = view.findViewById<TextView>(R.id.recovered_item)

        fun bind(country: Country) {
            countryName.text = country.country
            confirmed.text = country.totalConfirmed.toString()
            death.text = country.totalDeaths.toString()
            recovered.text = country.totalRecovered.toString()
        }

    }
}




//class RankingAdapter(private val listener: (Country) -> Unit) :
//    ListAdapter<Country, ViewHolder> {
//
//
//
//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//
//    }
//}