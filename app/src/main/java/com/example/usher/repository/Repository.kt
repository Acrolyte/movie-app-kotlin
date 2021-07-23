package com.example.usher.repository

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.usher.call.RetrofitInstance
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository constructor(val application: Application) {

    val carouselData = MutableLiveData<Upcoming>()
    val playingData = MutableLiveData<NowPlaying>()
    val popularData = MutableLiveData<Popular>()
    val topData = MutableLiveData<TopRated>()
    val upcomingData = MutableLiveData<Upcoming>()
    val trendingData = MutableLiveData<Trending>()
    val latestData = MutableLiveData<Latest>()
    val searchData = MutableLiveData<SearchResult>()
    val moviesCast = MutableLiveData<MovieCredits>()
    val similarMovies = MutableLiveData<SimilarMovies>()
    val movieDetails = MutableLiveData<MovieDetails>()
    val personDetails = MutableLiveData<PersonDetails>()
    val personImages = MutableLiveData<PersonImages>()

    fun getTrending() {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getTrending()

        callAPI.enqueue(object : Callback<Trending> {
            override fun onFailure(call: Call<Trending>, t: Throwable) {
                //Log.d("Repo", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Trending>, response: Response<Trending>) {
                Log.d("trending", "onResponse: $response")
                val play = response.body()
                if (play != null) {
                    val pop = play.results
                    trendingData.value = Trending(pop)
                }
            }
        })
    }

    /*fun getCarousel() {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getUpcoming()

        callAPI.enqueue(object : Callback<Upcoming> {
            override fun onFailure(call: Call<Upcoming>, t: Throwable) {
                Log.d("onFailure", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Upcoming>, response: Response<Upcoming>) {
                Log.d("nowPlaying", "onResponse: $response")
                val play = response.body()
                if (play != null) {
                    val pop = play.results
                    carouselData.value = Upcoming(pop)
                }
            }
        })
    }*/

    fun getLatest() {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getLatest()

        callAPI.enqueue(object : Callback<Latest> {
            override fun onFailure(call: Call<Latest>, t: Throwable) {
                //Log.d("Repo", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Latest>, response: Response<Latest>) {
                Log.d("detailsResponse", "onResponse: $response")
                val play = response.body()
                latestData.value = play
            }
        })
    }

    fun getPlaying() {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getNowPlaying()

        callAPI.enqueue(object : Callback<NowPlaying> {
            override fun onFailure(call: Call<NowPlaying>, t: Throwable) {
                Log.d("playRepo", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<NowPlaying>, response: Response<NowPlaying>) {
                Log.d("playNowResponse", "onResponse: $response")
                val play = response.body()
                Log.d("trending", "onResponse: $play")
                //playingData.value = play
                if (play != null) {
                    val pop = play.results
                    playingData.value = NowPlaying(pop)
                }
            }
        })
    }

    fun getPopular() {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getPopularMovies()

        callAPI.enqueue(object : Callback<Popular> {
            override fun onFailure(call: Call<Popular>, t: Throwable) {
                Log.d("onFailure", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Popular>, response: Response<Popular>) {
                val play = response.body()
                if (play != null) {
                    val pop = play.results
                    popularData.value = Popular(pop)
                }
            }
        })
    }

    fun getTop() {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getTopRated()

        callAPI.enqueue(object : Callback<TopRated> {
            override fun onFailure(call: Call<TopRated>, t: Throwable) {
                Log.d("onFailure", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<TopRated>, response: Response<TopRated>) {
                val play = response.body()
                if (play != null) {
                    val pop = play.results
                    topData.value = TopRated(pop)
                }
            }
        })
    }

    fun getUpcoming() {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getUpcoming()

        callAPI.enqueue(object : Callback<Upcoming> {
            override fun onFailure(call: Call<Upcoming>, t: Throwable) {
                Log.d("onFailure", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Upcoming>, response: Response<Upcoming>) {
                Log.d("nowPlaying", "onResponse: $response")
                val play = response.body()
                if (play != null) {
                    val pop = play.results
                    upcomingData.value = Upcoming(pop)
                }
            }
        })
    }


    fun multiSearch(query: String) {
        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.multiSearch(query)

        callAPI.enqueue(
            object : Callback<SearchResult> {
                override fun onResponse(
                    call: Call<SearchResult>,
                    response: Response<SearchResult>
                ) {
                    Log.d("searchedResult", "onResponse: $response")
                    val play = response.body()
                    if (play != null) {
                        val pop = play.results
                        searchData.value = SearchResult(pop)
                    }
                }

                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    Log.d("onFailure", "onFailure: ${t.message}")
                    Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
                }

            }
        )
    }

    fun getMoviesCast(id: Int) {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getMovieCredits(id)

        callAPI.enqueue(object : Callback<MovieCredits> {
            override fun onFailure(call: Call<MovieCredits>, t: Throwable) {
                //Log.d("Repo", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieCredits>, response: Response<MovieCredits>) {
                //Log.d("cast", "onResponse: $response")
                val play = response.body()
                if (play != null) {
                    val pop = play.cast
                    moviesCast.value = MovieCredits(pop)
                }
            }
        })
    }

    fun getSimilarMovies(id: Int) {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getSimilarMovies(id)

        callAPI.enqueue(object : Callback<SimilarMovies> {
            override fun onFailure(call: Call<SimilarMovies>, t: Throwable) {
                //Log.d("Repo", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<SimilarMovies>, response: Response<SimilarMovies>) {
                Log.d("trending", "onResponse: $response")
                val play = response.body()
                Log.d("trending", "onResponse: $play")
                if (play != null) {
                    val pop = play.results
                    similarMovies.value = SimilarMovies(pop)
                }
            }
        })
    }

    fun getMovieDetails(id: Int) {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getMovieDetails(id)

        callAPI.enqueue(object : Callback<MovieDetails> {
            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                //Log.d("Repo", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                Log.d("detailsResponse", "onResponse: $response")
                val play = response.body()
                movieDetails.value = play
            }
        })
    }

    fun getPersonDetails(id: Int) {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getPersonDetails(id)

        callAPI.enqueue(object : Callback<PersonDetails> {
            override fun onFailure(call: Call<PersonDetails>, t: Throwable) {
                //Log.d("Repo", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PersonDetails>, response: Response<PersonDetails>) {
                Log.d("detailsResponse", "onResponse: $response")
                val play = response.body()
                personDetails.value = play
            }
        })
    }

    fun getPersonImages(id: Int) {

        val retrofitService = RetrofitInstance.getClient(application)
        val callAPI = retrofitService.getPersonImages(id)

        callAPI.enqueue(object : Callback<PersonImages> {
            override fun onFailure(call: Call<PersonImages>, t: Throwable) {
                //Log.d("Repo", "onFailure: ${t.message}")
                Toast.makeText(application, "Error", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PersonImages>, response: Response<PersonImages>) {
                Log.d("trending", "onResponse: $response")
                val play = response.body()
                Log.d("trending", "onResponse: $play")
                if (play != null) {
                    val pop = play.profiles
                    personImages.value = PersonImages(pop)
                }
            }
        })
    }
}