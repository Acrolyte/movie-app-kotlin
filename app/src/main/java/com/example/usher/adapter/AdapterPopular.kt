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
import com.example.usher.models.get_popular_movie.Result
import com.example.usher.util.InternetConnectivity
import com.google.android.material.snackbar.Snackbar


class AdapterPopular(private val context: Context) :
    RecyclerView.Adapter<AdapterPopular.ViewHolder>() {

    private var itemList: List<Result> = emptyList()

    //    var home = Home()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_view,
                parent,
                false
            )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entity = itemList[position]
        val url = backdrop + entity.posterPath
        Glide.with(context).load(url).into(holder.newsImage)
        holder.newsTitle.text = entity.title
//        addDataDataBase(entity.id,url,entity.posterPath,entity.title)

        holder.itemView.setOnClickListener {
            if (InternetConnectivity.isNetworkAvailable(context) == true) {
                val bundle = Bundle()
                bundle.putInt("id", itemList[position].id)
                it.findNavController().navigate(R.id.action_home_to_details, bundle)
            } else {
                Snackbar.make(it, "Please check your Internet connection!!", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val newsImage: ImageView = itemView.findViewById(R.id.viewImage)
        val newsTitle: TextView = itemView.findViewById(R.id.viewTitle)

    }

    //    fun addDataDataBase(id : Int, url: String, posterpath : String,title: String){
//        val scope = CoroutineScope(CoroutineName("AddDataScope"))
//        scope.launch {
//            val result : ResultData = ResultData(id,posterpath,title,makeBmap(url))
//        }
//        scope.cancel()
//        Log.d("dekh","cancellled")
//    }
//    private suspend fun makeBmap(url: String) : Bitmap {
//        val loading = ImageLoader(context)
//        val request = ImageRequest.Builder(context)
//            .data(url)
//            .build()
//        var result  = (loading.execute(request) as SuccessResult).drawable
//        return (result as BitmapDrawable).bitmap
//    }
    fun setData(element: List<Result>) {
        this.itemList = element
        notifyDataSetChanged()
    }
}