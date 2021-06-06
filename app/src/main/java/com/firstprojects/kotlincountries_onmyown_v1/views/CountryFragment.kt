package com.firstprojects.kotlincountries_onmyown_v1.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.firstprojects.kotlincountries_onmyown_v1.databinding.FragmentCountryBinding
import com.firstprojects.kotlincountries_onmyown_v1.viewmodels.CountryViewModel

class CountryFragment : Fragment(){

    private var _binding : FragmentCountryBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : CountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCountryBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        getDataWithModelView()


    }

    private fun getDataWithModelView() {
        arguments?.let {
            val country = CountryFragmentArgs.fromBundle(it).countrymodel
            viewModel.getData(country)
            binding.countrymodel = country
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}