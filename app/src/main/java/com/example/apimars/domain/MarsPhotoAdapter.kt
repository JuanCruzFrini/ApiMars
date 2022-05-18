package com.example.apimars.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apimars.R
import com.example.apimars.network.MarsPhoto

class MarsPhotoAdapter(private val listPhotos : List<MarsPhoto>) : RecyclerView.Adapter<MarsPhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotoAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.mars_photo_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MarsPhotoAdapter.ViewHolder, position: Int) {
        holder.id.text = listPhotos[position].id

        Glide.with(holder.itemView.context).load(listPhotos[position].img_src).error(R.drawable.ic_launcher_foreground).into(holder.image)
        //use cleatTextTraffic atributte in Manifest to correct function
    }

    override fun getItemCount(): Int = listPhotos.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val id = itemView.findViewById<TextView>(R.id.idMarsPhoto)
        val image = itemView.findViewById<ImageView>(R.id.imageMarsPhoto)

        init { setListeners() }

        private fun setListeners() {
            itemView.setOnClickListener { Toast.makeText(itemView.context, "Photo Id: ${listPhotos[adapterPosition].id}", Toast.LENGTH_SHORT).show() }
        }
    }
}