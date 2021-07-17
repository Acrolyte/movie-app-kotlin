package com.example.usher.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.usher.R
import com.example.usher.adapter.*
import com.example.usher.databinding.HomeBinding
import com.example.usher.viewmodel.ViewModel


class Home : Fragment() {

    private lateinit var binding: HomeBinding
    private lateinit var viewModel: ViewModel
    private lateinit var adapterTrending: AdapterTrending
    private lateinit var adapterLatest: AdapterLatest
    private lateinit var adapterPlaying: AdapterPlaying
    private lateinit var adapterPopular: AdapterPopular
    private lateinit var adapterTop: AdapterTop
    private lateinit var adapterUpcoming: AdapterUpcoming

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trendingRecyclerView: RecyclerView = binding.trending
        trendingRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val playRecyclerView: RecyclerView = binding.nowPlaying
        playRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val popularRecyclerView: RecyclerView = binding.popular
        popularRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val topRecyclerView: RecyclerView = binding.topRated
        topRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val upcomingRecyclerView: RecyclerView = binding.upcoming
        upcomingRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getTrending()
        viewModel.getPlaying()
        viewModel.getPopular()
        viewModel.getTop()
        viewModel.getUpcoming()

        adapterTrending = AdapterTrending(requireContext())
        trendingRecyclerView.adapter = adapterTrending
        adapterPlaying = AdapterPlaying(requireContext())
        playRecyclerView.adapter = adapterPlaying
        adapterPopular = AdapterPopular(requireContext())
        popularRecyclerView.adapter = adapterPopular
        adapterTop = AdapterTop(requireContext())
        topRecyclerView.adapter = adapterTop
        adapterUpcoming = AdapterUpcoming(requireContext())
        upcomingRecyclerView.adapter = adapterUpcoming

        viewModel.trendingData.observe(viewLifecycleOwner, {
            adapterTrending.setData(it.results)
        })

        viewModel.playingData.observe(viewLifecycleOwner, {
            adapterPlaying.setData(it.results)
        })

        viewModel.popularData.observe(viewLifecycleOwner, {
            adapterPopular.setData(it.results)
        })

        viewModel.topData.observe(viewLifecycleOwner, {
            adapterTop.setData(it.results)
        })

        viewModel.upcomingData.observe(viewLifecycleOwner, {
            adapterUpcoming.setData(it.results)
        })

        binding.floatingActionButtonSearch.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_home_to_search)
        }
    }

    /*private fun displayCarousel(){
        var imageSlider: ImageSlider = binding.carousel
        var slideModels = ArrayList<SlideModel>()

        slideModels.add(SlideModel(image1,"image 1"))
        slideModels.add(SlideModel(image2,"image 1"))
        slideModels.add(SlideModel(image3,"image 1"))
        slideModels.add(SlideModel(image4,"image 1"))
        imageSlider.setImageList(slideModels,true)
    }*/

}