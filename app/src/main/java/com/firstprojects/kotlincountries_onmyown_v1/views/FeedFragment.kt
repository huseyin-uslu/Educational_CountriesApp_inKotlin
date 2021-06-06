package com.firstprojects.kotlincountries_onmyown_v1.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.firstprojects.kotlincountries_onmyown_v1.adapter.AdapterForCountryModels
import com.firstprojects.kotlincountries_onmyown_v1.databinding.FragmentFeedBinding
import com.firstprojects.kotlincountries_onmyown_v1.models.CountriesMainModelItem
import com.firstprojects.kotlincountries_onmyown_v1.models.CountryMainArray
import com.firstprojects.kotlincountries_onmyown_v1.service.Countries_service
import com.firstprojects.kotlincountries_onmyown_v1.viewmodels.FeedViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FeedFragment : Fragment() {

    private var _binding : FragmentFeedBinding? = null
    private val binding get() = _binding!!
    //
    private lateinit var viewModel : FeedViewModel
    private var adapter = AdapterForCountryModels(CountryMainArray())



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentFeedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //temporary disposable objects for training the project
        viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refreshData()


        binding.recyclerviewInFeedfragment.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerviewInFeedfragment.adapter       = adapter

        binding.swiperefreshlayoutInFeedfragment.setOnRefreshListener {
            binding.errortextviewInFeedfragment.visibility = View.GONE
            binding.progressbarInFeedfragment.visibility   = View.VISIBLE
            binding.recyclerviewInFeedfragment.visibility  = View.GONE
            viewModel.refreshData()
            binding.swiperefreshlayoutInFeedfragment.isRefreshing = false
        }

        onObserveData()

    }

    private fun onObserveData() {
        viewModel.countrylistlive.observe(viewLifecycleOwner,{countries ->
            countries?.let {
                binding.recyclerviewInFeedfragment.visibility = View.VISIBLE
                adapter.countryListRefresh(it)


            /* val counter = CountriesMainModelItem("","", listOf(),2.0, listOf(), listOf(),"","",
                    listOf(),"","https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/dza.png",2.0, listOf(), listOf(),"training country","","",3213,"asia",
                    listOf(),"",
                    listOf(),
                    listOf(),null)
                val cshow = CountryMainArray()
                cshow.add(counter)

                adapter.countryListRefresh(cshow)
*/

            }
        })

        viewModel.errorlive.observe(viewLifecycleOwner,{error ->
            if(error == 1) {
                binding.errortextviewInFeedfragment.visibility = View.VISIBLE
                binding.recyclerviewInFeedfragment.visibility  = View.GONE
            }else {
                binding.errortextviewInFeedfragment.visibility = View.GONE
            }

        })

        viewModel.loadinglive.observe(viewLifecycleOwner,{loading ->
            if(loading == 1) {
                binding.progressbarInFeedfragment.visibility = View.VISIBLE
                binding.errortextviewInFeedfragment.visibility = View.GONE
            }else {
                binding.progressbarInFeedfragment.visibility = View.GONE
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        //composite adding don't forget
    }
}