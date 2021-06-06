package com.firstprojects.kotlincountries_onmyown_v1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.firstprojects.kotlincountries_onmyown_v1.databinding.ItemCountryBinding
import com.firstprojects.kotlincountries_onmyown_v1.models.CountriesMainModelItem
import com.firstprojects.kotlincountries_onmyown_v1.models.CountryMainArray
import com.firstprojects.kotlincountries_onmyown_v1.models.CountrySeriliazableModel
import com.firstprojects.kotlincountries_onmyown_v1.views.FeedFragmentDirections


class AdapterForCountryModels(private var countryArray : CountryMainArray) :
    RecyclerView.Adapter<AdapterForCountryModels.CountryHolder>() , ItemOnClickListener {


    private var _binding : ItemCountryBinding? = null
    private val binding get() = _binding!!

    class CountryHolder(var itemView : ItemCountryBinding) : RecyclerView.ViewHolder(itemView.root)
    {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binding = ItemCountryBinding.inflate(inflater,parent,false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        binding.country = countryArray[position]
        holder.itemView.setOnClickListener {
            onItemClickListener(it,countryArray[position])
        }
    }

    override fun getItemCount(): Int {
       return countryArray.count()
    }


    fun countryListRefresh(newCountryList : CountryMainArray) {
        countryArray.clear()
        countryArray.addAll(newCountryList)
        notifyDataSetChanged()

    }

    override fun onItemClickListener(view: View,country : CountriesMainModelItem) {
        val countrymodel = CountrySeriliazableModel(
            country.name,
            "Country Code = " + country.alpha2Code,
            "Capital = " + country.capital,
            "Region = " + country.region,
            "Population = " + country.population.toString(),
            "Currency = " + country.currencies?.get(0)?.name,
            "Native Name = " + country.nativeName, country.flag
        )

        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countrymodel)
        Navigation.findNavController(view).navigate(action)
    }


}