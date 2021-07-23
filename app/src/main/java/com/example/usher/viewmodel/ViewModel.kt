package com.example.usher.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.usher.models.getTrending.Trending
import com.example.usher.models.get_latest_movie.Latest
import com.example.usher.models.get_movie_credits.MovieCredits
import com.example.usher.models.get_movie_details.MovieDetails
import com.example.usher.models.get_now_playing.NowPlaying
import com.example.usher.models.get_person_details.PersonDetails
import com.example.usher.models.get_person_images.PersonImages
import com.example.usher.models.get_popular_movie.Popular
import com.example.usher.models.get_similar_movies.SimilarMovies
import com.example.usher.models.get_top_rated_movies.TopRated
import com.example.usher.models.get_upcoming.Upcoming
import com.example.usher.models.multi_search.SearchResult
import com.example.usher.repository.Repository

class ViewModel(application: Application) : AndroidViewModel(application) {

    val latestData: LiveData<Latest>
    val carouselData: LiveData<Upcoming>
    val playingData: LiveData<NowPlaying>
    val popularData: LiveData<Popular>
    val topData: LiveData<TopRated>
    val upcomingData: LiveData<Upcoming>
    val trendingData: LiveData<Trending>
    val castData: LiveData<MovieCredits>
    val similarData: LiveData<SimilarMovies>
    val movieData: LiveData<MovieDetails>
    val personData: LiveData<PersonDetails>
    val personImagesData: LiveData<PersonImages>
    var searchData: LiveData<SearchResult>

    private val repository = Repository(application)

    init {
        this.latestData = repository.latestData
        this.playingData = repository.playingData
        this.popularData = repository.popularData
        this.topData = repository.topData
        this.upcomingData = repository.upcomingData
        this.trendingData = repository.trendingData
        this.castData = repository.moviesCast
        this.similarData = repository.similarMovies
        this.movieData = repository.movieDetails
        this.personData = repository.personDetails
        this.personImagesData = repository.personImages
        this.searchData = repository.searchData
        this.carouselData = repository.carouselData
    }

    fun getLatest() {
        repository.getLatest()
    }

    fun getCarousel() {
        repository
    }

    fun getTrending() {
        repository.getTrending()
    }

    fun getPlaying() {
        repository.getPlaying()
    }

    fun getPopular() {
        repository.getPopular()
    }

    fun getTop() {
        repository.getTop()
    }

    fun getUpcoming() {
        repository.getUpcoming()
    }

    fun searchData(query: String) {
        repository.multiSearch(query)
    }

    fun getMoviesCast(id: Int) {
        repository.getMoviesCast(id)
    }

    fun getSimilarMovies(id: Int) {
        repository.getSimilarMovies(id)
    }

    fun getMovieDetails(id: Int) {
        repository.getMovieDetails(id)
    }

    fun getPersonDetails(id: Int) {
        repository.getPersonDetails(id)
    }

    fun getPersonImages(id: Int) {
        repository.getPersonImages(id)
    }
}