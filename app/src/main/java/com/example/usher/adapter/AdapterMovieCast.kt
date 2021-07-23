package com.example.usher.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.usher.R
import com.example.usher.call.MoviesAPI
import com.example.usher.models.get_movie_credits.Cast

class AdapterMovieCast(private val context: Context) :
    RecyclerView.Adapter<AdapterMovieCast.ViewHolder>() {

    private var itemList: List<Cast> = emptyList()
    private var limit: Int = 20

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view,
                parent,
                false
            )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = itemList[position]
        Glide.with(context).load(MoviesAPI.backdrop + entity.profilePath).into(holder.newsImage)
        holder.newsTitle.text = entity.originalName

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id", itemList[position].id)
            it.findNavController().navigate(R.id.action_details_to_castDetails, bundle)
        }
    }

    override fun getItemCount(): Int {
        return if (itemList.size > limit)
            limit
        else
            itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage: ImageView = itemView.findViewById(R.id.viewImage)
        val newsTitle: TextView = itemView.findViewById(R.id.viewTitle)

    }

    fun setData(element: List<Cast>) {
        this.itemList = element
        notifyDataSetChanged()
    }
}