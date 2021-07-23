package com.example.usher.view.fragment

import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usher.adapter.AdapterCastImages
import com.example.usher.call.MoviesAPI
import com.example.usher.databinding.DetailsCastBinding
import com.example.usher.viewmodel.ViewModel

class CastDetails : Fragment() {

    private lateinit var adapterCastImages: AdapterCastImages
    private lateinit var viewModel: ViewModel
    private lateinit var binding: DetailsCastBinding
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsCastBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = requireArguments().getInt("id")
        Log.d("CastId", "onViewCreated: $id")

        val castImagesRecyclerView: RecyclerView = binding.castImages
        castImagesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getPersonDetails(id)
        viewModel.getPersonImages(id)

        adapterCastImages = AdapterCastImages(requireContext())
        castImagesRecyclerView.adapter = adapterCastImages

        viewModel.personData.observe(viewLifecycleOwner, {
            progressBar = binding.castProgressBar
            progressBar.visibility = View.GONE
            Glide.with(requireContext()).load(MoviesAPI.backdrop + it.profilePath)
                .into(binding.castImage)
            binding.castName.text = it.name
            binding.castOverview.text = it.biography

            (activity as AppCompatActivity?)!!.supportActionBar!!.title = it.name
        })

        viewModel.personImagesData.observe(viewLifecycleOwner, {
            Log.d("personImages", "onViewCreated: $viewModel")
            adapterCastImages.setData(it.profiles)
        })

        binding.castOverviewCard.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.castOverviewCard);
            binding.castOverview.visibility = View.VISIBLE
        }
    }
}