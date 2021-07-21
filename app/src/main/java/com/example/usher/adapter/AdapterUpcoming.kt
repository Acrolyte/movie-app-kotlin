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
import com.example.usher.call.MoviesAPI.Companion.backdrop
import com.example.usher.models.get_upcoming.Result


class AdapterUpcoming(private val context: Context) :
    RecyclerView.Adapter<AdapterUpcoming.ViewHolder>() {

    private var itemList: List<Result> = emptyList()

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
        Glide.with(context).load(backdrop + entity.posterPath).into(holder.newsImage)
        holder.newsTitle.text = entity.title

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id", itemList[position].id)
            it.findNavController().navigate(R.id.action_home_to_details, bundle)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage: ImageView = itemView.findViewById(R.id.viewImage)
        val newsTitle: TextView = itemView.findViewById(R.id.viewTitle)
    }

    fun setData(element: List<Result>) {
        this.itemList = element
        notifyDataSetChanged()
    }
}