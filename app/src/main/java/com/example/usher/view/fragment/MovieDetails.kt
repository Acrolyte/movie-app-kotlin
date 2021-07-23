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
import com.example.usher.adapter.AdapterMovieCast
import com.example.usher.adapter.AdapterMovieSimilar
import com.example.usher.call.MoviesAPI
import com.example.usher.databinding.DetailsBinding
import com.example.usher.viewmodel.ViewModel
import com.google.android.material.snackbar.Snackbar

class MovieDetails : Fragment() {

    private lateinit var adapterMovieCast: AdapterMovieCast
    private lateinit var adapterSimilarMovies: AdapterMovieSimilar
    private lateinit var viewModel: ViewModel
    private lateinit var binding: DetailsBinding
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id: Int = requireArguments().getInt("id")
        Log.d("MovieId", "onViewCreated: $id")

        val castRecyclerView: RecyclerView = binding.cast
        castRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val similarRecyclerView: RecyclerView = binding.similarMovies
        similarRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.getMoviesCast(id)
        viewModel.getSimilarMovies(id)
        viewModel.getMovieDetails(id)

        adapterMovieCast = AdapterMovieCast(requireContext())
        castRecyclerView.adapter = adapterMovieCast
        adapterSimilarMovies = AdapterMovieSimilar(requireContext())
        similarRecyclerView.adapter = adapterSimilarMovies


        viewModel.castData.observe(viewLifecycleOwner, {
            adapterMovieCast.setData(it.cast)
        })

        viewModel.similarData.observe(viewLifecycleOwner, {
            adapterSimilarMovies.setData(it.results)
        })

        viewModel.movieData.observe(viewLifecycleOwner, {
            progressBar = binding.progressBar
            progressBar.visibility = View.GONE
            binding.contentTitle.text = it.title
            binding.contentOverview.text = it.overview
            Glide.with(requireContext()).load(MoviesAPI.backdrop + it.backdropPath)
                .into(binding.contentImage)
            binding.voteAverage.text = it.voteAverage.toString()
            binding.voteCount.text = it.voteCount.toString()

            (activity as AppCompatActivity?)!!.supportActionBar!!.title = it.title
        })

        binding.voteCard.setOnClickListener {
            Snackbar.make(it, "Votes", Snackbar.LENGTH_SHORT).show()
        }

        binding.ratingCard.setOnClickListener {
            Snackbar.make(it, "Rating", Snackbar.LENGTH_SHORT).show()
        }

        binding.contentOverviewCard.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.contentOverviewCard)
            binding.contentOverview.visibility = View.VISIBLE
        }
    }

}