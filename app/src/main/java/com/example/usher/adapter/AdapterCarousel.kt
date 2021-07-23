package com.example.usher.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usher.R
import com.example.usher.call.MoviesAPI
import com.example.usher.models.get_upcoming.Result
import com.example.usher.util.InternetConnectivity
import com.google.android.material.snackbar.Snackbar

class AdapterCarousel(val images: List<Result>, private val context: Context) :
    RecyclerView.Adapter<AdapterCarousel.ViewHolder>() {

    val limit: Int = 7

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.carousel,
                parent,
                false
            )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = images[position]
        Glide.with(context).load(MoviesAPI.backdrop + entity.backdropPath).into(holder.newsImage)

        holder.itemView.setOnClickListener {
            if (InternetConnectivity.isNetworkAvailable(context) == true) {
                val bundle = Bundle()
                bundle.putInt("id", images[position].id)
                it.findNavController().navigate(R.id.action_home_to_details, bundle)
            } else {
                Snackbar.make(it, "Please check your Internet connection!!", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun getItemCount(): Int {
        return if (images.size > limit)
            limit
        else
            images.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage: ImageView = itemView.findViewById(R.id.carouselImage)
    }
}